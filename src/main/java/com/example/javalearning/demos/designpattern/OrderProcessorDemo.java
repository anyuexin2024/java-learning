package com.example.javalearning.demos.designpattern;

public class OrderProcessorDemo {
    public static void main(String[] args) {
        // 创建测试订单
        Order normalVipLargeOrder = new Order("001", "NORMAL", 1500, new Customer("VIP"));
        Order normalRegularSmallOrder = new Order("002", "NORMAL", 500, new Customer("NORMAL"));
        Order urgentLargeOrder = new Order("003", "URGENT", 2000, new Customer("NORMAL"));
        Order discountOrder = new Order("004", "DISCOUNT", 800, new Customer("NORMAL"));
        
        System.out.println("========== 传统方式处理订单 ==========");
        TraditionalOrderProcessor traditionalProcessor = new TraditionalOrderProcessor();
        traditionalProcessor.processOrder(normalVipLargeOrder);
        traditionalProcessor.processOrder(normalRegularSmallOrder);
        traditionalProcessor.processOrder(urgentLargeOrder);
        traditionalProcessor.processOrder(discountOrder);
        

        System.out.println("\n========== 责任链模式处理订单 ==========");
        OrderProcessorChain chainProcessor = new OrderProcessorChain();
        chainProcessor.processOrder(normalVipLargeOrder);
        chainProcessor.processOrder(normalRegularSmallOrder);
        chainProcessor.processOrder(urgentLargeOrder);
        chainProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 状态模式处理订单 ==========");
        OrderProcessorState stateProcessor = new OrderProcessorState();
        stateProcessor.processOrder(normalVipLargeOrder);
        stateProcessor.processOrder(normalRegularSmallOrder);
        stateProcessor.processOrder(urgentLargeOrder);
        stateProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 组合模式处理订单 ==========");
        OrderProcessorCompositeDemo compositeProcessor = new OrderProcessorCompositeDemo();
        compositeProcessor.processOrder(normalVipLargeOrder);
        compositeProcessor.processOrder(normalRegularSmallOrder);
        compositeProcessor.processOrder(urgentLargeOrder);
        compositeProcessor.processOrder(discountOrder);
        
        System.out.println("\n========== 各种模式的优势对比 ==========");
        System.out.println("1. 策略模式:");
        System.out.println("   - 优势：将不同的处理逻辑封装成独立的策略类，易于扩展和维护");
        System.out.println("   - 适用：当有多个算法或处理逻辑需要根据条件选择时");
        System.out.println("   - 扩展：添加新策略时无需修改现有代码");
        
        System.out.println("\n2. 责任链模式:");
        System.out.println("   - 优势：将请求的发送者和接收者解耦，可以动态指定处理顺序");
        System.out.println("   - 适用：当多个对象都有机会处理请求，且处理者未知时");
        System.out.println("   - 扩展：可以动态调整责任链的顺序和组成");
        
        System.out.println("\n3. 状态模式:");
        System.out.println("   - 优势：将不同状态的行为封装在独立的类中，避免大量条件判断");
        System.out.println("   - 适用：当对象的行为依赖于其状态，并且状态会动态改变时");
        System.out.println("   - 扩展：添加新状态时无需修改现有状态类");
        
        System.out.println("\n4. 组合模式:");
        System.out.println("   - 优势：将对象组合成树形结构，统一处理单个对象和组合对象");
        System.out.println("   - 适用：处理具有层级结构的复杂条件判断");
        System.out.println("   - 扩展：可以轻松添加新的层级和处理逻辑");
    }
}