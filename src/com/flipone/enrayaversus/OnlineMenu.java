package com.flipone.enrayaversus;

import com.flipone.enrayaversus.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OnlineMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuonline);
        
        final Button Buscar = (Button)findViewById(R.id.buscar);
        final Button Crear = (Button)findViewById(R.id.crear);
        final Button Atras = (Button)findViewById(R.id.atras);
        
        Buscar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OnlineMenu.this,Buscar.class);
				startActivity(intent);
				
			}
		});
        
        Crear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OnlineMenu.this,Crear.class);
				startActivity(intent);
			}
		});
        
        Atras.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OnlineMenu.this,MainActivity.class);
				startActivity(intent);
			}
		});
        
    }
}
