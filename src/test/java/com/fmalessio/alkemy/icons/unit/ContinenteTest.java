package com.fmalessio.alkemy.icons.unit;

import com.fmalessio.alkemy.icons.auth.dto.AuthenticationRequest;
import com.fmalessio.alkemy.icons.auth.dto.AuthenticationResponse;
import com.fmalessio.alkemy.icons.auth.entity.UserEntity;
import com.fmalessio.alkemy.icons.auth.repository.UserRepository;
import com.fmalessio.alkemy.icons.dto.ContinenteDTO;
import com.fmalessio.alkemy.icons.entity.ContinenteEntity;
import com.fmalessio.alkemy.icons.repository.ContinenteRepository;
import com.fmalessio.alkemy.icons.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContinenteTest {

    private static Logger logger = LoggerFactory.getLogger(ContinenteTest.class);
    private AuthenticationResponse authResponse;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContinenteRepository continenteRepository;
    @MockBean
    private UserRepository userRepository;

    @BeforeAll
    void setup() throws Exception {
        logger.info("Starting tests..");
        UserEntity userMock = new UserEntity();
        userMock.setUsername("mock@mock.com");
        userMock.setPassword("12345678");
        when(userRepository.findByUsername(anyString())).thenReturn(
                userMock
        );
        AuthenticationRequest authRq = new AuthenticationRequest();
        authRq.setUsername("mock@mock.com");
        authRq.setPassword("12345678");
        MvcResult authResult = this.mockMvc.perform(
                post("/auth/singin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(authRq))
        ).andReturn();
        authResponse = TestUtils.parseResponse(authResult, AuthenticationResponse.class);
    }

    @BeforeEach
    void init() {
        logger.info("Init");
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
                        .header("Authorization", "Bearer " + authResponse.getJwt())
                        .content(TestUtils.asJsonString(getMockContinenteDTO()))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private ContinenteDTO getMockContinenteDTO() {
        ContinenteDTO mock = new ContinenteDTO();
        mock.setImagen("/path/img.jpg");
        mock.setDenominacion("Continente Mocked");
        return mock;
    }

    private ContinenteEntity getMockContinenteEntity() {
        ContinenteEntity mock = new ContinenteEntity();
        mock.setId(1L);
        mock.setImagen("/path/img.jpg");
        mock.setDenominacion("Continente Mocked");
        return mock;
    }

}
