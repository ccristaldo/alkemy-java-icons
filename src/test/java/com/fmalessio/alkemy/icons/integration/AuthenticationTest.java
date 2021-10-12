package com.fmalessio.alkemy.icons.integration;

import com.fmalessio.alkemy.icons.auth.dto.AuthenticationRequest;
import com.fmalessio.alkemy.icons.auth.dto.AuthenticationResponse;
import com.fmalessio.alkemy.icons.auth.entity.UserEntity;
import com.fmalessio.alkemy.icons.auth.repository.UserRepository;
import com.fmalessio.alkemy.icons.unit.ContinenteServiceTest;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for Auth")
public class AuthenticationTest {
    private static Logger logger = LoggerFactory.getLogger(ContinenteServiceTest.class);

    private AuthenticationResponse authResponse;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @BeforeAll
    static void setup() {
        logger.info("Starting tests..");
    }

    @BeforeEach
    void init() {
        logger.info("Init");
    }

    @Test
    public void shouldReturnCreated_WhenPostContienteWithAuth() throws Exception {
        String username = "mock@mock.com";
        String password = "12345678";
        // Mock find by username
        UserEntity userMock = new UserEntity();
        userMock.setUsername(username);
        userMock.setPassword(password);
        when(userRepository.findByUsername(anyString())).thenReturn(
                userMock
        );
        // Request
        AuthenticationRequest authRq = new AuthenticationRequest();
        authRq.setUsername(username);
        authRq.setPassword(password);
        // Perform
        this.mockMvc.perform(
                post("/auth/singin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.asJsonString(authRq))
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        );
    }
}
