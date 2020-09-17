package com.example.taxi_bot.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Coordinates {

    private final Double upperCornerLatitude;

    private final Double upperCornerLongitude;

    private final Double lowerCornerLongitude;

    private final Double lowerCornerLatitude;

}
