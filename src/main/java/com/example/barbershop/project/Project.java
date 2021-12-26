package com.example.barbershop.project;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String coingeckoId;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Double marketCap;
    private Double circulatingSupply;
    private Double totalSupply;
    private String symbol;
    private Double priceChangePercentage24h;
    private Double currentPrice;
    private Double totalVolume;
    private Double ath;
    private Double atl;
    private Double atlChangePercentage;
    // TODO: Implement lastUpdated, so we can update only if the project data is older than 5 mins
    // private Date lastUpdated;
    // TODO: Create Category as separate Entity and link it here MANY TO MANY
}
