package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(int id);
    Customer findCustomerByIdAndUser(int customerId, User user);
}
