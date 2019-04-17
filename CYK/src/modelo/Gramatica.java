package modelo;

import java.util.ArrayList;

public class Gramatica {

	private ArrayList<Produccion> producciones;
	private String[] alfabeto;
	private String[][] tablaCYK;
	
	public Gramatica(int cantProducciones, String[] alfabeto) {
		
		producciones = new ArrayList<Produccion>();
		
		char nombreVariable;
		nombreVariable = 'S';
		for (int i = 0; i < cantProducciones; i++) {
			 
			if(i==0) {
				 Produccion produccion= new Produccion(nombreVariable+"");
				 producciones.add(produccion);
				 
				 nombreVariable = 'A';
			}else {
				 Produccion produccion= new Produccion(nombreVariable+"");
				 producciones.add(produccion);

				 nombreVariable++;
			}
			
		}
		
		this.setAlfabeto(alfabeto);
		
	}
	public String[] getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(String[] alfabeto) {
		this.alfabeto = alfabeto;
	}
	
	public void agregarProducciones(int produccion, String valor) {
		producciones.get(produccion).agregarProduccion(valor);
	}
	
	public ArrayList<Produccion> getProducciones(){
		return producciones;
	}
	
	public boolean CYK(String cadena) {
		tablaCYK = new String[cadena.length()][cadena.length()];
		tablaCYK = paso1CYK(tablaCYK, cadena);
		
		for (int i = 0; i <tablaCYK[0].length; i++) {
			System.out.println(tablaCYK[i][0]);
		}
		return true;
	}
	
	public boolean validarCadenaPerteneceAlAlfabeto(String cadenaValidar) {
		
		boolean valido=true;
		for (int i = 0; i < cadenaValidar.length() && valido ; i++) {
			String letra=cadenaValidar.charAt(i)+"";
			boolean encontrado=false;
			for (int j = 0; j < alfabeto.length && !encontrado; j++) {
				
				if(letra.equals(alfabeto[j])) {
					encontrado = true;
				}
			}
			
			if(encontrado == false) {
				valido=false;
			}
		}
		return valido;
	}
	
	public String[][] paso1CYK(String[][] tabla, String cadena) {
		String[][]tabla1 = tabla;
		
		for (int i = 0; i < cadena.length(); i++) {
			String simbolo = cadena.charAt(i)+"";
			String variables = "";
			boolean primeraVez = false;
			for (int j = 0; j < producciones.size(); j++) {
				boolean pertenece = producciones.get(j).encontrarProduccion(simbolo);
				if(pertenece == true  && primeraVez == false) {
					variables +=producciones.get(j).getNombre();
					primeraVez=true;
				}else if(pertenece == true) {
					variables += ","+producciones.get(j).getNombre();
				}
			}
			
			tabla1[i][0] = variables;
		}
		return tabla1;
	}
	
}
