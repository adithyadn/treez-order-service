package io.treez.orderservice.controller;

import io.treez.orderservice.model.Order;
import io.treez.orderservice.repository.OrderItemRepository;
import io.treez.orderservice.repository.OrderRepository;
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

import static io.treez.orderservice.util.OrderServiceTestUtil.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;


    @Autowired
    private JacksonTester<Order> json;

    @Test
    public void testListAllOrdersWhenNoData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testFindOrderByIdWhenNotFound() throws Exception {
        BDDMockito.given(orderRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockOrder()));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;

    }

    @Test
    public void testFindOrderById() throws Exception {
        BDDMockito.given(orderRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockOrder()));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus")
                        .value("CONFIRMED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderTotal")
                        .value(25.00))
        ;

    }

    @Test
    public void testCreateOrderWhenNoProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/")
                .content(json.write(getMockOrder()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @Test
    public void testCreateOrder() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/")
                .content(json.write(getMockOrder()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        ;
    }

    @Test
    public void testCreateOrderWhenInsufficientInventory() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/")
                .content(json.write(getMockInsufficientOrder()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isConflict())
        ;
    }

    @Test
    public void testUpdateOrder() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));
        BDDMockito.given(orderRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockOrder()));


        mockMvc.perform(MockMvcRequestBuilders.put("/orders/1")
                .content(json.write(getMockUpdatedOrder()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testUpdateOrderWhenNoOrder() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/orders/1")
                .content(json.write(getMockUpdatedOrder()).getJson())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @Test
    public void testDeleteProductWhenInvalidID() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));

        BDDMockito.given(orderRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockOrder()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/inventories/2")
        ).andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @Test
    public void testDeleteProduct() throws Exception {
        BDDMockito.given(productRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockProduct()));
        BDDMockito.given(orderRepository.findById(1L))
                .willReturn(java.util.Optional.of(getMockOrder()));

        mockMvc.perform(MockMvcRequestBuilders.delete("/inventories/1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

}
