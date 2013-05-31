package com.flipone.enrayaversus;

public class Persona {
	
	String nombre;
	int numero;
	
	Persona(String nombre,int numero){
		this.nombre = nombre;
		this.numero = numero;	
	}
	
	public String toString()
	{
		return nombre + " " + numero;
	}
	public int getScore(){
		return numero;
	}

}
