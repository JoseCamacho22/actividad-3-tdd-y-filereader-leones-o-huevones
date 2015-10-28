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

public class Ibex {

	public static final int CAMPO_FECHA = 2;
	final static String RUTA = "res/bolsa.csv";
	public static final long CAMPO_CLOSE = 1086310;

	static float getCloseValue(Date fecha, String path) {
		String record;
		Path ruta = Paths.get(path);
		float close = 0.0f;
		if (fecha == null)
			return -1;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = sdf.format(fecha);

		// Utilizo el paquete nio de Java y un try with resources
		// para cerrarlo automáticamente
		try (BufferedReader br = Files.newBufferedReader(ruta)) {
			// BufferedReader br = new BufferedReader (new FileReader(path));

			while ((record = br.readLine()) != null) {

				ArrayList<String> lFields = leerCampos(record);

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

	/**
	 * Método que me obtiene los campos de un fichero csv
	 * 
	 * @param record
	 * @return
	 */
	private static ArrayList<String> leerCampos(String record) {
		ArrayList<String> lista = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(record, ",");
		while (st.hasMoreTokens()) {
			lista.add(st.nextToken());
		}

		return lista;
	}

	static float getCloseValue(Date fecha) {
		return getCloseValue(fecha, RUTA);
	}

	/**
	 * Metodo 6 Crear mediante un método que recibe la ruta de un fichero de
	 * texto CSV y devuelve un ArraytList de fechas en las que se haya superado
	 * el valor dado en el cierre, si ninguna lo cumple devolverá un arraylist
	 * vacio si ocurriera algún error devolverá null ArrayList<Date>
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
						fechas.add(fecha);// que recibe como parámetro, se
											// añadirá al array final.
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