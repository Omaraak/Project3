package com.example.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeDTO {

    @NotEmpty
    @Size(min = 4, max = 10)
    private String username;

    @NotEmpty
    @Size(min = 6)
    private String password;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "(CUSTOMER|EMPLOYEE|ADMIN)")
    private String role;

    @NotEmpty
    private String position;

    @NotNull
    @Positive
    private double salary;
}
