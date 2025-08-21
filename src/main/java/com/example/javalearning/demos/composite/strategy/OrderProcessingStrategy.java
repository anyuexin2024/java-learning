package com.example.javalearning.demos.composite.strategy;

import com.example.javalearning.demos.Order;

/**
 * 订单处理策略接口
 * 用于组合模式中的具体处理逻辑
 */
public interface OrderProcessingStrategy {
    
    /**
     * 判断是否可以处理该订单
     */
    boolean canHandle(Order order);
    
    /**
     * 处理订单
     */
    void process(Order order);
    
    /**
     * 获取策略名称
     */
    String getStrategyName();
}
