package com.example.partydrawandroid;

import java.util.ArrayList;

public class Controller {
	
	private ArrayList<Player> players;
	
	public Controller(){
		this.setPlayers(new ArrayList<Player>());
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}	

}
