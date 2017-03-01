package principal;

import java.util.Random;

import model.GestionLlamadas;
import model.Llamada;
import model.Settings;

public class Principal {

	public static void main(String[] args) {
		Random rdm = new Random();
		GestionLlamadas gestionLlamadas = new GestionLlamadas();
		
		//Para comprobar que aun hay stock;
		
		try {
			for(int i = 0;i<Settings.TIEMPO_SIMULACION && gestionLlamadas.getnumProductos()>0;i++){
			//Probabilidad de que se genere una llamada
				if(rdm.nextInt(10)+1>4){
				
					Llamada llamada = new Llamada(i, gestionLlamadas);
					Thread hilo = new Thread(llamada);
					hilo.start();
					//Dormimos el hilo un segundo;
					Thread.sleep(1000);
				}
			
				if(gestionLlamadas.getnumProductos()==0){
					System.out.println("NO HAY STOCK");
				}
				if(i==Settings.TIEMPO_SIMULACION){
					System.out.println("SE ACABO EL TIEMPO");
				}

			
				
				
			
			}
			Thread.sleep(2000);
			System.out.println("RESUMEN");
			System.out.println("Numero de llamadas gestionadas :"+gestionLlamadas.getnumLlamadas());
			System.out.println("Numero de productos"+ (10 -gestionLlamadas.getnumProductos()));
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
