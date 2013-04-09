package com.flipone.enrayaversus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Sala extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sala);
        
        final Button Comenzar = (Button)findViewById(R.id.Empezar);
        final TextView NombreServer = (TextView)findViewById(R.id.ServerName);
        final TextView Nombre1 = (TextView)findViewById(R.id.Nombre1);
        final TextView Nombre2 = (TextView)findViewById(R.id.Nombre2);
        
        Bundle b = getIntent().getExtras();
        
        NombreServer.setText(b.getString("SERVERNAME"));
        Nombre1.setText(b.getString("ALIAS"));
        
        Comenzar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
					
					if(!Nombre2.getText().equals("Esperando.."))
					{
						/////////abrir tablero online//////////
						Intent intent = new Intent(Sala.this,Online.class);
						startActivity(intent);
					}
			}
		});
        
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
            	InitServer(Nombre2);
            }
         }.start();
        
    }
    
    public void InitServer(TextView Nombre2)
    {
    try {
		Log.v("SERVER", "entrando en el try");
		DatagramSocket Socket = new DatagramSocket(1212);
		Socket.setBroadcast(true);
		byte[] recibir = new byte [256];
		DatagramPacket peticion = new DatagramPacket(recibir,recibir.length);
		Log.v("SERVER", "esperando peticion");
		Socket.receive(peticion);
		Log.v("SERVER", "peticion recibida");
		if(!peticion.getData().toString().equals(""))
		{
			Nombre2.setText(peticion.getData().toString());
			String aceptado = "Peticion Aceptada";
			byte[] buf = aceptado.getBytes();
			String dircliente = peticion.getAddress().toString();
			InetAddress ipcliente = InetAddress.getByName(dircliente);
			DatagramPacket respuesta = new DatagramPacket(buf,buf.length,ipcliente,1212);
			Log.v("SERVER", "enviando respuesta");
			Socket.send(respuesta);
			Log.v("SERVER", "respuesta enviada");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
    	
    }
}
