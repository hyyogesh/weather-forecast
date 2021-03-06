package io.yogesh.api.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.yogesh.api.entity.WeatherReading;


public interface WeatherService {

	WeatherReading create(WeatherReading reading);

	Set getCityList();

	WeatherReading getCityWeather(String city);

	WeatherReading getWeatherProp(String city, String weatherProp);

	WeatherReading getHourlyCityWeather(String city);

	WeatherReading getDailyCityWeather(String city);


}
