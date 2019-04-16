package com.gmail.etauroginskaya;

import com.gmail.etauroginskaya.controller.DocumentController;
import com.gmail.etauroginskaya.controller.impl.DocumentControllerImpl;
import com.gmail.etauroginskaya.controller.validator.ValidatorService;
import com.gmail.etauroginskaya.service.DocumentService;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {

    private DocumentController documentController;
    @Mock
    private ValidatorService validatorService;
    @Mock
    private DocumentService documentService;

    @Before
    public void init() {
        documentController = new DocumentControllerImpl(documentService, validatorService);
    }

    @Test
    public void shouldAddDocument() {
        DocumentDTO documentDTO = new DocumentDTO();

        DocumentDTO savedDocument = new DocumentDTO();
        savedDocument.setId(1L);

        when(documentService.add(documentDTO)).thenReturn(savedDocument);
        Map<String, String> message = new HashMap<>();
        when(validatorService.validateDocumentDTO(message, documentDTO)).thenReturn(message);

        savedDocument = documentController.add(documentDTO);
        Assert.assertEquals(1L, savedDocument.getId().longValue());
        verify(documentService, times(1)).add(documentDTO);
        verify(validatorService, times(1)).validateDocumentDTO(message, documentDTO);
    }

    @Test
    public void shouldReturnNullWhenNotValidDocument() {
        DocumentDTO documentDTO = new DocumentDTO();
        Map<String, String> message = new HashMap<>();
        message.put("test", "test");

        when(validatorService.validateDocumentDTO(message, documentDTO)).thenReturn(message);

        Assert.assertNull(documentController.add(documentDTO));
    }

    @Test
    public void shouldGetDocumen() {
        Long id = 1L;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(id);

        Map<String, String> message = new HashMap<>();
        when(validatorService.validateDocumentId(message, id)).thenReturn(message);
        when(documentService.getDocumentById(id)).thenReturn(documentDTO);

        documentDTO = documentController.getDocumentById(id);
        Assert.assertEquals(1L, documentDTO.getId().longValue());
        verify(documentService, times(1)).getDocumentById(id);
        verify(validatorService, times(1)).validateDocumentId(message, id);

    }

    @Test
    public void shouldReturnNullWhenNotValidId() {
        Map<String, String> message = new HashMap<>();
        message.put("test", "test");
        Long id = 1L;

        when(validatorService.validateDocumentId(message, id)).thenReturn(message);

        Assert.assertNull(documentController.getDocumentById(id));
    }

    @Test
    public void shouldDeleteDocument() {
        Long id = 1L;
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(id);

        Map<String, String> message = new HashMap<>();
        when(validatorService.validateDocumentId(message, id)).thenReturn(message);

        documentController.delete(id);
        verify(documentService, times(1)).delete(id);
        verify(validatorService, times(1)).validateDocumentId(message, id);
    }
}
