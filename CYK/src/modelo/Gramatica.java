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
		tablaCYK = inicializarCYK(tablaCYK, cadena);
		
		for (int i = 0; i <tablaCYK.length; i++) {
			for (int j = 0; j < tablaCYK[0].length; j++) {
				System.out.print(tablaCYK[i][j] +"\t");
			}
			System.out.println("");
		}
		return true;
	}
	//pendiente
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
	
	public String[][] inicializarCYK(String[][] tabla, String cadena) {
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
	
	public boolean repetirCYK() {
		int n = tablaCYK.length;
		for (int j = 1; j < n; j++) {
			
			for (int i = 0; i <n-j; i++) {
				String Xij = "";
				boolean primeraVez = false;
				for (int k = 0; k <= j-1; k++) {
								
					String Xik = tablaCYK[i][k];
					String X2 = tablaCYK[i+k][j-k];
					
					if(primeraVez == false) {
						String valor =concatenacion(Xik, X2);
						Xij += valor;
						primeraVez=true;
					}else {
						String valor =","+concatenacion(Xik, X2);
						Xij += valor;
					}
				}
				
				String resultado= buscarProducciones(Xij);
				tablaCYK[i][j] = resultado;
			}
		}
		return true;
	}
	public String buscarProducciones(String Xij) {
		String[] produc = Xij.split(",");
		String variables = "";

		for (int i = 0; i < produc.length; i++) {
			String simbolo = produc[i];
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

		}
		
		return variables;
	}
	public String concatenacion(String Xik, String X2) {
		
		String distributiva = "";
		String[] X0 = Xik.split(",");
		String[] X1 = X2.split(",");
		boolean primeraVez = false;
		
		for (int i = 0; i < X0.length; i++) {
			for (int j = 0; j < X1.length; j++) {
				if(primeraVez == false) {
					String valor = X0[i]+""+X1[j];
					distributiva += valor;
					primeraVez=true;
				}else {
					String valor = ","+X0[i]+""+X1[j];
					distributiva += valor;
				}
			}
		}
		
		return distributiva;
	}
}
