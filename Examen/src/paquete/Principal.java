package paquete;



import java.util.List;
import java.util.Scanner;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal {

	public static void main(String[] args) {
		final String SERVER_URL = "http://putsreq.com/h6zbeXTLaS6Zo6nAuUln/";
		Retrofit retrofit;
		ProductoCallbackArray productoCallbackarray = new ProductoCallbackArray();
		int opcion;
		
		double precioinicial=0,preciofinal=99999999;
		char letrainicial,opcionfiltradoprecio,opcionfiltradoletra;
		
		retrofit = new Retrofit.Builder()
							   .baseUrl(SERVER_URL)
							   .addConverterFactory(GsonConverterFactory.create())
							   .build();
		
		ProductoInterface almacenInter = (ProductoInterface) retrofit.create(ProductoInterface.class);
		
		Scanner sc = new Scanner(System.in);
		Call<List<Producto>> call;
		do{
			System.out.println("Quieres obtener todos productos o filtrar por por precio y/o letra");
			System.out.println("Introduzca 1 para obtener todos los productos");
			System.out.println("Introduzca 2 para filtrar");
			opcion=sc.nextInt();
		}while(opcion<0 || opcion>2);
		while(opcion!=0){
			
			switch(opcion){
			case 1:
				//Pinta Todos los productos
				System.out.println("Obtenemos los productos");
				
				
				
				call = almacenInter.get();
		        
		        call.enqueue(productoCallbackarray);
				break;
			case 2:
			
				//Pinta un array de Productos con filtros
				
				System.out.println("Quieres filtrar por precio? S o  n");
				opcionfiltradoprecio=sc.next().charAt(0);
				if(opcionfiltradoprecio=='S' || opcionfiltradoprecio=='s'){
					System.out.println("Introduzca precio inicial");
					precioinicial=sc.nextDouble();
				
					System.out.println("Introduzca precio final");
					preciofinal = sc.nextDouble();
				}
				System.out.println("Quieres filtrar por letra? S o  n");
				opcionfiltradoletra=sc.next().charAt(0);
				if(opcionfiltradoletra=='S' || opcionfiltradoletra=='s'){
					System.out.println("Introduzca letra");
					letrainicial = sc.next().charAt(0);
					call = almacenInter.get(precioinicial,preciofinal,letrainicial);
				}
				else{
					call = almacenInter.get(precioinicial,preciofinal);		   
				}
						        
		        call.enqueue(productoCallbackarray);
				
				break;
			}
			
			
			do{
				System.out.println("Quieres obtener todos productos o filtrar por por precio y/o letra");
				System.out.println("Introduzca 1 para obtener todos los productos");
				System.out.println("Introduzca 2 para filtrar");
				opcion=sc.nextInt();
			}while(opcion<0 || opcion>2);
		}
	
        

	}

}
