package com.example.javalearning.demos.strategy;

import com.example.javalearning.demos.Order;
import com.example.javalearning.demos.strategy.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 高级策略模式订单处理器
 * 使用多重策略组合，完全消除if-else嵌套
 */
public class AdvancedStrategyOrderProcessor {
    
    private final List<OrderTypeStrategy> orderTypeStrategies;
    private final List<AmountStrategy> amountStrategies;
    private final List<CustomerLevelStrategy> customerLevelStrategies;
    
    public AdvancedStrategyOrderProcessor() {
        this.orderTypeStrategies = new ArrayList<>();
        this.amountStrategies = new ArrayList<>();
        this.customerLevelStrategies = new ArrayList<>();
        
        // 初始化订单类型策略
        orderTypeStrategies.add(new NormalOrderStrategy());
        orderTypeStrategies.add(new UrgentOrderStrategy());
        orderTypeStrategies.add(new DiscountOrderStrategy());
        
        // 初始化金额策略
        amountStrategies.add(new LargeAmountStrategy());
        amountStrategies.add(new SmallAmountStrategy());
        
        // 初始化客户等级策略
        customerLevelStrategies.add(new VipCustomerStrategy());
        customerLevelStrategies.add(new NormalCustomerStrategy());
    }
    
    /**
     * 处理订单 - 完全消除if-else嵌套
     */
    public void processOrder(Order order) {
        System.out.println("=== 高级策略模式处理订单: " + order.getId() + " ===");
        System.out.println("  - 订单类型: " + order.getType());
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        
        // 1. 首先应用订单类型策略
        OrderTypeStrategy orderTypeStrategy = findOrderTypeStrategy(order);
        if (orderTypeStrategy != null) {
            orderTypeStrategy.process(order);
        }
        
        // 2. 然后应用金额策略
        AmountStrategy amountStrategy = findAmountStrategy(order);
        if (amountStrategy != null) {
            amountStrategy.process(order);
        }
        
        // 3. 最后应用客户等级策略
        CustomerLevelStrategy customerLevelStrategy = findCustomerLevelStrategy(order);
        if (customerLevelStrategy != null) {
            customerLevelStrategy.process(order);
        }
    }
    
    /**
     * 查找订单类型策略
     */
    private OrderTypeStrategy findOrderTypeStrategy(Order order) {
        return orderTypeStrategies.stream()
                .filter(strategy -> strategy.canHandle(order))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 查找金额策略
     */
    private AmountStrategy findAmountStrategy(Order order) {
        return amountStrategies.stream()
                .filter(strategy -> strategy.canHandle(order))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 查找客户等级策略
     */
    private CustomerLevelStrategy findCustomerLevelStrategy(Order order) {
        return customerLevelStrategies.stream()
                .filter(strategy -> strategy.canHandle(order))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 添加新的订单类型策略
     */
    public void addOrderTypeStrategy(OrderTypeStrategy strategy) {
        orderTypeStrategies.add(strategy);
    }
    
    /**
     * 添加新的金额策略
     */
    public void addAmountStrategy(AmountStrategy strategy) {
        amountStrategies.add(strategy);
    }
    
    /**
     * 添加新的客户等级策略
     */
    public void addCustomerLevelStrategy(CustomerLevelStrategy strategy) {
        customerLevelStrategies.add(strategy);
    }
}
