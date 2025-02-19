package com.erebelo.springmysqldemo.service.impl;

import com.erebelo.springmysqldemo.domain.entity.AddressEntity;
import com.erebelo.springmysqldemo.domain.entity.BrokerAdvisorEntity;
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
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
        log.info("Fetching all brokers");
        List<BrokerEntity> entityList = brokerRepository.findAll();

        log.info("{} brokers found", entityList.size());
        return mapper.entityListToResponseList(entityList);
    }

    @Override
    @Transactional(readOnly = true)
    public BrokerResponse findById(Long id) {
        log.info("Fetching broker with id: {}", id);
        BrokerEntity entity = brokerRepository.findById(id).orElse(null);

        log.info("Broker successfully retrieved: {}", entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse insert(BrokerRequest request) {
        log.info("Creating broker");
        BrokerEntity entity = brokerRepository.save(buildEntityObject(request));

        log.info("Broker created successfully: {}", entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public BrokerResponse update(Long id, BrokerRequest request) {
        log.info("Updating broker with id: {}", id);
        BrokerEntity entity = brokerRepository.findById(id).orElse(null);

        if (entity != null) {
            BrokerEntity updatedEntity = buildEntityObject(request);
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

        log.info("Broker updated successfully: {}", entity);
        return mapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting broker with id: {}", id);

        brokerRepository.deleteById(id);
        log.info("Broker deleted successfully");
    }

    private BrokerEntity buildEntityObject(BrokerRequest request) {
        BrokerEntity brokerEntity = mapper.requestToEntity(request);

        // Handle BrokerType
        brokerEntity.setBrokerType(brokerTypeRepository.findById(request.getBrokerTypeId()).orElse(null));

        // Handle BrokerAdvisor
        for (RelationshipRequest relationshipRequest : request.getRelationships()) {
            BrokerAdvisorEntity brokerAdvisorEntity = mapper
                    .mapRelationshipRequestToBrokerAdvisorEntity(relationshipRequest);
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
