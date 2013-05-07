package com.flipone.enrayaversus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Multijugador extends Activity {
	
	Boolean turno = true;//true es jugador 1
	Boolean SigueTurno = false;
	Boolean Jugando = true;
    String pinta = "";
    int regla = 0;
    int XUsadas = 0;
    int OUsadas = 0;
    int XGanadas = 0;
    int OGanadas = 0;
    String ultimaX = "";//para saber la que has pintado vacia y no dejar que se repita
    String ultimaO = "";//para saber la que has pintado vacia y no dejar que se repita
    String esta = "";
    Boolean Debe_Contar = true;
    Button casillas[];
    int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);
        
        casillas = new Button[] {
                (Button)findViewById(R.id.Boton1),
                (Button)findViewById(R.id.Boton2),
                (Button)findViewById(R.id.Boton3),
                //fila2
                (Button)findViewById(R.id.Boton4),
                (Button)findViewById(R.id.Boton5),
                (Button)findViewById(R.id.Boton6),
                //fila3
                (Button)findViewById(R.id.Boton7),
                (Button)findViewById(R.id.Boton8),
                (Button)findViewById(R.id.Boton9),
        };
        
        final TextView ganador = (TextView)findViewById(R.id.textView3);
        final TextView jugador = (TextView)findViewById(R.id.textView2);
        
        final TextView XWins = (TextView)findViewById(R.id.XWins);
        final TextView OWins = (TextView)findViewById(R.id.OWins);
        
        final Button again = (Button)findViewById(R.id.again);
        again.setVisibility(View.INVISIBLE);
        again.setClickable(false);

        for(index=0;index<=casillas.length-1;index++)//CONTROL DE BOTONES UNICO
        {
     	   casillas[index].setId(index);
         casillas[index].setOnClickListener(new OnClickListener() 
         {	
 			@Override
 			public void onClick(View v) {
 				Button b = (Button)v;
	        	Debe_Contar = true;
				if(Jugando)
				{
				CheckTurno();
					if(b.getId()==0)
					esta = "A";
					if(b.getId()==1)
					esta = "B";
					if(b.getId()==2)
					esta = "C";
					if(b.getId()==3)
					esta = "D";
					if(b.getId()==4)
					esta = "E";
					if(b.getId()==5)
					esta = "F";
					if(b.getId()==6)
					esta = "G";
					if(b.getId()==7)
					esta = "H";
					if(b.getId()==8)
					esta = "I";
				if(pinta.equals(b.getText())||b.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && b.getText().equals("")||pinta.equals("X") && XUsadas>=3 && b.getText().equals(""))//no devuelve nada cuando tienes las 3 fichas y pulsas un boton vacio
					{	
						return;
					}
				PintaLlena(casillas[0],casillas[1],casillas[2],casillas[3],casillas[4],casillas[5],casillas[6],casillas[7],casillas[8],again,ganador,b,XWins,OWins);
				PintaVacia(casillas[0],casillas[1],casillas[2],casillas[3],casillas[4],casillas[5],casillas[6],casillas[7],casillas[8],b);
				if(b.getId()==0)
				Ultima("A");
				if(b.getId()==1)
				Ultima("B");
				if(b.getId()==2)
				Ultima("C");
				if(b.getId()==3)
				Ultima("D");
				if(b.getId()==4)
				Ultima("E");
				if(b.getId()==5)
				Ultima("F");
				if(b.getId()==6)
				Ultima("G");
				if(b.getId()==7)
				Ultima("H");
				if(b.getId()==8)
				Ultima("I");
				CuentaFichas();
				EnableO(casillas[0],casillas[1],casillas[2],casillas[3],casillas[4],casillas[5],casillas[6],casillas[7],casillas[8]);
				EnableX(casillas[0],casillas[1],casillas[2],casillas[3],casillas[4],casillas[5],casillas[6],casillas[7],casillas[8]);
				ChangeTurno(jugador);
				}
 				}}});
        }
        again.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				for(int i=0;i<casillas.length;i++)
				{
					casillas[i].setText("");
					casillas[i].setClickable(true);
				}
				again.setVisibility(View.INVISIBLE);
				again.setClickable(false);
				ganador.setText("");
				Jugando = true;
				turno = true;
				regla = 0;
				OUsadas = 0;
				XUsadas = 0;
				ultimaX = "";
				ultimaO = "";
				esta = "";
				jugador.setText("X");
				}});
        
        
        }
    
    public void PintaLlena(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I,Button again,TextView ganador,Button Z,TextView XWins,TextView OWins)
    {
    	if((OUsadas<3)&&(pinta.equals("O"))||(XUsadas<3)&&(pinta.equals("X")))
		{
    		if(!turno && ultimaO!=esta ||turno && ultimaX!=esta)
    		{
    		if(pinta.equals("O"))
    		{	
    		Z.setTextColor(Color.parseColor("#8A0886"));
    		}
    		if(pinta.equals("X"))
    		{	
    		Z.setTextColor(Color.parseColor("#088A08"));
    		}
			Z.setText(pinta);
			Z.setClickable(false);
			SigueTurno = false;
			CheckWin(A,B,C,D,E,F,G,H,I,again,ganador,XWins,OWins);
    		}
    		else
    		{
            	Debe_Contar = false;
    			Context context = getApplicationContext();
    			int duration = Toast.LENGTH_SHORT;
    			Toast.makeText(context,getResources().getString(R.string.NoRepite), duration).show();
    			SigueTurno = true;
    		}
		}
    	
    }
    public void PintaVacia(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I,Button Z)
    {
    	if(pinta.equals("O"))
		{
			if(Z.getText().equals("O"))
			{
				if(OUsadas>=3)
				{
					SigueTurno = true;
					Z.setText("");					
					OUsadas=OUsadas-2;
	    			DisableO(A,B,C,D,E,F,G,H,I);
				}
			}
		}
		if(pinta.equals("X"))
		{
			if(Z.getText().equals("X"))
			{
				if(XUsadas>=3)
				{
					SigueTurno = true;
					Z.setText("");
					XUsadas=XUsadas-2;
	    			DisableX(A,B,C,D,E,F,G,H,I);
				}
			}
		}
    	
    }
     
    public void ChangeTurno(TextView jugador)
    {
    	if(SigueTurno==false)
    	{	
    		//final TextView jugador = (TextView)findViewById(R.id.textView2);
    		if(turno)
    		{
    			turno = false;
    			jugador.setText("O");
    		}
    		else
    		{
    			turno = true;
    			jugador.setText("X");
    		}
    	}
    }
    
    public void CheckTurno()
    {
        if(turno)
        {	
        	pinta = "X";
        }
        else if(turno == false)
        {
        	pinta = "O";
        }
    }
    
    public void CheckWin(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I,Button again,TextView ganador,TextView XWins,TextView OWins)
    {
    	if(A.getText().equals("X")&&B.getText().equals("X")&&C.getText().equals("X"))
        {
        	regla = 1;
        }
        if(D.getText().equals("X")&&E.getText().equals("X")&&F.getText().equals("X"))
        {				CheckTurno();
        	regla = 1;
        }
        if(G.getText().equals("X")&&H.getText().equals("X")&&I.getText().equals("X"))
        {
        	regla = 1;
        }
        if(A.getText().equals("X")&&D.getText().equals("X")&&G.getText().equals("X"))
        {
        	regla = 1;
        }
        if(B.getText().equals("X")&&E.getText().equals("X")&&H.getText().equals("X"))
        {
        	regla = 1;
        }
        if(C.getText().equals("X")&&F.getText().equals("X")&&I.getText().equals("X"))
        {
        	regla = 1;
        }
        if(A.getText().equals("X")&&E.getText().equals("X")&&I.getText().equals("X"))
        {
        	regla = 1;
        }
        if(C.getText().equals("X")&&E.getText().equals("X")&&G.getText().equals("X"))
        {
        	regla = 1;
        }
        if(A.getText().equals("O")&&B.getText().equals("O")&&C.getText().equals("O"))
        {
        	regla = 2;
        }
        if(D.getText().equals("O")&&E.getText().equals("O")&&F.getText().equals("O"))
        {
        	regla = 2;
        }
        if(G.getText().equals("O")&&H.getText().equals("O")&&I.getText().equals("O"))
        {
        	regla = 2;
        }
        if(A.getText().equals("O")&&D.getText().equals("O")&&G.getText().equals("O"))
        {
        	regla = 2;
        }
        if(B.getText().equals("O")&&E.getText().equals("O")&&H.getText().equals("O"))
        {
        	regla = 2;
        }
        if(C.getText().equals("O")&&F.getText().equals("O")&&I.getText().equals("O"))
        {
        	regla = 2;
        }
        if(A.getText().equals("O")&&E.getText().equals("O")&&I.getText().equals("O"))
        {
        	regla = 2;
        }
        if(C.getText().equals("O")&&E.getText().equals("O")&&G.getText().equals("O"))
        {
        	regla = 2;
        }
        if(regla==1)
        {
            DisableAll(A,B,C,D,E,F,G,H,I);
            Jugando = false;
        	ganador.setText("X "+getResources().getString(R.string.Gana));
        	again.setVisibility(View.VISIBLE);
            again.setClickable(true);
            XGanadas++;
            XWins.setText(""+XGanadas);
        }
        if(regla==2)
        {
            DisableAll(A,B,C,D,E,F,G,H,I);
            Jugando = false;
        	ganador.setText("O "+getResources().getString(R.string.Gana));
        	again.setVisibility(View.VISIBLE);
            again.setClickable(true);
            OGanadas++;
            OWins.setText(""+OGanadas);
        }
    }
    public void DisableO(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	for(int i=0;i<casillas.length;i++)
    	{
    		if(casillas[i].getText().equals("O"))
    			casillas[i].setClickable(false);
    	}
    }
    public void DisableX(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	for(int i=0;i<casillas.length;i++)
    	{
    		if(casillas[i].getText().equals("X"))
    			casillas[i].setClickable(false);
    	}
    }
    public void CuentaFichas()//incrementa las variables que cuentan las fichas usadas
    {
    	if(Debe_Contar)
    	{
    		if(pinta.equals("O"))
    		{
    			OUsadas++;
    		}
    		if(pinta.equals("X"))
    		{
    			XUsadas++;
    		}
    	}
    }
    public void Ultima(String nombre)
    {
    	if(turno)
    	{
    		ultimaX = nombre;
    	}
    	else
    	{
    		ultimaO = nombre;
    	}
    }
    public void EnableO(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(OUsadas==3)
    	{
    		for(int i=0;i<casillas.length;i++)
    		{
    			if(casillas[i].getText().equals("O"))
    			{
    				casillas[i].setClickable(true);
    			}
    		}
    	}
    }
    public void DisableAll(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	for(int i=0;i<casillas.length;i++)
    	{
    		casillas[i].setClickable(false);
    	}
    }
    public void EnableX(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(XUsadas==3)
    	{
    		for(int i=0;i<casillas.length;i++)
    		{
    			if(casillas[i].getText().equals("X"))
    			{
    				casillas[i].setClickable(true);
    			}
    		}
    	}
    
    }
}