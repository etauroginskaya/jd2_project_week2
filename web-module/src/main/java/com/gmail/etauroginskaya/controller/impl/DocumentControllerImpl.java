package com.gmail.etauroginskaya.controller.impl;

import com.gmail.etauroginskaya.controller.DocumentController;
import com.gmail.etauroginskaya.controller.util.ValidatorService;
import com.gmail.etauroginskaya.service.DocumentService;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = LogManager.getLogger(DocumentControllerImpl.class);

    private DocumentService documentService;
    private ValidatorService validatorService;

    private Map<String, String> validateResult;

    public DocumentControllerImpl(DocumentService documentService, ValidatorService validatorService) {
        this.documentService = documentService;
        this.validatorService = validatorService;
    }

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        validateResult = new HashMap<>();
        validateResult = validatorService.validateDocumentDTO(validateResult, documentDTO);
        if (validateResult.isEmpty()) {
            return documentService.add(documentDTO);
        }
        validateResult.forEach((key, value) -> logger.info(value));
        return null;
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        validateResult = new HashMap<>();
        validateResult = validatorService.validateDocumentId(validateResult, id);
        if (validateResult.isEmpty()) {
            return documentService.getDocumentById(id);
        }
        validateResult.forEach((key, value) -> logger.info(value));
        return null;
    }

    @Override
    public void delete(Long id) {
        validateResult = new HashMap<>();
        validateResult = validatorService.validateDocumentId(validateResult, id);
        if (validateResult.isEmpty()) {
            documentService.delete(id);
        }
        validateResult.forEach((key, value) -> logger.info(value));
    }
}
