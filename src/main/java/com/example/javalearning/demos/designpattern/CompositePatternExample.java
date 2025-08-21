package com.example.javalearning.demos.designpattern;

import java.util.ArrayList;
import java.util.List;

// 抽象表达式类
abstract class Expression {
    abstract double calculate();
    abstract void add(Expression expression);
    abstract void remove(Expression expression);
    abstract List<Expression> getChildren();
}

// 数字表达式 - 叶子节点
class NumberExpression extends Expression {
    private double value;
    
    public NumberExpression(double value) {
        this.value = value;
    }
    
    @Override
    double calculate() {
        return value;
    }
    
    @Override
    void add(Expression expression) {
        throw new UnsupportedOperationException("Leaf node cannot add children");
    }
    
    @Override
    void remove(Expression expression) {
        throw new UnsupportedOperationException("Leaf node cannot remove children");
    }
    
    @Override
    List<Expression> getChildren() {
        return new ArrayList<>();
    }
}

// 加法表达式 - 组合节点
class AddExpression extends Expression {
    private List<Expression> children = new ArrayList<>();
    
    @Override
    double calculate() {
        double result = 0;
        for (Expression child : children) {
            result += child.calculate();
        }
        return result;
    }
    
    @Override
    void add(Expression expression) {
        children.add(expression);
    }
    
    @Override
    void remove(Expression expression) {
        children.remove(expression);
    }
    
    @Override
    List<Expression> getChildren() {
        return children;
    }
}

// 乘法表达式 - 组合节点
class MultiplyExpression extends Expression {
    private List<Expression> children = new ArrayList<>();
    
    @Override
    double calculate() {
        double result = 1;
        for (Expression child : children) {
            result *= child.calculate();
        }
        return result;
    }
    
    @Override
    void add(Expression expression) {
        children.add(expression);
    }
    
    @Override
    void remove(Expression expression) {
        children.remove(expression);
    }
    
    @Override
    List<Expression> getChildren() {
        return children;
    }
}

// 复杂表达式计算器演示
public class CompositePatternExample {
    public static void main(String[] args) {
        // 构建表达式: (2 + 3) * (4 + 5)
        // 等价于: Multiply(Add(2, 3), Add(4, 5))
        
        // 创建叶子节点
        NumberExpression num2 = new NumberExpression(2);
        NumberExpression num3 = new NumberExpression(3);
        NumberExpression num4 = new NumberExpression(4);
        NumberExpression num5 = new NumberExpression(5);
        
        // 创建加法表达式节点
        AddExpression add1 = new AddExpression();
        add1.add(num2);
        add1.add(num3);
        
        AddExpression add2 = new AddExpression();
        add2.add(num4);
        add2.add(num5);
        
        // 创建乘法表达式节点
        MultiplyExpression multiply = new MultiplyExpression();
        multiply.add(add1);
        multiply.add(add2);
        
        System.out.println("表达式: (2 + 3) * (4 + 5)");
        System.out.println("计算结果: " + multiply.calculate());
        
        // 构建更复杂的表达式: ((1 + 2) * 3) + (4 * (5 + 6))
        buildComplexExpression();
        
        // 展示传统if-else方式的复杂性
        showTraditionalApproach();
    }
    
    public static void buildComplexExpression() {
        System.out.println("\n=== 构建复杂表达式 ===");
        // 表达式: ((1 + 2) * 3) + (4 * (5 + 6))
        
        // 左侧部分: ((1 + 2) * 3)
        NumberExpression one = new NumberExpression(1);
        NumberExpression two = new NumberExpression(2);
        NumberExpression three = new NumberExpression(3);
        
        AddExpression leftAdd = new AddExpression();
        leftAdd.add(one);
        leftAdd.add(two);
        
        MultiplyExpression leftMultiply = new MultiplyExpression();
        leftMultiply.add(leftAdd);
        leftMultiply.add(three);
        
        // 右侧部分: (4 * (5 + 6))
        NumberExpression four = new NumberExpression(4);
        NumberExpression five = new NumberExpression(5);
        NumberExpression six = new NumberExpression(6);
        
        AddExpression rightAdd = new AddExpression();
        rightAdd.add(five);
        rightAdd.add(six);
        
        MultiplyExpression rightMultiply = new MultiplyExpression();
        rightMultiply.add(four);
        rightMultiply.add(rightAdd);
        
        // 最终加法
        AddExpression finalAdd = new AddExpression();
        finalAdd.add(leftMultiply);
        finalAdd.add(rightMultiply);
        
        System.out.println("表达式: ((1 + 2) * 3) + (4 * (5 + 6))");
        System.out.println("计算结果: " + finalAdd.calculate());
    }
    
    public static void showTraditionalApproach() {
        System.out.println("\n=== 传统方式实现的复杂性 ===");
        System.out.println("如果用传统方式处理表达式计算，需要大量的条件判断:");
        System.out.println("switch (expressionType) {");
        System.out.println("  case ADD:");
        System.out.println("    // 遍历操作数");
        System.out.println("    for (Object operand : operands) {");
        System.out.println("      if (operand instanceof Number) {");
        System.out.println("        // 处理数字");
        System.out.println("      } else if (operand instanceof AddExpression) {");
        System.out.println("        // 递归处理加法表达式");
        System.out.println("      } else if (operand instanceof MultiplyExpression) {");
        System.out.println("        // 递归处理乘法表达式");
        System.out.println("      }");
        System.out.println("    }");
        System.out.println("    break;");
        System.out.println("  case MULTIPLY:");
        System.out.println("    // 类似ADD的处理逻辑");
        System.out.println("    break;");
        System.out.println("}");
        System.out.println("当表达式类型增多时，代码会变得非常复杂且难以维护。");
    }
}