package pl.wsikora.successbudget.v3.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;
import java.util.Set;


@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

	List<Category> findAllByPriorityAndUserId(Priority priority, UserId userId);

	List<Category> findAllByUserId(UserId userId);

	boolean existsByNameAndUserId(Name name, UserId userId);

	@Query("select distinct c.priority from Category c where c.userId = :userId")
	Set<Priority> getPrioritiesByUserId(@Param("userId") UserId userId);

	int countAllByUserId(UserId userId);
}
