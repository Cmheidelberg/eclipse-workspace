package cheidelb_CSCI201L_Assignment1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Main {

	static boolean DEBUG_MODE = false;
	
	public static void main(String[] args) {
		
		String path = "";
		Data read = null;
		
		Scanner scan = new Scanner(System.in);
		do {
			
			System.out.print("What is the name of the company file? ");
			path = scan.nextLine();
			//path = "stock.json";
			read = readJson(path);
			
		} while(read == null);
		
		System.out.println("The file has been properly read.");
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Display all public companies");
		options.add("Search for a stock (by ticker)");
		options.add("Search for all stocks on an exchange");
		options.add("Add a new company/stocks");
		options.add("Remove a company");
		options.add("Sort companies");
		options.add("Exit");
		
		
		boolean cont = true;
		
		while(cont) {
			System.out.print("\n");
			int num = menu("What would you like to do?", options);
			
			switch(num) {
			
			case 1:
				if (read.getData().size() > 0) {
					printData(read);
				} else {
					System.out.println("There are no stocks to display.");
				}
				break;
			
			case 2:
				
				boolean valid = false;
				if (read.getData().size() > 0) {
					while(!valid) {
						
						String key = awaitValidInput("What is the name of the company you would like to search for?",scan); 

						ArrayList<Stock> tick = searchByType(key,"ticker",read);
						if (tick.size() > 0) {
							System.out.printf("\n%s, symbol %s, started on %s, listed on %s\n",tick.get(0).getName(),tick.get(0).getTicker(),tick.get(0).getStartDate(),tick.get(0).getExchangeCode());
							valid = true;
						} else {
							System.out.printf("\n%s could not be found.\n\n",key);
						}
						
					}
				} else {
					System.out.println("There are no stocks to search for.");
				}
				
				break;
			
			case 3:
				
				valid = false;
				if (read.getData().size() > 0) {
						
					while(!valid) {
						String key = awaitValidInput("What stock exchange would you like to search for?", scan);
						
						ArrayList<Stock> tick = searchByType(key,"exchangeCode",read);
						if (tick.size() > 0) {
							String out = "";
							for (int i = 0; i < tick.size(); i++) {
								
								String currName = tick.get(i).getTicker();
								
								if (i == tick.size() -1) {
									out += currName;
								}
								else if(i < tick.size() -2) {
									out += String.format("%s, ", currName);
								}
								else {
									out += currName + " and ";
								}
							}
							System.out.printf("\n%s found on the %s exchange\n",out,tick.get(0).getExchangeCode());
							valid = true;
						} else {
							System.out.printf("\nNo exchange named %s found.\n\n",key);
						}
						
					}
				} else {
					System.out.println("There are no stocks to search for.");
				}
				
				break;
				
			case 4:
				addStock(read);
				break;
				
			case 5:
				removeStock(read);
				break;
				
			case 6:
				
				if (read.getData().size() > 0) {
					ArrayList<String> op = new ArrayList<String>();
					op.add("A to Z");
					op.add("Z to A");
					
					int ch = menu("What would you like to sort by?",op);
					String sortedBy = "A-Z";
					boolean aToZ = true;
					if (ch == 2) {
						aToZ = false;
						sortedBy = "Z-A";
					}
					
					ArrayList<Stock> sorted = sort("name", read, aToZ);
					read.setData(sorted);
					
					System.out.printf("Your companies are now sorted in alphabetical order (%s).\n",sortedBy);
				} else {
					System.out.println("There are no stocks to sort.");
				}
				break;
			case 7:
				
				ArrayList<String> yn = new ArrayList<String>();
				yn.add("Yes");
				yn.add("No");
				
				int ch = menu("Would you like to save your edits?",yn);
				
				switch(ch) {
					case 1:
						
						boolean saved = true;
						try {
							Writer wr = new FileWriter(path);
							
							Gson gson = new GsonBuilder().setPrettyPrinting().create();
							
							gson.toJson(read, wr);
							
							wr.close();
							
							System.out.println("Your edits have been saved to " + path);
						} catch (IOException ioe) {
							System.out.println("Unable to save to " + path + ". Is it open?"); 
						}
						
						break;
						
					case 2:
						System.out.println("Quitting without saving.");
						break;
				}
				
				System.out.println("Thank you for using my program!");
				cont = false;
				break;
			}
		}
		scan.close();
	}
	
	private static boolean addStock(Data data) {
		boolean validName = false;
		Scanner scan = new Scanner(System.in);
	
		String name = "";
		String ticker = "";
		String startDate = "";
		String description = "";
		String exchangeCode = "";
		
		while (!validName) {
			name = awaitValidInput(String.format("What is the name of the company you would like to add?"),scan);
			System.out.print("\n");
			
			ArrayList<Stock> tmp = searchByType(name, "name", data);
			
			if (tmp.size() == 0) {
				validName = true;
			}else {
				System.out.printf("There is already an entry for %s.\n\n",name);
			}
			
		}
		
		validName = false;
		while (!validName) {
			ticker = awaitValidInput(String.format("What is the stock symbol of %s? ",name),scan).toUpperCase();
			System.out.print("\n");
			
			if (searchByType(ticker, "ticker", data).size() == 0) {
				validName = true;
			}else {
				System.out.printf("There is already an ticker with value %s.\n\n",ticker);
			}
		}
		
		
		validName = false;
		while(!validName) {
			startDate = awaitValidInput(String.format("What is the start date of %s?", name),scan);
			System.out.print("\n");
			
			boolean valid = true;
			
			if(startDate != null) {
				String[] dates = startDate.split("-");
				
				if(dates.length == 3) {
					if(!dates[0].matches("[0-9]+") || dates[0].length() != 4) {
						valid = false;
						debug("ValidateData","Invalid year \"" + dates[0] + "\" in " + startDate);
					}
					
					if(!dates[1].matches("[0-9]+") || dates[1].length() != 2) {
						valid = false;
						debug("ValidateData","Invalid month in \"" + dates[1] + "\" in " + startDate);
					}
					
					if(!dates[2].matches("[0-9]+") || dates[2].length() != 2) {
						valid = false;
						debug("ValidateData","Invalid day in \"" + dates[1] + "\" in " + startDate);
					}
				} else {
					valid = false;
					debug("ValidateData","Invalid date format \"" + startDate + "\" in " + startDate);
				}
			} else {
				valid = false;
			}
			
			
			if (valid) {
				validName = true;
			} else {
				System.out.println("Invalid date format \"" + startDate + "\". Must be YYYY-MM-DD.\n");
			}
		}
		validName = false;
		while(!validName) {
			exchangeCode = awaitValidInput(String.format("What is the exchange where %s is listed? ",name),scan).toUpperCase();
			System.out.print("\n");
			
			if(exchangeCode.toLowerCase().equals("nasdaq") || exchangeCode.toLowerCase().equals("nyse")) {
				validName = true;
			}
			else {
				System.out.printf("The stock exchange code \"%s\" is invalid. Exchange code must be either NYSE or NASDAQ.\n\n",ticker);
			}
		}
		
		description = awaitValidInput(String.format("What is the description of %s?\n", name),scan);
		System.out.print("\n");
		
		Stock newStock = new Stock();
		newStock.setName(name);
		newStock.setTicker(ticker);
		newStock.setStartDate(startDate);
		newStock.setDescription(description);
		newStock.setExchangeCode(exchangeCode);
		
		data.getData().add(newStock);
		
		System.out.printf("There is a new entry for:\n%s, symbol %s, started on %s listed on %s,\n%s\n", name,ticker,startDate,exchangeCode,wrapText(description,70));
		return true;
		
	}
	
	private static String awaitValidInput(String outpMessage, Scanner scan) {
		boolean done = false;
		String outp = "";
		
		while (!done) {
			System.out.print(outpMessage + " ");
			outp = scan.nextLine();
			if (outp.length() > 0) {
				done = true;
			}
		}
		
		return outp;
	}
	
	
	public static void removeStock(Data data) {
		
		ArrayList<Stock> stocks = data.getData();
		
		if (stocks.size() > 0) {
			ArrayList<String> options = new ArrayList<String>();
			for (int i = 0; i < stocks.size(); i++) {
				options.add(stocks.get(i).getName());
			}
			
			int choice = menu("Which company would you like to remove?",options);
			
			System.out.printf("%s is now removed.\n",stocks.get(choice-1).getName());
			
			stocks.remove(choice - 1);
		} else {
			System.out.println("There are no stocks to remove.\n");
		}
		
		
	}
	
	
	public static ArrayList<Stock> sort(String dataField, Data data, boolean aToZ) {
		
		ArrayList<Stock> output = new ArrayList<Stock>();
		ArrayList<Stock> stocks = data.getData();
		debug("MIDDLE SORT",data.getData());
		
		int compMod = -1;
		if (aToZ) {
			compMod = 1;
		}
		
		for (int i = 0; i < data.getData().size(); i++) {
			String currValue = "";
			Stock currStock = stocks.get(i);
			
			switch(dataField) {
				case "name":
					currValue = currStock.getName();
					break; 
				case "ticker":
					currValue = currStock.getTicker();
					break;
				case "startDate":
					currValue = currStock.getStartDate();
					break;
				case "description":  
					currValue = currStock.getDescription();
					break;
				case "exchangeCode":
					currValue = currStock.getExchangeCode();
					break;
				default:
					return null;
				
			}
			
			if (output.size() == 0) {
				output.add(currStock);
			} else {
				
				boolean added = false;
				
				for (int j = 0; j < output.size(); j++) {
					String currOutput = "";
					Stock currOutputStock = output.get(j);
					switch(dataField) {
						case "name":
							currOutput = currOutputStock.getName();
							break; 
						case "ticker":
							currOutput = currOutputStock.getTicker();
							break;
						case "startDate":
							currOutput = currOutputStock.getStartDate();
							break;
						case "description":  
							currOutput = currOutputStock.getDescription();
							break;
						case "exchangeCode":
							currOutput = currOutputStock.getExchangeCode();
							break;
						default:
							return null;
							
					}
					debug(currValue);
					if (0 > compMod * currValue.toLowerCase().compareTo(currOutput.toLowerCase())) {
						output.add(j,currStock);
						added = true;
						break;
					}
				}
				
				if(!added) {
					output.add(currStock);
				}
				
			}
		}
		
		return output;
			
	}
	
	public static void printData(Data data) {
		ArrayList<Stock> stocks = data.getData();
		
		String out = "";
		for (int i = 0; i < stocks.size(); i++) {
			Stock currStock = stocks.get(i);
			String desc = currStock.getDescription();

			out += String.format("%s, symbol %s, started on %s, listed on %s,\n%s\n", currStock.getName(),currStock.getTicker(),currStock.getStartDate(),currStock.getExchangeCode(),wrapText(desc,70));
		}
		
		System.out.print(out);
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
			debug(fnfe.getMessage());
			System.out.printf("The file \"%s\" cannot be found.\n\n",path);
			
		} catch (IOException ioe) {
			System.out.printf("io exception when reading \"%s\". Please check that file is valid\n",path);
			debug("ERROR",ioe.getMessage());
		}
		
		//validate date fields
		if (data != null && !validateData(data)) {
			System.out.printf("%s has missing or malformed parameter(s). Please select a valid file.\n\n", path);
			data = null;
		}
						
		return data;
	}
	
	
	private static boolean validateData(Data data) {
		
		boolean valid = true;
		for(int i = 0; i < data.getData().size(); i++) {
			Stock curr = data.getData().get(i);
			if (curr.getName() == null) {
				//System.out.println("Missing name field for company.");
				valid = false;
			} else if (curr.getTicker() == null || curr.getDescription() == null || curr.getExchangeCode() == null || curr.getStartDate() == null){
				//System.out.printf("%s has null field(s)\n", curr.getName());
				valid = false;
			}
			
			if(curr.getStartDate() != null) {
				String[] dates = curr.getStartDate().split("-");
				
				if(dates.length == 3) {
					if(!dates[0].matches("[0-9]+") || dates[0].length() != 4) {
						valid = false;
						debug("ValidateData","Invalid year \"" + dates[0] + "\" in " + curr.getName());
					}
					
					if(!dates[1].matches("[0-9]+") || dates[1].length() != 2) {
						valid = false;
						debug("ValidateData","Invalid month in \"" + dates[1] + "\" in " + curr.getName());
					}
					
					if(!dates[2].matches("[0-9]+") || dates[2].length() != 2) {
						valid = false;
						debug("ValidateData","Invalid day in \"" + dates[1] + "\" in " + curr.getName());
					}
				} else {
					valid = false;
					debug("ValidateData","Invalid date format \"" + curr.getStartDate() + "\" in " + curr.getName());
				}
			} else {
				valid = false;
			}
			
			

		}
		
		return valid; 
	}
	
	
	private static ArrayList<Stock> searchByType(String key, String dataField, Data data) {
		
		ArrayList<Stock> output = new ArrayList<Stock>();
		ArrayList<Stock> stocks = data.getData();
		for (int i = 0; i < data.getData().size(); i++) {
			String currValue = "";
			Stock currStock = stocks.get(i);
			switch(dataField) {
				case "name":
					currValue = currStock.getName();
					break; 
				case "ticker":
					currValue = currStock.getTicker();
					debug("ticker", currValue);
					break;
				case "startDate":
					currValue = currStock.getStartDate();
					break;
				case "description":  
					currValue = currStock.getDescription();
					break;
				case "exchangeCode":
					currValue = currStock.getExchangeCode();
					break;
				default:
					return null;
			}
			
			if (key.toLowerCase().equals(currValue.toLowerCase())) {
				output.add(currStock);
			}
		}
		
		return output;
	}
	private static int menu(String title, ArrayList<String> options) {
		boolean valid = false;
		String outp = "";
		
		for (int i = 1; i < options.size() + 1; i++) {
			outp += String.format("     %d) %s\n", i, options.get(i-1));
		}
		
		outp += title;
		Scanner scan = new Scanner(System.in);
		int choise = -1;
		System.out.print(outp + " ");
		while(!valid) {
			
			try {
				
				String tmp = scan.nextLine();
				System.out.print("\n");
				choise = Integer.parseInt(tmp);
				
				int lowerBound = 1;
				int upperBound = options.size();
				
				if (choise >= lowerBound && choise <= upperBound) {
					valid = true;
				} else {
					System.out.println("That is not a valid option.\n");
					System.out.print(title + " ");
				}
			} catch (InputMismatchException ime) {
				System.out.println("That is not a valid option.\n");
				System.out.print(title + " ");
			} catch (NumberFormatException nfe) {
				System.out.println("That is not a valid option.\n");
				System.out.print(title + " ");
			}
			
		}
		
		return choise;
	}
	
	
	private static String wrapText(String text, int lines) {
		
		lines = lines - 5;
		String out = "     ";
		int count = 0;
		int tmpCount = 0;
		boolean nextSpaceBeforeEnd;
		char curr;
		
		for (int i = 0; i < text.length(); i++) {
			count += 1;
			nextSpaceBeforeEnd = false;
			curr = text.charAt(i);
			
			if(curr == ' ') {
				tmpCount = count;
				for (int j = i + 1; j < text.length(); j++) {
					tmpCount += 1;
					if (text.charAt(j) == ' ' || j == text.length()-1) {
						if (tmpCount < lines || j == text.length()-1) {
							nextSpaceBeforeEnd = true;
						} else {
							count = 0;
						}
						break;
					}
				}
				
				if (!nextSpaceBeforeEnd && i+1 < text.length() && text.charAt(i+1) != '\n' && text.charAt(i+1) != ' ') {
					out += "\n     ";
				} else {
					out += ' ';
				}
				
			} else {
				out += text.charAt(i);
			}
		}
		
		return out;
	}
	
	
	private static void debug(Object str) {
		
		if(DEBUG_MODE) {
			System.out.println("DEBUG   " + String.valueOf(str));
		}
	}

	
	private static void debug(String message, Object str) {
		if(DEBUG_MODE) {
			System.out.println("DEBUG   " + message + "   " + String.valueOf(str));
		}
	}
	
}

class Data {
	private ArrayList<Stock> data;
	
	public Data() {}
	
	public void setData(ArrayList<Stock> d) {
		data = d;
	}
	
	public ArrayList<Stock> getData() {
		return data;
	}
	
}


class Stock {
	private String name;
	private String ticker;
	private String startDate;
	private String description;
	private String exchangeCode;
	
	public Stock() {}
	
	public Stock(boolean debug) {
		if(debug) {
			name = "default";
			ticker = "AAAA";
			startDate = "2000-01-01";
			description = "Default stock used for testing purposes";
			exchangeCode = "AAAAAA";
		}
	}
	//Setters
	public void setName(String n) {
		name = n;
	}
	
	public void setTicker(String t) {
		ticker = t;
	}
	
	public void setStartDate(String sd) {
		startDate = sd;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	public void setExchangeCode(String ec) {
		exchangeCode = ec;
	}
	
	
	//Getters
	public String getName() {
		return name;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getExchangeCode() {
		return exchangeCode;
	}
	
	public String toString() {
		return String.format(
				"name: %s, ticker: %s, StartDate: %s, description: %s, exchengeCode: %s",
				name,ticker,startDate,description,exchangeCode
				);
	}
	
}
