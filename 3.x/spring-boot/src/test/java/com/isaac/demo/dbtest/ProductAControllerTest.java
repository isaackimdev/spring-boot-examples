package com.isaac.demo.dbtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestProductAController.class)
class ProductAControllerTest {
    @Autowired private MockMvc mockMvc; // controller url 호출
    @MockBean private TestProductService productService;

    @Test
    void getProductTest() throws Exception {
        given(productService.getProduct("book")).willReturn(TestProductDto.builder().name("book").price(10000).build());

        String name = "book";

        mockMvc.perform(
                get("/products/"+name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("book"))
                .andExpect(jsonPath("$.price").value(10000))
                .andDo(print());

        verify(productService).getProduct("book");
    }

    @Test
    void postProductTest() throws Exception {
        given(productService.saveProduct("book", 10000)).willReturn(TestProductDto.builder().name("book").price(10000).build());

        TestProductDto productDto = TestProductDto.builder().name("book").price(10000).build();

        String testJson = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(post("/products").content(testJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("book"))
                .andExpect(jsonPath("$.price").value(10000))
                .andDo(print());

        verify(productService).saveProduct("book", 10000);
    }
}