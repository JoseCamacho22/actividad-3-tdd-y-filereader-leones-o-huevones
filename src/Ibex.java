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

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.List;


public class Ibex {

	public static final int CAMPO_FECHA = 2;
	public static final int CAMPO_CLOSE = 7;
	private static final int TAM_AÑO = 4;

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


	

}
	

