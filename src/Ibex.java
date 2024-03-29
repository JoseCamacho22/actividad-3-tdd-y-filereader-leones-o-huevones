import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author Miguel
 *
 */

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.List;


public class Ibex {

	public static final int CAMPO_FECHA = 2;
	public static final int CAMPO_CLOSE = 7;
	private static final int TAM_AÑO = 4;
	public static final int COL_CLOSE = 7;
	public static final int COL_FECHA =2;


	final static String RUTA = "res/bolsa.csv";

	
/*Metodo 3
 * 
3- Crear mediante sobrecarga otro método que recibe la ruta de un fichero de texto CSV y 
devuelve en float con la media de cierre de la bolsa de un año dado, si la fecha no existe devolverá 
0.0, si ocurriera algún error devolverá -1
*/
		
		public float getCloseValue(int year, String path) {
			
			Path ruta = Paths.get(path);
			String record ;
			float closeValue = 0.0f;
			String sYear = Integer.toString(year);
			int numYears =0;
			
					//dos maneras de leer fichero java Nio y un try witch resources 
					//para cerrarlo automaticamente
				try (BufferedReader br = Files.newBufferedReader(ruta)){
					//BufferedReader br = new BufferedReader(new FileReader(patch)) ***Paquete java.io
					
					while ((record=br.readLine())!=null){
					
					String[] campos = record.split(",");//reconoce hasta la coma tanto el split como tokenizer
					//StringTokenizer st =new StringTokenizer(record, ",");
					
					String sReadYear = campos[CAMPO_FECHA].substring(0, TAM_AÑO);//la primera posicion y hasta donde
					
						//comparo las dos para saber si son iguales despues de pasarlo al mismo tipo
							if (sYear.equals(sReadYear)) {
								numYears++;
								closeValue += Float.parseFloat(campos[CAMPO_CLOSE]);
				}
					}
							
						if (numYears != 0)
							closeValue = closeValue / numYears;
						}catch (Exception e) {
					 	e.printStackTrace();					 
					closeValue = -1;
				}
				return closeValue;

		}


