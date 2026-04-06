package com.salary;

import java.util.ArrayList;

/**
 * 业务层
 */
public class SalaryService {
    private SalaryDao dao = new SalaryDao();

    public void add(Employee e) {
        dao.save(e);
    }

    public void report() {
        System.out.println("--- 薪资报表 ---");
        for (Employee e : dao.findAll()) {
            System.out.println(e + " ==> 实发：" + e.calculateSalary());
        }
    }
}
