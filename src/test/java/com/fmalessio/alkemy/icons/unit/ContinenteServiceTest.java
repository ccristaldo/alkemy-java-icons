package com.fmalessio.alkemy.icons.unit;

import com.fmalessio.alkemy.icons.dto.ContinenteDTO;
import com.fmalessio.alkemy.icons.entity.ContinenteEntity;
import com.fmalessio.alkemy.icons.repository.ContinenteRepository;
import com.fmalessio.alkemy.icons.service.ContinenteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContinenteServiceTest {

    private static Logger logger = LoggerFactory.getLogger(ContinenteServiceTest.class);

    @MockBean
    private ContinenteRepository continenteRepository;
    @Autowired
    private ContinenteService continenteService;

    @BeforeAll
    static void setup() {
        logger.info("Starting tests..");
    }

    @BeforeEach
    void init() {
        logger.info("Init");
        when(continenteRepository.save(any())).thenReturn(
                getMockContinenteEntity()
        );
    }

    @Test
    @Tag("Should create DTO with all fields")
    public void shouldCreateDTOWithAllFields_WhenSaveContiente() {
        ContinenteDTO toSave = getMockContinenteDTO();
        ContinenteDTO result = continenteService.save(toSave);
        assertAll("Should return the same values with an ID",
                () -> assertNotNull(result.getId()),
                () -> assertEquals(result.getImagen(), toSave.getImagen()),
                () -> assertEquals(result.getDenominacion(), toSave.getDenominacion())
        );
    }

    private ContinenteDTO getMockContinenteDTO() {
        ContinenteDTO mock = new ContinenteDTO();
        mock.setImagen("/path/img.jpg");
        mock.setDenominacion("Continente Mocked");
        return mock;
    }

    private ContinenteEntity getMockContinenteEntity() {
        ContinenteDTO dto = getMockContinenteDTO();
        // Create entity from dto
        ContinenteEntity mock = new ContinenteEntity();
        mock.setId(new Random().nextLong());
        mock.setImagen(dto.getImagen());
        mock.setDenominacion(dto.getDenominacion());
        return mock;
    }

}
