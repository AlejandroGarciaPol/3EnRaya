package com.flipone.enrayaversus;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Online extends Activity {
	
	Boolean turno = true;//true es jugador 1
	Boolean SigueTurno = false;
	Boolean Jugando = true;
    String pinta = "";
    int regla = 0;
    int XUsadas = 0;
    int OUsadas = 0;
    int XGanadas = 0;
    int OGanadas = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer);
      
        //fila1
        final Button A = (Button)findViewById(R.id.Boton1);
        final Button B = (Button)findViewById(R.id.Boton2);
        final Button C = (Button)findViewById(R.id.Boton3);
        //fila2
        final Button D = (Button)findViewById(R.id.Boton4);
        final Button E = (Button)findViewById(R.id.Boton5);
        final Button F = (Button)findViewById(R.id.Boton6);
        //fila3
        final Button G = (Button)findViewById(R.id.Boton7);
        final Button H = (Button)findViewById(R.id.Boton8);
        final Button I = (Button)findViewById(R.id.Boton9);
        
        final TextView ganador = (TextView)findViewById(R.id.textView3);
        final TextView jugador = (TextView)findViewById(R.id.textView2);
        
        final TextView XWins = (TextView)findViewById(R.id.XWins);
        final TextView OWins = (TextView)findViewById(R.id.OWins);
        
        final Button again = (Button)findViewById(R.id.again);
        again.setVisibility(View.INVISIBLE);
        again.setClickable(false);

        
        
        A.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(A.getText())||A.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && A.getText().equals("")||pinta.equals("X") && XUsadas>=3 && A.getText().equals(""))//no devuelve nada cuando tienes las 3 fichas y pulsas un boton vacio
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,A,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,A);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        
        B.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(B.getText())||B.getText().equals(""))
				{	
					if(pinta.equals("O") && OUsadas>=3 && B.getText().equals("")||pinta.equals("X") && XUsadas>=3 && B.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,B,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,B);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        C.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(C.getText())||C.getText().equals(""))
				{	
					if(pinta.equals("O") && OUsadas>=3 && C.getText().equals("")||pinta.equals("X") && XUsadas>=3 && C.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,C,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,C);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        D.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(D.getText())||D.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && D.getText().equals("")||pinta.equals("X") && XUsadas>=3 && D.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,D,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,D);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        E.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(E.getText())||E.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && E.getText().equals("")||pinta.equals("X") && XUsadas>=3 && E.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,E,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,E);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        F.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(F.getText())||F.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && F.getText().equals("")||pinta.equals("X") && XUsadas>=3 && F.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,F,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,F);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        G.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(G.getText())||G.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && G.getText().equals("")||pinta.equals("X") && XUsadas>=3 && G.getText().equals(""))
					{	
						return;
					}	
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,G,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,G);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        H.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(H.getText())||H.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && H.getText().equals("")||pinta.equals("X") && XUsadas>=3 && H.getText().equals(""))
					{	
						return;
					}	
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,H,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,H);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        I.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				if(Jugando)
				{
				CheckTurno();
				if(pinta.equals(I.getText())||I.getText().equals(""))
				{
					if(pinta.equals("O") && OUsadas>=3 && I.getText().equals("")||pinta.equals("X") && XUsadas>=3 && I.getText().equals(""))
					{	
						return;
					}
				PintaLlena(A,B,C,D,E,F,G,H,I,again,ganador,I,XWins,OWins);
				PintaVacia(A,B,C,D,E,F,G,H,I,I);
				CuentaFichas();
				EnableO(A,B,C,D,E,F,G,H,I);
				EnableX(A,B,C,D,E,F,G,H,I);
				ChangeTurno(jugador);
				}
				}}});
        again.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) {
				A.setText("");
				A.setClickable(true);
				B.setText("");
				B.setClickable(true);
				C.setText("");
				C.setClickable(true);
				D.setText("");
				D.setClickable(true);
				E.setText("");
				E.setClickable(true);
				F.setText("");
				F.setClickable(true);
				G.setText("");
				G.setClickable(true);
				H.setText("");
				H.setClickable(true);
				I.setText("");
				I.setClickable(true);
				again.setVisibility(View.INVISIBLE);
				again.setClickable(false);
				ganador.setText("");
				Jugando = true;
				turno = true;
				regla = 0;
				OUsadas = 0;
				XUsadas = 0;
				jugador.setText("X");
				}});
        
        
        }
    
    public void PintaLlena(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I,Button again,TextView ganador,Button Z,TextView XWins,TextView OWins)
    {
    	if((OUsadas<3)&&(pinta.equals("O"))||(XUsadas<3)&&(pinta.equals("X")))
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
        	ganador.setText("Gana X!");
        	again.setVisibility(View.VISIBLE);
            again.setClickable(true);
            XGanadas++;
            XWins.setText(""+XGanadas);
        }
        if(regla==2)
        {
            DisableAll(A,B,C,D,E,F,G,H,I);
            Jugando = false;
        	ganador.setText("Gana O!");
        	again.setVisibility(View.VISIBLE);
            again.setClickable(true);
            OGanadas++;
            OWins.setText(""+OGanadas);
        }
    }
    public void DisableO(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(A.getText().equals("O"))
    	{
    		A.setClickable(false);
    	}
      	if(B.getText().equals("O"))
    	{
    		B.setClickable(false);
    	}
      	if(C.getText().equals("O"))
    	{
    		C.setClickable(false);
    	}
      	if(D.getText().equals("O"))
    	{
    		D.setClickable(false);
    	}
      	if(E.getText().equals("O"))
    	{
    		E.setClickable(false);
    	}
      	if(F.getText().equals("O"))
    	{
    		F.setClickable(false);
    	}
      	if(G.getText().equals("O"))
    	{
    		G.setClickable(false);
    	}
      	if(H.getText().equals("O"))
    	{
    		H.setClickable(false);
    	}
      	if(I.getText().equals("O"))
    	{
    		I.setClickable(false);
    	}
    }
    public void DisableX(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(A.getText().equals("X"))
    	{
    		A.setClickable(false);
    	}
      	if(B.getText().equals("X"))
    	{
    		B.setClickable(false);
    	}
      	if(C.getText().equals("X"))
    	{
    		C.setClickable(false);
    	}
      	if(D.getText().equals("X"))
    	{
    		D.setClickable(false);
    	}
      	if(E.getText().equals("X"))
    	{
    		E.setClickable(false);
    	}
      	if(F.getText().equals("X"))
    	{
    		F.setClickable(false);
    	}
      	if(G.getText().equals("X"))
    	{
    		G.setClickable(false);
    	}
      	if(H.getText().equals("X"))
    	{
    		H.setClickable(false);
    	}
      	if(I.getText().equals("X"))
    	{
    		I.setClickable(false);
    	}
    }
    public void CuentaFichas()//incrementa las variables que cuentan las fichas usadas
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
    public void EnableO(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(OUsadas==3)
    	{
    	if(A.getText().equals("O"))
		{
			A.setClickable(true);
		}
		if(B.getText().equals("O"))
		{
			B.setClickable(true);
		}
		if(C.getText().equals("O"))
		{
			C.setClickable(true);
		}
		if(D.getText().equals("O"))
		{
			D.setClickable(true);
		}
		if(E.getText().equals("O"))
		{
			E.setClickable(true);
		}
		if(F.getText().equals("O"))
		{
			F.setClickable(true);
		}
		if(G.getText().equals("O"))
		{
			G.setClickable(true);
		}
		if(H.getText().equals("O"))
		{
			H.setClickable(true);
		}
		if(I.getText().equals("O"))
		{
			I.setClickable(true);
		}
    	}
    }
    public void DisableAll(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	A.setClickable(false);
    	B.setClickable(false);
    	C.setClickable(false);
    	D.setClickable(false);
    	E.setClickable(false);
    	F.setClickable(false);
    	G.setClickable(false);
    	H.setClickable(false);
    	I.setClickable(false);
    }
    public void EnableX(Button A,Button B,Button C,Button D,Button E,Button F,Button G,Button H,Button I)
    {
    	if(XUsadas==3)
    	{
    	if(A.getText().equals("X"))
		{
			A.setClickable(true);
		}
		if(B.getText().equals("X"))
		{
			B.setClickable(true);
		}
		if(C.getText().equals("X"))
		{
			C.setClickable(true);
		}
		if(D.getText().equals("X"))
		{
			D.setClickable(true);
		}
		if(E.getText().equals("X"))
		{
			E.setClickable(true);
		}
		if(F.getText().equals("X"))
		{
			F.setClickable(true);
		}
		if(G.getText().equals("X"))
		{
			G.setClickable(true);
		}
		if(H.getText().equals("X"))
		{
			H.setClickable(true);
		}
		if(I.getText().equals("X"))
		{
			I.setClickable(true);
		}
    	}
    }
}