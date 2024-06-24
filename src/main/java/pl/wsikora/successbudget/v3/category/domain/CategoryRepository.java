package pl.wsikora.successbudget.v3.category.domain;


import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface CategoryRepository {

	Category getByIdAndUserId(Long id, UserId userId);

	Category getById(Long id);

	void save(Category objective);
}
