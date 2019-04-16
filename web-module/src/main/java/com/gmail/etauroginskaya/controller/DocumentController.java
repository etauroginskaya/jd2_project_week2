package com.gmail.etauroginskaya.controller;

import com.gmail.etauroginskaya.service.model.DocumentDTO;

public interface DocumentController {

    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO getDocumentById(Long id);

    void delete(Long id);
}
