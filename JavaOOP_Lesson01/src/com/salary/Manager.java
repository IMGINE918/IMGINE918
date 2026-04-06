package com.salary;

/**
 * Superpowers Model: Manager (经理)
 * 职责：经理享有“季度奖金”
 */
public class Manager extends Employee {
    private double bonus;

    public Manager(String name, String id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}
