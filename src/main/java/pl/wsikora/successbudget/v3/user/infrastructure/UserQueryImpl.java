package pl.wsikora.successbudget.v3.user.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.user.application.query.UserQuery;
import pl.wsikora.successbudget.v3.user.domain.User;

import java.util.Optional;


@Service
class UserQueryImpl implements UserQuery {

	private final UserJpaRepository userJpaRepository;

	UserQueryImpl(UserJpaRepository userJpaRepository) {

		Assert.notNull(userJpaRepository, "userJpaRepository must not be null");

		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public boolean existsByUsername(Name username) {

		Assert.notNull(username, "username must not be null");

		return userJpaRepository.existsByUsername(username);
	}

	@Override
	public Optional<User> findByUsername(Name username) {

		Assert.notNull(username, "username must not be null");

		return userJpaRepository.findByUsername(username);
	}
}
