package com.example.javalearning.demos.designpattern;

import java.util.ArrayList;
import java.util.List;

// 组件接口 - 所有组件都需要实现这个接口
interface Employee {
    void showDetails();
    double getSalary();
    String getName();
    List<Employee> getSubordinates();
    void add(Employee e);
    void remove(Employee e);
}

// 叶子节点 - 普通员工类
class Developer implements Employee {
    private String name;
    private double salary;

    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + ", Salary: " + salary);
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Employee> getSubordinates() {
        // 叶子节点没有下属
        return new ArrayList<>();
    }

    @Override
    public void add(Employee e) {
        // 叶子节点不能添加下属
        throw new UnsupportedOperationException("Developer cannot have subordinates");
    }

    @Override
    public void remove(Employee e) {
        // 叶子节点没有下属可移除
        throw new UnsupportedOperationException("Developer cannot have subordinates");
    }
}

// 叶子节点 - 设计师类
class Designer implements Employee {
    private String name;
    private double salary;

    public Designer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void showDetails() {
        System.out.println("Designer: " + name + ", Salary: " + salary);
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Employee> getSubordinates() {
        // 叶子节点没有下属
        return new ArrayList<>();
    }

    @Override
    public void add(Employee e) {
        // 叶子节点不能添加下属
        throw new UnsupportedOperationException("Designer cannot have subordinates");
    }

    @Override
    public void remove(Employee e) {
        // 叶子节点没有下属可移除
        throw new UnsupportedOperationException("Designer cannot have subordinates");
    }
}

// 组合节点 - 管理者类
class Manager implements Employee {
    private String name;
    private double salary;
    private List<Employee> subordinates;

    public Manager(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.subordinates = new ArrayList<>();
    }

    @Override
    public void showDetails() {
        System.out.println("Manager: " + name + ", Salary: " + salary);
        System.out.println("Subordinates:");
        for (Employee employee : subordinates) {
            // 缩进显示下属
            System.out.print("  ");
            employee.showDetails();
        }
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public void add(Employee e) {
        subordinates.add(e);
    }

    @Override
    public void remove(Employee e) {
        subordinates.remove(e);
    }
}

// 组合模式演示类
public class CompositePatternDemo {
    
    public static void main(String[] args) {
        // 创建员工
        Employee dev1 = new Developer("张三", 8000);
        Employee dev2 = new Developer("李四", 9000);
        Employee designer1 = new Designer("王五", 7500);
        
        // 创建管理者
        Manager techManager = new Manager("赵六", 15000);
        Manager designManager = new Manager("孙七", 14000);
        Manager generalManager = new Manager("周八", 25000);
        
        // 构建组织结构
        // 技术经理管理开发者
        techManager.add(dev1);
        techManager.add(dev2);
        
        // 设计经理管理设计师
        designManager.add(designer1);
        
        // 总经理管理所有经理
        generalManager.add(techManager);
        generalManager.add(designManager);
        
        // 显示组织结构
        System.out.println("=== 公司组织结构 ===");
        generalManager.showDetails();
        
        System.out.println("\n=== 各部门薪资总额 ===");
        System.out.println("技术部门薪资总额: " + calculateTotalSalary(techManager));
        System.out.println("设计部门薪资总额: " + calculateTotalSalary(designManager));
        System.out.println("全公司薪资总额: " + calculateTotalSalary(generalManager));
        
        // 展示传统方式需要大量if-else的情况
        System.out.println("\n=== 传统方式与组合模式对比 ===");
        traditionalApproach();
    }
    
    // 计算员工或管理者及其下属的总薪资 - 组合模式的优势
    public static double calculateTotalSalary(Employee employee) {
        double totalSalary = employee.getSalary();
        
        // 遍历所有下属，递归计算薪资总额
        for (Employee subordinate : employee.getSubordinates()) {
            totalSalary += calculateTotalSalary(subordinate);
        }
        
        return totalSalary;
    }
    
    // 传统方式实现同样的功能 - 需要大量条件判断
    public static void traditionalApproach() {
        System.out.println("如果用传统方式实现薪资计算，需要判断员工类型:");
        System.out.println("if (employee instanceof Manager) {");
        System.out.println("  // 处理管理者逻辑");
        System.out.println("  if (employee.hasSubordinates()) {");
        System.out.println("    // 遍历下属");
        System.out.println("    for (Employee sub : subordinates) {");
        System.out.println("      if (sub instanceof Developer) {");
        System.out.println("        // 处理开发者");
        System.out.println("      } else if (sub instanceof Designer) {");
        System.out.println("        // 处理设计师");
        System.out.println("      } else if (sub instanceof Manager) {");
        System.out.println("        // 递归处理...");
        System.out.println("      }");
        System.out.println("    }");
        System.out.println("  }");
        System.out.println("} else if (employee instanceof Developer) {");
        System.out.println("  // 处理开发者逻辑");
        System.out.println("} else if (employee instanceof Designer) {");
        System.out.println("  // 处理设计师逻辑");
        System.out.println("}");
        System.out.println("这种方式随着员工类型增加，if-else会变得非常复杂");
    }
}