package com.erebelo.springmysqldemo.mapper;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.response.AddressResponse;
import com.erebelo.springmysqldemo.domain.response.BrokerResponse;
import com.erebelo.springmysqldemo.domain.response.BrokerTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BrokerMapper {

    List<BrokerResponse> entityToResponse(List<BrokerEntity> entityList);

    BrokerResponse entityToResponse(BrokerEntity entity);

    AddressResponse entityToResponse(AddressEntity entity);

    BrokerTypeResponse entityToResponse(BrokerTypeEntity entity);

    BrokerEntity requestToEntity(BrokerRequest request);

}