		/*Metodo 5
 * 
*/
	
	
	static float getValue(Date fecha, int field, String path ){
		String record;
		Path ruta = Paths.get(path);
		float close = 0.0f;
		String sFecha = dateToString(fecha);
		
		if(sFecha==null) return 0.0f;
		
		// Utilizo el paquete nio de Java y un try with resources
		// para cerrarlo automáticamente
		try (BufferedReader br = Files.newBufferedReader(ruta)) {
			// BufferedReader br = new BufferedReader (new FileReader(path));

			while ((record = br.readLine()) != null) {

				ArrayList<String> lFields = leerCampos(record, ",");

				try {
					if (sFecha.equals(lFields.get(CAMPO_FECHA))) {
						close = Float.parseFloat(lFields.get(field));
					}

				} 
				
				catch (NumberFormatException e) {
					System.err.println("Campo no existente");
					return -2;
				}
				
				catch (Exception e) {
					System.err.println("Error al leer la linea");
					return -2;
				}
				

			}

		} catch (IOException e) {
			System.err.println("Ruta no existente");
			close = -1;
		}

		return close;

	};
	
	
	
	
	private static String dateToString(Date fecha) {
		String sFecha =  null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			sFecha = sdf.format(fecha);
		}
		catch(Exception e){
		}
		return sFecha;
	}
	
	
	private static ArrayList<String> leerCampos(String record, String separador) {
		ArrayList<String> lista = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(record, separador);
		while (st.hasMoreTokens()) {
			lista.add(st.nextToken());
		}

		return lista;
	}
	

	/**
	 * 
	 * @param ini Fecha inicial cogida de getCloseAvg(Date fecha1, Date fecha2), procedente del main
	 * @param fin Fecha final cogida de getCloseAvg(Date fecha1, Date fecha2), procedente del main
	 * @param path Ruta del archivo
	 * @return La media del valor close comprendido entre ambas fechas
	 */
	static float getCloseAvg(Date ini, Date fin, String path) {
		
		String record;
		Path ruta = Paths.get(path);
		String fechaIni = dateToString(ini);
		String fechaFin = dateToString(fin);
		int contador = 0;
		float close = 0.0f;

		if (fechaIni == null || fechaFin == null)
			return -1;

		try (BufferedReader br = Files.newBufferedReader(ruta)) {

			// Bucle while para leer el fichero
			while ((record = br.readLine()) != null) {

				ArrayList<String> lFields = leerCampos(record, ",");

				//Si la fecha de la linea actual est� comprendida entre las fechas inicial y final,
				//inclusive, sumar� el valor del campo cierre a la variable close
				try {
					if ((Float.parseFloat(lFields.get(COL_FECHA))) >= (Float
							.parseFloat(fechaIni))
							&& (Float.parseFloat(lFields.get(COL_FECHA))) <= (Float
									.parseFloat(fechaFin))) {
						contador++;
						close += Float.parseFloat(lFields.get(COL_CLOSE));
					}

				} catch (Exception e) {
					System.err.print("");
				}

			}

		} catch (IOException e) {
			System.err.println("Error E/S con " + fechaIni);
			close = -1;
		}

		return close / contador;

	}
	
		static float getCloseAvg(Date fecha1, Date fecha2) {
		return getCloseAvg(fecha1, fecha2, RUTA);
	}


	static float getCloseValue(Date fecha, String path) {
		String record;
		Path ruta = Paths.get(path);
		float close = 0.0f;
		if (fecha == null)
			return -1;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = sdf.format(fecha);

		// Utilizo el paquete nio de Java y un try with resources
		// para cerrarlo autom�ticamente
		try (BufferedReader br = Files.newBufferedReader(ruta)) {
			// BufferedReader br = new BufferedReader (new FileReader(path));

			while ((record = br.readLine()) != null) {

				ArrayList<String> lFields = leerCampos(record, ",");

				try {
					if (sFecha.equals(lFields.get(CAMPO_FECHA))) {
						int CAMPO_CLOSE = 7;
						close = Float.parseFloat(lFields.get(CAMPO_CLOSE));
					}

				} catch (Exception e) {
					System.err.println("Erro al leer la linea");
				}

			}

		} catch (IOException e) {
			System.err.println("Error E/S");
			close = -1;
		}

		return close;
	}
	
	static float getCloseValue(Date fecha) {
		return getCloseValue(fecha, RUTA);
	}
	
/**
	 * Metodo 6 Crear mediante un m�todo que recibe la ruta de un fichero de
	 * texto CSV y devuelve un ArraytList de fechas en las que se haya superado
	 * el valor dado en el cierre, si ninguna lo cumple devolver� un arraylist
	 * vacio si ocurriera alg�n error devolver� null ArrayList<Date>
	 * getDatePoints(long points, String path)
	 */

	public static ArrayList<Date> getDatePoints(long points, String path) {

		ArrayList<Date> fechas = new ArrayList<>();
		File fcsv = new File(RUTA);
		int contador = 0;
		try {

			// Para saltar el linea del titulo
			double elementoFila;// Variable donde se convierte en la cadena de
								// los datos de cierre
			List<String> lineas = Files.readAllLines(fcsv.toPath(),
					StandardCharsets.UTF_8);

			List<String> lines = Files.readAllLines(fcsv.toPath(),
					StandardCharsets.UTF_8);

			for (String line : lines) {

				if (contador > 0) {
					String[] array = line.split(",");
					DateFormat format = new SimpleDateFormat("yyyyMMdd");
					Date fecha = format.parse(array[2]);
					elementoFila = Double.parseDouble(array[7]);
					if (elementoFila > points) {
						fechas.add(fecha);// que recibe como par�metro, se
											// a�adir� al array final.
					}

				}
				contador++;

			}

			for (Date fecha : fechas) {
				System.out.println(fecha.toString());
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("ERROR E/S");
		} catch (ParseException e) {
			System.out.println("Error");
		}
		return fechas;
	}
}
	

