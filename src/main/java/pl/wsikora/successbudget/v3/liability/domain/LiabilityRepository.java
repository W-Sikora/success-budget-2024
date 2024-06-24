package pl.wsikora.successbudget.v3.liability.domain;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface LiabilityRepository {

	Liability getById(Long id);

	Liability getByIdAndUserId(Long id, UserId userId);

	void save(Liability liability);
}
