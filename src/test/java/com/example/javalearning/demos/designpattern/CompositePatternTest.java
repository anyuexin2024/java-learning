package com.example.javalearning.demos.designpattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompositePatternTest {

    @Test
    public void testEmployeeCompositePattern() {
        // 创建员工
        Employee dev1 = new Developer("张三", 8000);
        Employee dev2 = new Developer("李四", 9000);
        Employee designer1 = new Designer("王五", 7500);

        // 创建管理者
        Manager techManager = new Manager("赵六", 15000);
        Manager designManager = new Manager("孙七", 14000);
        Manager generalManager = new Manager("周八", 25000);

        // 构建组织结构
        techManager.add(dev1);
        techManager.add(dev2);
        designManager.add(designer1);
        generalManager.add(techManager);
        generalManager.add(designManager);

        // 验证薪资计算
        assertEquals(8000, dev1.getSalary());
        assertEquals(15000, techManager.getSalary());
        assertEquals(17000, CompositePatternDemo.calculateTotalSalary(techManager), 0.01);
        assertEquals(7500, CompositePatternDemo.calculateTotalSalary(designManager), 0.01);
        assertEquals(61500, CompositePatternDemo.calculateTotalSalary(generalManager), 0.01);
    }

    @Test
    public void testExpressionCompositePattern() {
        // 构建表达式: (2 + 3) * (4 + 5)
        NumberExpression num2 = new NumberExpression(2);
        NumberExpression num3 = new NumberExpression(3);
        NumberExpression num4 = new NumberExpression(4);
        NumberExpression num5 = new NumberExpression(5);

        AddExpression add1 = new AddExpression();
        add1.add(num2);
        add1.add(num3);

        AddExpression add2 = new AddExpression();
        add2.add(num4);
        add2.add(num5);

        MultiplyExpression multiply = new MultiplyExpression();
        multiply.add(add1);
        multiply.add(add2);

        assertEquals(50, multiply.calculate(), 0.01);
    }

    @Test
    public void testComplexExpression() {
        // 表达式: ((1 + 2) * 3) + (4 * (5 + 6))
        NumberExpression one = new NumberExpression(1);
        NumberExpression two = new NumberExpression(2);
        NumberExpression three = new NumberExpression(3);
        NumberExpression four = new NumberExpression(4);
        NumberExpression five = new NumberExpression(5);
        NumberExpression six = new NumberExpression(6);

        AddExpression leftAdd = new AddExpression();
        leftAdd.add(one);
        leftAdd.add(two);

        MultiplyExpression leftMultiply = new MultiplyExpression();
        leftMultiply.add(leftAdd);
        leftMultiply.add(three);

        AddExpression rightAdd = new AddExpression();
        rightAdd.add(five);
        rightAdd.add(six);

        MultiplyExpression rightMultiply = new MultiplyExpression();
        rightMultiply.add(four);
        rightMultiply.add(rightAdd);

        AddExpression finalAdd = new AddExpression();
        finalAdd.add(leftMultiply);
        finalAdd.add(rightMultiply);

        assertEquals(53, finalAdd.calculate(), 0.01);
    }
}