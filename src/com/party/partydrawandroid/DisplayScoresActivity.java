package com.party.partydrawandroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DisplayScoresActivity extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_display_scores);
		
		Intent intent = getIntent();
		int ammount = intent.getIntExtra("playerAmmount", 0);
		
		int[] ids = {R.id.player1, R.id.player2, R.id.player3, R.id.player4, R.id.player5, R.id.player6};
		for(int i = 0; i < ammount; i++ ){
			Player player  = (Player) intent.getSerializableExtra("player" + (i+1));
			((TextView)findViewById(ids[i])).setText(player.getName() + " - " + player.getPoints());
		}
		
		
	}
	
	public void finished(View view){
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_scores, menu);
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
