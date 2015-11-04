	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Scanner;

	public class Main {

		public static void main(String[] args) {
			
			/* Metodo 3*/
			System.out.println("******Metodo3******");
			
			Ibex ibex=new Ibex();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		float pr=ibex.getCloseValue(2001,"res/bolsa.csv");
		System.out.println(pr);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fecha1="20000103";
		String fecha2="20000120";
		
		try{
			System.out.println(Ibex.getCloseAvg(sdf.parse(fecha1), sdf.parse(fecha2)));
		}catch(ParseException e){}
	}

	}
		
