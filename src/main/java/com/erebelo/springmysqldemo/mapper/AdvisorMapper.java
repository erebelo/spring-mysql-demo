package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorBrokerResponse;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorRelationshipResponse;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface AdvisorMapper {

    default List<AdvisorResponse> entityListToResponseList(List<AdvisorEntity> entityList) {
        return entityList.stream().map(this::lazyEntityToResponse).toList();
    }

    /*
     Specify the properties (relationships) to be ignored and not mapped,
     leveraging the lazy fetch strategy and saving on additional queries
     */
    @Mapping(target = "relationships", ignore = true)
    AdvisorResponse lazyEntityToResponse(AdvisorEntity entity);

    @Mapping(source = "advisorBrokers", target = "relationships", qualifiedByName = "mapAdvisorBrokersToRelationships")
    AdvisorResponse entityToResponse(AdvisorEntity entity);

    AdvisorRelationshipResponse mapBrokerAdvisorEntityToAdvisorRelationshipResponse(BrokerAdvisorEntity entity);

    AdvisorBrokerResponse mapBrokerEntityToAdvisorBrokerResponse(BrokerEntity entity);

    AdvisorEntity requestToEntity(AdvisorRequest request);

    @Named("mapAdvisorBrokersToRelationships")
    default List<AdvisorRelationshipResponse> mapAdvisorBrokersToRelationships(Set<BrokerAdvisorEntity> advisorBrokers) {
        return advisorBrokers.stream().map(this::mapBrokerAdvisorEntityToAdvisorRelationshipResponse).toList();
    }
}
