package com.innowise.auditmicroservice.service.impl;

import avro.ClientActionRequest;
import com.innowise.auditmicroservice.entity.ClientActionEntity;
import com.innowise.auditmicroservice.repository.ClientActionRepo;
import com.innowise.auditmicroservice.repository.ClientActionRepository;
import com.innowise.auditmicroservice.security.filter.AuthTokenFilter;
import com.innowise.auditmicroservice.service.ClientActionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientActionServiceImpl implements ClientActionService {

    private final MongoTemplate mongoTemplate;
    private final ClientActionRepository clientActionRepository;
    private final ClientActionRepo clientActionRepo;
    private final static Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<ClientActionEntity> getClientActions(String email, String action, String actionType, LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit) {
        return clientActionRepo.getClientActionEntitiesByParams(email, action, actionType, dateFrom, dateTo, offset, limit);
    }

    public void saveClientAction(ClientActionRequest clientActionRequest) throws ParseException {
        clientActionRepository.save(new ClientActionEntity(
                clientActionRequest.getEmail().toString(),
                clientActionRequest.getAction().toString(),
                clientActionRequest.getActionType().toString(),
                LocalDateTime.parse(clientActionRequest.getDate())
        ));
        logger.info("ClientAction was successfully saved!");
    }
}
