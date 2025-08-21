package com.example.javalearning.demos.designpattern;

import org.junit.jupiter.api.Test;

public class OrderProcessorTest {
    
    @Test
    public void testAllOrderProcessors() {
        // 创建测试订单
        Order normalVipLargeOrder = new Order("001", "NORMAL", 1500, new Customer("VIP"));
        Order normalRegularSmallOrder = new Order("002", "NORMAL", 500, new Customer("NORMAL"));
        Order urgentLargeOrder = new Order("003", "URGENT", 2000, new Customer("NORMAL"));
        Order discountOrder = new Order("004", "DISCOUNT", 800, new Customer("NORMAL"));
        
        System.out.println("========== 单元测试: 传统方式处理订单 ==========");
        TraditionalOrderProcessor traditionalProcessor = new TraditionalOrderProcessor();
        traditionalProcessor.processOrder(normalVipLargeOrder);
        traditionalProcessor.processOrder(normalRegularSmallOrder);
        traditionalProcessor.processOrder(urgentLargeOrder);
        traditionalProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 单元测试: 策略模式处理订单 ==========");
        LingMaOrderProcessorStrategy strategyProcessor = new LingMaOrderProcessorStrategy();
        strategyProcessor.processOrder(normalVipLargeOrder);
        strategyProcessor.processOrder(normalRegularSmallOrder);
        strategyProcessor.processOrder(urgentLargeOrder);
        strategyProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 单元测试: 责任链模式处理订单 ==========");
        OrderProcessorChain chainProcessor = new OrderProcessorChain();
        chainProcessor.processOrder(normalVipLargeOrder);
        chainProcessor.processOrder(normalRegularSmallOrder);
        chainProcessor.processOrder(urgentLargeOrder);
        chainProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 单元测试: 状态模式处理订单 ==========");
        OrderProcessorState stateProcessor = new OrderProcessorState();
        stateProcessor.processOrder(normalVipLargeOrder);
        stateProcessor.processOrder(normalRegularSmallOrder);
        stateProcessor.processOrder(urgentLargeOrder);
        stateProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 单元测试: 组合模式处理订单 ==========");
        OrderProcessorCompositeDemo compositeProcessor = new OrderProcessorCompositeDemo();
        compositeProcessor.processOrder(normalVipLargeOrder);
        compositeProcessor.processOrder(normalRegularSmallOrder);
        compositeProcessor.processOrder(urgentLargeOrder);
        compositeProcessor.processOrder(discountOrder);
    }
}