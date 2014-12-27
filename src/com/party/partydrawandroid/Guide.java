package com.party.partydrawandroid;


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
        		"Each player has 30 seconds to draw a given word. One random player will be given slightly different word. " +
        		"When you are done drawing, click the ✔ button in the upper right corner or wait for the time to run out " +
        		"and then pass the device to the next player. When everyone has finished their drawing, starting from the first " +
        		"player, each player has to guess which picture was drawn from the different word. ");
        return guide;
	}
}
