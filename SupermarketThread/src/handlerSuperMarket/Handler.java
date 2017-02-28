package handlerSuperMarket;

import java.util.ArrayList;
import java.util.List;

public class Handler {

	private List<Integer> vecescajerosparados=new ArrayList<>();
	public List<Integer> getvecescajerosparados() {
		return vecescajerosparados;
	}


	private List<Integer> nummaximodeclientesxcola=new ArrayList<>();
	
	
	
	public Handler(int numerodecajeros){
		for (int i =0 ;i<numerodecajeros;i++){
			vecescajerosparados.add(0);
			nummaximodeclientesxcola.add(0);
		}
	}
	
	
	public void anadirCajeroParado(int numCajero){
		vecescajerosparados.set(numCajero, vecescajerosparados.get(numCajero)+1);
	}
	
	
	
}
