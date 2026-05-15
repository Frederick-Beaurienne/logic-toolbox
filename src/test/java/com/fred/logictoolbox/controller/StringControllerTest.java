package com.fred.logictoolbox.controller;

import com.fred.logictoolbox.service.string.StringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StringController.class)
class StringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StringService stringService;

    @Nested
    @DisplayName("Tests API de getLengthWithoutSpaces")
    class GetLengthWithoutSpacesApiTests {

        @Test
        @DisplayName("Doit retourner une réponse API standardisée")
        void shouldReturnStandardizedApiResponse() throws Exception {

            when(stringService.getLengthWithoutSpaces("Bonjour le monde !"))
                    .thenReturn(15);

            mockMvc.perform(
                            get("/api/string/length-without-spaces")
                                    .param("input", "Bonjour le monde !")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.message")
                            .value("Longueur calculée avec succès"))
                    .andExpect(jsonPath("$.data").value(15));
        }

        @Test
        @DisplayName("Doit retourner une erreur 400 si le paramètre input est absent")
        void shouldReturnBadRequestWhenInputIsMissing() throws Exception {

            mockMvc.perform(
                            get("/api/string/length-without-spaces")
                    )
                    .andExpect(status().isBadRequest());
        }
    }
}