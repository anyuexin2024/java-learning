package com.example.javalearning.demos;

import com.example.javalearning.demos.composite.CompositeOrderProcessor;
import com.example.javalearning.demos.composite.OptimizedCompositeOrderProcessor;
import com.example.javalearning.demos.strategy.StrategyOrderProcessor;
import com.example.javalearning.demos.strategy.AdvancedStrategyOrderProcessor;
import com.example.javalearning.demos.strategy.UltimateStrategyOrderProcessor;
import com.example.javalearning.demos.TraditionalOrderProcessor;

/**
 * 订单处理演示类
 * 对比传统方式、策略模式和组合模式
 */
public class OrderProcessingDemo {
    
    public static void main(String[] args) {
        System.out.println("🚀 订单处理设计模式对比演示");
        System.out.println("=====================================\n");
        
        // 创建测试数据
        Customer vipCustomer = new Customer("C001", "张三", "VIP");
        Customer normalCustomer = new Customer("C002", "李四", "NORMAL");
        
        Order[] orders = {
            new Order("O001", "NORMAL", 1500, vipCustomer),      // VIP大额普通订单
            new Order("O002", "NORMAL", 800, normalCustomer),     // 普通小额普通订单
            new Order("O003", "URGENT", 2000, vipCustomer),      // VIP大额紧急订单
            new Order("O004", "URGENT", 500, normalCustomer),    // 普通小额紧急订单
            new Order("O005", "DISCOUNT", 1200, vipCustomer),    // VIP折扣订单
        };
        
        // 1. 传统方式处理
        System.out.println("📋 1. 传统方式 - 多层嵌套if-else");
        System.out.println("-------------------------------------");
        TraditionalOrderProcessor traditionalProcessor = new TraditionalOrderProcessor();
        
        for (Order order : orders) {
            traditionalProcessor.processOrder(order);
            System.out.println();
        }
        
        // 2. 基础策略模式处理
        System.out.println("📋 2. 基础策略模式 - 消除订单类型嵌套");
        System.out.println("-------------------------------------");
        StrategyOrderProcessor strategyProcessor = new StrategyOrderProcessor();
        
        for (Order order : orders) {
            strategyProcessor.processOrder(order);
            System.out.println();
        }
        
        // 3. 高级策略模式处理
        System.out.println("📋 3. 高级策略模式 - 多重策略组合");
        System.out.println("-------------------------------------");
        AdvancedStrategyOrderProcessor advancedStrategyProcessor = new AdvancedStrategyOrderProcessor();
        
        for (Order order : orders) {
            advancedStrategyProcessor.processOrder(order);
            System.out.println();
        }
        
        // 4. 终极策略模式处理
        System.out.println("📋 4. 终极策略模式 - 完全消除所有if-else");
        System.out.println("-------------------------------------");
        UltimateStrategyOrderProcessor ultimateStrategyProcessor = new UltimateStrategyOrderProcessor();
        
        for (Order order : orders) {
            ultimateStrategyProcessor.processOrder(order);
            System.out.println();
        }
        
        // 5. 原始组合模式处理（仍有if-else）
        System.out.println("📋 5. 原始组合模式 - 处理器链（仍有if-else）");
        System.out.println("-------------------------------------");
        CompositeOrderProcessor compositeProcessor = new CompositeOrderProcessor();
        
        for (Order order : orders) {
            compositeProcessor.processOrder(order);
            System.out.println();
        }
        
        // 6. 优化的组合模式处理（完全消除if-else）
        System.out.println("📋 6. 优化的组合模式 - 组合模式+策略模式（完全消除if-else）");
        System.out.println("-------------------------------------");
        OptimizedCompositeOrderProcessor optimizedCompositeProcessor = new OptimizedCompositeOrderProcessor();
        
        for (Order order : orders) {
            optimizedCompositeProcessor.processOrder(order);
            System.out.println();
        }
        
        // 总结对比
        System.out.println("📊 设计模式对比总结");
        System.out.println("=====================================");
        System.out.println("传统方式:");
        System.out.println("  ❌ 多层嵌套if-else，代码难以维护");
        System.out.println("  ❌ 新增订单类型需要修改现有代码");
        System.out.println("  ❌ 违反开闭原则");
        System.out.println("  ❌ 代码可读性差");
        System.out.println();
        
        System.out.println("基础策略模式:");
        System.out.println("  ✅ 消除订单类型嵌套，代码结构清晰");
        System.out.println("  ✅ 新增策略只需添加新类，符合开闭原则");
        System.out.println("  ✅ 策略可以动态切换");
        System.out.println("  ✅ 每个策略职责单一");
        System.out.println("  ⚠️  仍有金额和客户等级的if-else嵌套");
        System.out.println();
        
        System.out.println("高级策略模式:");
        System.out.println("  ✅ 多重策略组合，更加灵活");
        System.out.println("  ✅ 策略可以独立扩展");
        System.out.println("  ✅ 部分消除if-else嵌套");
        System.out.println("  ⚠️  仍有部分if-else逻辑");
        System.out.println();
        
        System.out.println("终极策略模式:");
        System.out.println("  ✅ 完全消除所有if-else嵌套");
        System.out.println("  ✅ 每个策略处理一个具体业务场景");
        System.out.println("  ✅ 完全符合开闭原则");
        System.out.println("  ✅ 代码结构最清晰");
        System.out.println();
        
        System.out.println("原始组合模式:");
        System.out.println("  ✅ 构建处理器链，线性处理逻辑");
        System.out.println("  ✅ 处理器可以动态组合");
        System.out.println("  ✅ 支持复杂的处理流程");
        System.out.println("  ❌ 仍有大量if-else嵌套");
        System.out.println("  ❌ 违反单一职责原则");
        System.out.println();
        
        System.out.println("优化的组合模式:");
        System.out.println("  ✅ 组合模式+策略模式，双重优势");
        System.out.println("  ✅ 完全消除if-else嵌套");
        System.out.println("  ✅ 处理器链 + 策略选择");
        System.out.println("  ✅ 完全符合开闭原则");
        System.out.println("  ✅ 代码结构最清晰");
        System.out.println();
        
        System.out.println("🎯 推荐使用优化的组合模式来完全替代多层嵌套if-else！");
        System.out.println("💡 组合模式+策略模式 = 双重设计模式优势！");
        System.out.println("🚀 从传统方式到优化组合模式，代码质量逐步提升！");
        System.out.println("🌟 设计模式组合使用，效果更佳！");
    }
}
