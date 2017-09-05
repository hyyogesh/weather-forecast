package io.yogesh.api.repositoryImpl;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.yogesh.api.entity.WeatherReading;
import io.yogesh.api.repository.WeatherRepositoryCustom;

public class WeatherRepositoryImpl implements WeatherRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Object> getWeatherProp(String wcity, String weatherProp) {
		TypedQuery<Object> tqw = em.createQuery("select w."+weatherProp+" from WeatherReading w where w.city=:city ORDER BY w.timestamp desc",Object.class).setParameter("city",wcity);
		tqw.setMaxResults(1);
		List<Object> wl = tqw.getResultList();
		if(!wl.isEmpty()){
			return Optional.of(wl.get(0));
		}
		else{
			return Optional.empty();
		}
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
