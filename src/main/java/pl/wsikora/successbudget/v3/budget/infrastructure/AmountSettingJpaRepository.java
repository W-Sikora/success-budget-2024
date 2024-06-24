package pl.wsikora.successbudget.v3.budget.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.budget.domain.AmountSetting;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Optional;


@Repository
interface AmountSettingJpaRepository extends JpaRepository<AmountSetting, Long> {

	Optional<AmountSetting> findByUserId(UserId userId);
}
