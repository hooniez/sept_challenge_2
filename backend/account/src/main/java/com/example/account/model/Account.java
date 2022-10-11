package com.example.account.model;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Currency;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
// TODO:
public class Account {

    @Id
    private Long accountNumber;
    private Long id;
    private String accountType;
    private String accountName;
    private String balance;
    private String date;
}
