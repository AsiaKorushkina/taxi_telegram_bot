package com.example.taxi_bot.bot.handlers.search_handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TaxiSearchRequestData {
    private String pickup;
    private String destination;
    private String data;
}
