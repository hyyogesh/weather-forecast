package io.yogesh.api.repository;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.entity.Wind;



public interface WeatherRepository extends Repository<WeatherReading, String>,WeatherRepositoryCustom{
	
	public void save(Wind wind);
	
	public WeatherReading save(WeatherReading reading);
	
	@Query("SELECT Distinct(w.city) FROM WeatherReading w")
	public List<WeatherReading> findDistinctCity();
	
	public Optional<WeatherReading> findFirstByCityOrderByTimestampDesc(String city);
}
