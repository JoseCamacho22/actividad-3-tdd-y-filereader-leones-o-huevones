
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestIbex {
	
	final static String ruta = "res/bolsa.csv";
	Ibex ibex=new Ibex();
	@Test
	public void testFechaNoExisteGetValueALVARO() {
		Float resultado = 0.0f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, Ibex.getValue(dFecha,7, ruta ),0.0001);
	}
	
	@Test
	public void testFechaNullGetValueALVARO(){
		float resultado= 0.0f;
		assertEquals(resultado, Ibex.getValue(null,7, ruta ),0.0001);

	}
	
	@Test
	public void testFechaExisteGetValueALVARO(){
		float resultado= 11206.6f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "20000104";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, 
				Ibex.getValue(dFecha,7, ruta ),0.1);

	}

}
	
