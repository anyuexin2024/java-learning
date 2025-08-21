
package com.example.javalearning.demos.designpattern;

// 订单处理组件接口 - 组合模式
interface OrderProcessingComponent {
    boolean canProcess(Order order);
    void process(Order order);
    void add(OrderProcessingComponent component);
    void remove(OrderProcessingComponent component);
}

// 基础订单处理器 - 叶子节点
abstract class BaseOrderProcessor implements OrderProcessingComponent {
    @Override
    public void add(OrderProcessingComponent component) {
        throw new UnsupportedOperationException("Leaf node cannot add children");
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        throw new UnsupportedOperationException("Leaf node cannot remove children");
    }
    
    @Override
    public boolean canProcess(Order order) {
        // 默认实现，子类可以覆盖
        return false;
    }
    
    @Override
    public void process(Order order) {
        // 默认实现，子类必须覆盖
        throw new UnsupportedOperationException("Process method must be implemented by subclass");
    }
}

// 普通订单处理器
class NormalOrderProcessorLeaf extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return "NORMAL".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("组合模式处理普通订单: " + order.getId());
        
        // 使用组合模式处理客户等级和金额分类
        OrderProcessingComponent amountProcessor = new AmountProcessorComposite();
        amountProcessor.process(order);
    }
}

// 紧急订单处理器
class UrgentOrderProcessorLeaf extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return "URGENT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("组合模式处理紧急订单: " + order.getId());
        
        // 使用组合模式处理金额分类
        OrderProcessingComponent amountProcessor = new UrgentAmountProcessorComposite();
        amountProcessor.process(order);
    }
}

// 折扣订单处理器
class DiscountOrderProcessorLeaf extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return "DISCOUNT".equals(order.getType());
    }
    
    @Override
    public void process(Order order) {
        System.out.println("组合模式处理折扣订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 金额处理器组合节点
class AmountProcessorComposite implements OrderProcessingComponent {
    private java.util.List<OrderProcessingComponent> children = new java.util.ArrayList<>();
    
    public AmountProcessorComposite() {
        children.add(new LargeAmountProcessor());
        children.add(new SmallAmountProcessor());
    }
    
    @Override
    public boolean canProcess(Order order) {
        // 组合节点不直接处理订单
        return false;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理金额分类...");
        
        // 根据客户等级创建适当的处理器
        OrderProcessingComponent customerProcessor;
        if ("VIP".equals(order.getCustomer().getLevel())) {
            customerProcessor = new VipCustomerProcessorComposite();
        } else {
            customerProcessor = new RegularCustomerProcessorComposite();
        }
        
        customerProcessor.process(order);
    }
    
    @Override
    public void add(OrderProcessingComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        children.remove(component);
    }
}

// 紧急订单金额处理器组合节点
class UrgentAmountProcessorComposite implements OrderProcessingComponent {
    private java.util.List<OrderProcessingComponent> children = new java.util.ArrayList<>();
    
    public UrgentAmountProcessorComposite() {
        children.add(new UrgentLargeAmountProcessor());
        children.add(new UrgentSmallAmountProcessor());
    }
    
    @Override
    public boolean canProcess(Order order) {
        return false; // 组合节点不直接处理订单
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理紧急订单金额分类...");
        
        for (OrderProcessingComponent child : children) {
            if (child.canProcess(order)) {
                child.process(order);
                return;
            }
        }
    }
    
    @Override
    public void add(OrderProcessingComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        children.remove(component);
    }
}

// 大额订单处理器
class LargeAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() > 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 小额订单处理器
class SmallAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() <= 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// VIP客户处理器组合
class VipCustomerProcessorComposite implements OrderProcessingComponent {
    private java.util.List<OrderProcessingComponent> children = new java.util.ArrayList<>();
    
    public VipCustomerProcessorComposite() {
        children.add(new VipLargeAmountProcessor());
        children.add(new VipSmallAmountProcessor());
    }
    
    @Override
    public boolean canProcess(Order order) {
        return false; // 组合节点不直接处理订单
    }
    
    @Override
    public void process(Order order) {
        System.out.println("    处理VIP客户订单...");
        
        for (OrderProcessingComponent child : children) {
            if (child.canProcess(order)) {
                child.process(order);
                return;
            }
        }
    }
    
    @Override
    public void add(OrderProcessingComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        children.remove(component);
    }
}

// 普通客户处理器组合
class RegularCustomerProcessorComposite implements OrderProcessingComponent {
    private java.util.List<OrderProcessingComponent> children = new java.util.ArrayList<>();
    
    public RegularCustomerProcessorComposite() {
        children.add(new RegularLargeAmountProcessor());
        children.add(new RegularSmallAmountProcessor());
    }
    
    @Override
    public boolean canProcess(Order order) {
        return false; // 组合节点不直接处理订单
    }
    
    @Override
    public void process(Order order) {
        System.out.println("    处理普通客户订单...");
        
        for (OrderProcessingComponent child : children) {
            if (child.canProcess(order)) {
                child.process(order);
                return;
            }
        }
    }
    
    @Override
    public void add(OrderProcessingComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        children.remove(component);
    }
}

// VIP大额订单处理器
class VipLargeAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() > 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("      处理VIP大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// VIP小额订单处理器
class VipSmallAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() <= 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("      处理VIP小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 普通客户大额订单处理器
class RegularLargeAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() > 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("      处理普通客户大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 普通客户小额订单处理器
class RegularSmallAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() <= 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("      处理普通客户小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 紧急大额订单处理器
class UrgentLargeAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() > 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理紧急大额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 紧急小额订单处理器
class UrgentSmallAmountProcessor extends BaseOrderProcessor {
    @Override
    public boolean canProcess(Order order) {
        return order.getAmount() <= 1000;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("  处理紧急小额订单: " + order.getId() + ", 金额: " + order.getAmount());
    }
}

// 订单处理器组合节点 - 根节点
class OrderProcessorComposite implements OrderProcessingComponent {
    private java.util.List<OrderProcessingComponent> children = new java.util.ArrayList<>();
    
    public OrderProcessorComposite() {
        children.add(new NormalOrderProcessorLeaf());
        children.add(new UrgentOrderProcessorLeaf());
        children.add(new DiscountOrderProcessorLeaf());
    }
    
    @Override
    public boolean canProcess(Order order) {
        // 根节点不直接处理订单
        return false;
    }
    
    @Override
    public void process(Order order) {
        System.out.println("=== 组合模式处理订单: " + order.getId() + " ===");
        
        // 遍历所有子处理器，找到能处理该订单的处理器
        for (OrderProcessingComponent child : children) {
            if (child.canProcess(order)) {
                child.process(order);
                return;
            }
        }
        
        System.out.println("未知订单类型: " + order.getType());
    }
    
    @Override
    public void add(OrderProcessingComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(OrderProcessingComponent component) {
        children.remove(component);
    }
}

// 组合模式订单处理器
public class OrderProcessorCompositeDemo {
    private OrderProcessingComponent rootProcessor;
    
    public OrderProcessorCompositeDemo() {
        rootProcessor = new OrderProcessorComposite();
    }
    
    public void processOrder(Order order) {
        rootProcessor.process(order);
    }
}