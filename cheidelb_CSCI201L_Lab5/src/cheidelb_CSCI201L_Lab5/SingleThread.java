package cheidelb_CSCI201L_Lab5;

public class SingleThread {

	int[] arr;
	public SingleThread(int[] arr) {
		this.arr = arr;
	}
	
	
	public int getIndexOfValue(int value) {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				return i;
			}
		}
		
		return -1;
	}
}
