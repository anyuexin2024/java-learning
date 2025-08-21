package com.example.javalearning.demos;

/**
 * 订单实体类
 */
public class Order {
    private String id;
    private String type;        // 订单类型：NORMAL, URGENT, DISCOUNT
    private double amount;      // 订单金额
    private Customer customer;  // 客户信息
    
    public Order(String id, String type, double amount, Customer customer) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.customer = customer;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", customer=" + customer +
                '}';
    }
}
