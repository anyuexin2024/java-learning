package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.OrderTypeStrategy;

/**
 * VIP大额订单策略实现
 * 完全消除if-else的终极策略
 */
public class VipLargeOrderStrategy implements OrderTypeStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType()) 
            && order.getAmount() > 1000 
            && "VIP".equals(order.getCustomer().getLevel());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 终极策略模式处理VIP大额普通订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: 普通订单");
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (VIP)");
        System.out.println("  - 金额: " + order.getAmount() + " (大额)");
        System.out.println("  - 处理VIP大额订单");
        System.out.println("  - 享受VIP大额订单优惠");
    }
    
    @Override
    public String getStrategyName() {
        return "VIP大额普通订单策略";
    }
}
