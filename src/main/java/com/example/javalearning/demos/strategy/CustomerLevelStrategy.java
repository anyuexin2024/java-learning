package com.example.javalearning.demos.strategy;

import com.example.javalearning.demos.Order;

/**
 * 客户等级处理策略接口
 */
public interface CustomerLevelStrategy {
    
    /**
     * 判断是否可以处理该客户等级
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
