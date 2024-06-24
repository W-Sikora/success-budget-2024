package pl.wsikora.successbudget.v3.liability.ui.view;

import pl.wsikora.successbudget.v3.liability.application.query.LiabilityFilterAttributes;


public class LiabilityFilterForm implements LiabilityFilterAttributes {

	private String name;

	public LiabilityFilterForm() {}

	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}
}
