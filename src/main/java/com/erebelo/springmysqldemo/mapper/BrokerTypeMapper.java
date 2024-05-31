package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.BrokerLazyResponse;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerTypeMapper {

    default List<BrokerTypeResponse> entityListToResponseList(List<BrokerTypeEntity> entityList) {
        return entityList.stream().map(this::lazyEntityToResponse).toList();
    }

    // Lazy fetch strategy that saves additional queries by ignoring/not mapping properties
    @Mapping(target = "brokers", ignore = true)
    BrokerTypeResponse lazyEntityToResponse(BrokerTypeEntity entity);

    @Mapping(target = "brokers", source = "brokers", qualifiedByName = "mapSetBrokersToListBrokers")
    BrokerTypeResponse entityToResponse(BrokerTypeEntity entity);

    BrokerLazyResponse mapBrokerEntityToBrokerLazyResponse(BrokerEntity entity);

    BrokerTypeEntity requestToEntity(BrokerTypeRequest request);

    @Named("mapSetBrokersToListBrokers")
    default List<BrokerLazyResponse> mapSetBrokersToListBrokers(Set<BrokerEntity> brokers) {
        return brokers.stream()
                .map(this::mapBrokerEntityToBrokerLazyResponse)
                .toList();
    }
}
