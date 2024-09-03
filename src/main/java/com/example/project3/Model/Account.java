package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "(\\d){4}(-)(\\d){4}(-)(\\d){4}(-)(\\d){4}")
    @Column(columnDefinition = "varchar(19) not null")
    private String accountNumber;

    @NotEmpty
    @Positive
    @Column(columnDefinition = "int not null")
    private int balance;

    @AssertFalse
    @Column(columnDefinition = "boolean")
    private boolean active;

    //==========Relations==============//
    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Account(String accountNumber, int balance, boolean active, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.active = active;
        this.customer = customer;
    }
}
