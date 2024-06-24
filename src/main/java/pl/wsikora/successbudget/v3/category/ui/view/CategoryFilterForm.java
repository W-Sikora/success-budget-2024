package pl.wsikora.successbudget.v3.category.ui.view;

import pl.wsikora.successbudget.v3.category.application.query.CategoryFilterAttributes;


public class CategoryFilterForm implements CategoryFilterAttributes {

	private String name;
	private Integer priority;

	public CategoryFilterForm() {}

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
