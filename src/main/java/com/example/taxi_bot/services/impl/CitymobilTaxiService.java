package com.example.taxi_bot.services.impl;

import com.example.taxi_bot.model.Aggregator;
import com.example.taxi_bot.model.Coordinates;
import com.example.taxi_bot.model.RidePrice;
import com.example.taxi_bot.services.TaxiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CitymobilTaxiService implements TaxiService {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String DEL_LATITUDE = "del_latitude";
    private static final String DEL_LONGITUDE = "del_longitude";

    private Map<String, Object> body = new HashMap<>();

    @Value("${citymobil.body}")
    private String citymobilBody;

    @Value("${citymobil.url}")
    private String citymobilUrl;

    private final RestTemplate restTemplate;

    @SneakyThrows
    @PostConstruct
    public void initBody() {
        body = new ObjectMapper().readValue(citymobilBody, HashMap.class);
    }

    @Override
    public List<RidePrice> getRideInfo(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> body = getBody(startPoint, endPoint);
        ResponseEntity<String> exchange = restTemplate.exchange(citymobilUrl, HttpMethod.POST, new HttpEntity<>(body), String.class);
        return extractPrises(exchange.getBody());
    }

    private Map<String, Object> getBody(Coordinates startPoint, Coordinates endPoint) {
        Map<String, Object> res = new HashMap(body);
        res.put(LATITUDE, startPoint.getLongitude());
        res.put(LONGITUDE, startPoint.getLatitude());
        res.put(DEL_LATITUDE, endPoint.getLongitude());
        res.put(DEL_LONGITUDE, endPoint.getLatitude());
        return res;
    }

    @SneakyThrows
    private List<RidePrice> extractPrises(String jsonSource) {

        List<RidePrice> ridePriceList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonSource);
        JSONArray jsonArray = jsonObject.getJSONArray("prices");

        for (int i = 0; i < jsonArray.length(); i++) {
            int price = jsonArray.getJSONObject(i).getInt("price");
            String classTaxiName = jsonArray.getJSONObject(i).getJSONObject("tariff_info").getString("name");

            ridePriceList.add(
                    RidePrice.builder()
                            .price(price)
                            .classTaxi(classTaxiName)
                            .aggregator(Aggregator.CITY_MOBIL)
                            .build()
            );
        }
        return ridePriceList;
    }
}
