package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.CustomerLevelStrategy;

/**
 * VIP客户策略实现
 */
public class VipCustomerStrategy implements CustomerLevelStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "VIP".equals(order.getCustomer().getLevel());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  - 处理VIP客户订单");
        
        // 根据订单金额选择具体处理策略
        if (order.getAmount() > 1000) {
            processVipLargeOrder(order);
        } else {
            processVipSmallOrder(order);
        }
    }
    
    @Override
    public String getStrategyName() {
        return "VIP客户策略";
    }
    
    private void processVipLargeOrder(Order order) {
        System.out.println("    - 处理VIP大额订单");
        System.out.println("    - 享受VIP大额订单优惠");
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("    - 处理VIP小额订单");
        System.out.println("    - 享受VIP小额订单优惠");
    }
}
