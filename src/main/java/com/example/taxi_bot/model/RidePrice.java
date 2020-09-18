package com.example.taxi_bot.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class RidePrice {

    @Id
    @GeneratedValue
    private int id;

    private String aggregator;
    private String classTaxi;
    private int price;

    @ManyToOne
    private Ride ride;



}
