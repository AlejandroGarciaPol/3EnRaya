package com.flipone.enrayaversus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Crear extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);
        
        final EditText Alias = (EditText)findViewById(R.id.alias);
        final Button Crear = (Button)findViewById(R.id.crear);
        final EditText ServerName = (EditText)findViewById(R.id.nombreEdit);
        final Button VerIP = (Button)findViewById(R.id.VerIp);
        
        VerIP.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent siguiente = new Intent(Crear.this,IP.class);
				startActivity(siguiente);
				
			}
		});
        
        Crear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v("SERVER", "boton pulsado");
				if(Alias.getText().length()>0 && ServerName.getText().length()>0)
				{
					Log.v("SERVER", "creando partida");
					Intent intent = new Intent(Crear.this,Sala.class);
					Bundle b = new Bundle();
	        		b.putString("ALIAS", Alias.getText().toString());
	        		b.putString("SERVERNAME", ServerName.getText().toString());
	        		intent.putExtras(b);
					startActivity(intent);
				}
				
			}
		});
        
    }
}
