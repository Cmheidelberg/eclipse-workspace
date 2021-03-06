package cheidelb_CSCI201L_Assignment2;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class Main {

	static boolean DEBUG_MODE = true;
	private static Map<String,Semaphore> semaphores;
	
	public static void main(String[] args) {
		
		String path = "";
		Data read = null;
		Csv csv = null;
		
		Scanner scan = new Scanner(System.in);
		do {
			
			System.out.print("What is the name of the file contianing company information? ");
			path = scan.nextLine();
			read = readJson(path);
			
		} while(read == null);
		
		initiateSemaphores(read);
		path = "";
		
		do {
			
			System.out.print("What is the name of the file containing the schedule information? ");
			path = scan.nextLine();
			csv = readCsv(path);			
		} while (csv == null);
		
		scan.close();	
		
		TimeDifference date = new TimeDifference();
		String[] line = csv.getNext();
		int lastTime = 0;
		int currTime;
		int count = 0;
		
		System.out.println("\nStarting execution of program...");
		
		// Loop over the csv file
		while(line != null) {
		currTime = Integer.valueOf(line[0]);
		
		//debug("csv","[" + line[0] + "," + line[1] + "," + line[2] + "]");
		// Wait as time progresses 
		if(lastTime != currTime) {
			try {
				Thread.sleep((currTime - lastTime) * 1000);
			}catch (InterruptedException ie) {
				System.out.println("Main interrupted!");
			}
		}
		
		Semaphore s = getSemaphore(line[1]);
		
		if (s != null) {
			StockBroker sb = new StockBroker(s,line[1],count,Integer.valueOf(line[2]),date);
			sb.start();
		}
		
		count++;
		lastTime = currTime;
		line = csv.getNext();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie){
			System.out.println("MAIN INTERUPTED: " + ie);
		}
		System.out.println("All trades completed!");
		
	}


	public static Semaphore getSemaphore(String ticker) {
		ticker = ticker.replaceAll("\\s", ""); 
		ticker = ticker.toUpperCase();
		return semaphores.get(ticker);
		
	}
	
	
	public static void initiateSemaphores(Data data) {
		ArrayList<Stock> al = data.getData();
		semaphores = new HashMap<String,Semaphore>();
		for(int i = 0; i < al.size(); i++) {
			Stock stock = al.get(i);
			Semaphore s = new Semaphore(stock.getStockBrokers());
			semaphores.put(stock.getTicker(),s);
		}
	}
	
	
	public static Csv readCsv(String path) {
		
		Csv csv = null;
		
		try {
			ArrayList<String[]> tmp = new ArrayList<String[]>();
			BufferedReader br = new BufferedReader(new FileReader(path));			
			String line = br.readLine(); 
			while(line != null) {
				String[] values = line.split(",");
				tmp.add(values);
				line = br.readLine();
			}
			
			if(tmp.size() > 0) {
				csv = new Csv(tmp);
			}
			
			if ( csv != null && !csv.validCsv()) {
				System.out.println("Malformed csv file.");
				br.close();
				return null;
			}
			br.close();
			return csv;
		} catch(FileNotFoundException fnfe) {
			System.out.printf("The file \"%s\" cannot be found.\n\n",path);
		} catch(IOException ioe) {
			System.out.printf("io exception when reading \"%s\". Please check that file is valid\n",path);
		}
				
		return null;
		
	}
	
	
	public static Data readJson(String path) {
		FileReader fr = null;
		Data data = null;
		
		try {
			
			fr = new FileReader(path);
			Gson gson = new Gson();
			
			try {
			data = gson.fromJson(fr, Data.class);
			
			}
			catch (JsonSyntaxException jse) {
				System.out.printf("The file %s is not formatted properly.\n\n",path);
			}
					
		} catch (FileNotFoundException fnfe) {
			System.out.printf("The file \"%s\" cannot be found.\n\n",path);
			
		} 
		
		//validate date fields
		if (data != null && !data.validData()) {
			System.out.printf("%s has missing or malformed parameter(s). Please select a valid file.\n\n", path);
			data = null;
		}
						
		return data;
	}
	
}



