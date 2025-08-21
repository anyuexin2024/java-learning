package com.example.javalearning.demos.composite.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;

/**
 * 紧急订单处理策略
 * 完全消除if-else的具体策略实现
 */
public class UrgentOrderStrategy implements OrderProcessingStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 优化组合模式处理紧急订单: " + order.getId() + " ===");
        System.out.println("  - 处理器: 紧急订单策略");
        System.out.println("  - 订单类型: 紧急订单");
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        
        // 根据金额进一步处理 - 这里可以继续使用策略模式
        if (order.getAmount() > 1000) {
            System.out.println("  - 处理紧急大额订单");
            System.out.println("  - 紧急大额订单优先处理");
        } else {
            System.out.println("  - 处理紧急小额订单");
            System.out.println("  - 紧急小额订单优先处理");
        }
    }
    
    @Override
    public String getStrategyName() {
        return "紧急订单策略";
    }
}
