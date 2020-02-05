package io.treez.orderservice.controller;

import io.treez.orderservice.model.Product;
import io.treez.orderservice.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.treez.orderservice.util.OrderServiceTestUtil.getMockProduct;
import static io.treez.orderservice.util.OrderServiceTestUtil.getMockUpdatedProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class InventoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private JacksonTester<Product> json;

    @Test
    public void testListAllProductsWhenNoData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inventories/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testFindProductByIdWhenNotFound() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.get("/inventories/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;

    }

    @Test
    public void testFindProductById() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.get("/inventories/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Test"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/inventories/")
                .content(json.write(getMockProduct()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        ;
    }

    @Test
    public void testUpdateProduct() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.put("/inventories/1")
                .content(json.write(getMockUpdatedProduct()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testUpdateProductWhenInvalidID() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.put("/inventories/2")
                .content(json.write(getMockUpdatedProduct()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @Test
    public void testDeleteProductWhenInvalidID() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/inventories/2")
                .content(json.write(getMockUpdatedProduct()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @Test
    public void testDeleteProduct() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/inventories/1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

}
