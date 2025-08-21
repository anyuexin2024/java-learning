package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.OrderTypeStrategy;

/**
 * 折扣订单类型策略实现
 */
public class DiscountOrderStrategy implements OrderTypeStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 策略模式处理折扣订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: 折扣订单");
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 折扣订单特殊处理");
    }
    
    @Override
    public String getStrategyName() {
        return "折扣订单策略";
    }
}
