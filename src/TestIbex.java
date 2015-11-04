/*import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestIbex {
	
<<<<<<< HEAD

=======
	final static String ruta = "./bolsa.csv";
	Ibex ibex=new Ibex();
>>>>>>> refs/remotes/origin/rama_miguel
	@Test
	public void testFechaNoExiste() {
<<<<<<< HEAD
		float resultado = 0.0f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, Ibex.getCloseValue(dFecha ), 0.1f);
=======
		Float resultado = 0.0f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, Ibex.getCloseValue(dFecha, ruta ),0.0001);
>>>>>>> refs/remotes/origin/rama_miguel
	}
	
	@Test
	public void testFechaNull(){
		float resultado= -1;
<<<<<<< HEAD
		assertEquals(resultado, Ibex.getCloseValue(null ),0.1f);

	}
	

	
	@Test
	public void testFechaExiste(){
		float resultado= 11206.6f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "20000104";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, 
				Ibex.getCloseValue(dFecha),0.1f);

	}
	
	
=======
		assertEquals(resultado, Ibex.getCloseValue(null ),0.0001);
>>>>>>> refs/remotes/origin/rama_miguel

	}
	
	@Test
	public void testFechaExiste(){
		float resultado= 11206.6f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "20000104";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, 
				Ibex.getCloseValue(dFecha),0.1);

	}
	

	
	
	
	
	@Test
	public void testFechaNull2(){
		float resultado= -1;
		assertEquals(resultado, Ibex.getDatePoints(00001, ruta));

	}
	
	
}
<<<<<<< HEAD
*/
