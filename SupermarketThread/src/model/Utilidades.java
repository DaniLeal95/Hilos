package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import handlerSuperMarket.Handler;

public class Utilidades {
	 
	
	private List<Cliente> cola= new ArrayList<>();
	private List<Semaphore> cajeros= new ArrayList<>();
	private Handler handler;
	
	
	
	public Utilidades(int numcolas,Handler handler){
		this.handler = handler;
		for(int i = 0; i< numcolas;i++){
			cajeros.add(new Semaphore(1));
		}
	}
	
	public void pagar(Cliente cliente){
		
		
		try{
			Thread.sleep(cliente.getTiempodecompra());
			System.out.println("El cliente "+ cliente.getIdCliente() +" ha comprado en "+ cliente.getTiempodecompra()/10f+"min y se mete en la cola");
		}catch (InterruptedException ex) {

		}
		
		cola.add(cliente);
		
		while(cola.get(0)!= cliente){}
		
			cola.remove(0);
			int numcajero = comprobarCajas();
			System.out.println("El cliente "+cliente.getIdCliente()+" se mete en la cola del cajero "+ numcajero);
			
			try {
				cajeros.get(numcajero).acquire();
			
				Thread.sleep(cliente.getTiempodepago());
				System.out.println("El cliente "+cliente.getIdCliente()+" ha esperado "+ cliente.getTiempodepago()/10f+"min pagando y ya esta en el coche tirando pa su casa");
				cajeros.get(numcajero).release();
				if(cajeros.get(numcajero).getQueueLength()==0){
					handler.anadirCajeroParado(numcajero);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		
	}
	
	
	public int comprobarCajas() {
		  int id = -1;
		  for (int i = 0; i < this.cajeros.size() && id!=-1; i++) {
			  if(this.cajeros.get(i).tryAcquire()){
				  id=i;				  
			  }
		   
		  }
		  if(id==-1){
			  
			  int numpersonasmenor=99999999;
			  for (int i = 0; i < this.cajeros.size(); i++) {
				  
				  if(this.cajeros.get(i).getQueueLength()<numpersonasmenor){
					  numpersonasmenor=this.cajeros.get(i).getQueueLength();
					  id=i;				  
				  }
			   
			  }
		  }
		  
		  return id;
		 }
	
	
	
}
