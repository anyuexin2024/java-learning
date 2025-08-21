package com.example.javalearning.demos.designpattern;

// 订单状态接口
interface OrderState {
    void processOrder(OrderContext context);
}

// 普通订单状态
class NormalOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        Order order = context.getOrder();
        System.out.println("状态模式处理普通订单: " + order.getId());
        
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

// 紧急订单状态
class UrgentOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        Order order = context.getOrder();
        System.out.println("状态模式处理紧急订单: " + order.getId());
        
        if (order.getAmount() > 1000) {
            System.out.println("处理紧急大额订单: " + order.getId() + ", 金额: " + order.getAmount());
        } else {
            System.out.println("处理紧急小额订单: " + order.getId() + ", 金额: " + order.getAmount());
        }
    }
}

// 折扣订单状态
class DiscountOrderState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        Order order = context.getOrder();
        System.out.println("状态模式处理折扣订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 订单上下文
class OrderContext {
    private Order order;
    private OrderState state;
    
    public OrderContext(Order order) {
        this.order = order;
        // 根据订单类型设置状态
        setStateByOrderType(order.getType());
    }
    
    private void setStateByOrderType(String type) {
        switch (type) {
            case "NORMAL":
                this.state = new NormalOrderState();
                break;
            case "URGENT":
                this.state = new UrgentOrderState();
                break;
            case "DISCOUNT":
                this.state = new DiscountOrderState();
                break;
            default:
                throw new IllegalArgumentException("未知订单类型: " + type);
        }
    }
    
    public void processOrder() {
        System.out.println("=== 状态模式处理订单: " + order.getId() + " ===");
        state.processOrder(this);
    }
    
    // Getters
    public Order getOrder() {
        return order;
    }
}

// 状态模式订单处理器
public class OrderProcessorState {
    public void processOrder(Order order) {
        OrderContext context = new OrderContext(order);
        context.processOrder();
    }
}