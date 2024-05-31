package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.request.RelationshipRequest;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerResponse;
import com.erebelo.springmysqldemo.mapper.BrokerMapper;
import com.erebelo.springmysqldemo.repository.AddressRepository;
import com.erebelo.springmysqldemo.repository.AdvisorRepository;
import com.erebelo.springmysqldemo.repository.BrokerRepository;
import com.erebelo.springmysqldemo.repository.BrokerTypeRepository;
import com.erebelo.springmysqldemo.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrokerServiceImpl implements BrokerService {

    private final BrokerRepository brokerRepository;
    private final AddressRepository addressRepository;
    private final BrokerTypeRepository brokerTypeRepository;
    private final AdvisorRepository advisorRepository;
    private final BrokerMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<BrokerResponse> findAll() {
        var entityList = brokerRepository.findAll();
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerResponse findById(Long id) {
        var entity = brokerRepository.findById(id).orElse(null);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse insert(BrokerRequest request) {
        var entity = buildEntityObject(request);
        entity = brokerRepository.save(entity);

        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse update(Long id, BrokerRequest request) {
        var entity = brokerRepository.findById(id).orElse(null);

        if (entity != null) {
            var updatedEntity = buildEntityObject(request);
            updatedEntity.setId(id);

            AddressEntity addressToDelete = null;
            if (entity.getAddress() != null) {
                if (updatedEntity.getAddress() != null) {
                    updatedEntity.getAddress().setId(entity.getAddress().getId());
                } else {
                    addressToDelete = entity.getAddress();
                }
            }

            entity = brokerRepository.save(updatedEntity);

            // Unused address cleanup
            if (addressToDelete != null) {
                addressRepository.delete(addressToDelete);
            }
        }

        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        brokerRepository.deleteById(id);
    }

    private BrokerEntity buildEntityObject(BrokerRequest request) {
        var brokerEntity = mapper.requestToEntity(request);

        // Handle BrokerType
        brokerEntity.setBrokerType(brokerTypeRepository.findById(request.getBrokerTypeId()).orElse(null));

        // Handle BrokerAdvisor
        for (RelationshipRequest relationshipRequest : request.getRelationships()) {
            var brokerAdvisorEntity = mapper.relationshipRequestToBrokerAdvisorEntity(relationshipRequest);
            brokerAdvisorEntity.setAdvisor(advisorRepository.findById(relationshipRequest.getAdvisorId()).orElse(null));
            brokerAdvisorEntity.setBroker(brokerEntity);

            brokerEntity.getBrokerAdvisors().add(brokerAdvisorEntity);
        }

        // Handle AssociatedBrokers
        for (Long associatedBrokerId : request.getAssociatedBrokerIds()) {
            brokerEntity.getAssociatedBrokers().add(brokerRepository.findById(associatedBrokerId).orElse(null));
        }

        return brokerEntity;
    }
}
