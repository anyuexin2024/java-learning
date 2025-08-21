package com.example.javalearning.demos.composite.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.OptimizedOrderHandler;
import com.example.javalearning.demos.composite.strategy.impl.DiscountOrderStrategy;

/**
 * 优化的折扣订单处理器 - 组合模式 + 策略模式
 * 完全消除if-else嵌套
 */
public class OptimizedDiscountOrderHandler extends OptimizedOrderHandler {
    
    @Override
    public boolean canHandle(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    protected void initializeStrategies() {
        // 初始化折扣订单策略
        strategies.add(new DiscountOrderStrategy());
    }
    
    @Override
    public String getHandlerName() {
        return "优化的折扣订单处理器";
    }
}
