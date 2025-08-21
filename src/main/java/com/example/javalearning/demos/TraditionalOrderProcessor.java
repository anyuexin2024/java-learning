package com.example.javalearning.demos;

/**
 * 传统方式 - 多层嵌套if-else订单处理器
 * 问题：代码嵌套层级深，难以维护和扩展
 */
public class TraditionalOrderProcessor {
    
    /**
     * 处理订单 - 多层嵌套if-else
     */
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
    
    // 各种处理方法
    private void processVipLargeOrder(Order order) {
        System.out.println("处理VIP大额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (VIP)");
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 享受VIP大额订单优惠");
    }
    
    private void processNormalLargeOrder(Order order) {
        System.out.println("处理普通大额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (普通)");
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 标准大额订单处理");
    }
    
    private void processVipSmallOrder(Order order) {
        System.out.println("处理VIP小额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (VIP)");
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 享受VIP小额订单优惠");
    }
    
    private void processNormalSmallOrder(Order order) {
        System.out.println("处理普通小额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName() + " (普通)");
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 标准小额订单处理");
    }
    
    private void processUrgentLargeOrder(Order order) {
        System.out.println("处理紧急大额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 紧急大额订单优先处理");
    }
    
    private void processUrgentSmallOrder(Order order) {
        System.out.println("处理紧急小额订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 紧急小额订单优先处理");
    }
    
    private void processDiscountOrder(Order order) {
        System.out.println("处理折扣订单: " + order.getId());
        System.out.println("  - 客户: " + order.getCustomer().getName());
        System.out.println("  - 金额: " + order.getAmount());
        System.out.println("  - 折扣订单特殊处理");
    }
}
