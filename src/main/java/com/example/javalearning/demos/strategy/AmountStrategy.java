package com.example.javalearning.demos.strategy;

import com.example.javalearning.demos.Order;

/**
 * 订单金额处理策略接口
 */
public interface AmountStrategy {
    
    /**
     * 判断是否可以处理该金额范围
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
