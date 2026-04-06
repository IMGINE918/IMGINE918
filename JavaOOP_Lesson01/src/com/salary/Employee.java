package com.salary;

/**
 * Superpowers Model: Employee (抽象员工基类)
 */
public abstract class Employee implements Payable {
    protected String name;
    protected String id;
    protected double baseSalary;

    public Employee(String name, String id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    // 实现通用的展示信息逻辑
    @Override
    public void showSalaryInfo() {
        System.out.println("员工: " + name + " (ID: " + id + "), 应发薪资: " + calculateSalary());
    }

    // Getter & Setter
    public String getName() { return name; }
}
