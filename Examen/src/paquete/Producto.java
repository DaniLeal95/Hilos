package paquete;

public class Producto {

	/*propiedades*/
	private int cod;
	private String nombre, descripcion;
	private double precio;
	
	/*constructores*/
	public Producto(int cod, String nombre, String descripcion, double precio) {
		
		this.cod = cod;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Producto() {
		
		this.cod = 0;
		this.nombre = null;
		this.descripcion = null;
		this.precio = 0.0;
	}

	/*getters&setters*/
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/*metodos*/
	@Override
	public String toString() {
		return "Producto [cod=" + cod + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
	}
	

	
	
	
	
}
