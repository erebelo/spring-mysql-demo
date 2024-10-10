package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import com.erebelo.springmysqldemo.mapper.BrokerTypeMapper;
import com.erebelo.springmysqldemo.repository.BrokerTypeRepository;
import com.erebelo.springmysqldemo.service.BrokerTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrokerTypeServiceImpl implements BrokerTypeService {

    private final BrokerTypeRepository repository;
    private final BrokerTypeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<BrokerTypeResponse> findAll() {
        var entityList = repository.findAll();
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerTypeResponse findById(Long id) {
        var entity = repository.findById(id).orElse(null);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerTypeResponse insert(BrokerTypeRequest request) {
        var entity = repository.save(mapper.requestToEntity(request));
        return mapper.lazyEntityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerTypeResponse update(Long id, BrokerTypeRequest request) {
        var entity = repository.findById(id).orElse(null);

        if (entity != null) {
            var brokers = entity.getBrokers();

            if (brokers == null || brokers.isEmpty()) {
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
