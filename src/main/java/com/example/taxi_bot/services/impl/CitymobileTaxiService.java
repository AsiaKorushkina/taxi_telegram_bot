package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.RidePrice;
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
public class CitymobileTaxiService implements TaxiService {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String DEL_LATITUDE = "del_latitude";
    private static final String DEL_LONGITUDE = "del_longitude";

    private Map<String, Object> body = new HashMap<>();

    @Value("${citymobile.body}")
    private String citymobileBody;

    @Value("${citymobile.url}")
    private String citymobileUrl;

    private final RestTemplate restTemplate;

    @SneakyThrows
    @PostConstruct
    public void initBody() {
        body = new ObjectMapper().readValue(citymobileBody, HashMap.class);
    }

    @Override
    public List<RidePrice> getRideInfo(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> body = getBody(startPoint, endPoint);
        ResponseEntity<String> exchange = restTemplate.exchange(citymobileUrl, HttpMethod.POST, new HttpEntity<>(body), String.class);
        return null;
    }

    private Map<String, Object> getBody(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> res = new HashMap(body);
        res.put(LATITUDE, startPoint.getLatitude());
        res.put(LONGITUDE, startPoint.getLongitude());
        res.put(DEL_LATITUDE, endPoint.getLatitude());
        res.put(DEL_LONGITUDE, endPoint.getLatitude());
        return res;
    }
}
