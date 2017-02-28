package Principal;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import model.Filosofo;

public class MainStoteles {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Cuantos filosofos hay?");
		int filosofos = Integer.parseInt(sc.nextLine());
		
		int[][] filosofospalillos=new int[filosofos][2];
		
		Semaphore[] palillos_semaforo = new Semaphore[filosofos];
		
		for(int i=0;i<filosofos;i++){
			palillos_semaforo[i]= new Semaphore(1);
				if(i==0){
					filosofospalillos[i][0]= filosofos-1;
					filosofospalillos[i][1]= i;
				}
				else{
					filosofospalillos[i][0]= i-1;
					filosofospalillos[i][1]= i;
				}
				
		}
		
		for (int i = 0; i < filosofos; i++) {
			
            new Filosofo(i, palillos_semaforo, filosofospalillos).start();
            
        }
		
		
		
		
				
	}

}
