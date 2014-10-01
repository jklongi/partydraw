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

public class GuessPictureActivity extends Activity {
	
	private int index;
	private int playerAmmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_guess_picture);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		index = intent.getIntExtra("index", 0);
		playerAmmount = intent.getIntExtra("playerAmmount", 0);
		
		new AlertDialog.Builder(this)
        .setTitle("Guess picture")
        .setMessage(name + " guess picture")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                playerGuess();
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();

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
		        	img=(ImageView)findViewById(R.id.imageView1);
		        } else if(i == 1){
		        	img=(ImageView)findViewById(R.id.imageView2);
		        } else if(i == 2){
		        	img=(ImageView)findViewById(R.id.imageView3);
		        } else if(i == 3){
		        	img=(ImageView)findViewById(R.id.imageView4);
		        } else if(i == 4){
		        	img=(ImageView)findViewById(R.id.imageView5);
		        } else {
		        	img=(ImageView)findViewById(R.id.imageView6);
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
		finish();	
	}
	
	public void guessPicture2(View view){
		finish();
	}
	
	public void guessPicture3(View view){
		finish();
	}
	
	public void guessPicture4(View view){
		finish();
	}
	
	public void guessPicture5(View view){
		finish();
	}
	
	public void guessPicture6(View view){
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
