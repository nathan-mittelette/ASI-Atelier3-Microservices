package com.cardmicroservice.cardmicroservice.controllers;

import com.asi.lib.AbstractLoginControllerTest;
import com.asi.lib.dto.CardDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CardControllerTestTests extends AbstractLoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void whenCardAvailable_ShouldReturn200() throws Exception {
        this.prepareAuthFilter();
        MvcResult result = this.mockMvc.perform(get("/secured/cards/available").with(this::authProcessRequest)).andDo(print()).andExpect(status().isOk()).andReturn();

        List<CardDTO> cardDTOList = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<CardDTO>>() {
        });

        assertEquals(cardDTOList.size(), 3);

        List<Long> ids = Arrays.asList(1003L, 1004L, 1005L);

        for (int i = 0; i < cardDTOList.size(); i++) {
            assertEquals(cardDTOList.get(i).getId(), ids.get(i));
            assertEquals(cardDTOList.get(i).getName(), String.format("card %d", ids.get(i)));
            assertEquals(cardDTOList.get(i).getDescription(), String.format("description %d", ids.get(i)));
        }
    }

    @Test
    public void whenCardAvailable_WithNoAuth_ShouldReturn403() throws Exception {
        this.mockMvc.perform(get("/secured/cards/available")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void when_CardById_ShouldReturn200() throws Exception {
        Long id = 1001L;
        this.prepareAuthFilter();
        MvcResult result = this.mockMvc.perform(get("/private/cards/".concat(String.valueOf(id))).with(this::authProcessRequest)).andDo(print()).andExpect(status().isOk()).andReturn();

        CardDTO cardDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), CardDTO.class);

        assert cardDTO != null;

        assertEquals(cardDTO.getId(), id);
    }

    @Test
    public void when_CardByIdAndNoCard_ShouldReturn500() throws Exception {
        Long id = -1L;
        this.prepareAuthFilter();
        this.mockMvc.perform(get("/private/cards/".concat(String.valueOf(id)))).andDo(print()).andExpect(status().isInternalServerError()).andReturn();
    }

    @Test
    public void when_createRandomCard_ShouldCreate() throws Exception {
        Long userId = 1000L;
        MvcResult result = this.mockMvc.perform(post("/private/cards/createRandom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userId))).andDo(print()).andExpect(status().isOk()).andReturn();

        CardDTO cardDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), CardDTO.class);
        assertEquals(cardDTO.getUserId(), userId);
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void when_updateCard_ShouldUpdate() throws Exception {
        Long cardId = 1001L;

        ObjectMapper objectMapper = new ObjectMapper();

        this.prepareAuthFilter();
        MvcResult result = this.mockMvc.perform(get("/private/cards/".concat(String.valueOf(cardId))).with(this::authProcessRequest)).andDo(print()).andExpect(status().isOk()).andReturn();
        CardDTO cardDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), CardDTO.class);

        Random random = new Random();
        String randomValue = "Content ".concat(String.valueOf(random.nextInt()));

        cardDTO.setFamily(randomValue);
        cardDTO.setAffinity(randomValue);
        cardDTO.setDescription(randomValue);
        cardDTO.setImageUrl(randomValue);
        cardDTO.setName(randomValue);

        this.mockMvc.perform(put("/private/cards/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cardDTO))).andDo(print()).andExpect(status().isOk());

        result = this.mockMvc.perform(get("/private/cards/".concat(String.valueOf(cardId))).with(this::authProcessRequest)).andDo(print()).andExpect(status().isOk()).andReturn();
        cardDTO = new ObjectMapper().readValue(result.getResponse().getContentAsString(), CardDTO.class);

        assertEquals(cardDTO.getFamily(), randomValue);
        assertEquals(cardDTO.getAffinity(), randomValue);
        assertEquals(cardDTO.getDescription(), randomValue);
        assertEquals(cardDTO.getImageUrl(), randomValue);
        assertEquals(cardDTO.getName(), randomValue);
    }
}
