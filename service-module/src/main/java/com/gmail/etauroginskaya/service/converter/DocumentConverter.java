package com.gmail.etauroginskaya.service.converter;

import com.gmail.etauroginskaya.repository.model.Document;
import com.gmail.etauroginskaya.service.model.DocumentDTO;

public interface DocumentConverter {

    DocumentDTO toDTO(Document document);

    Document fromDTO(DocumentDTO documentDTO);
}
