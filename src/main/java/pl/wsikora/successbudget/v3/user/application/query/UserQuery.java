package pl.wsikora.successbudget.v3.user.application.query;

import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.user.domain.User;

import java.util.Optional;


public interface UserQuery {

	boolean existsByUsername(Name username);

	Optional<User> findByUsername(Name username);
}
