package pl.wsikora.successbudget.v3.liability.application.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.List;


public interface LiabilityQuery {

	LiabilityDto getById(Long id, UserId userId);

	Page<LiabilityDto> findAll(Pageable pageable, LiabilityFilterAttributes filter, UserId userId);

	LiabilityCollectiveDto getLiabilityCollectiveDto(UserId userId);

	boolean existsByNameAndUserId(Name name, UserId userId);

	List<LiabilityDto> findAllByUserId(UserId userId);

	boolean isUserLimitExceeded(UserId userId);
}
