package com.example.taxi_bot.model;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Ride ride;

}
