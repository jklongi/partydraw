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
        		"Each player has 30 seconds to draw a given word. When you are finished drawing, pass the device to the next player " +
        		"All players will get the same word, except one! When everyone has finished their drawing, each player has to guess " +
        		"which player had different word. Beware! The words are similiar to each other. Even the player who has the " +
        		"other word doesn't know what the right word is and has to guess like all the others. ");
        return guide;
	}
}
