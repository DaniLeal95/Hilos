package main;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import handlerSuperMarket.Handler;
import model.Cajero;
import model.Cliente;
import model.Utilidades;

public class Principal {
	
	
	public static void main(String args[]){
	
		int colas,clientesxminuto;
		List<Cajero> cajeros= new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Cuantas colas hay??");
		
		colas = Integer.parseInt(sc.nextLine());
		Handler handler = new Handler(colas);
		Utilidades utilidades = new Utilidades(colas,handler);
		
		System.out.println("Cuantos clientes por minutos entraran?");
		clientesxminuto = Integer.parseInt(sc.nextLine());
		
		/*Cliente cliente = new Cliente(0,utilidades);
		Thread hilito= new Thread(cliente);
		hilito.start();*/
	
		int clientesAroundTHeWorld=0;
		for(int i=0;i<300;i++){
			
			for(int j=0;j<clientesxminuto;j++){
				clientesAroundTHeWorld++;
				Cliente cliente = new Cliente(clientesAroundTHeWorld,utilidades);
				Thread hilito= new Thread(cliente);
				hilito.start();
			}
			try {
				
				Thread.sleep(10);
				System.out.println("\n");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Integer> vecescolasesperando = handler.getvecescajerosparados();
		for(int i = 0 ; i<vecescolasesperando.size();i++){
			System.out.println("La caja "+i+" se ha quedado esperando "+vecescolasesperando.get(i)+" vecesSSSSSSSSSSSSS");
		}
		
		
		
	
	}
}
