package com.example.taxi_bot.services;

import com.example.taxi_bot.model.Coordinates;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class YandexTaxiService implements TaxiService {

    @Value("${yandex.body}")
    private String yandexBody;

    @Value("${yandex.url}")
    private String yandexUrl;

    private final RestTemplate restTemplate;

    public YandexTaxiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, Integer> getPrices(Pair<Coordinates, Coordinates> coordinates) {

        String body = placeholders(coordinates);

        ResponseEntity<String> exchange = restTemplate.exchange(yandexUrl, HttpMethod.POST, new HttpEntity<>(new JSONObject(body)), String.class);


        return null;
    }

    private String placeholders(Pair<Coordinates, Coordinates> coordinates) {

        String body = yandexBody.replace("{$StartLatitude}", coordinates.getFirst().getLatitude());
        body = body.replace("{$StartLongitude}", coordinates.getFirst().getLongitude());
        body = body.replace("{$EndLatitude}", coordinates.getSecond().getLatitude());
        body = body.replace("{$EndLongitude}", coordinates.getSecond().getLongitude());
        return body;
    }
}
