package com.gmail.etauroginskaya.repository;

import com.gmail.etauroginskaya.repository.model.Document;

public interface DocumentRepository {

    Document addDocument(Document document);

    Document getDocumentById(Long id);

    void deleteDocumentById(Long id);
}
