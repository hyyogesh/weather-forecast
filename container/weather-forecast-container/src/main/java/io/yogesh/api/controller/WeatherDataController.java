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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.service.WeatherService;

@RestController
@Api(value="Weather Data Controller")
@RequestMapping(value="weather")
@CrossOrigin("http://mocker.egen.io")
@ApiResponses(value={
		@ApiResponse(code=200,message="Success!"),
		@ApiResponse(code=400,message="Bad Request"),
		@ApiResponse(code=404,message="Not Found"),
		@ApiResponse(code=500,message="Internal Server Error")
})

public class WeatherDataController {
	
	private WeatherService weatherService;
	
	public WeatherDataController(WeatherService weatherService) {
		this.weatherService=weatherService;		
	}
	
	// Getting the data and store it.
	@ApiOperation(value="Create weather data", notes="Create weather Data in app")
	@RequestMapping(method=RequestMethod.POST )
	public WeatherReading create(@RequestBody WeatherReading reading){
		return weatherService.create(reading);
	}
	
	//Get the list of cities.
	@ApiOperation(value="Find all city", notes="Get all city list")
	@RequestMapping(method=RequestMethod.GET)
	public List<WeatherReading> cityList(){
		return weatherService.getCityList();
	}
	
	//Get the weather of city.
	@ApiOperation(value="Find latest weather of city", notes="Get weather for given city")
	@RequestMapping(method=RequestMethod.GET , value="{city}")
	public WeatherReading cityWeather(@PathVariable("city") String city){
		return weatherService.getCityWeather(city);
	}
	
	//Get the weather Property of city.
	@ApiOperation(value="Find weather property of city ", notes="Get property of weather for given city")
	@RequestMapping(method=RequestMethod.GET , value="{city}/{weatherProperty}")
	public Object cityWeatherProperty(@PathVariable("city") String city, @PathVariable("weatherProperty") String weatherProp){
		return weatherService.getWeatherProp(city,weatherProp);
	}
	
	//Get the Average Hourly weather for given city. 
	@ApiOperation(value="Find hourly weather", notes="Get average hourly weather for given city")
	@RequestMapping(method=RequestMethod.GET , value="hourly/{city}")
	public WeatherReading hourlyCityWeather(@PathVariable("city") String city){
		return weatherService.getHourlyCityWeather(city);
	}
	
	//Get the Average Daily weather for given city. 
	@ApiOperation(value="Find daily weather", notes="Get average daily weather for given city")
	@RequestMapping(method=RequestMethod.GET , value="daily/{city}")
	public WeatherReading daillyCityWeather(@PathVariable("city") String city){
		return weatherService.getDailyCityWeather(city);
	}
}
