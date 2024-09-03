package com.example.project3.Controller;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.register(employeeDTO);
        return ResponseEntity.status(200).body("Register successful");
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody Employee employee) throws ApiException {
        employeeService.updateEmployee(user.getId(), employee);
        return ResponseEntity.status(200).body("Update user successful");
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user) throws ApiException {
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(200).body("Delete user successful");
    }
}
