package io.yogesh.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.exception.BadRequestException;
import io.yogesh.api.exception.NotFoundException;
import io.yogesh.api.repository.WeatherRepository;
import io.yogesh.api.repository.WeatherRepositoryCustom;
import io.yogesh.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private WeatherRepository weatherRepo;
		
	public WeatherServiceImpl(WeatherRepository weatherRepo) {
		this.weatherRepo=weatherRepo;
	}

	@Override
	public WeatherReading create(WeatherReading reading) {
		weatherRepo.save(reading.getWind());
		return weatherRepo.save(reading);
	}

	@Override
	public List<WeatherReading> getCityList() {
		return weatherRepo.findDistinctCity();
	}

	@Override
	public WeatherReading getCityWeather(String city) {
		return weatherRepo.findFirstByCityOrderByTimestampDesc(city)
				.orElseThrow(()-> new NotFoundException("No details available for Given city"));
	}

	@Override
	public Object getWeatherProp(String city, String weatherProp) {
		return weatherRepo.getWeatherProp(city,weatherProp)
				.orElseThrow(()-> new NotFoundException("No details available for Given city"));
	}

	@Override
	public WeatherReading getHourlyCityWeather(String city) {
		return weatherRepo.getHourlyCityWeather(city).orElseThrow(()-> new NotFoundException("No details available for Given city"));

	}

	@Override
	public WeatherReading getDailyCityWeather(String city) {
		return weatherRepo.getDailyCityWeather(city).orElseThrow(()-> new NotFoundException("No details available for Given city"));
	}

}
