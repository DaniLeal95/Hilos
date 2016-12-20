package paquete;

import java.util.List;

import com.google.gson.stream.JsonReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoCallbackArray implements Callback<List<Producto>>{
	
	
	


	@Override
	public void onFailure(Call<List<Producto>> arg0, Throwable arg1) {
		System.out.println(arg1.getMessage());
		
	}

	@Override
	public void onResponse(Call<List<Producto>> arg0, Response<List<Producto>> arg1) {
		try{
			
			List<Producto> productos = arg1.body();
			
			
			for (int i= 0 ; i<productos.size();i++){
				System.out.println(productos.get(i).toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
