package com.example.javalearning.demos.composite;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.composite.impl.DiscountOrderHandler;
import com.example.javalearning.demos.composite.impl.NormalOrderHandler;
import com.example.javalearning.demos.composite.impl.UrgentOrderHandler;

/**
 * 组合模式订单处理器
 * 使用组合模式构建处理器链，消除多层嵌套if-else
 */
public class CompositeOrderProcessor {
    
    private OrderHandler firstHandler;
    
    public CompositeOrderProcessor() {
        // 构建处理器链
        buildHandlerChain();
    }
    
    /**
     * 构建处理器链
     */
    private void buildHandlerChain() {
        // 创建处理器
        NormalOrderHandler normalHandler = new NormalOrderHandler();
        UrgentOrderHandler urgentHandler = new UrgentOrderHandler();
        DiscountOrderHandler discountHandler = new DiscountOrderHandler();
        
        // 设置处理器链
        normalHandler.setNext(urgentHandler);
        urgentHandler.setNext(discountHandler);
        
        // 设置第一个处理器
        this.firstHandler = normalHandler;
    }
    
    /**
     * 处理订单 - 组合模式
     */
    public void processOrder(Order order) {
        System.out.println("=== 组合模式处理订单: " + order.getId() + " ===");
        
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
    public void addHandler(OrderHandler handler) {
        if (firstHandler != null) {
            handler.setNext(firstHandler);
        }
        firstHandler = handler;
    }
    
    /**
     * 在指定处理器后添加新处理器
     */
    public void addHandlerAfter(OrderHandler targetHandler, OrderHandler newHandler) {
        if (targetHandler != null && newHandler != null) {
            OrderHandler next = targetHandler.nextHandler;
            targetHandler.setNext(newHandler);
            newHandler.setNext(next);
        }
    }
}
