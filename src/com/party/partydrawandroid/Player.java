package com.party.partydrawandroid;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	
	private String name;
	private int points;
	private int guess;
	private int index;
	
	public Player(String name, int index){
		this.setName(name);
		this.points = 0;
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
			this.points += points;
	}

	public int getGuess() {
		return guess;
	}

	public void setGuess(int guess) {
		this.guess = guess;
	}
	
	public String toString(){
		return this.name;
	}

}
