package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.EmployeeRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void register(EmployeeDTO employeeDTO) {
        User user = new User();
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(employeeDTO.getPassword());
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        user.setRole("EMPLOYEE");

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        employee.setUser(user);
        user.setEmployee(employee);

        employeeRepository.save(employee);
        userRepository.save(user);
    }

    public void updateEmployee(Integer authID, Employee employee) throws ApiException {
        User user = userRepository.findUserById(authID);
        Employee employeeFromDB = employeeRepository.findEmployeeByIdAndUser(authID, user);
        if (employeeFromDB == null) {
            throw new ApiException("User is not found");
        }
        user.getEmployee().setPosition(employeeFromDB.getPosition());
        user.getEmployee().setSalary(employeeFromDB.getSalary());
        userRepository.save(user);
    }

    public void deleteEmployee(Integer authID) throws ApiException {
        User user = userRepository.findUserById(authID);
        Employee employeeFromDB = employeeRepository.findEmployeeByIdAndUser(authID, user);
        if (employeeFromDB == null) {
            throw new ApiException("User is not found");
        }
        userRepository.delete(user);
    }
}
