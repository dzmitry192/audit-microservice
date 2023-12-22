package com.innowise.auditmicroservice.repository;

import com.innowise.auditmicroservice.entity.ClientActionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientActionRepository extends MongoRepository<ClientActionEntity, String> {
}
