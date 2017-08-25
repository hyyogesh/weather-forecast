package io.yogesh.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.exception.NotFoundException;
import io.yogesh.api.repository.WeatherRepository;
import io.yogesh.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	private WeatherRepository weatherRepo;
	
	
	public WeatherServiceImpl(WeatherRepository weatherRepo) {
		this.weatherRepo=weatherRepo;
	}

	@Override
	public WeatherReading create(WeatherReading reading) {
		return weatherRepo.create(reading);
		
	}

	@Override
	public Set getCityList() {
		return weatherRepo.getCityList();
	}

	@Override
	public WeatherReading getCityWeather(String city) {
		return weatherRepo.getCityWeather(city)
				.orElseThrow(()-> new NotFoundException("No details available for Given city"));
	}

	@Override
	public List<WeatherReading> getWeatherProp(String city, String weatherProp) {
		return weatherRepo.getWeatherProp(city,weatherProp);
	}

}
