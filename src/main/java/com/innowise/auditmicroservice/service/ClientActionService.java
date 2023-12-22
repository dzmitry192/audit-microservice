package com.innowise.auditmicroservice.service;

import com.innowise.auditmicroservice.entity.ClientActionEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientActionService {
    List<ClientActionEntity> getClientActions(String email, String action, String actionType, LocalDateTime dateFrom, LocalDateTime dateTo, Integer offset, Integer limit);
}
