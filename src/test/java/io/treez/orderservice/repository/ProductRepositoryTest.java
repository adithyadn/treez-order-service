package io.treez.orderservice.repository;

import io.treez.orderservice.util.OrderServiceTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCanary() {
        assertTrue(true);
    }

    @Test
    public void testH2Integration() {

        productRepository.save(OrderServiceTestUtil.getMockProduct());

        assertEquals(productRepository.findById(1L).get().getProductName(), OrderServiceTestUtil.getMockProduct().getProductName());
        assertEquals(productRepository.findAll().size(), 1);
    }
}

