package com.erebelo.springmysqldemo.mapper;

import static org.mapstruct.ReportingPolicy.WARN;

import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorRelationshipResponse;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerLazyResponse;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface AdvisorMapper {

    default List<AdvisorResponse> entityListToResponseList(List<AdvisorEntity> entityList) {
        return entityList.stream().map(this::lazyEntityToResponse).toList();
    }

    // Lazy fetch strategy that saves additional queries by ignoring/not mapping
    // properties
    @Mapping(target = "relationships", ignore = true)
    AdvisorResponse lazyEntityToResponse(AdvisorEntity entity);

    @Mapping(target = "relationships", source = "advisorBrokers", qualifiedByName = "mapSetAdvisorBrokersToListRelationships")
    AdvisorResponse entityToResponse(AdvisorEntity entity);

    AdvisorRelationshipResponse mapBrokerAdvisorEntityToAdvisorRelationshipResponse(BrokerAdvisorEntity entity);

    BrokerLazyResponse mapBrokerEntityToBrokerLazyResponse(BrokerEntity entity);

    AdvisorEntity requestToEntity(AdvisorRequest request);

    @Named("mapSetAdvisorBrokersToListRelationships")
    default List<AdvisorRelationshipResponse> mapSetAdvisorBrokersToListRelationships(
            Set<BrokerAdvisorEntity> advisorBrokers) {
        return advisorBrokers.stream().map(this::mapBrokerAdvisorEntityToAdvisorRelationshipResponse).toList();
    }
}
