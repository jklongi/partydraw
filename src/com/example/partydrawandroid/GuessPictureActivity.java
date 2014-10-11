package com.example.partydrawandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuessPictureActivity extends Activity {
	
	private Player player;
	private int playerAmmount;
	private int answer;
	private ImageView view1;
	private ImageView view2;
	private ImageView view3;
	private ImageView view4;
	private ImageView view5;
	private ImageView view6;
	
	LinearLayout layout4;
	LinearLayout layout5;
	LinearLayout layout6;

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
		
		getImageViews();		
		getLinearLayouts();	
		setLinearLayoutVisibility();	
		setImageViewVisivility();
		showReadyAlert();

	}

	private void showReadyAlert() {
		new AlertDialog.Builder(this)
        .setTitle("Guess picture")
        .setMessage(this.player.getName() + " guess picture")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                playerGuess();
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
	}

	private void setImageViewVisivility() {
		view1.setVisibility(View.GONE);
		view2.setVisibility(View.GONE);
		view3.setVisibility(View.GONE);
		view4.setVisibility(View.GONE);
		view5.setVisibility(View.GONE);
		view6.setVisibility(View.GONE);
	}

	private void setLinearLayoutVisibility() {
		layout4.setVisibility(View.GONE);
		layout5.setVisibility(View.GONE);
		layout6.setVisibility(View.GONE);
	}

	private void getLinearLayouts() {
		layout4 = (LinearLayout)findViewById(R.id.layoutView4);
		layout5 = (LinearLayout)findViewById(R.id.layoutView5);
		layout6 = (LinearLayout)findViewById(R.id.layoutView6);
	}

	private void getImageViews() {
		view1 = (ImageView)findViewById(R.id.imageView1);
		view2 = (ImageView)findViewById(R.id.imageView2);
		view3 = (ImageView)findViewById(R.id.imageView3);
		view4 = (ImageView)findViewById(R.id.imageView4);
		view5 = (ImageView)findViewById(R.id.imageView5);
		view6 = (ImageView)findViewById(R.id.imageView6);
	}
	
	public void playerGuess(){
		
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		
		for(int i = 0 ; i < playerAmmount; i++){
			try {
		        File f=new File(directory, "player" + i + ".jpg");
		        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
		        ImageView img;
		        if(i == 0){
		        	view1.setVisibility(View.VISIBLE);
		        	img=view1;
		        } else if(i == 1){
		        	view2.setVisibility(View.VISIBLE);
		        	img=view2;
		        } else if(i == 2){
		        	view3.setVisibility(View.VISIBLE);
		        	img=view3;
		        } else if(i == 3){
		        	layout4.setVisibility(View.VISIBLE);
		        	view4.setVisibility(View.VISIBLE);
		        	img=view4;
		        } else if(i == 4){
		        	layout5.setVisibility(View.VISIBLE);
		        	view5.setVisibility(View.VISIBLE);
		        	img=view5;
		        } else {
		        	
		        	layout6.setVisibility(View.VISIBLE);
		        	view6.setVisibility(View.VISIBLE);
		        	img=view6;
		        }
		        
		        img.setImageBitmap(b);
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
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
		if(answer == 0){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();	
	}
	
	public void guessPicture2(View view){
		if(answer == 1){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture3(View view){
		if(answer == 2){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture4(View view){
		if(answer == 3){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture5(View view){
		if(answer == 4){
			Intent returnIntent = new Intent();
			setResult(RESULT_OK,returnIntent);
			finish();
		}
		finish();
	}
	
	public void guessPicture6(View view){
		if(answer == 5){
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
}
