package com.example.javalearning.demos.strategy.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.CustomerLevelStrategy;

/**
 * 普通客户策略实现
 */
public class NormalCustomerStrategy implements CustomerLevelStrategy {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getCustomer().getLevel());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  - 处理普通客户订单");
        
        // 根据订单金额选择具体处理策略
        if (order.getAmount() > 1000) {
            processNormalLargeOrder(order);
        } else {
            processNormalSmallOrder(order);
        }
    }
    
    @Override
    public String getStrategyName() {
        return "普通客户策略";
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("    - 处理普通大额订单");
        System.out.println("    - 标准大额订单处理");
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("    - 处理普通小额订单");
        System.out.println("    - 标准小额订单处理");
    }
}
