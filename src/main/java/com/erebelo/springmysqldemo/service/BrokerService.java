package com.erebelo.springmysqldemo.service;

import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerResponse;

import java.util.List;

public interface BrokerService {

    List<BrokerResponse> findAll();

    BrokerResponse findById(Long id);

    BrokerResponse insert(BrokerRequest request);

    BrokerResponse update(Long id, BrokerRequest request);

    void delete(Long id);

}
