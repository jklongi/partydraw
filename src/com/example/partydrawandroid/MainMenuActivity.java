package com.example.partydrawandroid;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


public class MainMenuActivity extends FragmentActivity {
	
    
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;
 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		setContentView(R.layout.pager);
        createTabs();          
	}

	private void createTabs() {
		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
	        		new ViewPager.SimpleOnPageChangeListener() {
	        			@Override
	                    public void onPageSelected(int position) {
	                      actionBar = getActionBar();
	                      actionBar.setSelectedNavigationItem(position);                    }
	                });
	        Tab.setAdapter(TabAdapter);
	        actionBar = getActionBar();
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	        actionBar.setDisplayShowTitleEnabled(false);
	        actionBar.setDisplayShowHomeEnabled(false);
	        actionBar.setDisplayHomeAsUpEnabled(false);
	        ActionBar.TabListener tabListener = new ActionBar.TabListener(){
	        	@Override
	        	public void onTabReselected(android.app.ActionBar.Tab tab,
	        		FragmentTransaction ft) {
			    }
			    @Override
			    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
			    	Tab.setCurrentItem(tab.getPosition());
			    }
			    @Override
			    public void onTabUnselected(android.app.ActionBar.Tab tab,
			    	FragmentTransaction ft) {
			}
		};
      
	    actionBar.addTab(actionBar.newTab().setText("Main").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Scores").setTabListener(tabListener));
	    actionBar.addTab(actionBar.newTab().setText("Guide").setTabListener(tabListener));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
	

	