package modelo;

import java.util.ArrayList;
/**
 * Clase encargada de manejar la información de cada producción 
 */
public class Produccion {
	/**
	 * Cadena con la cabeza de la producción 
	 */
	private String nombre;
	
	/**
	 * Lista con las producciones o cuerpos de las producciones 
	 */
	private ArrayList<String> producciones;
	/**
	 * Constructor de la clase producción
	 * @param nombre String con la cabeza de la producción
	 */
	public Produccion(String nombre) {
		this.nombre = nombre;
		producciones = new ArrayList<String>();
	}
	
	/**
	 * Método get de las producciones
	 * @return Lista con las producciones
	 */
	public ArrayList<String> getProducciones() {
		return producciones;
	}
	
	/**
	 * Método set de las producciones
	 * @param producciones Nuevas producciones
	 */
	public void setProducciones(ArrayList<String> producciones) {
		this.producciones = producciones;
	}
	
	/**
	 * Método get de la cabeza de la producción
	 * @return Cadena con la cabeza de la producción
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Método set del nombre de la producción
	 * @param nombre Nuevo nombre de la cabeza de la producción
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Método encargado de agregar producciones a la lista de producciones
	 * @param valor produción a agregar
	 */
	public void agregarProduccion(String valor) {
		producciones.add(valor);
	}
	
	/**
	 * Método encargado de buscar si una cadena se encuentra en las producciones
	 * @param cadena Cadena a buscar
	 * @return true en caso de encontrar que la cadena está en las producciones, false en caso contrario
	 */
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
