package com.erebelo.springmysqldemo.service;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;

import java.util.List;

public interface BrokerTypeService {

    List<BrokerTypeResponse> findAll();

    BrokerTypeResponse findById(Long id);

    BrokerTypeResponse insert(BrokerTypeRequest request);

    BrokerTypeResponse update(Long id, BrokerTypeRequest request);

    void delete(Long id);

}
