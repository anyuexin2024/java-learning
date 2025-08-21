package com.example.javalearning.demos.composite.impl;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.OptimizedOrderHandler;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;
import com.example.javalearning.demos.composite.strategy.impl.*;

/**
 * 优化的普通订单处理器 - 组合模式 + 策略模式
 * 完全消除if-else嵌套
 */
public class OptimizedNormalOrderHandler extends OptimizedOrderHandler {
    
    @Override
    public boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType());
    }
    
    @Override
    protected void initializeStrategies() {
        // 初始化所有普通订单相关的策略
        strategies.add(new VipLargeNormalOrderStrategy());
        strategies.add(new NormalLargeNormalOrderStrategy());
        strategies.add(new VipSmallNormalOrderStrategy());
        strategies.add(new NormalSmallNormalOrderStrategy());
    }
    
    @Override
    public String getHandlerName() {
        return "优化的普通订单处理器";
    }
}
