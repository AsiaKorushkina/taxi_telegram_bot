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
    @Column(unique = true)
    private int telegramId;

    @Basic(optional = false)
    private String name;

    @OneToMany(targetEntity = Ride.class)
    private List<Ride> rides;
}
