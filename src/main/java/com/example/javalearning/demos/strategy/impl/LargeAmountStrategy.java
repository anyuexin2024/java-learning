package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.AmountStrategy;

/**
 * 大额订单策略实现
 */
public class LargeAmountStrategy implements AmountStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return order.getAmount() > 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  - 处理大额订单");
        
        // 根据客户等级选择具体处理策略
        if (order.getCustomer().getLevel().equals("VIP")) {
            processVipLargeOrder(order);
        } else {
            processNormalLargeOrder(order);
        }
    }
    
    @Override
    public String getStrategyName() {
        return "大额订单策略";
    }
    
    private void processVipLargeOrder(Order order) {
        System.out.println("    - 处理VIP大额订单");
        System.out.println("    - 享受VIP大额订单优惠");
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("    - 处理普通大额订单");
        System.out.println("    - 标准大额订单处理");
    }
}
