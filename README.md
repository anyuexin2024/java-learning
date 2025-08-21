# Java学习项目 - 设计模式应用

## 项目简介

本项目展示了如何使用设计模式来优化多层嵌套的if-else代码，提高代码的可维护性、可读性和可扩展性。

## 项目结构

```
src/main/java/com/example/javalearning/demos/
├── Order.java                           # 订单实体类
├── Customer.java                        # 客户实体类
├── TraditionalOrderProcessor.java       # 传统多层嵌套if-else实现
├── OrderProcessingDemo.java             # 主演示类
├── strategy/                            # 策略模式实现
│   ├── OrderTypeStrategy.java          # 订单类型策略接口
│   ├── AmountStrategy.java             # 订单金额策略接口
│   ├── CustomerLevelStrategy.java      # 客户等级策略接口
│   ├── StrategyOrderProcessor.java     # 策略模式处理器
│   ├── AdvancedStrategyOrderProcessor.java # 高级策略模式处理器
│   ├── UltimateStrategyOrderProcessor.java # 终极策略模式处理器
│   └── impl/                           # 具体策略实现
│       ├── NormalOrderStrategy.java    # 普通订单策略
│       ├── UrgentOrderStrategy.java    # 紧急订单策略
│       ├── DiscountOrderStrategy.java  # 折扣订单策略
│       ├── LargeAmountStrategy.java    # 大额订单策略
│       ├── SmallAmountStrategy.java    # 小额订单策略
│       ├── VipCustomerStrategy.java    # VIP客户策略
│       ├── NormalCustomerStrategy.java # 普通客户策略
│       ├── VipLargeOrderStrategy.java  # VIP大额订单策略
│       ├── NormalLargeOrderStrategy.java # 普通大额订单策略
│       ├── VipSmallOrderStrategy.java  # VIP小额订单策略
│       └── NormalSmallOrderStrategy.java # 普通小额订单策略
├── composite/                           # 组合模式实现
│   ├── OrderHandler.java               # 订单处理器抽象类
│   ├── CompositeOrderProcessor.java    # 组合模式处理器
│   ├── OptimizedOrderHandler.java      # 优化的订单处理器抽象类
│   ├── OptimizedCompositeOrderProcessor.java # 优化的组合模式处理器
│   ├── strategy/                       # 组合模式中的策略
│   │   ├── OrderProcessingStrategy.java # 订单处理策略接口
│   │   └── impl/                       # 具体策略实现
│   │       ├── VipLargeNormalOrderStrategy.java # VIP大额普通订单策略
│   │       ├── NormalLargeNormalOrderStrategy.java # 普通大额普通订单策略
│   │       ├── VipSmallNormalOrderStrategy.java # VIP小额普通订单策略
│   │       ├── NormalSmallNormalOrderStrategy.java # 普通小额普通订单策略
│   │       ├── UrgentOrderStrategy.java # 紧急订单策略
│   │       └── DiscountOrderStrategy.java # 折扣订单策略
│   └── impl/                           # 具体处理器实现
│       ├── NormalOrderHandler.java     # 普通订单处理器
│       ├── UrgentOrderHandler.java     # 紧急订单处理器
│       ├── DiscountOrderHandler.java   # 折扣订单处理器
│       ├── OptimizedNormalOrderHandler.java # 优化的普通订单处理器
│       ├── OptimizedUrgentOrderHandler.java # 优化的紧急订单处理器
│       └── OptimizedDiscountOrderHandler.java # 优化的折扣订单处理器
```

## 设计模式说明

### 1. 传统方式（多层嵌套if-else）

**问题：**
- 代码嵌套层级深，难以维护
- 新增订单类型需要修改现有代码
- 违反开闭原则
- 代码可读性差

**代码示例：**
```java
public void processOrder(Order order) {
    if (order.getType().equals("NORMAL")) {
        if (order.getAmount() > 1000) {
            if (order.getCustomer().getLevel().equals("VIP")) {
                processVipLargeOrder(order);
            } else {
                processNormalLargeOrder(order);
            }
        } else {
            // 更多嵌套...
        }
    } else if (order.getType().equals("URGENT")) {
        // 更多嵌套...
    }
}
```

### 2. 策略模式

**优势：**
- 消除多层嵌套，代码结构清晰
- 新增策略只需添加新类，符合开闭原则
- 策略可以动态切换
- 每个策略职责单一

**核心思想：**
- 定义策略接口
- 实现具体策略类
- 使用策略上下文选择策略

### 3. 组合模式

**优势：**
- 构建处理器链，线性处理逻辑
- 处理器可以动态组合
- 支持复杂的处理流程
- 易于扩展和维护

**核心思想：**
- 定义处理器抽象类
- 实现具体处理器
- 构建处理器链

### 4. 优化的组合模式（组合模式+策略模式）

**优势：**
- 组合模式+策略模式，双重优势
- 完全消除if-else嵌套
- 处理器链 + 策略选择
- 完全符合开闭原则
- 代码结构最清晰

**核心思想：**
- 在组合模式中引入策略模式
- 每个处理器使用策略模式处理具体逻辑
- 既保持处理器链的灵活性，又消除条件判断

## 设计模式演进历程

### 第一代：传统方式
- ❌ 多层嵌套if-else
- ❌ 难以维护和扩展

### 第二代：基础策略模式
- ✅ 消除订单类型嵌套
- ⚠️ 仍有金额和客户等级的if-else

### 第三代：高级策略模式
- ✅ 多重策略组合
- ⚠️ 仍有部分if-else逻辑

### 第四代：终极策略模式
- ✅ 完全消除所有if-else嵌套
- ✅ 每个策略处理一个具体业务场景

### 第五代：原始组合模式
- ✅ 构建处理器链
- ❌ 仍有大量if-else嵌套

### 第六代：优化的组合模式
- ✅ 组合模式+策略模式，双重优势
- ✅ 完全消除if-else嵌套
- ✅ 处理器链 + 策略选择

## 运行方式

1. 确保已安装Java 17+
2. 编译项目：`mvn compile`
3. 运行演示：`mvn exec:java -Dexec.mainClass="com.example.javalearning.demos.OrderProcessingDemo"`

## 学习要点

1. **识别代码异味**：多层嵌套if-else是典型的代码异味
2. **选择合适的设计模式**：根据具体场景选择策略模式或组合模式
3. **设计模式组合使用**：组合模式+策略模式效果更佳
4. **遵循设计原则**：开闭原则、单一职责原则等
5. **代码重构**：逐步重构，保持功能不变

## 扩展思考

1. 如何进一步优化策略模式中的if-else？
2. 组合模式如何支持更复杂的处理流程？
3. 其他设计模式如何应用到这个场景？
4. 如何结合Spring框架使用这些设计模式？
5. 设计模式组合使用的最佳实践是什么？

## 参考资料

- 《设计模式：可复用面向对象软件的基础》
- 《重构：改善既有代码的设计》
- 《Effective Java》
- 《Head First设计模式》
