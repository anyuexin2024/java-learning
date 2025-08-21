package com.example.javalearning.demos.composite.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.OrderHandler;

/**
 * 紧急订单处理器 - 组合模式实现
 */
public class UrgentOrderHandler extends OrderHandler {
    
    @Override
    public boolean canHandle(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    public void handle(Order order) {
        if (canHandle(order)) {
            System.out.println("=== 组合模式处理紧急订单: " + order.getId() + " ===");
            System.out.println("  - 处理器: " + getHandlerName());
            System.out.println("  - 订单类型: 紧急订单");
            System.out.println("  - 客户: " + order.getCustomer().getName());
            System.out.println("  - 金额: " + order.getAmount());
            
            // 根据金额进一步处理
            if (order.getAmount() > 1000) {
                processUrgentLargeOrder(order);
            } else {
                processUrgentSmallOrder(order);
            }
        } else {
            // 不能处理，传递给下一个处理器
            handleNext(order);
        }
    }
    
    @Override
    public String getHandlerName() {
        return "紧急订单处理器";
    }
    
    private void processUrgentLargeOrder(Order order) {
        System.out.println("  - 处理紧急大额订单");
        System.out.println("  - 紧急大额订单优先处理");
    }
    
    private void processUrgentSmallOrder(Order order) {
        System.out.println("  - 处理紧急小额订单");
        System.out.println("  - 紧急小额订单优先处理");
    }
}
