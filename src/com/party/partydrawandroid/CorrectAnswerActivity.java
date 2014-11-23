package com.party.partydrawandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CorrectAnswerActivity extends Activity {
	
	private String correct;
	private String incorrect;
	private Player player;
	
	private final int[] layoutIds = {R.id.correctView1, R.id.correctView2, R.id.correctView3,
			R.id.correctView4, R.id.correctView5, R.id.correctView6};
	private final int[] textIds = {R.id.correctText1, R.id.correctText2, R.id.correctText3,
			R.id.correctText4, R.id.correctText5, R.id.correctText6};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_correct_answer);
		
		Intent intent = getIntent();
		this.player = (Player) intent.getSerializableExtra("player");
		correct = intent.getStringExtra("correct");
		incorrect = intent.getStringExtra("incorrect");
		int playerAmmount = intent.getIntExtra("playerAmmount", 0);
		
		setVisibility(playerAmmount);
		
		String[] playernames = getPlayerNames(intent, playerAmmount);
		
		setPlayerNameToTextField();
		
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		
		for(int i = 0; i < playerAmmount; i ++){
			try {
		        File f=new File(directory, "player" + i + ".png");
		        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
		        
		        LinearLayout layout = (LinearLayout)findViewById(layoutIds[i]);
		        TextView text = (TextView)findViewById(textIds[i]);
		        
		        if(i == player.getIndex()){
		        	text.setText(correct + " by " + playernames[i]);
		        	text.setTextSize(20);
		        	text.setTextColor(Color.parseColor("#99CC00"));
		        } else {
		        	text.setText(incorrect + " by " + playernames[i]);
		        }
		        
		        
		        Drawable d = new BitmapDrawable(getResources(),b);
		        layout.setBackground(d);
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		        System.out.println("IMAGE LOAD NOT SUCCESFUL");
		    }
		}
			
		
	}

	private void setPlayerNameToTextField() {
		TextView view = (TextView)findViewById(R.id.correctTextView);
		view.setText("Correct Answer was " + "'" + correct + "'" + " by " + player.getName() + "!");
	}

	private String[] getPlayerNames(Intent intent, int playerAmmount) {
		String[] playernames = new String[6]; 
		
		for(int i = 0; i < playerAmmount; i++){
			playernames[i] = intent.getStringExtra("player" + i);
		}
		return playernames;
	}

	private void setVisibility(int playerAmmount) {
		if(playerAmmount < 5){
			findViewById(R.id.correctView5).setVisibility(View.GONE);
			
		}
		if(playerAmmount < 6){
			findViewById(R.id.correctView6).setVisibility(View.GONE);
		}
	}
	
	public void setViews(){

	}
	
	public void finished(View view){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.correct_answer, menu);
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
	public void onBackPressed() {
	}
	
}
