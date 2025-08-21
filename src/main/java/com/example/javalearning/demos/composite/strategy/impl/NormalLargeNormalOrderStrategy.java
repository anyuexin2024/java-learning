package com.example.javalearning.demos.composite.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;

/**
 * 普通大额普通订单处理策略
 * 完全消除if-else的具体策略实现
 */
public class NormalLargeNormalOrderStrategy implements OrderProcessingStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType()) 
            && order.getAmount() > 1000 
            && "NORMAL".equals(order.getCustomer().getLevel());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 优化组合模式处理普通大额普通订单: " + order.getId() + " ===");
        System.out.println("  - 处理器: 普通大额普通订单策略");
        System.out.println("  - 订单类型: 普通订单");
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (普通)");
        System.out.println("  - 金额: " + order.getAmount() + " (大额)");
        System.out.println("  - 处理普通大额订单");
        System.out.println("  - 标准大额订单处理");
    }
    
    @Override
    public String getStrategyName() {
        return "普通大额普通订单策略";
    }
}
