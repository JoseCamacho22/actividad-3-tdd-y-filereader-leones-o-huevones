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

	public static final int COL_FECHA = 2;
	final static String RUTA = "res//bolsa.csv";
	public static final int COL_CLOSE = 7;

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

				//Si la fecha de la linea actual está comprendida entre las fechas inicial y final,
				//inclusive, sumará el valor del campo cierre a la variable close
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

	private static String dateToString(Date fecha) {
		String sFecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			sFecha = sdf.format(fecha);
		} catch (Exception e) {
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

	static float getCloseAvg(Date fecha1, Date fecha2) {
		return getCloseAvg(fecha1, fecha2, RUTA);
	}
}
