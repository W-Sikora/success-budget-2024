package pl.wsikora.successbudget.v3.category.application.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;
import java.util.Optional;


public interface CategoryQuery {

	Optional<CategoryDto> findById(CategoryId id);

	CategoryDto getById(Long id, UserId userId);

	Page<CategoryDto> findAll(Pageable pageable, CategoryFilterAttributes filter, UserId userId);

	List<CategoryDto> findAllByPriorityAndUserId(Priority priority, UserId userId);

	List<CategoryId> findAllIdsByPriorityAndUserId(Priority priority, UserId userId);

	List<CategoryDto> findAllByUserIdSortedByRanking(UserId userId);

	List<CategoryDto> findAllByUserIdSortedById(UserId userId);

	List<CategoryDto> findAllByUserIdSortedByName(UserId userId);

	boolean existsByNameAndUserId(Name name, UserId userId);

	boolean hasAllRequiredPriorities(UserId userId);

	boolean isUserLimitExceeded(UserId userId);
}
