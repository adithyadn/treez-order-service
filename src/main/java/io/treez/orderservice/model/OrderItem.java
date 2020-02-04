package io.treez.orderservice.model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long orderItemID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_price")
    private double itemPrice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "order_id")
    private Order order;

    public long getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(long orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
