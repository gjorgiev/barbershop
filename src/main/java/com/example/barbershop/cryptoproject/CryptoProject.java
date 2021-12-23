package com.example.barbershop.cryptoproject;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CryptoProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Long marketCap;
    private Long circulatingSupply;
    private Long totalSupply;
    private String symbol;
}
