package com.example.taxi_bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritePlace {

    @Id
    @GeneratedValue
    private int id;

    private String address;

    private int userId;

}
