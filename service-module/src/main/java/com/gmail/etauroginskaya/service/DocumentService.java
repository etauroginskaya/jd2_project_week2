package com.gmail.etauroginskaya.service;

import com.gmail.etauroginskaya.service.model.DocumentDTO;

public interface DocumentService {

    DocumentDTO add(DocumentDTO documentDTO);

    DocumentDTO getDocumentById(Long id);

    void delete(Long id);
}
