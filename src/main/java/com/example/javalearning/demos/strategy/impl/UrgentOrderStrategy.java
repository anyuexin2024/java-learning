package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.OrderTypeStrategy;

/**
 * 紧急订单类型策略实现
 */
public class UrgentOrderStrategy implements OrderTypeStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 策略模式处理紧急订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: 紧急订单");
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        
        // 根据金额进一步处理
        if (order.getAmount() > 1000) {
            processUrgentLargeOrder(order);
        } else {
            processUrgentSmallOrder(order);
        }
    }
    
    @Override
    public String getStrategyName() {
        return "紧急订单策略";
    }
    
    private void processUrgentLargeOrder(Order order) {
        System.out.println("  - 处理紧急大额订单");
        System.out.println("  - 紧急大额订单优先处理");
    }
    
    private void processUrgentSmallOrder(Order order) {
        System.out.println("  - 处理紧急小额订单");
        System.out.println("  - 紧急小额订单优先处理");
    }
}
