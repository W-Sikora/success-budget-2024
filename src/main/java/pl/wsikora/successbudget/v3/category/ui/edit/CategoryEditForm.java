package pl.wsikora.successbudget.v3.category.ui.edit;

import pl.wsikora.successbudget.v3.category.application.command.CategoryAttributes;


public class CategoryEditForm implements CategoryAttributes {

	static final String F_NAME = "name";
	static final String F_PRIORITY = "priority";

	private Long id;
	private Long userId;
	private String name;
	private Integer priority;

	public CategoryEditForm(Long userId) {

		this.userId = userId;
	}

	@Override
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	@Override
	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public Integer getPriority() {

		return priority;
	}

	public void setPriority(Integer priority) {

		this.priority = priority;
	}
}
