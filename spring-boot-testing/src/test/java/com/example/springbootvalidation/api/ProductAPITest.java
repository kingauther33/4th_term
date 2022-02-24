package com.example.springbootvalidation.api;

import com.example.springbootvalidation.entity.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @BeforeEach
    void setUp() {
        gson = new Gson();
    }

    @Test
    void create() throws Exception {
        Product product = new Product();
        product.setId(18l);
        product.setName("Product 17");
        product.setDescription("Product 17");
        product.setPrice(new BigDecimal(100));

        String jsonData = gson.toJson(product);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(jsonData));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void testFindAll() throws Exception {
        Product product = new Product();
        product.setId(18l);
        product.setName("Product 17");
        product.setDescription("Product 17");
        product.setPrice(new BigDecimal(100));

        String jsonData = gson.toJson(product);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(jsonData));
        resultActions.andExpect(status().isCreated());

        product.setId(19l);
        product.setName("Product 18");
        product.setDescription("Product 18");
        product.setPrice(new BigDecimal(100));

        jsonData = gson.toJson(product);
        resultActions = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(jsonData));
        resultActions.andExpect(status().isCreated());

        product.setId(20l);
        product.setName("Product 20");
        product.setDescription("Product 20");
        product.setPrice(new BigDecimal(100));

        jsonData = gson.toJson(product);
        resultActions = mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(jsonData));
        resultActions.andExpect(status().isCreated());

        ResultActions resultActionGetList = mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON));
        resultActionGetList.andExpect(status().isOk());

        String result = resultActionGetList.andReturn().getResponse().getContentAsString();
        Type listType = new TypeToken<List<Product>>(){}.getType();
        List<Product> list = gson.fromJson(result, listType);
        assertThat(list.size()).isEqualTo(3);
    }
}