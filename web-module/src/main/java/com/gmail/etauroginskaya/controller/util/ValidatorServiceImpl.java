package com.gmail.etauroginskaya.controller.util;

import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.UUID;

@Controller
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public Map<String, String> validateDocumentDTO(Map<String, String> messages, DocumentDTO in) {
        if (in == null) {
            messages.put("dto", "DocumentDTO should not be null");
        }
        if (in.getId() != null) {
            messages.put("id", "Id documentDTO should be null");
        }
        try {
            UUID.fromString(in.getUnique_number());
        } catch (IllegalArgumentException e) {
            messages.put("unique", "Unique number doesn't match UUID");
        }
        if (in.getDescription().length() > 100) {
            messages.put("description", "Description length must be no more than 100");
        }
        if (in.getDescription() == null || in.getUnique_number() == null){
            messages.put("notnull", "Properties DocumentDTO description and unique number fields must not be empty");
        }
        return messages;
    }

    @Override
    public Map<String, String> validateDocumentId(Map<String, String> messages, Long in) {
        if (in == null) {
            messages.put("id", "Id shouldn't be null");
        }
        return messages;
    }
}
