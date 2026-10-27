package com.minsait.buscador.init.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.minsait.buscador.init.model.Item;
import com.minsait.buscador.init.service.ItemsService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ItemsController.class)
public class TestItemsController {
	@MockitoBean
    private ItemsService itemsService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ItemsController itemsController;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void testBuscarPorTematica() throws Exception {
        List<Item> items = Arrays.asList(
                new Item( "u1", "java", "d1"),
                new Item( "u2", "java", "d2")
            );

        when(itemsService.buscar("java")).thenReturn(items);
        mockMvc.perform(get("/items").param("tematica", "java"))
            .andExpect(status().isOk())             
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].url").value("u1"));
    }   
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemsController).build();
    }

}
