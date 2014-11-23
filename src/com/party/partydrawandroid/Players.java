package com.party.partydrawandroid;

import java.util.ArrayList;

public class Players {
	
	private ArrayList<Player> players;
	
	public Players(){
		this.setPlayers(new ArrayList<Player>());
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	

}
