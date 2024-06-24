package pl.wsikora.successbudget.v3.workflow.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.workflow.domain.Workflow;
import pl.wsikora.successbudget.v3.workflow.domain.WorkflowRepository;

import java.util.Optional;


@Service
class WorkflowRepositoryImpl implements WorkflowRepository {

	private final JpaWorkflowRepository jpaWorkflowRepository;

	WorkflowRepositoryImpl(JpaWorkflowRepository jpaWorkflowRepository) {

		Assert.notNull(jpaWorkflowRepository, "jpaWorkflowRepository must not be null");

		this.jpaWorkflowRepository = jpaWorkflowRepository;
	}

	@Override
	public Optional<Workflow> findByUserId(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return jpaWorkflowRepository.findByUserId(userId);
	}

	@Override
	public void save(Workflow workflow) {

		Assert.notNull(workflow, "workflow must not be null");

		jpaWorkflowRepository.save(workflow);
	}
}
