package com.example.javalearning.demos.designpattern;

public class TraditionalOrderProcessor {
    
    public void processOrder(Order order) {
        System.out.println("=== 传统方式处理订单: " + order.getId() + " ===");
        
        if (order.getType().equals("NORMAL")) {
            if (order.getAmount() > 1000) {
                if (order.getCustomer().getLevel().equals("VIP")) {
                    // VIP大额订单处理
                    processVipLargeOrder(order);
                } else {
                    // 普通大额订单处理
                    processNormalLargeOrder(order);
                }
            } else {
                if (order.getCustomer().getLevel().equals("VIP")) {
                    // VIP小额订单处理
                    processVipSmallOrder(order);
                } else {
                    // 普通小额订单处理
                    processNormalSmallOrder(order);
                }
            }
        } else if (order.getType().equals("URGENT")) {
            if (order.getAmount() > 1000) {
                // 紧急大额订单处理
                processUrgentLargeOrder(order);
            } else {
                // 紧急小额订单处理
                processUrgentSmallOrder(order);
            }
        } else if (order.getType().equals("DISCOUNT")) {
            // 折扣订单处理
            processDiscountOrder(order);
        }
    }
    
    private void processVipLargeOrder(Order order) {
        System.out.println("处理VIP大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("处理普通大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("处理VIP小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("处理普通小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processUrgentLargeOrder(Order order) {
        System.out.println("处理紧急大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processUrgentSmallOrder(Order order) {
        System.out.println("处理紧急小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
    
    private void processDiscountOrder(Order order) {
        System.out.println("处理折扣订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}