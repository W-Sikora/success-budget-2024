package pl.wsikora.successbudget.v3.workflow.domain;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Optional;


public interface WorkflowRepository {

	Optional<Workflow> findByUserId(UserId userId);

	void save(Workflow workflow);
}
