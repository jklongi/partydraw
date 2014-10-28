package com.example.partydrawandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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
import android.widget.TextView;

public class CorrectAnswerActivity extends Activity {
	
	private String word;
	private Player player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_correct_answer);
		
		Intent intent = getIntent();
		this.player = (Player) intent.getSerializableExtra("player");
		word = intent.getStringExtra("word");
		
		TextView view = (TextView)findViewById(R.id.correctTextView);
		view.setText("Correct Answer was " + "'" + word + "'" + " by " + player.getName() + "!");
		
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		
			try {
		        File f=new File(directory, "player" + player.getIndex() + ".png");
		        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
		        ImageView img = (ImageView)findViewById(R.id.correctImage);
		        
		        img.setImageBitmap(b);
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		        System.out.println("IMAGE LOAD NOT SUCCESFUL");
		    }
		
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
}
