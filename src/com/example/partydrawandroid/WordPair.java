package com.example.partydrawandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordPair {
	
	List<String[]> wordList = new ArrayList<String[]>();
	Random random;
	
	public WordPair(){
		random = new Random();
		add("Dog","Cat");
		add("Cow","Bull");
		add("Stick","Cane");
		add("Giraffe","Camel");
		add("Fireman","Doctor");
		add("Mansion","House");
		add("Sunset","Landscape");
		add("Man","Woman");
		add("Pencil","Spike");
		add("Closet","Door");
		add("Tree","Bush");
		add("Guitar","Bass");
		add("Explosion","Cloud");
		add("Zeppelin","Balloon");
		add("Plane","Missile");
		add("Pig","Cow");
		add("Shark","Salmon");
		add("Strawberry","Raspberry");
		add("Gorilla","Human");
		add("Sock","Shoe");
		
	}
	
	public void add(String a, String b){
		String[] word = {a, b};
		wordList.add(word);
	}
	
	public String[] getPair(){
		return wordList.get(random.nextInt(wordList.size()));
	}
	

}
