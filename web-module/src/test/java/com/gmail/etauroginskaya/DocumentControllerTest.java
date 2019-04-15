package com.gmail.etauroginskaya;

import com.gmail.etauroginskaya.controller.DocumentController;
import com.gmail.etauroginskaya.controller.impl.DocumentControllerImpl;
import com.gmail.etauroginskaya.service.DocumentService;
import com.gmail.etauroginskaya.service.model.DocumentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {

    private DocumentController documentController;
    @Mock
    private DocumentService documentService;

    @Before
    public void init() {
        documentController = new DocumentControllerImpl(documentService);
    }

    @Ignore
    public void shouldAddDocument() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setDescription("test");
        String uniqueNumber = UUID.randomUUID().toString();
        documentDTO.setUnique_number(uniqueNumber);

        DocumentDTO savedDocument = new DocumentDTO();
        savedDocument.setId(1L);
        savedDocument.setUnique_number(uniqueNumber);
        savedDocument.setDescription("test");

        when(documentService.add(documentDTO)).thenReturn(savedDocument);

        savedDocument = documentController.add(documentDTO);
        Assert.assertEquals(1L, savedDocument.getId().longValue());
    }

    @Test
    public void test(){}
}
