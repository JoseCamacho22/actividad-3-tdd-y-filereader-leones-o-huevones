
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		/* Metodo 1*/
		System.out.println("******Metodo1******");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sFecha = "20000104";
		try {
			System.out.println(Ibex.getCloseValue(sdf.parse(sFecha)));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		/*Metodo 6*/
		System.out.println("********Metodo6******");
		
		Ibex ibex=new Ibex();
		ibex.getDatePoints(15000,"bolsa.csv");
		
	
		
		
	}

}
