package com.flipone.enrayaversus;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Completado extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.completado);
		
		final TextView score = (TextView)findViewById(R.id.textView7);
		final Button share2 = (Button)findViewById(R.id.button1);
		
		final Bundle b = getIntent().getExtras();
		score.setText(""+b.getInt("SCORE"));
		
		share2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,getResources().getString(R.string.twit)+" "+ b.getInt("SCORE") +" "+ getResources().getString(R.string.twitc) +" "+ getResources().getString(R.string.twit3));
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TicTacToe");
				startActivity(Intent.createChooser(sharingIntent, null));
				
			}
		});
	}
}
