package com.salary;

/**
 * Superpowers Interface: Payable
 * 职责：定义所有“可支付”对象的通用行为
 */
public interface Payable {
    double calculateSalary(); // 计算薪水
    void showSalaryInfo();    // 展示薪水信息
}
