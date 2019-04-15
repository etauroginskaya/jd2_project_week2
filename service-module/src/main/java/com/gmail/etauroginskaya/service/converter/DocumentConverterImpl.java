package com.gmail.etauroginskaya.service.converter;

import com.gmail.etauroginskaya.repository.model.Document;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.springframework.stereotype.Service;

@Service
public class DocumentConverterImpl implements DocumentConverter {

    @Override
    public DocumentDTO toDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription(document.getDescription());
        documentDTO.setUnique_number(document.getUnique_number());
        documentDTO.setId(document.getId());
        return documentDTO;
    }

    @Override
    public Document fromDTO(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setDescription(documentDTO.getDescription());
        document.setUnique_number(documentDTO.getUnique_number());
        return document;
    }
}
