package model;

import java.util.ArrayList;

public class Filosofo extends Thread{
	private int id;
	private String nombre;
	private static ArrayList<Boolean> palillos;
	
	public Filosofo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public void run(){
	
		
	}
	
	
	public int getIdfilosofo() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	//method
	
	public void pensar(){
		
	}
	
	public void comer(){
		
	}
	
}
