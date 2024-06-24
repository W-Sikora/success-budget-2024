package pl.wsikora.successbudget.v3.common.abstraction.application.command;

public interface Attributable {

	Long getId();

	Long getUserId();

	default boolean hasId() {

		return getId() != null;
	}

	default boolean hasNoId() {

		return getId() == null;
	}
}
