package com.flipone.enrayaversus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Instrucciones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instrucciones);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.instrucciones, menu);
		return true;
	}

}
