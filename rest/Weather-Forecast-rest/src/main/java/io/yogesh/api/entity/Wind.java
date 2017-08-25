package io.yogesh.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wind {
	@Id
	private String id;
	private float speed;
	private int degree;
	
	
	public Wind() {
		this.id=UUID.randomUUID().toString();
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	
	

}
