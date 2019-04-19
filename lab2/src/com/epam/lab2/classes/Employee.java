package com.epam.lab2.classes;

public abstract class Employee {
    private String name;

    public Employee() {
        this.name = "Unknown";
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Employee source) {
        this.name = source.name;
    }

    public String getEmployeeName() {
        return this.name;
    }

    public void setEmployeeName(String name) {
        this.name = name;
    }
}
