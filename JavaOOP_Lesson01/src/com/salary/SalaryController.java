package com.salary;

/**
 * 控制层
 */
public class SalaryController {
    private SalaryService service = new SalaryService();

    public void register(Employee e) {
        service.add(e);
    }

    public void show() {
        service.report();
    }
}
