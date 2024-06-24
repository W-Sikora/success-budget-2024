package pl.wsikora.successbudget.v3.liability.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.domain.Liability;

import java.util.List;


@Repository
public interface LiabilityJpaRepository extends JpaRepository<Liability, Long>, JpaSpecificationExecutor<Liability> {

	List<Liability> findAllByUserId(UserId userId);

	boolean existsByNameAndUserId(Name name, UserId userId);

	int countAllByUserId(UserId userId);
}
