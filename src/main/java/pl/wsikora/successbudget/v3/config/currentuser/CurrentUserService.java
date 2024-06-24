package pl.wsikora.successbudget.v3.config.currentuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.user.application.query.UserQuery;
import pl.wsikora.successbudget.v3.user.domain.User;


@Service
class CurrentUserService implements UserDetailsService {

	private final UserQuery userQuery;

	CurrentUserService(UserQuery userQuery) {

		Assert.notNull(userQuery, "userQuery must not be null");

		this.userQuery = userQuery;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Assert.hasText(username, "username must not be null");

		return userQuery.findByUsername(Name.of(username))
				.map(this::toCurrentUser)
				.orElseThrow(() -> new UsernameNotFoundException(""));
	}

	CurrentUser toCurrentUser(User user) {

		Assert.notNull(user, "user must not be null");

		return new CurrentUser(
				user.getUsername().getName(),
				user.getPassword().getValue(),
				user.getUserId(),
				user.getPrimaryCurrency()
		);
	}
}
