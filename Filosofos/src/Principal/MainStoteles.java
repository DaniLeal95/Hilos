package Principal;

import java.util.Scanner;

public class MainStoteles {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Cuantos filosofos hay?");
		int filosofos = Integer.parseInt(sc.nextLine());
		
		int[][] filosofospalillos=new int[filosofos][1];
		
		for(int i=0;i<filosofos;i++){
			
				if(i==0){
					filosofospalillos[i][0]= filosofos-1;
					filosofospalillos[i][1]= i;
				}
				else{
					filosofospalillos[i][0]= i-1;
					filosofospalillos[i][1]= i;
				}
				
		}
				
	}

}
