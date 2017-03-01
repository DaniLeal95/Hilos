package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import model.Settings;

public class GestionLlamadas {

	private List<Llamada> listallamadas = new ArrayList<>();
	private List<Semaphore> operadores = new ArrayList<>();
	private Semaphore procesadoraCompra ;
	private Semaphore entraaLlamada; 
	private int numProductos = Settings.NUM_PRODUCTOS;
	private int numllamadas;

	public GestionLlamadas() {
		numllamadas = 0;
		entraaLlamada =  new Semaphore(4,true);
		procesadoraCompra = new Semaphore(1,true);
		for (int i = 0; i < Settings.NUM_OPERADORES; i++) {
			operadores.add(new Semaphore(1,true));
		}
	}

	public  void llamada(Llamada llamada) {
		// Si el tamaño de la cola de espera es menor a la permitida
		// La ponemos en espera.
		try {
			if (listallamadas.size() < Settings.TAM_COLA_ESPERA) {
				numllamadas++;
				listallamadas.add(llamada);
				System.out.println("Llamada " + llamada.getId() + " pasa a cola de espera. Hay "
						+ listallamadas.size() + " llamadas esperando.");

				// Esto es para que se quede esperando
				if (listallamadas.get(0) != llamada && !entraaLlamada.tryAcquire()) {
					
					synchronized (this) {
						wait();	
					}
					
				}
				
				entraaLlamada.acquire();
					
				
				//Quitamos la llamada de la lista
				listallamadas.remove(0);
				int numoperadorescogido = -1;
				while(numoperadorescogido == -1){
				
				// Intenta Coger algun operador
				if(this.operadores.get(0).tryAcquire()){
					  numoperadorescogido=0;	
				  }
				else if(this.operadores.get(1).tryAcquire()){
					  numoperadorescogido=1;				  
				  }
				else if(this.operadores.get(2).tryAcquire()){
					  numoperadorescogido=2;				  
				  }
				}
				
				

				
				
				// Esperamos el tiempo de llamada
				Thread.sleep(llamada.getTllamada()*1000);
				System.out.println("Operador " + numoperadorescogido + " atendiendo llamada " + llamada.getId()
						+ " durante " + llamada.getTllamada() + " segundos");

				// Si el cliente compra
				if (llamada.getCompra()) {
					System.out.println("VENTA: Operador " + numoperadorescogido + " a llamada " + llamada.getId());
					
					//Si alguien esta procesando alguna compra
					if(procesadoraCompra.availablePermits()<1){
						System.out.println("Operador "+numoperadorescogido+ " ESPERANDO para procesa compra");
					}
					
					
					//bloqueamos un permiso para procesar la compra
					procesadoraCompra.acquire();
					
					if(getnumProductos()>0){
						Thread.sleep(llamada.getTcompra()*1000);
						System.out.println("Operador "+numoperadorescogido+ " PROCESANDO compra durante "+llamada.getTcompra()+" segundos");

					}
					else{
						System.out.println("STOCK AGOTADO");
					}
					//Soltamos un permiso para que se pueda procesar una compra
					procesadoraCompra.release();
					//Soltamos el permiso del operador
					operadores.get(numoperadorescogido).release();
					//y notificamos para que pueda entrar una llamada nueva
					
					entraaLlamada.release();
					synchronized (this) {
						notify();	
					}
					ventaproducto();

				}
				// Si el cliente no compra
				else {
					System.out.println("Llamada " + llamada.getId() + " CUELGA sin comprar");
					operadores.get(numoperadorescogido).release();
					entraaLlamada.release();
					synchronized (this) {
						notify();	
					}
					
					
				}

			}
			
			// Si el tamaño de cola de espera esta lleno
			else {
				System.out.println("Llamada " + llamada.getId() + " Se ha PERDIDO. Cola llena");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getnumProductos() {
		return this.numProductos;
	}
	public int getnumLlamadas() {
		return this.numllamadas;
	}
	public void ventaproducto() {
		numProductos--;
	}
	
	
	public int comprobarOperadores() {
		  int id = -1;
		  for (int i = 0; i < this.operadores.size() && id<0; i++) {
			  if(this.operadores.get(i).availablePermits()>0){
				  id=i;				  
			  }
		   
		  }
		  return id;
	}

}
