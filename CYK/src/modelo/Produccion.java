package modelo;

import java.util.ArrayList;

public class Produccion {
	
	private String nombre;
	private ArrayList<String> producciones;
	
	public Produccion(String nombre) {
		this.nombre = nombre;
		producciones = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getProducciones() {
		return producciones;
	}
	public void setProducciones(ArrayList<String> producciones) {
		this.producciones = producciones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void agregarProduccion(String valor) {
		producciones.add(valor);
	}
	
	public boolean encontrarProduccion(String cadena) {
		boolean encontrado = false;
		
		for (int i = 0; i < producciones.size() && !encontrado; i++) {
			if(cadena.equals(producciones.get(i))) {
				
				encontrado = true;
			}
		}
		
		return encontrado;
	}

}
