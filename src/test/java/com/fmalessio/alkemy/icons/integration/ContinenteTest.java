package com.fmalessio.alkemy.icons.integration;

import com.fmalessio.alkemy.icons.auth.repository.UserRepository;
import com.fmalessio.alkemy.icons.dto.ContinenteDTO;
import com.fmalessio.alkemy.icons.entity.ContinenteEntity;
import com.fmalessio.alkemy.icons.repository.ContinenteRepository;
import com.fmalessio.alkemy.icons.utils.AuthResolver;
import com.fmalessio.alkemy.icons.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for Continente")
public class ContinenteTest {

    private static Logger logger = LoggerFactory.getLogger(ContinenteTest.class);

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContinenteRepository continenteRepository;
    @MockBean
    private UserRepository userRepository;

    @BeforeAll
    static void setup() throws Exception {
        logger.info("Starting tests..");
    }

    @BeforeEach
    void init() {
        logger.info("Init");
        when(userRepository.findByUsername(anyString())).thenReturn(
                AuthResolver.getInstance().getUserEntityMock()
        );
        when(continenteRepository.save(any())).thenReturn(
                new ContinenteEntity()
        );
    }

    @Test
    public void shouldReturnForbidden_WhenPostContienteWithoutAuth() throws Exception {
        this.mockMvc.perform(
                post("/continente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(getMockContinenteDTO()))
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void shouldReturnCreated_WhenPostContienteWithAuth() throws Exception {
        this.mockMvc.perform(
                post("/continente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(
                                HttpHeaders.AUTHORIZATION,
                                AuthResolver.getInstance().getToken()
                        )
                        .content(TestUtils.asJsonString(getMockContinenteDTO()))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private ContinenteDTO getMockContinenteDTO() {
        ContinenteDTO mock = new ContinenteDTO();
        mock.setImagen("/path/img.jpg");
        mock.setDenominacion("Continente Mocked");
        return mock;
    }

}
