package com.salary;

import java.util.ArrayList;
import java.util.List;

/**
 * Superpowers Application: SalaryApp (薪资管理系统控制中心)
 * 职责：展示多态在工程中的实际应用
 */
public class SalaryApp {
    private List<Payable> payees = new ArrayList<>();

    public void addPayee(Payable p) {
        payees.add(p);
    }

    public void runPayroll() {
        System.out.println("=== 正在运行 Superpowers 薪资结算系统 ===");
        for (Payable p : payees) {
            p.showSalaryInfo();
        }
    }

    public static void main(String[] args) {
        SalaryApp payroll = new SalaryApp();

        // 多态：将不同类型的员工向上转型为 Payable 接口
        payroll.addPayee(new Manager("张经理", "M001", 15000, 5000));
        payroll.addPayee(new Programmer("郑程序", "P001", 12000, 20, 100));
        payroll.addPayee(new Programmer("李代码", "P002", 10000, 10, 80));

        payroll.runPayroll();
    }
}
