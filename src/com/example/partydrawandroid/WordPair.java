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
		
	}
	
	public void add(String a, String b){
		String[] word = {a, b};
		wordList.add(word);
	}
	
	public String[] getPair(){
		return wordList.get(random.nextInt(wordList.size()));
	}
	

}
