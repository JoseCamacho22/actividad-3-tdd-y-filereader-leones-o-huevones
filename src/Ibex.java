import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Ibex {

	public static final int CAMPO_FECHA = 2;
	public static final int CAMPO_CLOSE = 7;

	final static String RUTA = "res/bolsa.csv";

	
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
