import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fecha1="20000103";
		String fecha2="20000120";
		
		try{
			System.out.println(Ibex.getCloseAvg(sdf.parse(fecha1), sdf.parse(fecha2)));
		}catch(ParseException e){}
	}

}
