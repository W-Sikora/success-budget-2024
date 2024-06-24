package pl.wsikora.successbudget.v3.user.application.command;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.user.domain.Password;
import pl.wsikora.successbudget.v3.user.domain.User;
import pl.wsikora.successbudget.v3.user.domain.UserRegisteredEvent;
import pl.wsikora.successbudget.v3.user.domain.UserRepository;


@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ApplicationEventPublisher eventPublisher;

	UserService(UserRepository userRepository,
				PasswordEncoder passwordEncoder,
				ApplicationEventPublisher eventPublisher) {

		Assert.notNull(userRepository, "userRepository must not be null");
		Assert.notNull(passwordEncoder, "passwordEncoder must not be null");
		Assert.notNull(eventPublisher, "eventPublisher must not be null");

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.eventPublisher = eventPublisher;
	}

	public void save(RegistrationAttributes attributes) {

		Name username = Name.of(attributes.getUsername());
		String encode = passwordEncoder.encode(attributes.getPassword());
		Password encodedPassword = Password.encoded(encode);
		Currency primaryCurrency = Currency.of(attributes.getPrimaryCurrency());

		User user = new User(username, encodedPassword, primaryCurrency);

		User savedUser = userRepository.save(user);

		UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(this,
			savedUser.getUserId(), savedUser.getPrimaryCurrency());

		eventPublisher.publishEvent(userRegisteredEvent);
	}
}
