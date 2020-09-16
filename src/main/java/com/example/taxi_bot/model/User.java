package com.example.taxi_bot.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Basic(optional = false)
    private String name;
    private String phone;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @Singular("taxis")
    private List<Taxi> taxis;


}
