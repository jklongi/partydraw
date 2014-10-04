package com.example.partydrawandroid;


import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
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


public class Main extends Fragment implements OnItemSelectedListener {
	
	private Players players;
	private Spinner spinner;
    private static final String[]options = {"3 players", "4 players", "5 players", "6 players"};
    private int playerAmmount;
    private int request;
    private EditText player1, player2, player3, player4, player5,player6;
    private String[] wordPair;
    private int answer;
    Button b1;
    Fragment fragment = this;
    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View main = inflater.inflate(R.layout.main_frag, container, false);
		players = new Players();	
		createSpinner(main);
		getEditTextFields(main);
        setVisibility();
        
        b1 = (Button) main.findViewById(R.id.startButton);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	playGame(view);
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
		player4.setVisibility(View.GONE);
        player5.setVisibility(View.GONE);
        player6.setVisibility(View.GONE);
	}

	private void getEditTextFields(View main) {
		player1 = (EditText) main.findViewById(R.id.player1);   
        player2 = (EditText) main.findViewById(R.id.player2);
        player3 = (EditText) main.findViewById(R.id.player3); 
        player4 = (EditText) main.findViewById(R.id.player4);  
        player5 = (EditText) main.findViewById(R.id.player5);
        player6 = (EditText) main.findViewById(R.id.player6);
	}
	
	public void playGame(View view){
		request = 0;
		WordPair pair = new WordPair();
		wordPair = pair.getPair();	
		Random random = new Random();
		answer = random.nextInt(playerAmmount);		
		setPlayerList();
		
		Intent intent = new Intent(getActivity(), PrepareActivity.class);
		fragment.startActivityForResult(intent, request);
		
	}

	private void setPlayerList() {
		ArrayList<Player> playerlist = new ArrayList<Player>();
		String[] playerNames = playersToArray();		
		playerNamesToArray(playerlist, playerNames);		
		players.setPlayers(playerlist);
	}

	private void playerNamesToArray(ArrayList<Player> playerlist, String[] playerNames) {
		for(int i = 0; i < playerAmmount; i++){
			if(playerNames[i].trim().length() != 0){
				playerlist.add(new Player(playerNames[i], i));
			} else{
				playerlist.add(new Player("Player" + (i+1), i ));
			}
		}
	}

	private String[] playersToArray() {
		String[] playerNames = {
    			player1.getText().toString(),
    			player2.getText().toString(),
    			player3.getText().toString(),
    			player4.getText().toString(),
    			player5.getText().toString(),
    			player6.getText().toString()
    	};
		return playerNames;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == request) {
	    	if(request < playerAmmount){
	    		startDrawActivity();
	    	} else if(request < playerAmmount * 2){
	    		startGuessPictureActivity();
	    	} else {
	    		startCorrectAnswerActivity();
	    	}
	    }
	}

	private void startCorrectAnswerActivity() {
		Intent intent = new Intent(getActivity(), CorrectAnswerActivity.class);
		intent.putExtra("player", players.getPlayers().get(answer));
		intent.putExtra("index", answer);
		intent.putExtra("word", wordPair[0]);
		startActivity(intent);
	}

	private void startGuessPictureActivity() {
		Intent intent = new Intent(getActivity(), GuessPictureActivity.class);
		intent.putExtra("player", players.getPlayers().get(request-playerAmmount));
		intent.putExtra("playerAmmount", playerAmmount);
		intent.putExtra("answer", answer);
		request++;
		startActivityForResult(intent, request);
	}

	private void startDrawActivity() {
		Intent intent = new Intent(getActivity(), DrawActivity.class);
		intent.putExtra("player", players.getPlayers().get(request));
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
            	player4.setVisibility(View.VISIBLE);
            	player5.setVisibility(View.GONE);
            	player6.setVisibility(View.GONE);
                break;
            case 2:
            	player4.setVisibility(View.VISIBLE);
            	player5.setVisibility(View.VISIBLE);
            	player6.setVisibility(View.GONE);
                break;
            case 3:
            	player4.setVisibility(View.VISIBLE);
            	player5.setVisibility(View.VISIBLE);
            	player6.setVisibility(View.VISIBLE);
                break;

        }
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}
