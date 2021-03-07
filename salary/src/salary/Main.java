package salary;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int WEEKS = 52;
		int HOURS = 40;
		
		System.out.print("How many employees?: ");
		Scanner scan = new Scanner(System.in);
		int employees = scan.nextInt();
		
		String[] names = new String[employees];
		Double[] rates = new Double[employees];
		
		for(int i = 0; i < employees; i++) {
			System.out.printf("Please enter name of employee #%d: ",i+1);
			String cname = scan.next();	
			names[i] = cname;
			
			System.out.printf("Please enter hourly rate of %s: ",cname);
			Double crate = scan.nextDouble();			
			rates[i] = crate;	
		}
		
		for (int i = 0; i < employees; i++) {
			System.out.printf("%s's annual salary is $%.1f%n",names[i],rates[i] * WEEKS * HOURS);
		}

	}

}
