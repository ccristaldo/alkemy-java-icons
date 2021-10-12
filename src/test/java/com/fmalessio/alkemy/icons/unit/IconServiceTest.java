package com.fmalessio.alkemy.icons.unit;

import com.fmalessio.alkemy.icons.dto.IconDTO;
import com.fmalessio.alkemy.icons.entity.ContinenteEntity;
import com.fmalessio.alkemy.icons.entity.IconEntity;
import com.fmalessio.alkemy.icons.entity.PaisEntity;
import com.fmalessio.alkemy.icons.repository.IconRepository;
import com.fmalessio.alkemy.icons.service.IconService;
import com.fmalessio.alkemy.icons.utils.CustomDateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IconServiceTest {

    private static Logger logger = LoggerFactory.getLogger(IconServiceTest.class);

    @MockBean
    private IconRepository iconRepository;
    @Autowired
    private IconService iconService;

    @BeforeEach
    void init() {
        logger.info("Init");
        when(iconRepository.save(any())).thenReturn(
                getMockIconEntity(false)
        );
    }

    @Test
    @Tag("Should create DTO with all fields")
    public void shouldCreateDTOWithAllFields_WhenSaveIcon() {
        IconDTO toSave = getMockIconDTO();
        IconDTO result = iconService.save(toSave);
        assertAll("Should return the same values with an ID",
                () -> assertNotNull(result.getId()),
                () -> assertNull(result.getPaises()),
                () -> assertEquals(result.getImagen(), toSave.getImagen()),
                () -> assertEquals(result.getDenominacion(), toSave.getDenominacion()),
                () -> assertEquals(result.getFechaCreacion(), toSave.getFechaCreacion()),
                () -> assertEquals(result.getAltura(), toSave.getAltura()),
                () -> assertEquals(result.getImagen(), toSave.getImagen())
        );
    }

    private IconDTO getMockIconDTO() {
        IconDTO dto = new IconDTO();
        dto.setImagen("/path/icon.jpg");
        dto.setDenominacion("Icon crazy one");
        dto.setFechaCreacion("2021-09-28");
        dto.setAltura(500L);
        dto.setHistoria("History");
        return dto;
    }

    private IconEntity getMockIconEntity(boolean mockPaises) {
        IconDTO dto = getMockIconDTO();
        //
        IconEntity entity = new IconEntity();
        entity.setId(new Random().nextLong());
        entity.setImagen(dto.getImagen());
        entity.setDenominacion(dto.getDenominacion());
        entity.setFechaCreacion(
                CustomDateUtils.string2LocalDate(dto.getFechaCreacion())
        );
        entity.setAltura(dto.getAltura());
        entity.setHistoria(dto.getHistoria());
        // Many to Many
        if (mockPaises) {
            entity.setPaises(Arrays.asList(
                    getMockPaisEntity(false),
                    getMockPaisEntity(false)
            ));
        } else {
            entity.setPaises(null);
        }
        return entity;
    }

    private PaisEntity getMockPaisEntity(boolean mockIcons) {
        PaisEntity entity = new PaisEntity();
        entity.setId(new Random().nextLong());
        entity.setImagen("/path/pais.jpg");
        entity.setDenominacion("Pais crazy one");
        entity.setCantidadHabitantes(new Random().nextLong());
        entity.setSuperficie(new Random().nextLong());
        entity.setContinenteId(new Random().nextLong());
        entity.setContinente(new ContinenteEntity());
        // Many to Many
        if (mockIcons) {
            entity.setIcons(new HashSet<>(Arrays.asList(
                    getMockIconEntity(false),
                    getMockIconEntity(false)
            )));
        } else {
            entity.setIcons(null);
        }
        return entity;
    }


}
