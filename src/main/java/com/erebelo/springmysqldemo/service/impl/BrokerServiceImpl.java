package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.mapper.BrokerMapper;
import com.erebelo.springmysqldemo.model.BrokerRequest;
import com.erebelo.springmysqldemo.model.BrokerResponse;
import com.erebelo.springmysqldemo.repository.BrokerRepository;
import com.erebelo.springmysqldemo.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {

    private final BrokerRepository repository;
    private final BrokerMapper mapper;

    @Override
    public List<BrokerResponse> findAll() {
        var entityList = repository.findAll();
        return mapper.entityToResponse(entityList);
    }

    @Override
    public BrokerResponse findById(Long id) {
        var entity = repository.findById(id).orElse(null);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse insert(BrokerRequest request) {
        var entity = repository.save(mapper.requestToEntity(request));
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse update(Long id, BrokerRequest request) {
        var entity = repository.findById(id).orElse(null);

        if (entity != null) {
            var tempEntity = mapper.requestToEntity(request);
            tempEntity.setId(entity.getId());

            entity = repository.save(tempEntity);

            if (entity == null) {
                throw new IllegalStateException("rolling back");
            }
        }

        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
