
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
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


public class Ibex {

	public static final int CAMPO_FECHA = 2;
	public static final int CAMPO_CLOSE = 7;

	final static String RUTA = "res/bolsa.csv";
	private static final int TAM_AÑO = 0;
		
		
/*Metodo 3
 * 
3- Crear mediante sobrecarga otro método que recibe la ruta de un fichero de texto CSV y 
devuelve en float con la media de cierre de la bolsa de un año dado, si la fecha no existe devolverá 
0.0, si ocurriera algún error devolverá -1
*/
		
		public float getCloseValue(int year, String path) {
			
			Path ruta = Paths.get(path);
			String record;
			float closeValue = 0.0f;
			String sYear = Integer.toString(year);
			int numYears =0;
			
					//dos maneras de leer fichero java Nio y un try witch resources 
					//para cerrarlo automaticamente
				try (BufferedReader br = Files.newBufferedReader(ruta)){
					//BufferedReader br = new BufferedReader(new FileReader(patch)) ***Paquete java.io
					
					while ((record=br.readLine())!=null);
					
					String[] campos = record.split(",");//reconoce hasta la coma tanto el split como tokenizer
					//StringTokenizer st =new StringTokenizer(record, ",");
					
					String sReadYear = campos[CAMPO_FECHA].substring(0, TAM_AÑO);
					
						//comparo las dos para saber si son iguales despues de pasarlo al mismo tipo
							if (sYear.equals(sReadYear)) {
								numYears++;
								closeValue += Float.parseFloat(campos[CAMPO_CLOSE]);
				}
							
						if (numYears != 0)
							closeValue = closeValue / numYears;}	
				 catch (Exception e) {
					closeValue = -1;
				}
				return closeValue;

		}
}
	

