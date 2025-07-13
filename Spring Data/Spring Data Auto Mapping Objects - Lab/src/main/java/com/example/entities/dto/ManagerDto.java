package com.example.entities.dto;

import com.example.entities.Employee;

import java.util.Set;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private Set<Employee> managerEmployees;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Employee> getManagerEmployees() {
        return managerEmployees;
    }

    public int getManagerEmployeesCount() {
        return managerEmployees.size();
    }

    public void setManagerEmployees(Set<Employee> managerEmployees) {
        this.managerEmployees = managerEmployees;
    }

    public ManagerDto() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName + " " + lastName + " | Employees: " + getManagerEmployeesCount());

        for (Employee employee : getManagerEmployees()) {
            builder.append("\n  - " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getSalary());
        }


        return builder.toString();
    }
}
