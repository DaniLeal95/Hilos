package model;

import java.util.Random;

public class Llamada implements Runnable{

	private int id;
	private GestionLlamadas gestionLlamadas;
	
	private int tllamada;
	private int tcompra;
	private boolean compra=false;
	
	public Llamada(int id,GestionLlamadas gestionLlamadas){
		Random rdm = new Random();
		this.id=id;
		this.gestionLlamadas= gestionLlamadas;
		tllamada = rdm.nextInt(Settings.TMAX_LLAMADA)+Settings.TMIN_LLAMADA;
		tcompra = rdm.nextInt(Settings.TMAX_COMPRA)+Settings.TMIN_COMPRA;
		if(rdm.nextInt(10)+1<3)compra=true;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getTllamada(){
		return this.tllamada;
	}
	public int getTcompra(){
		return this.tcompra;
	}
	public boolean getCompra(){
		return this.compra;
	}
	
	@Override
	public void run() {
		gestionLlamadas.llamada(this);
	}

}
