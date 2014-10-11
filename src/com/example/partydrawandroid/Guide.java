package com.example.partydrawandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class Guide extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View guide = inflater.inflate(R.layout.guide_frag, container, false);
        ((TextView)guide.findViewById(R.id.textView)).setText("How to Play?");
        ((TextView)guide.findViewById(R.id.guide)).setText(
        		"Each player gets the same word to draw except one player gets word similiar to the others. " +
        		"Each player then has to guess which picture had different word. ");
        ((TextView)guide.findViewById(R.id.note)).setText("Scores will reset if you change name or the ammount of players");
        
        
        return guide;
	}
}
