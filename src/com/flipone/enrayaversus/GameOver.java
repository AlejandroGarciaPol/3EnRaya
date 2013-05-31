package com.flipone.enrayaversus;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class GameOver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		
		final TextView score = (TextView)findViewById(R.id.score2);
		final Button share = (Button)findViewById(R.id.share);
		final Button goSave = (Button)findViewById(R.id.goSaveScreen);
		
		
		final Bundle b = getIntent().getExtras();
		score.setText(""+b.getInt("SCORE"));
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,getResources().getString(R.string.twit)+" "+ b.getInt("SCORE") +" "+ getResources().getString(R.string.twit2) +" "+ getResources().getString(R.string.twit3));
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TicTacToe");
				startActivity(Intent.createChooser(sharingIntent, null));
				
			}
		});
		
		goSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent goSaveIntent = new Intent(GameOver.this,GuardarPuntosActivity.class);
				Bundle bund = new Bundle();
				bund.putInt("score",b.getInt("SCORE"));
				goSaveIntent.putExtras(bund);
				startActivity(goSaveIntent);
				
			}
		});
	}


}
