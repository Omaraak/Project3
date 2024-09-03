package com.example.project3.Controller;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body("Register successful");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody Customer customer) throws ApiException {
        customerService.updateCustomer(user.getId(), customer);
        return ResponseEntity.status(200).body("Update user successful");
    }

    @DeleteMapping("/deleteCustomer{customerID}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable int customerID) throws ApiException {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body("Delete user successful");
    }
}
