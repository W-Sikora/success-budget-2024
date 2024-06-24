package pl.wsikora.successbudget.v3.liability.application.query;

import pl.wsikora.successbudget.v3.common.type.money.Money;


public record LiabilityCollectiveDto(Money total, Money totalRepaid, Money remainingToBeRepaid) {}
