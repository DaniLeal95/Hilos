package model;

import java.util.Random;

public class Cliente implements Runnable{

	private int idCliente;
	private long tiempodecompra;
	private long tiempodepago;
	private Random rdm = new Random();
	private Utilidades utilidades;
	
	//TODO FALTA METERLE TAMBIEN LA CLASE DE UTILIDADES
	public Cliente (int idCliente,Utilidades utilidades){
		this.idCliente = idCliente;
		
		tiempodecompra = rdm.nextInt(550)+50;
		
		tiempodepago=tiempodecompra/(rdm.nextInt(50)+10);
		this.utilidades=utilidades;
	}
	
	@Override
	public void run() {
		utilidades.pagar(this);
	}

	public int getIdCliente() {
		return idCliente;
	}

	public long getTiempodecompra() {
		return tiempodecompra;
	}

	public long getTiempodepago() {
		return tiempodepago;
	}

}
