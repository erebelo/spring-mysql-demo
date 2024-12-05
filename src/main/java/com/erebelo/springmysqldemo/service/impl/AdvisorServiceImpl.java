package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import com.erebelo.springmysqldemo.mapper.AdvisorMapper;
import com.erebelo.springmysqldemo.repository.AdvisorRepository;
import com.erebelo.springmysqldemo.service.AdvisorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository repository;
    private final AdvisorMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AdvisorResponse> findAll() {
        log.info("Fetching all advisors");
        var entityList = repository.findAll();

        log.info("{} advisors found", entityList.size());
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvisorResponse findById(Long id) {
        log.info("Fetching advisor with id: {}", id);
        var entity = repository.findById(id).orElse(null);

        log.info("Advisor successfully retrieved: {}", entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public AdvisorResponse insert(AdvisorRequest request) {
        log.info("Creating advisor");
        var entity = repository.save(mapper.requestToEntity(request));

        log.info("Advisor created successfully: {}", entity);
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public AdvisorResponse update(Long id, AdvisorRequest request) {
        log.info("Updating advisor with id: {}", id);
        var entity = repository.findById(id).orElse(null);

        if (entity != null) {
            var relationships = entity.getAdvisorBrokers();

            if (relationships == null || relationships.isEmpty()) {
                var updatedEntity = mapper.requestToEntity(request);
                updatedEntity.setId(id);

                entity = repository.save(updatedEntity);
            }
        }

        log.info("Advisor updated successfully: {}", entity);
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting advisor with id: {}", id);

        repository.deleteById(id);
        log.info("Advisor deleted successfully");
    }
}
