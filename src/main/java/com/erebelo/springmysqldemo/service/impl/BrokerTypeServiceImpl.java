package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import com.erebelo.springmysqldemo.mapper.BrokerTypeMapper;
import com.erebelo.springmysqldemo.repository.BrokerTypeRepository;
import com.erebelo.springmysqldemo.service.BrokerTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrokerTypeServiceImpl implements BrokerTypeService {

    private final BrokerTypeRepository repository;
    private final BrokerTypeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<BrokerTypeResponse> findAll() {
        log.info("Fetching all broker types");
        var entityList = repository.findAll();

        log.info("{} broker types found", entityList.size());
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerTypeResponse findById(Long id) {
        log.info("Fetching broker type with id: {}", id);
        var entity = repository.findById(id).orElse(null);

        log.info("Broker type successfully retrieved: {}", entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerTypeResponse insert(BrokerTypeRequest request) {
        log.info("Creating broker type");
        var entity = repository.save(mapper.requestToEntity(request));

        log.info("Broker type created successfully: {}", entity);
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerTypeResponse update(Long id, BrokerTypeRequest request) {
        log.info("Updating broker type with id: {}", id);
        var entity = repository.findById(id).orElse(null);

        if (entity != null) {
            var brokers = entity.getBrokers();

            if (brokers == null || brokers.isEmpty()) {
                var updatedEntity = mapper.requestToEntity(request);
                updatedEntity.setId(id);

                entity = repository.save(updatedEntity);
            }
        }

        log.info("Broker type updated successfully: {}", entity);
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting broker type with id: {}", id);

        repository.deleteById(id);
        log.info("Broker type deleted successfully");
    }
}
