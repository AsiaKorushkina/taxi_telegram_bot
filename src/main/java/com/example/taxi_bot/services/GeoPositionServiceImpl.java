package com.example.taxi_bot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class GeoPositionServiceImpl implements GeoPositionService {

    private final RestTemplate restTemplate;

    @Value("${geo.apikey}")
    private String geoApiKey;

    @Value("${geo.url}")
    private String geoUrl;

    public GeoPositionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void initUrl(){
        geoUrl = geoUrl.replace("{$geoApiKey}", geoApiKey);
    }

    @Override
    public Object getCoordinates(String address) {
        String endpoint = geoUrl.replace("{$geoAddress}", address.replace(' ', '+'));
        ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, null, String.class);
        return responseEntity;
    }
}
