package modelo;

import java.util.ArrayList;

/**
 * Clase que se encarga de manejar la información de la gramática en FNC y de hacer el algoritmo de CYK
 * @author Juan Manuel Imbachí - Jorge Antonio Morales 
 *
 */
public class Gramatica {
	
	/**
	 * Arreglo que contiene las producciones de la gramática
	 */
	private ArrayList<Produccion> producciones;
	
	/**
	 * Arreglo con el alfabeto permitido por la gramática
	 */
	private String[] alfabeto;
	
	/**
	 * Matriz que se usará para desarrollar el algoritmo de CYK
	 */
	private String[][] tablaCYK;
	
	/**
	 * Constructor de la clase Gramática
	 * @param cantProducciones Entero con la cantidad de producciones que tiene la gramática
	 * @param alfabeto Arreglo de String con el alfabeto aceptado por la gramática
	 */
	public Gramatica(int cantProducciones, String[] alfabeto) {
		
		producciones = new ArrayList<Produccion>();
		
		char nombreVariable;
		nombreVariable = 'S';
		/**
		 * Crea las producciones de la gramática.
		 * La primera producción tiene como cabeza la letra 'S' y luego asigna automáticamente a
		 * cada producción una letra, se empieza con 'A' y se continúa en orden alfabético
		 */
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
		
		this.alfabeto = alfabeto;
		
	}
	
	/**
	 * Método get del alfabeto de la gramática
	 * @return Retorna un arreglo de String con el alfabeto de la gramática
	 */
	public String[] getAlfabeto() {
		return alfabeto;
	}
	
	/**
	 * Método get de la tablaCYK
	 * @return Retorna una matriz de String 
	 */
	public String[][] getTablaCYK(){
		return tablaCYK;
	}
	
	/**
	 * Método encargado de dar formato a los elementos en la tablaCYK una vez se ha completado el algoritmo CYK
	 */
	public void presentarMatriz() {
		for (int i = 0; i <tablaCYK.length; i++) {
			for (int j = 0; j < tablaCYK[0].length; j++) {
				if(tablaCYK[i][j] == null) {
					tablaCYK[i][j] ="-";
				}else if(tablaCYK[i][j].equals("")) {
					tablaCYK[i][j] = "ø";
				}else {
					tablaCYK[i][j] = "{"+tablaCYK[i][j]+"}";
				}
			}
		}
	}
	
	/**
	 * Método set del alfabeto de la gramática
	 * @param alfabeto Arreglo de String con el nuevo alfabeto
	 */
	public void setAlfabeto(String[] alfabeto) {
		this.alfabeto = alfabeto;
	}
	
	/**
	 * Método encargado de agregar producciones.
	 * @param produccion posición en la lista de producciones a la que se le desea agregar una producción
	 * @param valor String con el valor de la producción, cuerpo de la producción.
	 */
	public void agregarProducciones(int produccion, String valor) {
		producciones.get(produccion).agregarProduccion(valor);
	}
	
	/**
	 * Método get de la lista de producciones
	 * @return Un ArrayList con la lista de producciones
	 */
	public ArrayList<Produccion> getProducciones(){
		return producciones;
	}
	
	/**
	 * Método encargado de realizar el algoritmo de CYK para determinar si una cadena es generada o no por una gramática
	 * @param cadena Cadena que se quiere verificar
	 * @return True si la cadena es generada por la gramática, false en caso contrario.
	 */
	public boolean CYK(String cadena) {
		tablaCYK = new String[cadena.length()][cadena.length()];
		tablaCYK = inicializarCYK(tablaCYK, cadena);
		repetirCYK();
		boolean generado = SPertenece();
		presentarMatriz();
		return generado;
	}
	
	/**
	 * Método encargado de verificar si la cadena pertenece una vez se completó el algoritmo CYK, es decir, verifica si la 'S'
	 * quedó en el último conjunto generado por el algoritmo
	 * @return True en caso de que 'S' se encuentre en el último conjunto, false en caso contrario.
	 */
	public boolean SPertenece() {
		String[] producciones = tablaCYK[0][tablaCYK.length-1].split(",");
		boolean pertenece = false;
		for (int i = 0; i < producciones.length && !pertenece; i++) {
			if(producciones[i].equals("S")) {
				pertenece = true;
			}
		}
		return pertenece;
	}
	/**
	 * Método encargado de validar si una cadena está compuesta por elementos del alfabeto de la gramática.
	 * @param cadenaValidar Cadena que se quiere validar
	 * @return true en caso de que esté compuesta por elementos del alfabeto, false en caso contrario.
	 */
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
	
