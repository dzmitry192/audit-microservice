package com.innowise.auditmicroservice.repository;

import com.innowise.auditmicroservice.entity.ClientActionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientActionRepo {

    private final MongoTemplate mongoTemplate;
    private final ClientActionRepository clientActionRepository;

    public List<ClientActionEntity> getClientActionEntitiesByParams(String email, String action, String actionType, LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit) {
        if(email == null && action == null && actionType == null && dateFrom == null && dateTo == null) {
            return clientActionRepository.findAll(PageRequest.of(offset, limit)).toList();
        } else {
            Query query = new Query();
            if(email != null) {
                query.addCriteria(Criteria.where("email").is(email));
            }
            if(action != null) {
                query.addCriteria(Criteria.where("action").is(action));
            }
            if(actionType != null) {
                query.addCriteria(Criteria.where("actionType").is(actionType));
            }

            if (dateFrom != null && dateTo == null) {
                query.addCriteria(Criteria.where("date").gte(dateFrom));
            } else if (dateFrom == null && dateTo != null) {
                query.addCriteria(Criteria.where("date").lte(dateTo));
            } else if (dateFrom != null && dateTo != null) {
                query.addCriteria(Criteria.where("date").gte(dateFrom).lte(dateTo));
            }

            return mongoTemplate.find(query, ClientActionEntity.class);
        }
    }

}
