package com.example.javalearning.demos;

/**
 * 客户实体类
 */
public class Customer {
    private String id;
    private String name;
    private String level;  // 客户等级：VIP, NORMAL
    
    public Customer(String id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
