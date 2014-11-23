package com.party.partydrawandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class GuessPictureActivity extends Activity {
	
	private Player player;
	private int playerAmmount;
	private int answer;
	private LinearLayout[] layouts;
	
	private final int[] layoutIds = {R.id.layoutView1, R.id.layoutView2, R.id.layoutView3,
			R.id.layoutView4, R.id.layoutView5, R.id.layoutView6};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_guess_picture);
		
		Intent intent = getIntent();
		this.player = (Player) intent.getSerializableExtra("player");
		playerAmmount = intent.getIntExtra("playerAmmount", 0);
		this.answer = intent.getIntExtra("answer", 0);
		setLayoutVisibility();
		getLayouts();
		showReadyAlert();
		

	}

	private void setLayoutVisibility() {
		if(playerAmmount < 4){
			findViewById(R.id.layoutView4).setClickable(false);
		}
		if(playerAmmount < 5){
			findViewById(R.id.layoutView5).setVisibility(View.GONE);
		}
		if(playerAmmount < 6){
			findViewById(R.id.layoutView6).setVisibility(View.GONE);
		}
	}

	private void showReadyAlert() {
		AlertDialog dialog = new AlertDialog.Builder(this)
        .setTitle("Guess picture")
        .setMessage(this.player.getName() + " guess picture")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                playerGuess();
            }
         })
        .show();
		dialog.setCanceledOnTouchOutside(false);
	}


	private void getLayouts() {
		layouts = new LinearLayout[6];
		for(int i = 0 ; i < layouts.length; i ++){
			layouts[i] = (LinearLayout)findViewById(layoutIds[i]);
		}
	}
	
	public void playerGuess(){

		ArrayList<LinearLayout> usedLayouts = new ArrayList<LinearLayout>();
		for(int i = 0; i < playerAmmount; i++){
			usedLayouts.add(layouts[i]);
		}
		Collections.shuffle(usedLayouts);
		
		this.answer = getIntent().getIntExtra("answer", 0);

		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		
		
		for(int i = 0 ; i < playerAmmount; i++){
			
			try {
		        File f=new File(directory, "player" + i + ".png");
		        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
		        
		        LinearLayout currentLayout = usedLayouts.get(i);
		        
		        currentLayout.setVisibility(View.VISIBLE);
		        if (answer == i){
		        	changeAnswer(currentLayout);
		        }
		        Drawable d = new BitmapDrawable(getResources(),b);
		        currentLayout.setBackground(d);
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		        System.out.println("IMAGE LOAD NOT SUCCESFUL");
		    }
		}
		
	}



	private void changeAnswer(View currentView) {
		if(currentView == layouts[0]){
			answer = 10;
		} else if(currentView == layouts[1]){
			answer = 20;
		} else if(currentView == layouts[2]){
			answer = 30;
		} else if(currentView == layouts[3]){
			answer = 40;
		} else if(currentView == layouts[4]){
			answer = 50;
		} else if(currentView == layouts[5]){
			answer = 60;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guess_picture, menu);
		return true;
	}
	
	public void guessDone(View view){
		finish();
	}
	
	public void guessPicture1(View view){
		if(answer == 10){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();	
	}
	
	public void guessPicture2(View view){
		if(answer == 20){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture3(View view){
		if(answer == 30){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture4(View view){
		if(answer == 40){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture5(View view){
		if(answer == 50){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture6(View view){
		if(answer == 60){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
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
