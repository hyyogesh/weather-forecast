package io.yogesh.api.repository;

import java.util.Optional;

import io.yogesh.api.entity.WeatherReading;

public interface WeatherRepositoryCustom {
	
	public Optional<Object> getWeatherProp(String city, String weatherProp);
	
	public Optional<WeatherReading> getHourlyCityWeather(String city);

	public Optional<WeatherReading> getDailyCityWeather(String city);


}
