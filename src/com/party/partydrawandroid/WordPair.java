package com.party.partydrawandroid;

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
		add("Cloud","Smoke");
		add("Person escaping","Person jogging");
		add("Person falling","Person jumping");
		add("Little girl","Doll");
		add("Hallway","Room");
		add("Ferris wheel","Bicycle Wheel");
		add("Basketball","Bowling ball");
		add("Watermelon","Half moon");
		add("Partyhat","Traffic cone");
		add("Spoon","Tennis racket");
		add("Pillow","Bar soap");
		add("Soda bottle","Bowling pin");
		add("Marble ball","Globe");
		add("Pyramid","Traffic cone");	
		add("Glass","Vase");
		add("Sword","Dagger");
		add("Sock","Shoe");
		add("Hole","Cave");
		add("Married couple","Divorced couple");
		add("Television","Painting");
		add("Hand","Glove");
		add("Painting","Nature");
		add("Explosion","Fire");
		add("Corgi","Wiener dog");
		add("Painting","Nature");
		add("Window","Painting");
		add("Hospital","Pharmacy");
		add("Banjo","Guitar");
		add("Cherry","Apple");
		add("Apple","Plum");
		add("Banana","Crescent moon");
		add("Bucket","Trash can");
		add("Llama","Camel");
		add("Tiger","Panther");
		add("Elk","Antelope");
		add("Bucket","Trash can");
		add("Person sleeping","Dead person");
		add("Painter","Windor cleaner");
		add("Person swimming","Person drowning");
		add("Squid","Jellyfish");
		add("Person shouting","Person yawning");
		add("President","Lawyer");
		add("Dessert","Cake");
		add("Desert","Cactus");
		add("Adults","Parents");
		add("Heaven","Sky");
		add("Flamingo","Crane");
		add("Missile","Rocket");
		add("Explosion","New year");
		add("Surfing","Wave");
		add("Wave","Greet");
		add("Rain","Shower");
		add("Letter","Email");
		add("Person reading","Person thinking");
		add("Person praying","Person kneeling");
		add("Palm tree","Beach");
		add("Jungle","Tree");
		add("Fighting","Boxing");
		add("Praying","Kneeling");
		add("Shark","Dolphin");
		add("Ring","Jewelry");
		add("Clapping","Praying");
		add("Surprised","Horrified");
		add("Pluto","Mars");
		add("Printer","Shredder");
		add("Mosquito","Fly");
		add("Map","Europe");
		add("Trumpet","Saxophone");
		add("Notebook","Newspaper");
		add("Scream","Sing");
		add("Dirty","Clean");
		add("Bewerage","Wine");
		add("","");
		
		
	}
	
	public void add(String a, String b){
		String[] word = {a, b};
		wordList.add(word);
	}
	
	public String[] getPair(){
		int index = random.nextInt(wordList.size());
		String[] returned = wordList.get(index);
		wordList.remove(index);
		return returned;
	}
	
	public int getSize(){
		return wordList.size();
	}

}
