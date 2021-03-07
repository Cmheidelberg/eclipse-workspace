package cheidelb_CSCI201L_Assignment2;

import java.util.ArrayList;

class Csv {
	
	private int currLine = 0;
	
	private ArrayList<String[]> csv;
	
	
	//TODO validate csv tickers all exist within json
	
	Csv(ArrayList<String[]> csv) {
		this.csv = csv;
		
		sortByStartTimes();
	}
	
	//Setters
	public void setCsv(ArrayList<String[]> csv) {
		this.csv = csv;
	}
	
	//Getters
	public ArrayList<String[]> getCsv() {
		return csv;
	}
	
	public String[] getNext() {
		
		try {
			return csv.get(currLine++);
		} catch (Exception exception){
			return null;
		}
	}
	
	private void sortByStartTimes() {
		ArrayList<String[]> newCsv = new ArrayList<String[]>();
		
		
		while (csv.size() > 0) {
			
			int currMin = -1;
			for (int i = 0; i < csv.size(); i++) {
				int st = Integer.parseInt(csv.get(i)[0]);
				if (currMin == -1 || Integer.parseInt(csv.get(currMin)[0]) > st) {
					currMin = i;
				}
			}
			
			newCsv.add(csv.get(currMin));
			csv.remove(currMin);
		}
		csv = newCsv;
	}
	
	
	public boolean validCsv() {
		
		for (String[] element : csv) {
			
			
			//Each line of csv needs to contain 3 elements: [startTime,ticker,stockPriceDiff]
			if (element.length != 3) {
				return false;
			}
			
			// Can't start at a negative time
			if (Integer.parseInt(element[0]) < 0) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public String toString() {
		String out = "";
		
		for (String[] data : csv) {
			out += "[" + data[0] + "," + data[1] + "," + data[2] + "]\n";
		}
		
		return out;
	}
}


class Data {
	private ArrayList<Stock> data;
	
	public Data() {	}
	
	
	public void setData(ArrayList<Stock> d) {
		this.data = d;		
	}
	
	
	public ArrayList<Stock> getData() {
		return data;
	}
		

	public boolean validData() {
		
		boolean valid = true;
		for(int i = 0; i < data.size(); i++) {
			Stock curr = data.get(i);
			if (curr.getName() == null) {
				//System.out.println("Missing name field for company.");
				valid = false;
			} else if (curr.getTicker() == null || curr.getDescription() == null || curr.getExchangeCode() == null || curr.getStartDate() == null || curr.getStockBrokers() == -1){
				valid = false;
			}
			
			// Validate start date has date format
			if(curr.getStartDate() != null) {
				String[] dates = curr.getStartDate().split("-");
				
				if(dates.length == 3) {
					if(!dates[0].matches("[0-9]+") || dates[0].length() != 4) {
						valid = false;
					}
					
					if(!dates[1].matches("[0-9]+") || dates[1].length() != 2) {
						valid = false;
					}
					
					if(!dates[2].matches("[0-9]+") || dates[2].length() != 2) {
						valid = false;
					}
				} else {
					valid = false;
				}
			} else {
				valid = false;
			}
			
			if(curr.getStockBrokers() < 0) {
				valid = false;
			}
		}
		
		return valid; 
	}
	
	
	public String toString() {
		String outp = ""; 
		for (Stock stock : data) {
			outp += stock.toString() + "\n";
		}
		return outp;
	}
	
}


class Stock {
	private String name;
	private String ticker;
	private String startDate;
	private String description;
	private String exchangeCode;
	private int stockBrokers; 
	
	public Stock() {}
	
	public Stock(boolean debug) {
		if(debug) {
			name = "default";
			ticker = "AAAA";
			startDate = "2000-01-01";
			description = "Default stock used for testing purposes";
			exchangeCode = "AAAAAA";
			stockBrokers = 1;
		}
		
		stockBrokers = -1;
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
	
	public void setStockBrokers(int sb) {
		stockBrokers = sb;
	}
	
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int getStockBrokers() {
		return stockBrokers;
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
				"name: %s, ticker: %s, StartDate: %s, description: %s, exchengeCode: %s, stockBrokers: %d",
				name,ticker,startDate,description,exchangeCode,stockBrokers
				);
	}
}