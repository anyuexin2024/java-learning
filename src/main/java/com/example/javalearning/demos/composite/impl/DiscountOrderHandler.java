package com.example.javalearning.demos.composite.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.OrderHandler;

/**
 * 折扣订单处理器 - 组合模式实现
 */
public class DiscountOrderHandler extends OrderHandler {
    
    @Override
    public boolean canHandle(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    public void handle(Order order) {
        if (canHandle(order)) {
            System.out.println("=== 组合模式处理折扣订单: " + order.getId() + " ===");
            System.out.println("  - 处理器: " + getHandlerName());
            System.out.println("  - 订单类型: 折扣订单");
            System.out.println("  - 客户: " + order.getCustomer().getName());
            System.out.println("  - 金额: " + order.getAmount());
            System.out.println("  - 折扣订单特殊处理");
        } else {
            // 不能处理，传递给下一个处理器
            handleNext(order);
        }
    }
    
    @Override
    public String getHandlerName() {
        return "折扣订单处理器";
    }
}
