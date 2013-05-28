package com.flipone.enrayaversus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GuardarPuntosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guardar_puntos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guardar_puntos, menu);
		return true;
	}

}
