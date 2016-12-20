package paquete;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductoInterface {
	
		@GET("/producto/{id}")
		Call<List<Producto>> getProducto (@Path("id") int id);
		
		@GET("/producto")
		Call<List<Producto>> getProducto ();
		
		@GET(" ")
		Call<List<Producto>> get();
		
		
		/*Para filtro*/
		@GET(" ")
		Call<List<Producto>> get(@Query("precioinicial")double precioinicial,@Query("preciofinal")double preciofinal,@Query("caracterinicial")char caracterinicial);
		
		@GET(" ")
		Call<List<Producto>> get(@Query("precioinicial")double precioinicial,@Query("preciofinal")double preciofinal);
	
		
}
