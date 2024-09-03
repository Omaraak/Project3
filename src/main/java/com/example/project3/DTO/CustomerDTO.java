package com.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerDTO {
//    @NotEmpty
//    @Size(min = 4, max = 10)
    private String username;
//
//    @NotEmpty
//    @Size(min = 6)
    private String password;

//    @NotEmpty
//    @Size(min = 2, max = 20)
    private String name;

//    @Email
    private String email;

//    @NotEmpty
//    @Pattern(regexp = "^(05)(\\d){8}")
    private String phoneNumber;
}
