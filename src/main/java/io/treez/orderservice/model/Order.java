package io.treez.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.treez.orderservice.enums.OrderStatus;
import io.treez.orderservice.enums.ShippingType;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @Column(nullable = false, name = "order_ts")
    private Instant orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_type")
    private ShippingType shippingType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "order_sub_total")
    private double orderSubtotal;

    @Column(name = "tax")
    private double tax;

    @Column(name = "order_total")
    private double orderTotal;

    @Transient
    @JsonInclude
    private List<OrderItem> orderItems;

    @Column(name = "shipping_address")
    private String shippingAddress;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Instant getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Instant orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(double orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
