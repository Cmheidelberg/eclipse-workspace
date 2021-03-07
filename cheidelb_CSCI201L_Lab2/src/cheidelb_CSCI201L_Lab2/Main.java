package cheidelb_CSCI201L_Lab2;

public class Main {

	public static void main(String[] args) {
		

	}

}


class Person{
	private String firstName;
	private String lastName;
	private String birthdate;
	
	Person(String fn, String ln, String b) {
		firstName = fn;
		lastName = ln;
		birthdate = b;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
}


abstract class Employee extends Person {
	private int employeeID;
	private String jobTitle;
	private String company;
	
	Employee(String fn, String ln, String b, int eid, String jt, String c) {
		super(fn, ln, b);
		employeeID = eid;
		jobTitle = jt;
		company = c;
	}
	
	public abstract double getAnnualSalary();
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public String getCompany() {
		return company;
	}
}


class HourlyEmployee extends Employee {
	private int hourlyRate;
	private int numberHoursPerWeek;
	
	HourlyEmployee(String fn, String ln, String b, int eid, String jt, String c, int hr, int nhpw) {
		super(fn, ln, b, eid, jt, c);
		hourlyRate = hr;
		numberHoursPerWeek = nhpw;
	}
	
	public double getAnnualSalary() {
		return hourlyRate * numberHoursPerWeek * 52.0;
	}
}


class SalariedEmployee extends Employee {
	protected double annualSalary;
	
	SalariedEmployee(String fn, String ln, String b, int eid, String jt, String c, double as) {
		super(fn, ln, b, eid, jt, c);
		annualSalary = as;
	}
	
	public double getAnnualSalary() {
		return annualSalary;
	}
}


class CommissionEmployee extends SalariedEmployee {
	private double salesTotal;
	private double comissionPercentage;
	
	CommissionEmployee(String fn, String ln, String b, int eid, String jt, String c, double as, double st, double cp) {
		super(fn, ln, b, eid, jt, c, as);
		salesTotal = st;
		comissionPercentage = cp;		
	}
	
	public double getAnnualSalary() {
		return annualSalary + salesTotal * comissionPercentage;
	}
}