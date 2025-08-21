package com.example.javalearning.demos.designpattern;

// 客户类
class Customer {
    private String level; // 客户等级: NORMAL, VIP
    
    public Customer(String level) {
        this.level = level;
    }
    
    public String getLevel() {
        return level;
    }
}

// 订单类
public class Order {
    private String id;
    private String type; // 订单类型: NORMAL, URGENT, DISCOUNT
    private double amount;
    private Customer customer;
    
    public Order(String id, String type, double amount, Customer customer) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.customer = customer;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Customer getCustomer() {
        return customer;
    }
}