package com.example.javalearning.demos.composite.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.OptimizedOrderHandler;
import com.example.javalearning.demos.composite.strategy.impl.UrgentOrderStrategy;

/**
 * 优化的紧急订单处理器 - 组合模式 + 策略模式
 * 完全消除if-else嵌套
 */
public class OptimizedUrgentOrderHandler extends OptimizedOrderHandler {
    
    @Override
    public boolean canHandle(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    protected void initializeStrategies() {
        // 初始化紧急订单策略
        strategies.add(new UrgentOrderStrategy());
    }
    
    @Override
    public String getHandlerName() {
        return "优化的紧急订单处理器";
    }
}
