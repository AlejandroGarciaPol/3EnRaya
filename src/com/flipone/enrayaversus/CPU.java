package com.flipone.enrayaversus;

import java.util.Random;

import android.os.Bundle;
//import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CPU extends Activity {
	
	
	int level = 1;
	int puntos = 0;
	Boolean turno = true;//true es jugador 1
	Boolean SigueTurno = false;
	Boolean Jugando = true;
    String pinta = "";
    int regla = 0;
    int XUsadas = 0;
    int OUsadas = 0;
    int XGanadas = 0;
    int OGanadas = 0;
    //////////////////////PARA SABER QUE CASILLAS HA USADO LA CPU
    String Pri = "";
    String Sec = "";
    String Ter = "";
    ///////////PARA NO REPETIR
    String ultimaX = "";//para saber la que has pintado vacia y no dejar que se repita
    String ultimaO = "";//para saber la que has pintado vacia y no dejar que se repita
    String esta = "";
    Boolean Debe_Contar = true;
    int quitada = 99; // 0->A,1->B,2->C etc
    TextView Level;
    TextView Puntos;
    int vidas = 0;
    Button casillas[];
    int index = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpu);
        
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
        
        Level = (TextView)findViewById(R.id.textView6);
        Puntos = (TextView)findViewById(R.id.score);

        final AlertDialog alertDialog = new AlertDialog.Builder(CPU.this).create();
        
        alertDialog.setTitle("info");
        alertDialog.setMessage(getResources().getString(R.string.instrucciones));
        
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
       
             alertDialog.cancel();
       
          } });
        
        alertDialog.show();
		
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
				CheckTurno();
				Cpu(casillas[0],casillas[1],casillas[2],casillas[3],casillas[4],casillas[5],casillas[6],casillas[7],casillas[8],again,ganador,b,XWins,OWins);
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
				Pri = "";
				Sec = "";
				Ter = "";
				ultimaX = "";
				ultimaO = "";
				esta = "";
				quitada = 99;
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
					OUsadas--;
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
    public void LevelsManager(TextView XWins,TextView OWins)
    {
    	if(level == 1 || level == 4 || level == 7)
    	{
    		if(OGanadas==3)
    		{
    			Intent i = new Intent(CPU.this,GameOver.class);
    			i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        		Bundle b = new Bundle();
        		b.putInt("SCORE", puntos);
        		i.putExtras(b);
    			startActivity(i);
    		}
    		if(XGanadas==3)
    		{
    			puntos = puntos + 1000/(OGanadas+1);
    			OGanadas=0;
    			XGanadas=0;
    			XWins.setText(""+XGanadas);
    			OWins.setText(""+OGanadas);
    			level++;
    			Level.setText(""+level);
    			Puntos.setText(""+puntos);
    			vidas = 2;
    			Context context = getApplicationContext();
    			int duration = Toast.LENGTH_SHORT;
    			Toast.makeText(context,getResources().getString(R.string.level)+" "+level+"   "+getResources().getString(R.string.vidas)+" "+vidas, duration).show();
    		}
    	}
    	if(level == 2 || level == 5 || level == 8)
    	{
    		if(OGanadas==2)
    		{
    			Intent i = new Intent(CPU.this,GameOver.class);
    			i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        		Bundle b = new Bundle();
        		b.putInt("SCORE", puntos);
        		i.putExtras(b);
    			startActivity(i);
    		}
    		if(XGanadas==3)
    		{
    			puntos = puntos + 2000/(OGanadas+1);
    			OGanadas=0;
    			XGanadas=0;
    			XWins.setText(""+XGanadas);
    			OWins.setText(""+OGanadas);
    			level++;
    			Level.setText(""+level);
    			Puntos.setText(""+puntos);
    		    vidas = 1;
    		    Context context = getApplicationContext();
    			int duration = Toast.LENGTH_SHORT;
    			Toast.makeText(context,getResources().getString(R.string.level)+" "+level+"   "+getResources().getString(R.string.vidas)+" "+vidas, duration).show();
    		}
    	}
    	if(level == 3 || level == 6 || level == 9)
    	{
    		if(OGanadas==1)
    		{
    			Intent i = new Intent(CPU.this,GameOver.class);
    			i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        		Bundle b = new Bundle();
        		b.putInt("SCORE", puntos);
        		i.putExtras(b);
    			startActivity(i);
    		}
    		if(XGanadas==3)
    		{
    			puntos = puntos + 3000/(OGanadas+1);
    			OGanadas=0;
    			XGanadas=0;
    			XWins.setText(""+XGanadas);
    			OWins.setText(""+OGanadas);
    			level++;
    			Level.setText(""+level);
    			Puntos.setText(""+puntos);
    			vidas = 3;
    			Context context = getApplicationContext();
    			int duration = Toast.LENGTH_SHORT;
    			Toast.makeText(context,getResources().getString(R.string.level)+" "+level+"   "+getResources().getString(R.string.vidas)+" "+vidas, duration).show();
    		}
    	}
    	if(level == 10)
    	{
    		Intent i = new Intent(CPU.this,Completado.class);
    		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    		Bundle b = new Bundle();
    		b.putInt("SCORE", puntos);
    		i.putExtras(b);
			startActivity(i);
    		
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
            LevelsManager(XWins,OWins);
            puntos = puntos +150;
            Puntos.setText(""+puntos);
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
            LevelsManager(XWins,OWins);
            puntos = puntos -200;
            Puntos.setText(""+puntos);
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
    
    public Boolean Asignar(String NombreBoton)
    {
    	if(Pri.equals(""))
    	{
    		Pri = NombreBoton;
    		return false;
    	}
    	if(Sec.equals(""))
    	{
    		Sec = NombreBoton;
    		return false;
    	}
    	if(Ter.equals(""))
    	{
    		Ter = NombreBoton;
    		return false;
    	}
    	return true;
    }
    //////////////////////////////////////////DECISIONES DE LA CPU/////////////////////////////////
	public void Cpu(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I,Button again,TextView ganador,Button Z,TextView XWins,TextView OWins)
	{
		Boolean tapada = false;
		
		if(Jugando && pinta.equals("O"))
		{
			if(OUsadas>=3)/////LO QUE HACE CUANDO YA TIENE LAS 3 FICHAS PUESTAS(BORRAR UNA)
			{
				Log.v("QUITAR FICHA","QUITAR RANDOM");
				
				int cuenta1 = 0;
				int cuenta2 = 0;
				int cuenta3 = 0;
					if(Pri.equals("A"))
					{
						if(B.getText().equals("O"))
							cuenta1++;
						if(C.getText().equals("O"))
							cuenta1++;
						if(D.getText().equals("O"))
							cuenta1++;
						if(G.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("B"))
					{
						if(A.getText().equals("O"))
							cuenta1++;
						if(C.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(H.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("C"))
					{
						if(B.getText().equals("O"))
							cuenta1++;
						if(A.getText().equals("O"))
							cuenta1++;
						if(F.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(G.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("D"))
					{
						if(A.getText().equals("O"))
							cuenta1++;
						if(G.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(F.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("E"))
					{
						if(A.getText().equals("O"))
							cuenta1++;
						if(B.getText().equals("O"))
							cuenta1++;
						if(C.getText().equals("O"))
							cuenta1++;
						if(D.getText().equals("O"))
							cuenta1++;
						if(F.getText().equals("O"))
							cuenta1++;
						if(G.getText().equals("O"))
							cuenta1++;
						if(H.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("F"))
					{
						if(C.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(D.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("G"))
					{
						if(A.getText().equals("O"))
							cuenta1++;
						if(D.getText().equals("O"))
							cuenta1++;
						if(H.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(C.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("H"))
					{
						if(G.getText().equals("O"))
							cuenta1++;
						if(I.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
						if(B.getText().equals("O"))
							cuenta1++;
					}
					if(Pri.equals("I"))
					{
						if(C.getText().equals("O"))
							cuenta1++;
						if(F.getText().equals("O"))
							cuenta1++;
						if(H.getText().equals("O"))
							cuenta1++;
						if(G.getText().equals("O"))
							cuenta1++;
						if(A.getText().equals("O"))
							cuenta1++;
						if(E.getText().equals("O"))
							cuenta1++;
					}
					if(Sec.equals("A"))
					{
						if(B.getText().equals("O"))
							cuenta2++;
						if(C.getText().equals("O"))
							cuenta2++;
						if(D.getText().equals("O"))
							cuenta2++;
						if(G.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("B"))
					{	
						if(A.getText().equals("O"))
							cuenta2++;
						if(C.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(H.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("C"))
					{
						if(B.getText().equals("O"))
							cuenta2++;
						if(A.getText().equals("O"))
							cuenta2++;
						if(F.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(G.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("D"))
					{
						if(A.getText().equals("O"))
							cuenta2++;
						if(G.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(F.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("E"))
					{
						if(A.getText().equals("O"))
							cuenta2++;
						if(B.getText().equals("O"))
							cuenta2++;
						if(C.getText().equals("O"))
							cuenta2++;
						if(D.getText().equals("O"))
							cuenta2++;
						if(F.getText().equals("O"))
							cuenta2++;
						if(G.getText().equals("O"))
							cuenta2++;
						if(H.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("F"))
					{
						if(C.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(D.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("G"))
					{
						if(A.getText().equals("O"))
							cuenta2++;
						if(D.getText().equals("O"))
							cuenta2++;
						if(H.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(C.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("H"))
					{
						if(G.getText().equals("O"))
							cuenta2++;
						if(I.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
						if(B.getText().equals("O"))
							cuenta2++;
					}
					if(Sec.equals("I"))
					{
						if(C.getText().equals("O"))
							cuenta2++;
						if(F.getText().equals("O"))
							cuenta2++;
						if(H.getText().equals("O"))
							cuenta2++;
						if(G.getText().equals("O"))
							cuenta2++;
						if(A.getText().equals("O"))
							cuenta2++;
						if(E.getText().equals("O"))
							cuenta2++;
					}
					if(Ter.equals("A"))
					{
						if(B.getText().equals("O"))
							cuenta3++;
						if(C.getText().equals("O"))
							cuenta3++;
						if(D.getText().equals("O"))
							cuenta3++;
						if(G.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("B"))
					{	
						if(A.getText().equals("O"))
							cuenta3++;
						if(C.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(H.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("C"))
					{
						if(B.getText().equals("O"))
							cuenta3++;
						if(A.getText().equals("O"))
							cuenta3++;
						if(F.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(G.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("D"))
					{
						if(A.getText().equals("O"))
							cuenta3++;
						if(G.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(F.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("E"))
					{
						if(A.getText().equals("O"))
							cuenta3++;
						if(B.getText().equals("O"))
							cuenta3++;
						if(C.getText().equals("O"))
							cuenta3++;
						if(D.getText().equals("O"))
							cuenta3++;
						if(F.getText().equals("O"))
							cuenta3++;
						if(G.getText().equals("O"))
							cuenta3++;
						if(H.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("F"))
					{
						if(C.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(D.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("G"))
					{
						if(A.getText().equals("O"))
							cuenta3++;
						if(D.getText().equals("O"))
							cuenta3++;
						if(H.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(C.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("H"))
					{
						if(G.getText().equals("O"))
							cuenta3++;
						if(I.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
						if(B.getText().equals("O"))
							cuenta3++;
					}
					if(Ter.equals("I"))
					{
						if(C.getText().equals("O"))
							cuenta3++;
						if(F.getText().equals("O"))
							cuenta3++;
						if(H.getText().equals("O"))
							cuenta3++;
						if(G.getText().equals("O"))
							cuenta3++;
						if(A.getText().equals("O"))
							cuenta3++;
						if(E.getText().equals("O"))
							cuenta3++;
					}
				 Log.v("cuentas","cuenta1:"+cuenta1+" cuenta2:"+cuenta2+" cuenta3:"+cuenta3);
					if(cuenta1<cuenta2 && cuenta1<cuenta3)
					{
						Log.v("Quitando","Quitando la primera puesta");
						cuenta1=0;
						cuenta2=0;
						cuenta3=0;
						if(Pri.equals("A"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,A);
							Pri = "";
							quitada = 0;
						}
						if(Pri.equals("B"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,B);
							Pri = "";
							quitada = 1;
						}
						if(Pri.equals("C"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,C);
							Pri = "";
							quitada = 2;
						}
						if(Pri.equals("D"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,D);
							Pri = "";
							quitada = 3;
						}
						if(Pri.equals("E"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,E);
							Pri = "";
							quitada = 4;
						}
						if(Pri.equals("F"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,F);
							Pri = "";
							quitada = 5;
						}
						if(Pri.equals("G"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,G);
							Pri = "";
							quitada = 6;
						}
						if(Pri.equals("H"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,H);
							Pri = "";
							quitada = 7;
						}
						if(Pri.equals("I"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,I);
							Pri = "";
							quitada = 8;
						}
					}
					else if(cuenta2<cuenta1 && cuenta2<cuenta3)
					{
						Log.v("Quitando","Quitando la segunda puesta");
						cuenta1=0;
						cuenta2=0;
						cuenta3=0;
						if(Sec.equals("A"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,A);
							Sec = "";
							quitada = 0;
						}
						if(Sec.equals("B"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,B);
							Sec = "";
							quitada = 1;
						}
						if(Sec.equals("C"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,C);
							Sec = "";
							quitada = 2;
						}
						if(Sec.equals("D"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,D);
							Sec = "";
							quitada = 3;
						}
						if(Sec.equals("E"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,E);
							Sec = "";
							quitada = 4;
						}
						if(Sec.equals("F"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,F);
							Sec = "";
							quitada = 5;
						}
						if(Sec.equals("G"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,G);
							Sec = "";
							quitada = 6;
						}
						if(Sec.equals("H"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,H);
							Sec = "";
							quitada = 7;
						}
						if(Sec.equals("I"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,I);
							Sec = "";
							quitada = 8;
						}
					}
					else if(cuenta3<cuenta1 && cuenta3<cuenta2)
					{
						Log.v("Quitando","Quitando la tercera puesta");
						cuenta1=0;
						cuenta2=0;
						cuenta3=0;
						if(Ter.equals("A"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,A);
							Ter = "";
							quitada = 0;
						}
						if(Ter.equals("B"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,B);
							Ter = "";
							quitada = 1;
						}
						if(Ter.equals("C"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,C);
							Ter = "";
							quitada = 2;
						}
						if(Ter.equals("D"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,D);
							Ter = "";
							quitada = 3;
						}
						if(Ter.equals("E"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,E);
							Ter = "";
							quitada = 4;
						}
						if(Ter.equals("F"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,F);
							Ter = "";
							quitada = 5;
						}
						if(Ter.equals("G"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,G);
							Ter = "";
							quitada = 6;
						}
						if(Ter.equals("H"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,H);
							Ter = "";
							quitada = 7;
						}
						if(Ter.equals("I"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,I);
							Ter = "";
							quitada = 8;
						}
					}else{
						Log.v("Quitando","Quitando la primera puesta(por defecto)");
						cuenta1=0;
						cuenta2=0;
						cuenta3=0;
						if(Pri.equals("A"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,A);
							Pri = "";
							quitada = 0;
						}
						if(Pri.equals("B"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,B);
							Pri = "";
							quitada = 1;
						}
						if(Pri.equals("C"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,C);
							Pri = "";
							quitada = 2;
						}
						if(Pri.equals("D"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,D);
							Pri = "";
							quitada = 3;
						}
						if(Pri.equals("E"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,E);
							Pri = "";
							quitada = 4;
						}
						if(Pri.equals("F"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,F);
							Pri = "";
							quitada = 5;
						}
						if(Pri.equals("G"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,G);
							Pri = "";
							quitada = 6;
						}
						if(Pri.equals("H"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,H);
							Pri = "";
							quitada = 7;
						}
						if(Pri.equals("I"))
						{
							PintaVacia(A,B,C,D,E,F,G,H,I,I);
							Pri = "";
							quitada = 8;
						}
					}
			}
		///LO QUE HACE ANTES DE TENER LAS 3 FICHAS
			if(OUsadas<3)//colocar ficha ganadora
			{
				Log.v("FICHA WIN","WINNER");
				int abc = 0;
				Boolean Uno = false;
				Boolean Dos = false;
				Boolean Tres = false;
				//////ABC//////
				///////////////
				if(tapada == false)
				{
				if(A.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(B.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(C.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !A.getText().equals("X")&& !B.getText().equals("X")&& !C.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","ABC "+"A="+Uno.toString()+"B="+Dos.toString()+"C="+Tres.toString());
					if(Uno==false&&A.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
						Asignar("A");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&B.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
						Asignar("B");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&C.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
						Asignar("C");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				//////////////
				/////DEF//////
				if(tapada == false)
				{
				if(D.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(E.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(F.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !D.getText().equals("X")&& !E.getText().equals("X")&& !F.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","DEF "+"D="+Uno.toString()+"E="+Dos.toString()+"F="+Tres.toString());
					if(Uno==false&&D.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
						Asignar("D");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&E.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
						Asignar("E");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&F.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
						Asignar("F");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				///////////////
				//////GHI//////
				if(tapada == false)
				{
				if(G.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(H.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(I.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !G.getText().equals("X")&& !H.getText().equals("X")&& !I.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","GHI "+"G="+Uno.toString()+"H="+Dos.toString()+"I="+Tres.toString());
					if(Uno==false&&G.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
						Asignar("G");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&H.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
						Asignar("H");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&I.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
						Asignar("I");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				////////////////
				/////AEI////////
				if(tapada == false)
				{
				if(A.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(E.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(I.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !A.getText().equals("X")&& !E.getText().equals("X")&& !I.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","AEI "+"A="+Uno.toString()+"E="+Dos.toString()+"I="+Tres.toString());
					if(Uno==false&&A.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
						Asignar("A");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&E.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
						Asignar("E");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&I.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
						Asignar("I");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				//////////////
				/////CEG//////
				if(tapada == false)
				{
				if(C.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(E.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(G.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2&& !C.getText().equals("X")&& !E.getText().equals("X")&& !G.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","CEG "+"C="+Uno.toString()+"E="+Dos.toString()+"G="+Tres.toString());
					if(Uno==false&&C.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
						Asignar("C");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&E.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
						Asignar("E");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&G.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
						Asignar("G");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				//////////
				////ADG///
				if(tapada == false)
				{
				if(A.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(D.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(G.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !A.getText().equals("X")&& !D.getText().equals("X")&& !G.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","ADG "+"A="+Uno.toString()+"D="+Dos.toString()+"G="+Tres.toString());
					if(Uno==false&&A.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
						Asignar("A");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&D.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
						Asignar("D");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&G.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
						Asignar("G");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				////////////
				////BEH/////
				if(tapada == false)
				{
				if(B.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(E.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(H.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !B.getText().equals("X")&& !E.getText().equals("X")&& !H.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","BEH "+"B="+Uno.toString()+"E="+Dos.toString()+"H="+Tres.toString());
					if(Uno==false&&B.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
						Asignar("B");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&E.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
						Asignar("E");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&H.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
						Asignar("H");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				}
				////////////
				////CFI/////
				if(tapada == false)
				{
				if(C.getText().equals("O"))
				{
					abc++;
					Uno = true;
				}
				if(F.getText().equals("O"))
				{
					abc++;
					Dos = true;
				}
				if(I.getText().equals("O"))
				{
					abc++;
					Tres = true;
				}
				if(abc==2 && !C.getText().equals("X")&& !F.getText().equals("X")&& !I.getText().equals("X"))
				{
					Log.v("COMBINACION GANADORA","CFI "+"C="+Uno.toString()+"F="+Dos.toString()+"I="+Tres.toString());
					if(Uno==false&&C.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
						Asignar("C");
						CuentaFichas();
						tapada = true;
					}
					if(Dos==false&&F.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
						Asignar("F");
						CuentaFichas();
						tapada = true;
					}
					if(Tres==false&&I.getText().equals(""))
					{
						PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
						Asignar("I");
						CuentaFichas();
						tapada = true;
					}
				}
				else
				{
					abc = 0;
					Uno = false;
					Dos = false;
					Tres = false;
				}
				
				}
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
				
			}	
		if(OUsadas<3&&tapada==false)//colocar ficha tapando al jugador si ya tiene 2 en raya
		{
			Log.v("TAPAR FICHA","TAPAR");
			int abc = 0;
			Boolean Uno = false;
			Boolean Dos = false;
			Boolean Tres = false;
			//////ABC//////
			///////////////
			if(tapada == false)
			{
			if(A.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(B.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(C.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&A.getText().equals("")&& quitada!=0)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
					Asignar("A");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&B.getText().equals("")&& quitada!=1)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
					Asignar("B");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&C.getText().equals("")&& quitada!=2)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
					Asignar("C");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			//////////////
			/////DEF//////
			if(tapada == false)
			{
			if(D.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(E.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(F.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&D.getText().equals("")&& quitada!=3)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
					Asignar("D");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&E.getText().equals("")&& quitada!=4)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
					Asignar("E");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&F.getText().equals("")&& quitada!=5)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
					Asignar("F");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			///////////////
			//////GHI//////
			if(tapada == false)
			{
			if(G.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(H.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(I.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&G.getText().equals("")&& quitada!=6)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
					Asignar("G");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&H.getText().equals("")&& quitada!=7)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
					Asignar("H");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&I.getText().equals("")&& quitada!=8)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
					Asignar("I");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			////////////////
			/////AEI////////
			if(tapada == false)
			{
			if(A.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(E.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(I.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&A.getText().equals("")&& quitada!=0)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
					Asignar("A");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&E.getText().equals("")&& quitada!=4)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
					Asignar("E");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&I.getText().equals("")&& quitada!=8)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
					Asignar("I");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			//////////////
			/////CEG//////
			if(tapada == false)
			{
			if(C.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(E.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(G.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&C.getText().equals("")&& quitada!=2)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
					Asignar("C");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&E.getText().equals("")&& quitada!=4)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
					Asignar("E");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&G.getText().equals("")&& quitada!=6)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
					Asignar("G");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			//////////
			////ADG///
			if(tapada == false)
			{
			if(A.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(D.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(G.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&A.getText().equals("")&& quitada!=0)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
					Asignar("A");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&D.getText().equals("")&& quitada!=3)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
					Asignar("D");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&G.getText().equals("")&& quitada!=6)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
					Asignar("G");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			////////////
			////BEH/////
			if(tapada == false)
			{
			if(B.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(E.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(H.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&B.getText().equals("")&& quitada!=1)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
					Asignar("B");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&E.getText().equals("")&& quitada!=4)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
					Asignar("E");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&H.getText().equals("")&& quitada!=7)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
					Asignar("H");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			////////////
			////CFI/////
			if(tapada == false)
			{
			if(C.getText().equals("X"))
			{
				abc++;
				Uno = true;
			}
			if(F.getText().equals("X"))
			{
				abc++;
				Dos = true;
			}
			if(I.getText().equals("X"))
			{
				abc++;
				Tres = true;
			}
			if(abc==2)
			{
				if(Uno==false&&C.getText().equals("")&& quitada!=2)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
					Asignar("C");
					CuentaFichas();
					tapada = true;
				}
				if(Dos==false&&F.getText().equals("")&& quitada!=5)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
					Asignar("F");
					CuentaFichas();
					tapada = true;
				}
				if(Tres==false&&I.getText().equals("")&& quitada!=8)
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
					Asignar("I");
					CuentaFichas();
					tapada = true;
				}
			}
			else
			{
				abc = 0;
				Uno = false;
				Dos = false;
				Tres = false;
			}
			}
			
		}
		if(OUsadas<3&&tapada==false)///colocar ficha random
		{
			Log.v("FICHA RANDOM","RANDOM");
			Boolean realizado = false;
			while(!realizado)
			{	
				Random random = new Random();
				int resultado = random.nextInt(8);
				Log.v("RESULTADO1"," el resultado es"+resultado);
				while(resultado == quitada)
				{	
					resultado = random.nextInt(8);
					Log.v("RESULTADO2"," el resultado es"+resultado);
				}
				if(resultado==0&&A.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
					Asignar("A");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==1&&B.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
					Asignar("B");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==2&&C.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
					Asignar("C");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==3&&D.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
					Asignar("D");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==4&&E.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
					Asignar("E");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==5&&F.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
					Asignar("F");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==6&&G.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
					Asignar("G");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==7&&H.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
					Asignar("H");
					CuentaFichas();
					realizado = true;
					break;
				}
				if(resultado==8&&I.getText().equals(""))
				{
					PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
					Asignar("I");
					CuentaFichas();
					realizado = true;
					break;
				}
			}
		}
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
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
