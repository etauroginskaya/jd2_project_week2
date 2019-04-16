package com.gmail.etauroginskaya.controller.app;

import com.gmail.etauroginskaya.controller.DocumentController;
import com.gmail.etauroginskaya.controller.config.AppConfig;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        DocumentController documentController = context.getBean(DocumentController.class);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription("Abc");
        documentDTO.setUnique_number(UUID.randomUUID().toString());
        documentController.add(documentDTO);
        documentDTO.setUnique_number("kljklj");
        documentController.add(documentDTO);
        documentController.getDocumentById(1L);
        documentController.getDocumentById(7L);
        documentController.delete(1L);
        documentController.delete(1L);
    }
}
