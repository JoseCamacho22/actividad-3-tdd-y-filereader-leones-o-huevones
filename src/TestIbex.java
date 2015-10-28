import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestIbex {
	

	@Test
	public void testFechaNoExiste() {
		float resultado = 0.0f;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "00000000";
		Date dFecha = null;
		try {
			dFecha = sdf.parse(sFecha);
		} catch (ParseException e) {
			
		}
		assertEquals(resultado, Ibex.getCloseValue(dFecha ), 0.1f);
	}
	
	@Test
	public void testFechaNull(){
		float resultado= -1;
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
	
	

}