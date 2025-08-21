package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.OrderTypeStrategy;

/**
 * 普通订单类型策略实现
 */
public class NormalOrderStrategy implements OrderTypeStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 策略模式处理普通订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: 普通订单");
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        
        // 根据金额和客户等级进一步处理
        if (order.getAmount() > 1000) {
            if (order.getCustomer().getLevel().equals("VIP")) {
                processVipLargeOrder(order);
            } else {
                processNormalLargeOrder(order);
            }
        } else {
            if (order.getCustomer().getLevel().equals("VIP")) {
                processVipSmallOrder(order);
            } else {
                processNormalSmallOrder(order);
            }
        }
    }
    
    @Override
    public String getStrategyName() {
        return "普通订单策略";
    }
    
    private void processVipLargeOrder(Order order) {
        System.out.println("  - 处理VIP大额订单");
        System.out.println("  - 享受VIP大额订单优惠");
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("  - 处理普通大额订单");
        System.out.println("  - 标准大额订单处理");
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("  - 处理VIP小额订单");
        System.out.println("  - 享受VIP小额订单优惠");
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("  - 处理普通小额订单");
        System.out.println("  - 标准小额订单处理");
    }
}
