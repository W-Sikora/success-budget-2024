package pl.wsikora.successbudget.v3.user.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.user.domain.User;
import pl.wsikora.successbudget.v3.user.domain.UserRepository;


@Service
class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	UserRepositoryImpl(UserJpaRepository userJpaRepository) {

		Assert.notNull(userJpaRepository, "userJpaRepository must not be null");

		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public User save(User user) {

		Assert.notNull(user, "user must not be null");

		return userJpaRepository.save(user);
	}
}
