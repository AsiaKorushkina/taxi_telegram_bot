package com.example.taxi_bot.model;

import lombok.Builder;
import lombok.Getter;

import javax.swing.*;

@Builder
@Getter
public class Coordinates {

    private final String Latitude;

    private final String Longitude;


}
