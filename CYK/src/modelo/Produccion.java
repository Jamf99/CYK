package modelo;

import java.util.ArrayList;
/**
 * Clase encargada de manejar la informaci�n de cada producci�n 
 */
public class Produccion {
	/**
	 * Cadena con la cabeza de la producci�n 
	 */
	private String nombre;
	
	/**
	 * Lista con las producciones o cuerpos de las producciones 
	 */
	private ArrayList<String> producciones;
	/**
	 * Constructor de la clase producci�n
	 * @param nombre String con la cabeza de la producci�n
	 */
	public Produccion(String nombre) {
		this.nombre = nombre;
		producciones = new ArrayList<String>();
	}
	
	/**
	 * M�todo get de las producciones
	 * @return Lista con las producciones
	 */
	public ArrayList<String> getProducciones() {
		return producciones;
	}
	
	/**
	 * M�todo set de las producciones
	 * @param producciones Nuevas producciones
	 */
	public void setProducciones(ArrayList<String> producciones) {
		this.producciones = producciones;
	}
	
	/**
	 * M�todo get de la cabeza de la producci�n
	 * @return Cadena con la cabeza de la producci�n
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * M�todo set del nombre de la producci�n
	 * @param nombre Nuevo nombre de la cabeza de la producci�n
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * M�todo encargado de agregar producciones a la lista de producciones
	 * @param valor produci�n a agregar
	 */
	public void agregarProduccion(String valor) {
		producciones.add(valor);
	}
	
	/**
	 * M�todo encargado de buscar si una cadena se encuentra en las producciones
	 * @param cadena Cadena a buscar
	 * @return true en caso de encontrar que la cadena est� en las producciones, false en caso contrario
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
