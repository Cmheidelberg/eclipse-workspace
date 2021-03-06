package cheidelb_CSCI201L_Assignment2;

import java.util.concurrent.Semaphore;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;


class StockBroker extends Thread{

	Semaphore sem;
	String exchange;
	int id;
	int numOfStocks;
	static TimeDifference td;

	public StockBroker(Semaphore sem, String exchange, int id, int numOfStocks, TimeDifference timeDifference) {
		
		super(exchange + Integer.toString(id));
		this.sem = sem;
		this.exchange = exchange;
		this.id = id;
		this.numOfStocks = numOfStocks;
		td = timeDifference;
	}
	
	public void run() {
		
		try {
			sem.acquire();
			String plural = "";
			String bs = "purchase";
			
			if(numOfStocks < 0) {
				bs = "sell";
				numOfStocks *= -1;
			}
			
			if(numOfStocks != 1 && numOfStocks != 0) {
				plural = "s";
			}		
		
			printMessage("Starting " + bs +" of " + numOfStocks +" stock" + plural + " of " + exchange);
					
			Thread.sleep(1000);
			
			printMessage("Finished " + bs +" of " + numOfStocks +" stock" + plural + " of " + exchange);
		} catch (InterruptedException ie){
			System.out.println("IE: " + ie.getMessage());
		} finally {
			sem.release();
		}
	}
	
	public static void printMessage(String message) {
		
//		Calendar cal = Calendar.getInstance();
//		String datetime = "[" + cal.get(Calendar.HOUR_OF_DAY);
//		datetime += ":" + cal.get(Calendar.MINUTE);
//		datetime += ":" + cal.get(Calendar.SECOND);
//		datetime += ":" + cal.get(Calendar.MILLISECOND);
//		System.out.println(datetime + "] " + message);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
		Date tmp = td.getTimeSinceStart();
		String dateLine = "[ParseException (null)]";
		
		if (tmp != null) {
			dateLine = "[" + sdf.format(tmp) + "]";
		}
		
		System.out.println(dateLine + " " + message);
	}
	
}

class TimeDifference {
	private static Date startTime;
	
	public TimeDifference () {
		startTime = new Date();
	}
	
	public Date getTimeSinceStart() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");		
		Date currTime = new Date();
		
		long diff = currTime.getTime() - startTime.getTime();
		long hourDiff   = diff / 3_600_000 % 24;
		long minuteDiff = diff / 60_000 % 60;
		long secondDiff = diff / 1000 % 60; 
		long milisecondDiff = diff % 1000;
				
		String difference = String.format("%d:%d:%d.%d", hourDiff,minuteDiff,secondDiff,milisecondDiff);
		try {
			return format.parse(difference);
		} catch (ParseException pe) {
			return null;
		}
		
		
	}
}
