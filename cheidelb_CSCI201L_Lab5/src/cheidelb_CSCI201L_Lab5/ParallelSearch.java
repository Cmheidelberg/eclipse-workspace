package cheidelb_CSCI201L_Lab5;

import java.util.*; 

public class ParallelSearch extends Thread{

	int[] arr;
	int index = -1;
	int value;
	int splitNum;
	Date timeStamp;
	long time = 0;
	
	
	public ParallelSearch(int splitNum, int value, int[] arr) {
		this.splitNum = splitNum;
		this.value = value;
		this.arr = arr;
	}
		
	public long getEndTime() {
		return time;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void run() {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				index = i + arr.length * splitNum;
				break;
			}
		}
		
		timeStamp = new Date();
		time = timeStamp.getTime();
	}
	
}
