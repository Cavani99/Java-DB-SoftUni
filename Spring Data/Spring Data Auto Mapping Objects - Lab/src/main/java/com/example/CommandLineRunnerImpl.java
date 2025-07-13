package com.example;

import com.example.entities.Employee;
import com.example.entities.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Stefanov");
        employee.setSalary(BigDecimal.valueOf(2000.12));
        employee.setBirthday(LocalDate.of(2000, 12, 1));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        System.out.println(employeeDto);
    }
}