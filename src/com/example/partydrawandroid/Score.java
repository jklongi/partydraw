package com.example.partydrawandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Score extends Fragment {
	

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View scores = inflater.inflate(R.layout.score_frag, container, false);	
        ((TextView)scores.findViewById(R.id.textView)).setText("Scores Here!");
        return scores;
	}
}
