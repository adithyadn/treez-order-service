package io.treez.orderservice.service;

import io.treez.orderservice.enums.OrderStatus;
import io.treez.orderservice.exceptions.InSufficientInventoryException;
import io.treez.orderservice.exceptions.OrderNotFoundException;
import io.treez.orderservice.model.Order;
import io.treez.orderservice.model.OrderItem;
import io.treez.orderservice.model.Product;
import io.treez.orderservice.repository.OrderItemRepository;
import io.treez.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository
            , InventoryService inventoryService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(Order order) {

        //validating and fetching products in orderItems.

        validateAndUpdateProduct(order);

        order.setOrderStatus(OrderStatus.CONFIRMED);

        return orderRepository.save(order);
    }

    public Order updateOrder(long orderId, Order order) {

        Order previousOrder = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        //Replenish the inventory for products in previous order.
        reStockInventory(previousOrder.getOrderItems());

        //remove previous orderItems

        for (OrderItem item : previousOrder.getOrderItems()) {
            System.out.println("DELETIN*********" + item.getOrderItemId());
            item.setOrder(null);
        }
        previousOrder.getOrderItems().clear();

        //Update previous order with new order details.
        previousOrder.setOrderStatus(OrderStatus.MODIFIED);
        previousOrder.setOrderDateTime(order.getOrderDateTime());
        previousOrder.setOrderTotal(order.getOrderTotal());
        previousOrder.setShippingAddress(order.getShippingAddress());
        previousOrder.setShippingType(order.getShippingType());
        previousOrder.setOrderSubTotal(order.getOrderSubTotal());
        previousOrder.setTax(order.getTax());
        previousOrder.getOrderItems().addAll(order.getOrderItems());

        validateAndUpdateProduct(previousOrder);

        return orderRepository.save(previousOrder);

    }

    private Order validateAndUpdateProduct(Order order) {
        for (OrderItem item : order.getOrderItems()) {

            Product product = inventoryService.findProductById(item.getProduct().getProductId());
            if (item.getQuantity() > product.getQuantity()) {
                throw new InSufficientInventoryException();
            }

            //Deduct the quantity from the product in the inventory
            product.setQuantity(product.getQuantity() - item.getQuantity());
            System.out.println("Order here is " + order);
            System.out.println("Order ID here is " + order.getOrderId());
            item.setOrder(order);
            item.setProduct(product);
        }

        return order;
    }

    private void reStockInventory(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            Product product = inventoryService.findProductById(item.getProduct().getProductId());
            product.setQuantity(product.getQuantity() + item.getQuantity());
            inventoryService.updateProduct(product.getProductId(), product);
        }
    }

    public Order cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setOrderStatus(OrderStatus.CANCELLED);

        reStockInventory(order.getOrderItems());

        return orderRepository.save(order);
    }
}
