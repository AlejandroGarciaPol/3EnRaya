package com.flipone.enrayaversus;

import com.flipone.enrayaversus.MainActivity;
import com.flipone.enrayaversus.Multijugador;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button Nueva = (Button)findViewById(R.id.button1);
        final Button Multi = (Button)findViewById(R.id.button2);
        //final Button Online = (Button)findViewById(R.id.button3);
        final Button Salir = (Button)findViewById(R.id.button4);
        final TextView Acerca = (TextView)findViewById(R.id.acercade);
        
        Salir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//System.exit(0);
				moveTaskToBack(true);
				
			}
		});
        
        Multi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Multijugador.class);
				startActivity(intent);
			}
		});
        
        Nueva.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,CPU.class);
				startActivity(intent);
				
			}
		});
        Acerca.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Acerca.class);
				startActivity(intent);
				
			}
		});
        /*
        Online.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,OnlineMenu.class);
				startActivity(intent);
				
			}
		});
		*/
    }

}
