package cheidelb_CSCI201L_Lab5;

import java.util.*; 

public class ThreadedSearch extends Thread{

	int[] arr;
	int startIndex;
	int endIndex;
	int index = -1;
	int value;
	Date timeStamp;
	long time = 0;
	
	public ThreadedSearch(int startIndex, int endIndex, int value, int[] arr) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
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
		
		for (int i = startIndex; i < endIndex; i++) {
			if (arr[i] == value) {
				index = i;
				break;
			}
		}
		
		timeStamp = new Date();
		time = timeStamp.getTime();
	}
	
}
