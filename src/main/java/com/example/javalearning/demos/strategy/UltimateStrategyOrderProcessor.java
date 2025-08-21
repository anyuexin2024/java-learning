package com.example.javalearning.demos.strategy;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 终极策略模式订单处理器
 * 完全消除所有if-else嵌套，每个策略处理一个具体的业务场景
 */
public class UltimateStrategyOrderProcessor {
    
    private final List<OrderTypeStrategy> strategies;
    
    public UltimateStrategyOrderProcessor() {
        this.strategies = new ArrayList<>();
        
        // 初始化所有具体策略 - 每个策略处理一个具体的业务场景
        strategies.add(new VipLargeOrderStrategy());      // VIP大额普通订单
        strategies.add(new NormalLargeOrderStrategy());   // 普通大额普通订单
        strategies.add(new VipSmallOrderStrategy());      // VIP小额普通订单
        strategies.add(new NormalSmallOrderStrategy());   // 普通小额普通订单
        strategies.add(new UrgentOrderStrategy());        // 紧急订单
        strategies.add(new DiscountOrderStrategy());      // 折扣订单
    }
    
    /**
     * 处理订单 - 完全消除所有if-else嵌套
     */
    public void processOrder(Order order) {
        System.out.println("=== 终极策略模式处理订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: " + order.getType());
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        
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
    
    /**
     * 获取所有策略
     */
    public List<OrderTypeStrategy> getStrategies() {
        return new ArrayList<>(strategies);
    }
}
