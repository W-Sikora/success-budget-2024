package pl.wsikora.successbudget.v3.category.application.command;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.CategoryRepository;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.util.StringUtils.hasText;


@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	CategoryService(CategoryRepository categoryRepository) {

		Assert.notNull(categoryRepository, "categoryRepository must not be null");

		this.categoryRepository = categoryRepository;
	}

	public void save(CategoryAttributes categoryAttributes, UserId userId) {

		Assert.notNull(categoryAttributes, "objectiveAttributes must not be null");
		Assert.notNull(userId, "userId must not be null");

		Category category;

		if (categoryAttributes.hasId()) {

			category = update(categoryAttributes, userId);
		}
		else {

			category = create(categoryAttributes, userId);
		}

		categoryRepository.save(category);
	}

	private Category update(CategoryAttributes categoryAttributes, UserId userId) {

		Category category = categoryRepository.getByIdAndUserId(categoryAttributes.getId(), userId);

		category.updateName(Name.of(categoryAttributes.getName()));
		category.updatePriority(Priority.of(categoryAttributes.getPriority()));

		return category;
	}

	private Category create(CategoryAttributes categoryAttributes, UserId userId) {

		return new Category(
				userId,
				Name.of(categoryAttributes.getName()),
				Priority.of(categoryAttributes.getPriority())
		);
	}

	public void saveRanking(String orderedIds, UserId userId) {

		Assert.notNull(orderedIds, "orderedIds must not be null");
		Assert.notNull(userId, "userId must not be null");

		AtomicInteger counter = new AtomicInteger(1);

		convertStringToLongList(orderedIds).forEach(id -> {

			Category category = categoryRepository.getByIdAndUserId(id, userId);

			category.updateRanking(counter.getAndIncrement());

			categoryRepository.save(category);
		});
	}

	private List<Long> convertStringToLongList(String orderedIds) {

		if (!hasText(orderedIds)) {

			return List.of();
		}

		return Arrays.stream(orderedIds.split(","))
				.map(String::trim)
				.map(Long::parseLong)
				.toList();
	}
}
