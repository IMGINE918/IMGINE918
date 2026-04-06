package com.salary;

/**
 * Superpowers Model: Programmer (程序员)
 * 职责：程序员享有“加班补助”
 */
public class Programmer extends Employee {
    private int overtimeHours;
    private double overtimeRate;

    public Programmer(String name, String id, double baseSalary, int overtimeHours, double overtimeRate) {
        super(name, id, baseSalary);
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (overtimeHours * overtimeRate);
    }
}
