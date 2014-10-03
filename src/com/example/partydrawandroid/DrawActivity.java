package com.example.partydrawandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrawActivity extends Activity implements OnClickListener {
	
	private DrawView dv;
	private float smallBrush, mediumBrush, largeBrush;
	private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn;
	private String filePath;
	private int index;
	
	public void paintClicked(View view) {
		
		if (view != currPaint) {
			
			dv.setErase(false);
			dv.setBrushSize(dv.getLastBrushSize());
			
			ImageButton imgView = (ImageButton) view;
			String color = view.getTag().toString();
			
			dv.setColor(color);
			
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint = (ImageButton) view;
		}
		
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        
        Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		index = intent.getIntExtra("index", 0);
		
        new AlertDialog.Builder(this)
        .setTitle("Draw")
        .setMessage(name + " are you ready to draw?")
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                playerIsReady();
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
    }
    
    public void playerIsReady(){
    	
    	Intent intent = getIntent();
		String word = intent.getStringExtra("word");
		
		TextView drawword = (TextView) findViewById(R.id.word);
		drawword.setText("Draw '" + word + "'");
		
    	
    	dv = (DrawView) findViewById(R.id.drawing);
        dv.setBrushSize(mediumBrush);
        
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        
        drawBtn = (ImageButton) findViewById(R.id.draw_btn);
        
        drawBtn.setOnClickListener(this);
        
        eraseBtn = (ImageButton) findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);
        
        newBtn = (ImageButton) findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);
        
        saveBtn = (ImageButton) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
	public void onClick(View view) {
		
		if (view.getId() == R.id.draw_btn) {
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Select brush size");
			brushDialog.setContentView(R.layout.brush_chooser);
			
			ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dv.setErase(false);
					dv.setBrushSize(smallBrush);
					dv.setLastBrushSize(smallBrush);
					brushDialog.dismiss();
				}
			});
			
			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			    	dv.setErase(false);
			        dv.setBrushSize(mediumBrush);
			        dv.setLastBrushSize(mediumBrush);
			        brushDialog.dismiss();
			    }
			});
			 
			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			    	dv.setErase(false);
			        dv.setBrushSize(largeBrush);
			        dv.setLastBrushSize(largeBrush);
			        brushDialog.dismiss();
			    }
			});
			
			brushDialog.show();
		}
		
		else if (view.getId() == R.id.erase_btn) {
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Select eraser size");
			brushDialog.setContentView(R.layout.brush_chooser);
			
			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			        dv.setErase(true);
			        dv.setBrushSize(smallBrush);
			        brushDialog.dismiss();
			    }
			});
			
			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			        dv.setErase(true);
			        dv.setBrushSize(mediumBrush);
			        brushDialog.dismiss();
			    }
			});
			
			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			        dv.setErase(true);
			        dv.setBrushSize(largeBrush);
			        brushDialog.dismiss();
			    }
			});
			
			brushDialog.show();
		}
		
		else if (view.getId() == R.id.new_btn) {
			
			AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
			newDialog.setTitle("New drawing");
			newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
			newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        dv.startNew();
			        dialog.dismiss();
			    }
			});
			newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        dialog.cancel();
			    }
			});
			newDialog.show();
		}
		
		else if (view.getId() == R.id.save_btn) {
			
			AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
			saveDialog.setTitle("Finished");
			saveDialog.setMessage("Done drawing?");
			saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        //save drawing
			    	saveImage();
			    	finish();
			    }
			});
			saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        dialog.cancel();
			    }
			});
			saveDialog.show();
			
		}
		
		
	}
	
	public void saveImage() {
		
		dv.setDrawingCacheEnabled(true);
		dv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		
		Bitmap bitmapImage = dv.getDrawingCache();
		
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
	    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
	    File mypath = new File(directory,"player" + index +".jpg");
	
	    FileOutputStream fos = null;
	    try {           
	    	fos = new FileOutputStream(mypath);
	        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
	        fos.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    filePath = directory.getAbsolutePath();
		
	}
	
	public void loadSavedImage() {
		
		try {
	        File f=new File(filePath, "player0.jpg");
	        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
	        ImageView img=(ImageView)findViewById(R.id.drawing);
	        img.setImageBitmap(b);
	    } 
	    catch (FileNotFoundException e) 
	    {
	        e.printStackTrace();
	    }
	}
}
