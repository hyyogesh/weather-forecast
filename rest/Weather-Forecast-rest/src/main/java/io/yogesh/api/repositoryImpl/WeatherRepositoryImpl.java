package io.yogesh.api.repositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	public List<WeatherReading> getWeatherProp(String wcity, String weatherProp) {
		Query tqw = em.createQuery("select w."+weatherProp+" from WeatherReading w where w.city=:city ORDER BY w.timestamp desc").setParameter("city",wcity);
		tqw.setMaxResults(1);
		return tqw.getResultList();
	}

}