	/**
	 * Método que se encarga de realizar el paso inicial del algoritmo CYK. Busca a que producciones pertenece cada simbolo de la cadena.
	 * @param tabla Matriz vacía con la que trabajará el algoritmo CYK
	 * @param cadena Cadena que se usará en el algoritmo CYK
	 * @return La matriz del CYK con la primera columna llenada.
	 */
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
	
	/**
	 * Método que se encarga de realizar el paso repetitivo del algoritmo CYK.
	 */
	public void repetirCYK() {
		int n = tablaCYK.length;
		for (int j = 1; j < n; j++) {
			
			for (int i = 0; i <n-j; i++) {
				String Xij = "";
				boolean primeraVez = false;
				for (int k = 0; k <= j-1; k++) {
								
					String Xik = tablaCYK[i][k];
					String X2 = tablaCYK[i+k+1][j-k-1];
					
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
	
	}
	/**
	 * Método encargado de buscar en que producciones se encuentra una determinada cadena.
	 * @param Xij Cadena que se desea buscar en las producciones de la gramática.
	 * @return Una cadena con las producciones, separadas por comas, donde se encuentra la cadena.
	 */
	public String buscarProducciones(String Xij) {
		String[] produc = Xij.split(",");
		String variables = "";
		boolean primeraVez = false;
		for (int i = 0; i < produc.length; i++) {
			String simbolo = produc[i];
			for (int j = 0; j < producciones.size(); j++) {
				boolean pertenece = producciones.get(j).encontrarProduccion(simbolo);
				if(pertenece == true  && primeraVez == false && variableRepetida(variables, producciones.get(j).getNombre()) == false) {
					variables +=producciones.get(j).getNombre();
					primeraVez=true;
				}else if(pertenece == true && variableRepetida(variables, producciones.get(j).getNombre()) == false) {
					variables += ","+producciones.get(j).getNombre();
				}
			}

		}
		
		return variables;
	}
	
	/**
	 * Método usado en la busqueda de producciones. Se encarga de indicar si una produccion ya se encuentra en el conjunto de producciones
	 * @param variables conjunto de producciones
	 * @param simbolo produccion que se va a verificar si ya está agregada
	 * @return true si la producción ya ha sido agregadaa, false en caso contrario
	 */
	public boolean variableRepetida(String variables, String simbolo) {
		String[] vars = variables.split(",");
		boolean repetido = false;
		for (int i = 0; i < vars.length && !repetido; i++) {
			if(vars[i].equals(simbolo)) {
				repetido = true;
			}
		}
		return repetido;
	}
	
	/**
	 * Método encargado de realizar la concatenación entre los elementos de los conjuntos en el paso repetitivo del algoritmo CYK
	 * @param Xik Elementos del primer conjunto separados por comas
	 * @param X2 Elementos del segundo conjunto separados por comas
	 * @return Cadena con la concatenación entre los elementos de los dos conjuntos separados por comas
	 */
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

	/**
	 * Método encargado de validar que las producciones de la gramática son de tamaño 2 o 1, y que los terminales pertenecen al alfabeto
	 * @param prod Arreglo de String con las producciones de la gramática
	 * @return true en caso de que cumpla las condiciones de tamaño y de pertenencia del alfabeto, false en caso contrario.
	 */
	public boolean validarProducciones(String[] prod) {
		boolean valido = true;
		for (int i = 0; i < prod.length && valido; i++) {
			String[] produccion = prod[i].split(",");
			for(int j = 0; j < produccion.length && valido; j++) {
				if(produccion[j].length() < 2) {
					valido = validarCadenaPerteneceAlAlfabeto(produccion[j]);
				} else if(produccion[j].length() > 2) {
					valido = false;
				}
			}
		}
		return valido;
	}
}
