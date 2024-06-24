package pl.wsikora.successbudget.v3.category.application.command;

import pl.wsikora.successbudget.v3.common.abstraction.application.command.Attributable;


public interface CategoryAttributes extends Attributable {

	String getName();

	Integer getPriority();
}
