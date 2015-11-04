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
			

	}
		
}