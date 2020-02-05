package io.treez.orderservice.util;

import io.treez.orderservice.enums.OrderStatus;
import io.treez.orderservice.enums.ShippingType;
import io.treez.orderservice.model.Order;
import io.treez.orderservice.model.OrderItem;
import io.treez.orderservice.model.Product;

import java.util.Collections;
import java.util.List;

public class OrderServiceTestUtil {
    public static Product getMockProduct() {
        Product product = new Product();
        product.setProductId(1L);
        product.setDescription("Test Product");
        product.setPrice(25.50);
        product.setQuantity(100L);
        product.setProductName("Test");
        return product;
    }

    public static Product getMockUpdatedProduct() {
        Product product = new Product();
        product.setProductId(1L);
        product.setDescription("Test Product");
        product.setPrice(50.00);
        product.setQuantity(100L);
        product.setProductName("Updated Name");
        return product;
    }

    public static Order getMockOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setTax(0.00);
        order.setOrderSubTotal(25.00);
        order.setOrderTotal(25.00);
        order.setShippingType(ShippingType.GROUND);
        order.setShippingAddress("Address");
        order.setOrderItems(getMockOrderItems(order, 1));
        return order;
    }

    public static Order getMockUpdatedOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setTax(0.00);
        order.setOrderSubTotal(25.00);
        order.setOrderTotal(25.00);
        order.setShippingType(ShippingType.OVERNIGHT);
        order.setShippingAddress("Address");
        order.setOrderItems(getMockOrderItems(order, 1));
        return order;
    }

    public static Order getMockInsufficientOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setTax(0.00);
        order.setOrderSubTotal(25.00);
        order.setOrderTotal(25.00);
        order.setShippingType(ShippingType.GROUND);
        order.setShippingAddress("Address");
        order.setOrderItems(getMockOrderItems(order, 200));
        return order;
    }

    private static List<OrderItem> getMockOrderItems(Order order, int quantity) {
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(getMockProduct());
        item.setItemPrice(25.00);
        item.setOrderItemId(1L);
        item.setQuantity(quantity);
        return Collections.singletonList(item);
    }
}
