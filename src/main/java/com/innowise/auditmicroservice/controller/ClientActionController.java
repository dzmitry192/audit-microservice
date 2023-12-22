package com.innowise.auditmicroservice.controller;

import com.innowise.auditmicroservice.entity.ClientActionEntity;
import com.innowise.auditmicroservice.service.impl.ClientActionServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class ClientActionController {

    private final ClientActionServiceImpl clientActionService;

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<List<ClientActionEntity>> getClientActionsWithParams(
            @RequestParam(value = "email", required = false) @Valid @Email String email,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "actionType", required = false) String actionType,
            @RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateFrom,
            @RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTo,
            @RequestParam(value = "offset", required = false, defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "20") @Min(1) @Max(100) Integer limit
    ) {
        return ResponseEntity.ok().body(clientActionService.getClientActions(email, action, actionType, dateFrom, dateTo, offset, limit));
    }
}
