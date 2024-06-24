package pl.wsikora.successbudget.v3.category.application.query;

import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.category.domain.Priority;


public record CategoryDto(Long id, Name name, Priority priority) {}
