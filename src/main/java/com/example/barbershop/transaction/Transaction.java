package com.example.barbershop.transaction;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    String hash;
    String from;
    String to;
    TransactionStatus status;
}
