package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.request.RelationshipRequest;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerAddressResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerAdvisorResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerRelationshipResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerResponse;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerMapper {

    default List<BrokerResponse> entityListToResponseList(List<BrokerEntity> entityList) {
        return entityList.stream()
                .map(this::lazyEntityToResponse)
                .toList();
    }

    /*
     Specify the properties (relationships and associatedBrokers) to be ignored and not mapped,
     leveraging the lazy fetch strategy and saving on additional queries
     */
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "associatedBrokers", ignore = true)
    BrokerResponse lazyEntityToResponse(BrokerEntity entity);

    @Mapping(source = "brokerAdvisors", target = "relationships", qualifiedByName = "mapBrokerAdvisorsToRelationships")
    BrokerResponse entityToResponse(BrokerEntity entity);

    BrokerAddressResponse mapAddressEntityToBrokerAddressResponse(AddressEntity entity);

    BrokerTypeResponse mapBrokerTypeEntityToBrokerTypeResponse(BrokerTypeEntity entity);

    BrokerRelationshipResponse mapBrokerAdvisorEntityToBrokerRelationshipResponse(BrokerAdvisorEntity entity);

    BrokerAdvisorResponse mapAdvisorEntityToBrokerAdvisorResponse(AdvisorEntity entity);

    BrokerEntity requestToEntity(BrokerRequest request);

    BrokerAdvisorEntity mapRelationshipRequestToBrokerAdvisorEntity(RelationshipRequest request);

    @Named("mapBrokerAdvisorsToRelationships")
    default List<BrokerRelationshipResponse> mapBrokerAdvisorsToRelationships(Set<BrokerAdvisorEntity> brokerAdvisors) {
        return brokerAdvisors.stream()
                .map(this::mapBrokerAdvisorEntityToBrokerRelationshipResponse)
                .toList();
    }
}
