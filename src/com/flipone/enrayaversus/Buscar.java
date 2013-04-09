package com.flipone.enrayaversus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Buscar extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);
        
        final Button empezar = (Button)findViewById(R.id.comenzar);
        final EditText ip = (EditText)findViewById(R.id.ipserver);
        final EditText alias = (EditText)findViewById(R.id.alias);
        final ProgressBar conectando = (ProgressBar)findViewById(R.id.conectando);
        final TextView progresconect = (TextView)findViewById(R.id.conectandoo);
        
		conectando.setIndeterminate(true);
		conectando.setVisibility(View.GONE);
		progresconect.setVisibility(View.GONE);
        
        /////////////iniciar cliente////////////////////
        
        empezar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!ip.getText().toString().equals("")||!alias.getText().toString().equals(""))
				{
			        try {
						conectando.setVisibility(View.VISIBLE);
						progresconect.setVisibility(View.VISIBLE);
						if(conectando.getProgress()>=99)
						{	
						Log.v("CLIENT", "entrando el el try");
			        	InetAddress dir = InetAddress.getByName(ip.getText().toString());
						byte[] buf =  alias.getText().toString().getBytes();
						DatagramSocket socket = new DatagramSocket(1212);
						socket.setBroadcast(true);
						DatagramPacket peticion = new DatagramPacket(buf,buf.length,dir,1212);
						Log.v("CLIENT", "enviando peticion");
						socket.send(peticion);
						Log.v("CLIENT", "peticion enviada");
						byte[] recibir = new byte [2048];
						DatagramPacket permiso = new DatagramPacket(recibir,recibir.length,dir,1212);
						Log.v("CLIENT", "recibiendo permiso");
						socket.receive(permiso);
						Log.v("CLIENT", "permiso recibido");
						if(permiso.getData().toString().equals("Peticion Aceptada"))
						{
							conectando.setVisibility(View.GONE);
							Intent intent = new Intent(Buscar.this,Online.class);
							startActivity(intent);
						}
						}
				
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else if(ip.getText().toString().equals(""))
				{
					ip.setText("Introduce la ip del servidor");
				}
				else if(alias.getText().toString().equals(""))
				{
					alias.setText("Introduce tu alias o nick");
				}
				
			}
		});
		
		////////////////fin iniciar cliente/////////////       
    }
}
