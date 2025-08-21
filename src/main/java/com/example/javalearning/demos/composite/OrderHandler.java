package com.example.javalearning.demos.composite;

import com.example.javalearning.demos.Order;

/**
 * 订单处理器抽象类 - 组合模式的核心
 */
public abstract class OrderHandler {
    
    protected OrderHandler nextHandler;
    
    /**
     * 设置下一个处理器
     */
    public void setNext(OrderHandler next) {
        this.nextHandler = next;
    }
    
    /**
     * 判断是否可以处理该订单
     */
    public abstract boolean canHandle(Order order);
    
    /**
     * 处理订单
     */
    public abstract void handle(Order order);
    
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
     * 获取处理器名称
     */
    public abstract String getHandlerName();
}
