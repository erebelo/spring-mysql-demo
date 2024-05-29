package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.response.AddressResponse;
import com.erebelo.springmysqldemo.domain.response.AdvisorResponse;
import com.erebelo.springmysqldemo.domain.response.BrokerResponse;
import com.erebelo.springmysqldemo.domain.response.BrokerTypeResponse;
import com.erebelo.springmysqldemo.domain.response.RelationshipResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerMapper {

    /*
     Specify the properties (relationships and associatedBrokers) to be ignored and not mapped,
     leveraging the lazy fetch strategy and saving on additional queries
     */
    @Mapping(target = "relationships", ignore = true)
    @Mapping(target = "associatedBrokers", ignore = true)
    BrokerResponse lazyFetchMap(BrokerEntity entity);

    @Mapping(source = "brokerAdvisors", target = "relationships", qualifiedByName = "mapBrokerAdvisorEntitySetToRelationshipResponseList")
    BrokerResponse entityToResponse(BrokerEntity entity);

    AddressResponse addressEntityToAddressResponse(AddressEntity entity);

    BrokerTypeResponse brokerTypeEntityToBrokerTypeResponse(BrokerTypeEntity entity);

    RelationshipResponse brokerAdvisorEntityToRelationshipResponse(BrokerAdvisorEntity entity);

    AdvisorResponse advisorEntityToAdvisorResponse(AdvisorEntity entity);

    BrokerEntity requestToEntity(BrokerRequest request);

    default List<BrokerResponse> entityListToResponseList(List<BrokerEntity> entityList) {
        return entityList.stream()
                .map(this::lazyFetchMap)
                .toList();
    }

    @Named("mapBrokerAdvisorEntitySetToRelationshipResponseList")
    default List<RelationshipResponse> mapBrokerAdvisorEntitySetToRelationshipResponseList(Set<BrokerAdvisorEntity> brokerAdvisors) {
        return brokerAdvisors.stream()
                .map(this::brokerAdvisorEntityToRelationshipResponse)
                .toList();
    }
}
