package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread{
	
    private final int idFilosofo;
 
    /**
     * Array de semáforos. Cada semáfaro es un palillo.
     */
    private final Semaphore[] palillos_semaforo;
 
    /**
     * Array de enteros 2 dimensiones. Por cada valor de su primer índice
     * (filas) almacena los palillos que necesita el filósofo de ese índice para
     * comer.
     */
    private final int[][] palillosFilosofo;
 
    /**
     * El índice del palillo a la izquierda del filósofo. Se obtiene en el
     * constructor a partir del array de enteros palillosFilósofo.
     */
    private final int palilloIzquierdo;
 
    /**
     * El índice del palillo a la derecha del filósofo. Se obtiene en el
     * constructor a partir del array de enteros palillosFilósofo.
     */
    private final int palilloDerecho;
    
    private final Random tiempoAleatorio = new Random();
    
	
	public Filosofo(int idFilosofo, Semaphore[] palillos_semaforo, int[][] palillosFilosofo) {
        this.idFilosofo = idFilosofo;
        this.palillos_semaforo = palillos_semaforo;
        this.palillosFilosofo = palillosFilosofo;
        this.palilloIzquierdo = palillosFilosofo[idFilosofo][0];
        this.palilloDerecho = palillosFilosofo[idFilosofo][1];
    }
	
	 protected void comer() {
		 
		 //Si hay permisos empieza a comer y coge los dos permisos
		 if(intentacogerPalillos()){
	     System.out.println("FILÓSOFO " + idFilosofo + " ESTÁ COMIENDO.");
	     try {
	      //Simular el tiempo que tarda el filósofo en comer,
	      // entre 0.5 y 1 segundos:
	                    sleep(tiempoAleatorio.nextInt(1000) + 500);
	                } catch (InterruptedException ex) {
	                    System.out.println("Error : " + ex.toString());
	                }
	                System.out.println("Filósofo " + idFilosofo + " ha terminado de comer.Libera los palillos " + palilloIzquierdo + " y " + palilloDerecho);
	            
	                // Ya que ha terminado de comer libera el semáforo-palillo de su derecha:
	            palillos_semaforo[palilloDerecho].release();
	            
	            // Y libera también el semáforo palillo de su izuierda.
	            palillos_semaforo[palilloIzquierdo].release();
	        
		 	}
		 else{
			 System.out.println("EL FILOSOFO "+idFilosofo+" SE HA QUEDAO HAMBRIENTO, QUE SE JODA");
		 }
	 	}
	 
	    /**
	     * Imprimir un mensaje "Filósofo n está pensando". Para simular esta
	     * actividad, dormirá el hilo un tiempo aleatorio.
	     */
	    protected void pensar() {
	        System.out.println("Filósofo " + idFilosofo + " está pensando.");
	        try {
	            //void El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
	            Filosofo.sleep(tiempoAleatorio.nextInt(5000) + 100);
	        } catch (InterruptedException ex) {
	            System.out.println("Error en el método pensar(): " + ex.toString());
	        }
	    }
	 
	
	@Override
	public void run(){
		while(true){
			pensar();
			comer();
		}
		
	}
	
	
	public boolean intentacogerPalillos(){
		boolean pallilloscogidos=false;
		
		if(palillos_semaforo[palilloIzquierdo].tryAcquire() && palillos_semaforo[palilloDerecho].tryAcquire()){
			pallilloscogidos=true;
		}
		return pallilloscogidos;
			
		
	}
	

}
