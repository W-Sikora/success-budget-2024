package pl.wsikora.successbudget.v3.workflow.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.workflow.domain.Workflow;

import java.util.Optional;


@Repository
interface JpaWorkflowRepository extends JpaRepository<Workflow, Long> {

	Optional<Workflow> findByUserId(UserId userId);
}
