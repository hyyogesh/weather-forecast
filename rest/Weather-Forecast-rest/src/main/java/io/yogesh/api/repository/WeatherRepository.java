package io.yogesh.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import io.yogesh.api.entity.WeatherReading;



public interface WeatherRepository {
	WeatherReading create(WeatherReading reading);

	Set getCityList();

	Optional<WeatherReading> getCityWeather(String city);

	List<WeatherReading> getWeatherProp(String city, String weatherProp);
}
