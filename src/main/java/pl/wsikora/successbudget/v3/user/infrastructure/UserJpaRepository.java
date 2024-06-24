package pl.wsikora.successbudget.v3.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.user.domain.User;

import java.util.Optional;


@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(Name username);

	boolean existsByUsername(Name username);
}
