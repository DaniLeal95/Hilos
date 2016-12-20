package paquete;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductoCallback implements Callback<Producto>{
	@Override
	public void onFailure(Call<Producto> arg0, Throwable arg1) {
		int i;
		
		i=0;
	}

	@Override
	public void onResponse(Call<Producto> arg0, Response<Producto> resp) {
		
		Producto producto;
		String contentType;
		int code;
		String message;
		boolean isSuccesful;
		
		producto = resp.body();
		
		Headers cabeceras = resp.headers();
		contentType = cabeceras.get("Content-Type");
		code = resp.code();
		message = resp.message();
		isSuccesful = resp.isSuccessful();
		
		System.out.println(producto);
	}
}
