package com.party.partydrawandroid;


import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;







public class Main extends Fragment implements OnItemSelectedListener {
	
	private WordPair pair;
	private Random random;
	private Spinner spinner;
    private static final String[] options = {"3 players", "4 players", "5 players", "6 players"};
    private int ids[] = {R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5, R.id.player6}; 
    private int playerAmmount;
    private int request;
    private String[] wordPair;
    private int answer;
    private View main;
    private ArrayList<Player> playerlist;
    private MediaPlayer mp;
    
    
    Button start, quit;
    Fragment fragment = this;
    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		main = inflater.inflate(R.layout.main_frag, container, false);

		mp = MediaPlayer.create(getActivity(), R.raw.click);
		
		pair = new WordPair();
		random = new Random();
		playerlist = new ArrayList<Player>();

		createSpinner(main);
        setVisibility();
        
        AdView adView = (AdView)main.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        
        start = (Button) main.findViewById(R.id.startButton);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	mp.start();
            	playGame(view);
            }
        });
        
        quit = (Button) main.findViewById(R.id.quitButton);
        quit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	mp.start();
            	quitGame(view);
            }
        });
        
        
       return main;
	}
	


	private void createSpinner(View main) {
		spinner = (Spinner)main.findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
	}

	private void setVisibility() {
		main.findViewById(R.id.player4).setVisibility(View.GONE);
		main.findViewById(R.id.player5).setVisibility(View.GONE);
		main.findViewById(R.id.player6).setVisibility(View.GONE);
	}


	
	public void quitGame(View view){   
		AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Quit Game?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	mp.start();
                getActivity().finish();
            }
        });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	mp.start();
                dialog.cancel();
            }
        }).create().show();


	}
	
	public void playGame(View view){
		request = 0;
		wordPair = pair.getPair();
		if(pair.getSize() < 5){
			pair = new WordPair();
		}
		answer = random.nextInt(playerAmmount);

		String[] playerNames = playersToArray();		
		playerNamesToArray(playerlist, playerNames);
		
		Intent intent = new Intent(getActivity(), PrepareActivity.class);
		fragment.startActivityForResult(intent, request);
		
	}
	


	private void playerNamesToArray(ArrayList<Player> playerlist, String[] playerNames) {
		for(int i = 0; i < playerAmmount; i++){
			if(playerlist.size() > i){
				if(!playerlist.get(i).getName().equals(playerNames[i])){
					playerlist.set(i ,new Player(playerNames[i], i));
				}				
			} else{
				playerlist.add(i ,new Player(playerNames[i], i));
			}
			
		}
	}

	private String[] playersToArray() {
		String[] playerNames = new String[6];
		for (int i = 0; i < playerNames.length; i++){
			EditText player = (EditText) main.findViewById(ids[i]);	
			playerNames[i] = replaceEmpty(player.getText().toString(), i);
		}
		return playerNames;
	}
	
	private String replaceEmpty(String name, int i){
		if(name.trim().length() != 0){
			return name;
		} else {
			return "Player" + (i+1);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == request) {
	    	if(resultCode == Activity.RESULT_OK){
	    		playerlist.get(request-playerAmmount-1).addPoints(1);
	    	}
	    	if(request < playerAmmount){
	    		startDrawActivity();
	    	} else if(request < playerAmmount * 2){
	    		startGuessPictureActivity();
	    	} else if (request == playerAmmount * 2){
	    		startCorrectAnswerActivity();
	    	} else if(request == playerAmmount * 2 + 1){
	    		startDisplayScoresAcitivty();
	    	} 
	    	
	    }
	}

	private void startDisplayScoresAcitivty(){
		Intent intent = new Intent(getActivity(), DisplayScoresActivity.class);
		intent.putExtra("playerAmmount", playerAmmount);
		for (int i = 0; i < playerAmmount ; i++){
			intent.putExtra("player" + (i+1), playerlist.get(i));
		}
		startActivity(intent);
	}
	
	private void startCorrectAnswerActivity() {
		Intent intent = new Intent(getActivity(), CorrectAnswerActivity.class);
		intent.putExtra("playerAmmount", playerAmmount);
		intent.putExtra("player", playerlist.get(answer));
		for(int i = 0; i < playerAmmount; i++){
			intent.putExtra("player" + i, playerlist.get(i).getName());
		}
		intent.putExtra("index", answer);
		intent.putExtra("correct", wordPair[0]);
		intent.putExtra("incorrect", wordPair[1]);
		request++;
		startActivityForResult(intent, request);
	}

	private void startGuessPictureActivity() {
		Intent intent = new Intent(getActivity(), GuessPictureActivity.class);
		intent.putExtra("player", playerlist.get(request-playerAmmount));
		intent.putExtra("playerAmmount", playerAmmount);
		intent.putExtra("answer", answer);
		request++;
		startActivityForResult(intent, request);
	}

	private void startDrawActivity() {
		Intent intent = new Intent(getActivity(), DrawActivity.class);
		intent.putExtra("player", playerlist.get(request));
		intent.putExtra("index", request);
		if(request == answer){
			intent.putExtra("word", wordPair[0]);
		} else {
			intent.putExtra("word", wordPair[1]);
		}
		request++;
		startActivityForResult(intent, request);
	}
	
	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		playerAmmount = 3 + position;
        switch (position) {
            case 0:
			setVisibility();
                break;
            case 1:
            	main.findViewById(R.id.player4).setVisibility(View.VISIBLE);
            	main.findViewById(R.id.player5).setVisibility(View.GONE);
            	main.findViewById(R.id.player6).setVisibility(View.GONE);
                break;
            case 2:
            	main.findViewById(R.id.player4).setVisibility(View.VISIBLE);
            	main.findViewById(R.id.player5).setVisibility(View.VISIBLE);
            	main.findViewById(R.id.player6).setVisibility(View.GONE);
                break;
            case 3:
            	main.findViewById(R.id.player4).setVisibility(View.VISIBLE);
            	main.findViewById(R.id.player5).setVisibility(View.VISIBLE);
            	main.findViewById(R.id.player6).setVisibility(View.VISIBLE);
                break;

        }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	


	
}
