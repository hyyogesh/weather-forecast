package io.yogesh.api.repositoryImpl;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.internal.util.ZonedDateTimeComparator;
import org.springframework.beans.propertyeditors.ZoneIdEditor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl  implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public WeatherReading create(WeatherReading reading) {
		// TODO Auto-generated method stub
		em.persist(reading.getWind());
		em.persist(reading);
		return reading;
	}

	@Override
	public Set getCityList() {
		Query tq = em.createNamedQuery("weather.getCityList");
		HashSet hst = new HashSet(tq.getResultList());
		return hst;
	}

	@Override
	public Optional<WeatherReading> getCityWeather(String city) {
		Query tq = em.createNamedQuery("weather.getCityWeather");
		tq.setParameter("city", city);
		tq.setMaxResults(1);
		List<WeatherReading> wlist = tq.getResultList();
		if(!wlist.isEmpty()){
			return Optional.of(wlist.get(0));
		}
		else{
			return Optional.empty();
		}
	}

	@Override
	public Optional<WeatherReading> getWeatherProp(String wcity, String weatherProp) {
		TypedQuery<WeatherReading> tqw = em.createQuery("select w."+weatherProp+" from WeatherReading w where w.city=:city ORDER BY w.timestamp desc",WeatherReading.class).setParameter("city",wcity);
		tqw.setMaxResults(1);
		List<WeatherReading> wl = tqw.getResultList();
		if(!wl.isEmpty()){
			return Optional.of(wl.get(0));
		}
		else{
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<WeatherReading> findByCity(String city) {
		return getCityWeather(city);
	}

	@Override
	public Optional<WeatherReading> getHourlyCityWeather(String city) {
		
		Instant timeStamp = Instant.now();
		Duration d = Duration.of(1,ChronoUnit.HOURS);
		Instant time=timeStamp.minus(d);
		System.out.println("time is : "	+time.toString());
	
		TypedQuery<Long> rowCount = em.createQuery("select COUNT(w.id) as id from WeatherReading w where w.city=:city and w.timestamp > :time",Long.class).setParameter("city",city).setParameter("time",time.toString());
		List<Long> wl = rowCount.getResultList();
		
		if(wl.get(0)!=0){
			Random r = new Random();
			int rowNumber = r.nextInt((int) (wl.get(0)-0));
			System.out.println("WeatherReading Row Number is : " +rowNumber);
			TypedQuery<WeatherReading> tqw = em.createQuery("select w from WeatherReading w where w.city=:city and w.timestamp > :time ORDER BY w.timestamp",WeatherReading.class).setParameter("city",city).setParameter("time",time.toString());
			List<WeatherReading> wlist = tqw.getResultList();
			return Optional.of(wlist.get(rowNumber));
		}
		else{
			return Optional.empty();
		}
	}

	@Override
	public Optional<WeatherReading> getDailyCityWeather(String city) {
		Instant timeStamp = Instant.now();
		Duration d = Duration.of(1,ChronoUnit.DAYS);
		Instant time=timeStamp.minus(d);
		System.out.println("time is : "	+time.toString());
	
		TypedQuery<Long> rowCount = em.createQuery("select COUNT(w.id) as id from WeatherReading w where w.city=:city and w.timestamp > :time",Long.class).setParameter("city",city).setParameter("time",time.toString());
		List<Long> wl = rowCount.getResultList();
		
		if(wl.get(0)!=0){
			Random r = new Random();
			int rowNumber = r.nextInt((int) (wl.get(0)-0));
			System.out.println("WeatherReading Row Number is : " +rowNumber);
			TypedQuery<WeatherReading> tqw = em.createQuery("select w from WeatherReading w where w.city=:city and w.timestamp > :time ORDER BY w.timestamp",WeatherReading.class).setParameter("city",city).setParameter("time",time.toString());
			List<WeatherReading> wlist = tqw.getResultList();
			return Optional.of(wlist.get(rowNumber));
		}
		else{
			return Optional.empty();
		}
	}
}