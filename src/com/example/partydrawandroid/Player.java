package com.example.partydrawandroid;

public class Player {
	
	private String name;
	private int points;
	private int guess;
	
	public Player(String name){
		this.setName(name);
		this.setPoints(0);	
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

	public void setPoints(int points) {
		this.points = points;
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
