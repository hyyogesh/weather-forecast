package io.yogesh.api.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.service.WeatherService;

@RestController
@Api(value="Weather Data Controller")
@RequestMapping(value="weather")
@CrossOrigin("http://mocker.egen.io")
public class WeatherDataController {
	
	private WeatherService weatherService;
	
	public WeatherDataController(WeatherService weatherService) {
		this.weatherService=weatherService;		
	}
	
	// Getting the data and store it.
	@RequestMapping(method=RequestMethod.POST )
	public WeatherReading create(@RequestBody WeatherReading reading){
		return weatherService.create(reading);
	}
	
	//Get the list of cities.
	@RequestMapping(method=RequestMethod.GET)
	public Set cityList(){
		return weatherService.getCityList();
	}
	
	//Get the weather of city.
	@RequestMapping(method=RequestMethod.GET , value="{city}")
	public WeatherReading cityWeather(@PathVariable("city") String city){
		return weatherService.getCityWeather(city);
	}
	
	//Get the weather Property of city.
	@RequestMapping(method=RequestMethod.GET , value="{city}/{weatherProperty}")
	public List<WeatherReading> cityWeatherProperty(@PathVariable("city") String city, @PathVariable("weatherProperty") String weatherProp){
		return weatherService.getWeatherProp(city,weatherProp);
	}
	

}
