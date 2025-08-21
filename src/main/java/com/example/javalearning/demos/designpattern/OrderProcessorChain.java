package com.example.javalearning.demos.designpattern;

// 责任链模式处理订单
abstract class OrderHandler {
    protected OrderHandler nextHandler;
    
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public void handleOrder(Order order) {
        if (canHandle(order)) {
            process(order);
        } else if (nextHandler != null) {
            nextHandler.handleOrder(order);
        } else {
            System.out.println("无法处理订单: " + order.getId());
        }
    }
    
    protected abstract boolean canHandle(Order order);
    protected abstract void process(Order order);
}

// 普通订单处理器
class NormalOrderHandler extends OrderHandler {
    @Override
    protected boolean canHandle(Order order) {
        return "NORMAL".equals(order.getType());
    }
    
    @Override
    protected void process(Order order) {
        System.out.println("责任链模式处理普通订单: " + order.getId());
        if (order.getAmount() > 1000) {
            if ("VIP".equals(order.getCustomer().getLevel())) {
                System.out.println("处理VIP大额订单: " + order.getId() + ", 金额: " + order.getAmount());
            } else {
                System.out.println("处理普通大额订单: " + order.getId() + ", 金额: " + order.getAmount());
            }
        } else {
            if ("VIP".equals(order.getCustomer().getLevel())) {
                System.out.println("处理VIP小额订单: " + order.getId() + ", 金额: " + order.getAmount());
            } else {
                System.out.println("处理普通小额订单: " + order.getId() + ", 金额: " + order.getAmount());
            }
        }
    }
}

// 紧急订单处理器
class UrgentOrderHandler extends OrderHandler {
    @Override
    protected boolean canHandle(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    protected void process(Order order) {
        System.out.println("责任链模式处理紧急订单: " + order.getId());
        if (order.getAmount() > 1000) {
            System.out.println("处理紧急大额订单: " + order.getId() + ", 金额: " + order.getAmount());
        } else {
            System.out.println("处理紧急小额订单: " + order.getId() + ", 金额: " + order.getAmount());
        }
    }
}

// 折扣订单处理器
class DiscountOrderHandler extends OrderHandler {
    @Override
    protected boolean canHandle(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    protected void process(Order order) {
        System.out.println("责任链模式处理折扣订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 订单处理责任链
public class OrderProcessorChain {
    private OrderHandler firstHandler;
    
    public OrderProcessorChain() {
        // 构建责任链
        NormalOrderHandler normalHandler = new NormalOrderHandler();
        UrgentOrderHandler urgentHandler = new UrgentOrderHandler();
        DiscountOrderHandler discountHandler = new DiscountOrderHandler();
        
        normalHandler.setNextHandler(urgentHandler);
        urgentHandler.setNextHandler(discountHandler);
        
        firstHandler = normalHandler;
    }
    
    public void processOrder(Order order) {
        System.out.println("=== 责任链模式处理订单: " + order.getId() + " ===");
        firstHandler.handleOrder(order);
    }
}