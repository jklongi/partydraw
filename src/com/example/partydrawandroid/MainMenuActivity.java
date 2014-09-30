package com.example.partydrawandroid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.partydrawandroid.R;


public class MainMenuActivity extends Activity  implements OnItemSelectedListener {
	
	private Players players;
	
	private Spinner spinner;
    private static final String[]paths = {"Three", "Four", "Five", "Six"};
    private int playerAmmount;
    private int request;
    EditText player1;
    EditText player2;
    EditText player3;
    EditText player4;
    EditText player5;
    EditText player6;
    
    Button start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    //getActionBar().hide();
        setContentView(R.layout.activity_main_menu);
        
        players = new Players();
        
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainMenuActivity.this,
        android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        player1 = (EditText) findViewById(R.id.player1);   
        player2 = (EditText) findViewById(R.id.player2);
        player3 = (EditText) findViewById(R.id.player3);
        
        player4 = (EditText) findViewById(R.id.player4);
        player4.setVisibility(View.GONE);
        
        player5 = (EditText) findViewById(R.id.player5);
        player5.setVisibility(View.GONE);
        
        player6 = (EditText) findViewById(R.id.player6);
        player6.setVisibility(View.GONE);
        
        start = (Button) findViewById(R.id.startButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		this.playerAmmount = 3 + position;
        switch (position) {
            case 0:
            	player4.setVisibility(View.GONE);
            	player5.setVisibility(View.GONE);
            	player6.setVisibility(View.GONE);
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
	
	public void playGame(View view){
		request = 0;
		
		ArrayList<Player> playerlist = new ArrayList<Player>();
		String[] playerNames = {
    			player1.getText().toString(),
    			player2.getText().toString(),
    			player3.getText().toString(),
    			player4.getText().toString(),
    			player5.getText().toString(),
    			player6.getText().toString()
    			};
    	
		for(int i = 0; i < playerAmmount; i++){
			if(playerNames[i].trim().length() != 0){
				playerlist.add(new Player(playerNames[i]));
			} else{
				playerlist.add(new Player("Player" + (i+1) ));
			}
		}
		
		players.setPlayers(playerlist);
		
		Intent intent = new Intent(MainMenuActivity.this, DrawActivity.class);
		intent.putExtra("name", playerlist.get(0).getName());
		startActivityForResult(intent, request);
		
	
		//Intent guess = new Intent(this, GuessPictureActivity.class);
		//startActivity(guess);
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == request) {
	    	request++;
	    	System.out.println(playerAmmount);
	    	System.out.println(request);
	    	if(request < playerAmmount){
	    		Intent intent = new Intent(MainMenuActivity.this, DrawActivity.class);
				intent.putExtra("name", players.getPlayers().get(request).getName());
				startActivityForResult(intent, request);
	    	} else if(request < playerAmmount * 2){
	    		Intent intent = new Intent(MainMenuActivity.this, GuessPictureActivity.class);
				intent.putExtra("name", players.getPlayers().get(request-playerAmmount).getName());
				startActivityForResult(intent, request);
	    	}
	    }
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
