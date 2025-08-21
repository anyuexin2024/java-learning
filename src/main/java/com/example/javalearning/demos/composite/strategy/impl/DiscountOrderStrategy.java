package com.example.javalearning.demos.composite.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;

/**
 * 折扣订单处理策略
 * 完全消除if-else的具体策略实现
 */
public class DiscountOrderStrategy implements OrderProcessingStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 优化组合模式处理折扣订单: " + order.getId() + " ===");
        System.out.println("  - 处理器: 折扣订单策略");
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
