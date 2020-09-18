package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.services.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class YandexTaxiService implements TaxiService {

    private static Map<String, Object> body;

    static {
        body = new HashMap<>();
        body.put("id", "b8bdeac793cb49da9898ee8314d542d7");
        body.put("zone_name", "moscow");
        body.put("skip_estimated_waiting", true);
        body.put("supports_forced_surge", true);
        body.put("format_currency", true);
        body.put("extended_description", true);
    }


    @Value("${yandex.body}")
    private String yandexBody;

    @Value("${yandex.url}")
    private String yandexUrl;

    private final RestTemplate restTemplate;

    @Override
    public Map<String, Integer> getPrices(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> body = getBody(startPoint, endPoint);
        ResponseEntity<String> exchange = restTemplate.exchange(yandexUrl, HttpMethod.POST, new HttpEntity<>(body), String.class);
        return null;
    }

    private Map<String, Object> getBody(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> res = Map.copyOf(body);
        res.put("route", List.of(
                List.of(Double.parseDouble(startPoint.getLatitude()), Double.parseDouble(startPoint.getLongitude())),
                List.of(Double.parseDouble(endPoint.getLatitude()), Double.parseDouble(endPoint.getLongitude()))
                )
        );
        return res;
    }

}
