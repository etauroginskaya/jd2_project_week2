package com.gmail.etauroginskaya.service.impl;

import com.gmail.etauroginskaya.repository.DocumentRepository;
import com.gmail.etauroginskaya.repository.model.Document;
import com.gmail.etauroginskaya.service.converter.DocumentConverter;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DocumentServiceImpl implements com.gmail.etauroginskaya.service.DocumentService {

    private static final Logger logger = LogManager.getLogger(DocumentServiceImpl.class);

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentConverter documentConverter;

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        Document document = documentConverter.fromDTO(documentDTO);
        document = documentRepository.addDocument(document);
        return documentConverter.toDTO(document);
    }

    @Override
    public DocumentDTO getDocumentById(Long id) {
        Document document = documentRepository.getDocumentById(id);
        return documentConverter.toDTO(document);
    }

    @Override
    public void delete(Long id) {
        Integer indicatorSoftDelete = documentRepository.getDocumentById(id).getDeleted();
        if (Objects.equals(indicatorSoftDelete, 0)) {
            documentRepository.deleteDocumentById(id);
        } else {
            logger.warn("Document with id (" + id + ") has been deleted previously.");
        }
    }
}
