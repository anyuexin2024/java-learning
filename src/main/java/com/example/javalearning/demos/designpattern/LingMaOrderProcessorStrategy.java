package com.example.javalearning.demos.designpattern;

// 订单处理策略接口
interface LingMaOrderProcessorStrategy {
    boolean canProcess(Order order);
    void processOrder(Order order);
}

// 普通订单处理策略
class NormalLingMaOrderProcessor implements LingMaOrderProcessorStrategy {
    @Override
    public boolean canProcess(Order order) {
        return "NORMAL".equals(order.getType());
    }
    
    @Override
    public void processOrder(Order order) {
        System.out.println("处理普通订单: " + order.getId());
        if (order.getAmount() > 1000) {
            if ("VIP".equals(order.getCustomer().getLevel())) {
                processVipLargeOrder(order);
            } else {
                processNormalLargeOrder(order);
            }
        } else {
            if ("VIP".equals(order.getCustomer().getLevel())) {
                processVipSmallOrder(order);
            } else {
                processNormalSmallOrder(order);
            }
        }
    }
    
    private void processVipLargeOrder(Order order) {
        System.out.println("处理VIP大额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("处理普通大额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("处理VIP小额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("处理普通小额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
}

// 紧急订单处理策略
class UrgentLingMaOrderProcessor implements LingMaOrderProcessorStrategy {
    @Override
    public boolean canProcess(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    public void processOrder(Order order) {
        System.out.println("处理紧急订单: " + order.getId());
        if (order.getAmount() > 1000) {
            processUrgentLargeOrder(order);
        } else {
            processUrgentSmallOrder(order);
        }
    }
    
    private void processUrgentLargeOrder(Order order) {
        System.out.println("处理紧急大额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
    
    private void processUrgentSmallOrder(Order order) {
        System.out.println("处理紧急小额订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
}

// 折扣订单处理策略
class DiscountLingMaOrderProcessor implements LingMaOrderProcessorStrategy {
    @Override
    public boolean canProcess(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    public void processOrder(Order order) {
        System.out.println("处理折扣订单: " + order.getId() + ", 金额: " + order.getAmount());
        // 具体处理逻辑
    }
}

// 订单处理上下文
 class LingMaOrderProcessorStrategyContext {
    private java.util.List<LingMaOrderProcessorStrategy> strategies;
    
    public LingMaOrderProcessorStrategyContext() {
        strategies = new java.util.ArrayList<>();
        strategies.add(new NormalLingMaOrderProcessor());
        strategies.add(new UrgentLingMaOrderProcessor());
        strategies.add(new DiscountLingMaOrderProcessor());
    }
    
    public void processOrder(Order order) {
        System.out.println("=== 策略模式处理订单: " + order.getId() + " ===");
        
        for (LingMaOrderProcessorStrategy strategy : strategies) {
            if (strategy.canProcess(order)) {
                strategy.processOrder(order);
                return;
            }
        }
        
        System.out.println("未知订单类型: " + order.getType());
    }
}