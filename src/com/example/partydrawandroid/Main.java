package com.example.partydrawandroid;


import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Switch;
import android.widget.TextView;


public class Main extends Fragment implements OnItemSelectedListener {
	

	private WordPair pair;
	private Random random;
	private Spinner spinner;
    private static final String[]options = {"3 players", "4 players", "5 players", "6 players"};
    private int playerAmmount;
    private int request;
    private EditText player1, player2, player3, player4, player5,player6;
    private String[] wordPair;
    private int answer;
    private View main;
    private ArrayList<Player> playerlist;
    
    
    Button start;
    Button quit;
    Fragment fragment = this;
    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		main = inflater.inflate(R.layout.main_frag, container, false);

		pair = new WordPair();
		random = new Random();
		playerlist = new ArrayList<Player>();
		
		createSpinner(main);
		getEditTextFields();
        setVisibility();
        
        start = (Button) main.findViewById(R.id.startButton);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	playGame(view);
            }
        });
        
        quit = (Button) main.findViewById(R.id.quitButton);
        quit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
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
		player4.setVisibility(View.GONE);
        player5.setVisibility(View.GONE);
        player6.setVisibility(View.GONE);
	}

	private void getEditTextFields() {		
		player1 = (EditText) main.findViewById(R.id.player1);   
        player2 = (EditText) main.findViewById(R.id.player2);
        player3 = (EditText) main.findViewById(R.id.player3); 
        player4 = (EditText) main.findViewById(R.id.player4);  
        player5 = (EditText) main.findViewById(R.id.player5);
        player6 = (EditText) main.findViewById(R.id.player6);
	}
	
	public void quitGame(View view){
		AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Quit Game?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getActivity().finish();
            }
        });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).create().show();


	}
	
	public void playGame(View view){
		request = 0;
		wordPair = pair.getPair();	
		answer = random.nextInt(playerAmmount);

		String[] playerNames = playersToArray();		
		playerNamesToArray(playerlist, playerNames);
		
		Intent intent = new Intent(getActivity(), PrepareActivity.class);
		fragment.startActivityForResult(intent, request);
		
	}


	private void playerNamesToArray(ArrayList<Player> playerlist, String[] playerNames) {
		System.out.println(playerlist.toString());
		for(int i = 0; i < playerAmmount; i++){
			System.out.println(playerAmmount);
			if(playerlist.size() == playerAmmount && 
					(playerlist.get(i).getName().equals(playerNames[i]) 
							|| (playerlist.get(i).getName().equals("Player"+(i+1)) 
									&& playerNames[i].trim().length() == 0) ) ){
				continue;
				
			} else if(playerNames[i].trim().length() != 0){
				playerlist.add(i ,new Player(playerNames[i], i));
			} else{
				playerlist.add(i, new Player("Player" + (i+1), i ));
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
	    	if(resultCode == Activity.RESULT_OK){
	    		playerlist.get(request-playerAmmount-1).addPoints(1);
	    	}
	    	if(request < playerAmmount){
	    		startDrawActivity();
	    	} else if(request < playerAmmount * 2){
	    		startGuessPictureActivity();
	    	} else if (request == playerAmmount * 2){
	    		startCorrectAnswerActivity();
	    	} else {
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
		intent.putExtra("player", playerlist.get(answer));
		intent.putExtra("index", answer);
		intent.putExtra("word", wordPair[0]);
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
