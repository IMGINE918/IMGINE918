package com.salary;

import java.util.ArrayList;

/**
 * 数据层
 */
public class SalaryDao {
    private static ArrayList<Employee> db = new ArrayList<>();

    public void save(Employee e) {
        db.add(e);
    }

    public ArrayList<Employee> findAll() {
        return db;
    }
}
