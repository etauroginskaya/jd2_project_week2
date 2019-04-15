package com.gmail.etauroginskaya.controller.util;

import com.gmail.etauroginskaya.service.model.DocumentDTO;

import java.util.Map;

public interface ValidatorService {

    Map<String, String> validateDocumentDTO(Map<String, String> messages, DocumentDTO in);

    Map<String, String> validateDocumentId(Map<String, String> messages, Long in);
}
