package com.devstack.pos.entity;

import com.devstack.pos.dao.customer.SuperDao;

public class Customer implements SuperEntity {
    private String email;
    private String name;
    private String contactNumber;
    private double salary;

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Customer() {
    }

    public Customer(String email, String name, String contactNumber, double salary) {
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
