package com.fred.logictoolbox.controller;

import com.fred.logictoolbox.service.objectutils.ObjectUtilsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ObjectUtilsController.class)
class ObjectUtilsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ObjectUtilsService objectUtilsService;

    @Nested
    @DisplayName("Tests API de mergeObjects")
    class MergeObjectsApiTests {

        @Test
        @DisplayName("Doit retourner une réponse API standardisée")
        void shouldReturnStandardizedApiResponse() throws Exception {

            Map<String, Number> mergedResult = Map.of(
                    "january", 1800,
                    "february", 2150
            );

            when(objectUtilsService.mergeObjects(
                    Map.of(
                            "january", 1000,
                            "february", 1200
                    ),
                    Map.of(
                            "january", 800,
                            "february", 950
                    )
            )).thenReturn(mergedResult);

            mockMvc.perform(
                            post("/api/object/merge")
                                    .contentType(APPLICATION_JSON)
                                    .content("""
                                            {
                                              "firstObject": {
                                                "january": 1000,
                                                "february": 1200
                                              },
                                              "secondObject": {
                                                "january": 800,
                                                "february": 950
                                              }
                                            }
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success")
                            .value(true))
                    .andExpect(jsonPath("$.message")
                            .value("Fusion effectuée avec succès"))
                    .andExpect(jsonPath("$.data.january")
                            .value(1800))
                    .andExpect(jsonPath("$.data.february")
                            .value(2150));
        }
    }

    @Nested
    @DisplayName("Tests API de validateObject")
    class ValidateObjectApiTests {

        @Test
        @DisplayName("Doit retourner une réponse API standardisée")
        void shouldReturnStandardizedApiResponse() throws Exception {

            when(objectUtilsService.validateObject(
                    Map.of(
                            "name", "Marie",
                            "age", 25
                    ),
                    Map.of(
                            "name", "string",
                            "age", "number"
                    )
            )).thenReturn(true);

            mockMvc.perform(
                            post("/api/object/validate")
                                    .contentType(APPLICATION_JSON)
                                    .content("""
                                            {
                                              "object": {
                                                "name": "Marie",
                                                "age": 25
                                              },
                                              "schema": {
                                                "name": "string",
                                                "age": "number"
                                              }
                                            }
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success")
                            .value(true))
                    .andExpect(jsonPath("$.message")
                            .value("Validation effectuée avec succès"))
                    .andExpect(jsonPath("$.data")
                            .value(true));
        }
    }
}