package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import com.erebelo.springmysqldemo.mapper.AdvisorMapper;
import com.erebelo.springmysqldemo.repository.AdvisorRepository;
import com.erebelo.springmysqldemo.service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository repository;
    private final AdvisorMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AdvisorResponse> findAll() {
        var entityList = repository.findAll();
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvisorResponse findById(Long id) {
        var entity = repository.findById(id).orElse(null);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public AdvisorResponse insert(AdvisorRequest request) {
        var entity = repository.save(mapper.requestToEntity(request));
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public AdvisorResponse update(Long id, AdvisorRequest request) {
        var entity = repository.findById(id).orElse(null);

        if (entity != null) {
            var relationships = entity.getAdvisorBrokers();

            if (relationships == null || relationships.isEmpty()) {
                var updatedEntity = mapper.requestToEntity(request);
                updatedEntity.setId(id);

                entity = repository.save(updatedEntity);
            }
        }

        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
