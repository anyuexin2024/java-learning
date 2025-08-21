package com.example.javalearning.demos.composite;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.impl.OptimizedDiscountOrderHandler;
import com.example.javalearning.demos.composite.impl.OptimizedNormalOrderHandler;
import com.example.javalearning.demos.composite.impl.OptimizedUrgentOrderHandler;

/**
 * 优化的组合模式订单处理器
 * 组合模式 + 策略模式，完全消除if-else嵌套
 */
public class OptimizedCompositeOrderProcessor {
    
    private OptimizedOrderHandler firstHandler;
    
    public OptimizedCompositeOrderProcessor() {
        // 构建优化的处理器链
        buildOptimizedHandlerChain();
    }
    
    /**
     * 构建优化的处理器链
     */
    private void buildOptimizedHandlerChain() {
        // 创建优化的处理器
        OptimizedNormalOrderHandler normalHandler = new OptimizedNormalOrderHandler();
        OptimizedUrgentOrderHandler urgentHandler = new OptimizedUrgentOrderHandler();
        OptimizedDiscountOrderHandler discountHandler = new OptimizedDiscountOrderHandler();
        
        // 设置处理器链
        normalHandler.setNext(urgentHandler);
        urgentHandler.setNext(discountHandler);
        
        // 设置第一个处理器
        this.firstHandler = normalHandler;
    }
    
    /**
     * 处理订单 - 优化的组合模式
     */
    public void processOrder(Order order) {
        System.out.println("=== 优化的组合模式处理订单: " + order.getId() + " ===");
        
        // 从第一个处理器开始处理
        if (firstHandler != null) {
            firstHandler.handle(order);
        } else {
            System.out.println("  - 没有可用的处理器");
        }
    }
    
    /**
     * 添加新的处理器到链的开头
     */
    public void addHandler(OptimizedOrderHandler handler) {
        if (firstHandler != null) {
            handler.setNext(firstHandler);
        }
        firstHandler = handler;
    }
    
    /**
     * 在指定处理器后添加新处理器
     */
    public void addHandlerAfter(OptimizedOrderHandler targetHandler, OptimizedOrderHandler newHandler) {
        if (targetHandler != null && newHandler != null) {
            OptimizedOrderHandler next = targetHandler.nextHandler;
            targetHandler.setNext(newHandler);
            newHandler.setNext(next);
        }
    }
}
