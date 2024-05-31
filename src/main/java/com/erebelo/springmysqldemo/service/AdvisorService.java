package com.erebelo.springmysqldemo.service;

import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;

import java.util.List;

public interface AdvisorService {

    List<AdvisorResponse> findAll();

    AdvisorResponse findById(Long id);

    AdvisorResponse insert(AdvisorRequest request);

    AdvisorResponse update(Long id, AdvisorRequest request);

    void delete(Long id);

}
