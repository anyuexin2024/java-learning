package com.example.javalearning.demos.strategy;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.impl.DiscountOrderStrategy;
import com.example.javalearning.demos.strategy.impl.NormalOrderStrategy;
import com.example.javalearning.demos.strategy.impl.UrgentOrderStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 策略模式订单处理器
 * 使用策略模式消除多层嵌套if-else
 */
public class StrategyOrderProcessor {
    
    private final List<OrderTypeStrategy> strategies;
    
    public StrategyOrderProcessor() {
        this.strategies = new ArrayList<>();
        // 初始化所有策略
        strategies.add(new NormalOrderStrategy());
        strategies.add(new UrgentOrderStrategy());
        strategies.add(new DiscountOrderStrategy());
    }
    
    /**
     * 处理订单 - 策略模式
     */
    public void processOrder(Order order) {
        System.out.println("=== 策略模式处理订单: " + order.getId() + " ===");
        
        // 查找并执行合适的策略
        OrderTypeStrategy strategy = findStrategy(order);
        if (strategy != null) {
            strategy.process(order);
        } else {
            System.out.println("  - 没有找到合适的处理策略");
        }
    }
    
    /**
     * 查找合适的策略
     */
    private OrderTypeStrategy findStrategy(Order order) {
        return strategies.stream()
                .filter(strategy -> strategy.canHandle(order))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 添加新的策略
     */
    public void addStrategy(OrderTypeStrategy strategy) {
        strategies.add(strategy);
    }
    
    /**
     * 移除策略
     */
    public void removeStrategy(OrderTypeStrategy strategy) {
        strategies.remove(strategy);
    }
}
