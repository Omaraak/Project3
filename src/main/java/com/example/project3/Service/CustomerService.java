package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void register(CustomerDTO customerDTO) {
        User user = new User();
        user.setUsername(customerDTO.getUsername());
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hash);
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        user.setRole("CUSTOMER");

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customer.setUser(user);
        user.setCustomer(customer);

        customerRepository.save(customer);
        userRepository.save(user);
    }

    public void updateCustomer(Integer authID, Customer customer) throws ApiException {
        User user = userRepository.findUserById(authID);
        Customer customerFromDB = customerRepository.findCustomerByIdAndUser(authID, user);
        if (customerFromDB == null) {
            throw new ApiException("User is not found");
        }
        user.getCustomer().setPhoneNumber(customer.getPhoneNumber());
        userRepository.save(user);
    }

    public void deleteCustomer(Integer authID) throws ApiException {
        User user = userRepository.findUserById(authID);
        Customer customerFromDB = customerRepository.findCustomerByIdAndUser(authID, user);
        if (customerFromDB == null) {
            throw new ApiException("User is not found");
        }
        userRepository.delete(user);
    }

}
