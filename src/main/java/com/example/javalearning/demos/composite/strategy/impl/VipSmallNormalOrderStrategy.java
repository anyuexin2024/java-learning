package com.example.javalearning.demos.composite.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;

/**
 * VIP小额普通订单处理策略
 * 完全消除if-else的具体策略实现
 */
public class VipSmallNormalOrderStrategy implements OrderProcessingStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType()) 
            && order.getAmount() <= 1000 
            && "VIP".equals(order.getCustomer().getLevel());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 优化组合模式处理VIP小额普通订单: " + order.getId() + " ===");
        System.out.println("  - 处理器: VIP小额普通订单策略");
        System.out.println("  - 订单类型: 普通订单");
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (VIP)");
        System.out.println("  - 金额: " + order.getAmount() + " (小额)");
        System.out.println("  - 处理VIP小额订单");
        System.out.println("  - 享受VIP小额订单优惠");
    }
    
    @Override
    public String getStrategyName() {
        return "VIP小额普通订单策略";
    }
}
