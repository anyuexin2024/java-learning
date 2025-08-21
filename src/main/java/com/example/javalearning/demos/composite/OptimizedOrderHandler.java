package com.example.javalearning.demos.composite;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.strategy.OrderProcessingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 优化的订单处理器 - 组合模式 + 策略模式
 * 完全消除if-else嵌套
 */
public abstract class OptimizedOrderHandler {
    
    protected OptimizedOrderHandler nextHandler;
    protected List<OrderProcessingStrategy> strategies;
    
    public OptimizedOrderHandler() {
        this.strategies = new ArrayList<>();
        initializeStrategies();
    }
    
    /**
     * 设置下一个处理器
     */
    public void setNext(OptimizedOrderHandler next) {
        this.nextHandler = next;
    }
    
    /**
     * 判断是否可以处理该订单
     */
    public abstract boolean canHandle(Order order);
    
    /**
     * 处理订单 - 使用策略模式消除if-else
     */
    public void handle(Order order) {
        if (canHandle(order)) {
            // 使用策略模式查找并执行合适的策略
            OrderProcessingStrategy strategy = findStrategy(order);
            if (strategy != null) {
                strategy.process(order);
            } else {
                System.out.println("  - 没有找到合适的处理策略");
            }
        } else {
            // 不能处理，传递给下一个处理器
            handleNext(order);
        }
    }
    
    /**
     * 处理下一个处理器
     */
    protected void handleNext(Order order) {
        if (nextHandler != null) {
            nextHandler.handle(order);
        } else {
            System.out.println("  - 没有找到合适的处理器");
        }
    }
    
    /**
     * 查找合适的策略
     */
    private OrderProcessingStrategy findStrategy(Order order) {
        return strategies.stream()
                .filter(strategy -> strategy.canHandle(order))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 初始化策略 - 子类实现
     */
    protected abstract void initializeStrategies();
    
    /**
     * 获取处理器名称
     */
    public abstract String getHandlerName();
}
