package com.example;

import com.example.entities.Address;
import com.example.entities.dto.ManagerDto;
import com.example.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        Address address = new Address();
        address.setCountry("Bulgaria");
        address.setCity("Ruse");


        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Stefanov");
        employee.setAddress(address);
        employee.setSalary(BigDecimal.valueOf(2000.12));
        employee.setBirthday(LocalDate.of(2000, 12, 1));
        employee.setOnHoliday(true);

        Employee employee2 = new Employee();
        employee2.setFirstName("Marin");
        employee2.setLastName("Marinov");
        employee2.setAddress(address);
        employee2.setSalary(BigDecimal.valueOf(1000.12));
        employee2.setBirthday(LocalDate.of(1898, 12, 1));
        employee2.setOnHoliday(true);

        Employee employee3 = new Employee();
        employee3.setFirstName("Nikolay");
        employee3.setLastName("Nokv");
        employee3.setAddress(address);
        employee3.setSalary(BigDecimal.valueOf(1420.12));
        employee3.setBirthday(LocalDate.of(1999, 12, 1));
        employee3.setOnHoliday(true);

        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        employeeSet.add(employee2);

        employee3.setManagerEmployees(employeeSet);

        employeeList.add(employee);
        employeeList.add(employee2);
        employeeList.add(employee3);

        ManagerDto managerDto = modelMapper.map(employee3, ManagerDto.class);
        System.out.println(managerDto);
    }
}