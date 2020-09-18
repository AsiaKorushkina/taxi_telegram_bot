package com.example.taxi_bot.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ride {

    @Id
    @GeneratedValue
    private int id;

    private String pickupPoint;
    private String endPoint;

    private LocalDate dateDepart;
    private LocalTime timeDepart;

    private LocalTime timeInWay;

    @OneToMany
    private List<RidePrice> ridePrices;
    @ManyToOne
    private User user;


}