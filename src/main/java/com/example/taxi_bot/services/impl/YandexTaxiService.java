package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.services.TaxiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class YandexTaxiService implements TaxiService {

    private static final String ROUTE = "route";
    private static final String YANDEX_TAXI = "Yandex Taxi";

    private Map<String, Object> body = new HashMap<>();

    @Value("${yandex.body}")
    private String yandexBody;

    @Value("${yandex.url}")
    private String yandexUrl;

    private final RestTemplate restTemplate;

    @SneakyThrows
    @PostConstruct
    public void initBody() {
        body = new ObjectMapper().readValue(yandexBody, HashMap.class);
    }

    @Override
    public List<RidePrice> getRideInfo(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> body = getBody(startPoint, endPoint);
        ResponseEntity<String> exchange = restTemplate.exchange(yandexUrl, HttpMethod.POST, new HttpEntity<>(body), String.class);
        return extractPrises(exchange.getBody());
    }

    private Map<String, Object> getBody(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> res = new HashMap<>(body);
        res.put(ROUTE, List.of(
                List.of(Double.parseDouble(startPoint.getLatitude()), Double.parseDouble(startPoint.getLongitude())),
                List.of(Double.parseDouble(endPoint.getLatitude()), Double.parseDouble(endPoint.getLongitude()))
                )
        );
        return res;
    }

}
