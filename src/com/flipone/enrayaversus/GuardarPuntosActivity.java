package com.flipone.enrayaversus;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GuardarPuntosActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guardar_puntos);
		
		final EditText NameImput = (EditText)findViewById(R.id.NameInput);
		final Button SaveName	= (Button)findViewById(R.id.saveName);
		final TextView scoresee	= (TextView)findViewById(R.id.scoresee);
		
		final Bundle b = getIntent().getExtras();
		
		scoresee.setText(""+b.getInt("score"));
		
		final UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBpuntos", null, 1);
        
        	final SQLiteDatabase db = usdbh.getWritableDatabase();
        
        
        	if(db != null)
        	{
        		Cursor c = db.rawQuery(" SELECT * FROM score ", null);
        		c.moveToFirst();

        		if (c.moveToFirst()) {
        			do {

        			} while(c.moveToNext());
        		}
        	db.close();
        	
        }
        	
        	SaveName.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				
    		        final SQLiteDatabase db = usdbh.getWritableDatabase();
    				if(db != null)
    				{
    					String nom = NameImput.getText().toString();
    					int num = b.getInt("score");
    					db.execSQL("INSERT INTO score (puntos, nombre) " + "VALUES (" + num + ", '" + nom +"')");
    					db.close();
    					NameImput.setText("");
    					Toast.makeText(getApplicationContext(), "Puntuacion guardada", Toast.LENGTH_LONG).show();
    					Intent i = new Intent(GuardarPuntosActivity.this,Score.class);
    					startActivity(i);
    				}
    				
    			}
    		});
	}

}
