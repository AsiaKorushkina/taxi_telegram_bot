package com.example.taxi_bot.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taxi {

    @Id
    @GeneratedValue
    private int id;

    private String company;

    private String pickupPoint;
    private String destinationPoint;

    private String dateDepart;
    private String timeDepart;

    private String timeInWay;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @Singular
    private List<Car> cars;

}