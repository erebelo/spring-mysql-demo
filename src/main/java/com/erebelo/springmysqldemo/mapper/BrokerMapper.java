package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.model.BrokerEntity;
import com.erebelo.springmysqldemo.model.BrokerRequest;
import com.erebelo.springmysqldemo.model.BrokerResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerMapper {

    List<BrokerResponse> entityToResponse(List<BrokerEntity> entityList);

    BrokerResponse entityToResponse(BrokerEntity entity);

    BrokerEntity requestToEntity(BrokerRequest request);

}
