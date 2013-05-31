package com.flipone.enrayaversus;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

public class Score extends Activity {
	
	static ArrayList<Persona> lista = new ArrayList<Persona>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		final UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBpuntos", null, 1);
		
		//rellenamos el arraylist con los resultados de la tabla
		final SQLiteDatabase db = usdbh.getReadableDatabase();
        if(db != null)
        {
        	Cursor c = db.rawQuery(" SELECT * FROM score ", null);
        	c.moveToFirst();
        	if (c.moveToFirst()) {
        	     do {
        	    	 //total = total + c.getString(1) + " " + c.getString(0)+ "\n";
        	    	 Persona p = new Persona(c.getString(1),c.getInt(0));
        	    	 lista.add(p);
        	     } while(c.moveToNext());
        	}
        	db.close();
        	
        }
        
        //ordenamos el arraylist para ver primero el mejor jugador
    	Persona aux=null;
    	Persona aux2=null;
    	for(int i=0;i<lista.size();i++)
    	{
    		for(int j=0;j<lista.size();j++)
    		{	
    			if(lista.get(j).getScore()<lista.get(i).getScore())
    			{
    				aux = lista.get(i);
    				aux2 = lista.get(j);
            		lista.remove(i);//testeo
    				lista.add(i,aux2 );
            		lista.remove(j);//testeo
    				lista.add(j,aux);
    			}
    		}
    	}
        
		
		final ListView lv = (ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<Persona> adapter = new ArrayAdapter<Persona>(this,android.R.layout.simple_list_item_1, lista){

	        @Override
	        public View getView(int position, View convertView,
	                ViewGroup parent) {
	            View view =super.getView(position, convertView, parent);

	            TextView textView=(TextView) view.findViewById(android.R.id.text1);
	            //ListView listView=(ListView) view.findViewById(android.R.id.list);

	            textView.setTextColor(Color.parseColor("#40FF00"));
	            //listView.setBackgroundColor(Color.parseColor("#000000"));
	            
	            return view;
	        }
	    };
		
		
		
		lv.setAdapter(adapter);
	}
}
