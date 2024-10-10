package com.erebelo.springmysqldemo.mapper;

import static org.mapstruct.ReportingPolicy.WARN;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.request.RelationshipRequest;
import com.erebelo.springmysqldemo.domain.response.address.AddressResponse;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorLazyResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerRelationshipResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerResponse;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeLazyResponse;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerMapper {

    default List<BrokerResponse> entityListToResponseList(List<BrokerEntity> entityList) {
        return entityList.stream().map(this::lazyEntityToResponse).toList();
    }

    // Lazy fetch strategy that saves additional queries by ignoring/not mapping
    // properties
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "associatedBrokers", ignore = true)
    BrokerResponse lazyEntityToResponse(BrokerEntity entity);

    @Mapping(target = "relationships", source = "brokerAdvisors", qualifiedByName = "mapSetBrokerAdvisorsToListRelationships")
    BrokerResponse entityToResponse(BrokerEntity entity);

    AddressResponse mapAddressEntityToAddressResponse(AddressEntity entity);

    BrokerTypeLazyResponse mapBrokerTypeEntityToBrokerTypeLazyResponse(BrokerTypeEntity entity);

    BrokerRelationshipResponse mapBrokerAdvisorEntityToBrokerRelationshipResponse(BrokerAdvisorEntity entity);

    AdvisorLazyResponse mapAdvisorEntityToAdvisorLazyResponse(AdvisorEntity entity);

    BrokerEntity requestToEntity(BrokerRequest request);

    BrokerAdvisorEntity mapRelationshipRequestToBrokerAdvisorEntity(RelationshipRequest request);

    @Named("mapSetBrokerAdvisorsToListRelationships")
    default List<BrokerRelationshipResponse> mapSetBrokerAdvisorsToListRelationships(
            Set<BrokerAdvisorEntity> brokerAdvisors) {
        return brokerAdvisors.stream().map(this::mapBrokerAdvisorEntityToBrokerRelationshipResponse).toList();
    }
}
