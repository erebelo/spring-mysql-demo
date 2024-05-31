package com.erebelo.springmysqldemo.mapper;

import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface AdvisorMapper {

//    /*
//     Specify the properties (relationships and associatedBrokers) to be ignored and not mapped,
//     leveraging the lazy fetch strategy and saving on additional queries
//     */
//    @Mapping(target = "relationships", ignore = true)
//    @Mapping(target = "associatedBrokers", ignore = true)
//    BrokerResponse lazyFetchMap(BrokerEntity entity);
//
//    @Mapping(source = "brokerAdvisors", target = "relationships", qualifiedByName = "mapBrokerAdvisorEntitySetToRelationshipResponseList")
//    BrokerResponse entityToResponse(BrokerEntity entity);
//
//    AddressResponse addressEntityToAddressResponse(AddressEntity entity);
//
//    BrokerTypeResponse brokerTypeEntityToBrokerTypeResponse(BrokerTypeEntity entity);
//
//    RelationshipResponse brokerAdvisorEntityToRelationshipResponse(BrokerAdvisorEntity entity);
//
//    AdvisorResponse advisorEntityToAdvisorResponse(AdvisorEntity entity);
//
//    BrokerEntity requestToEntity(BrokerRequest request);
//
//    BrokerAdvisorEntity relationshipRequestToBrokerAdvisorEntity(RelationshipRequest request);
//
//    default List<BrokerResponse> entityListToResponseList(List<BrokerEntity> entityList) {
//        return entityList.stream()
//                .map(this::lazyFetchMap)
//                .toList();
//    }
//
//    @Named("mapBrokerAdvisorEntitySetToRelationshipResponseList")
//    default List<RelationshipResponse> mapBrokerAdvisorEntitySetToRelationshipResponseList(Set<BrokerAdvisorEntity> brokerAdvisors) {
//        return brokerAdvisors.stream()
//                .map(this::brokerAdvisorEntityToRelationshipResponse)
//                .toList();
//    }
}
