package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.AmountStrategy;

/**
 * 小额订单策略实现
 */
public class SmallAmountStrategy implements AmountStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return order.getAmount() <= 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  - 处理小额订单");
        
        // 根据客户等级选择具体处理策略
        if (order.getCustomer().getLevel().equals("VIP")) {
            processVipSmallOrder(order);
        } else {
            processNormalSmallOrder(order);
        }
    }
    
    @Override
    public String getStrategyName() {
        return "小额订单策略";
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("    - 处理VIP小额订单");
        System.out.println("    - 享受VIP小额订单优惠");
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("    - 处理普通小额订单");
        System.out.println("    - 标准小额订单处理");
    }
}
