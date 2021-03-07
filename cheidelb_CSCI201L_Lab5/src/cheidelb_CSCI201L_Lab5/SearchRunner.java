package cheidelb_CSCI201L_Lab5;

import java.util.*;

public class SearchRunner {

	public static void main(String[] args) {
		
		// Create array
		int[] arr = new int[100_000_000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		// Shuffle array
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			int randIndex = rand.nextInt(arr.length);
			int tmp = arr[randIndex];
			arr[randIndex] = arr[i];
			arr[i] = tmp;
		}
		
		int valueToFind = rand.nextInt(arr.length);
		int impossibleValue = -100;
		System.out.println("ValueToFind: " + valueToFind + " | ImpossibleValue: " + impossibleValue);
		
		// Single thread solution
		System.out.println("\nSingle Thread: ");
		SingleThread st = new SingleThread(arr);
				
		Long startTime = new Date().getTime();
		int valueToFindIndex = st.getIndexOfValue(valueToFind);
		Long endTime = new Date().getTime();
		System.out.println("ValueToFind Index: " + valueToFindIndex + " | time elapsed: " + (endTime - startTime));
		
		startTime = new Date().getTime();
		valueToFindIndex = st.getIndexOfValue(impossibleValue);
		endTime = new Date().getTime();
		System.out.println("ImpossibleValue Index: " + valueToFindIndex + " | time elapsed: " + (endTime - startTime));
		
		
		// MultiThreaded Solution
		int threadCount = 4;
		System.out.println("\n" + threadCount +" Threads: ");
		runThreads(valueToFind, "ValueToFind", arr, threadCount);
		runThreads(impossibleValue, "ImpossibleValue", arr, threadCount);
		
		
		// Parallel solution
		System.out.println("\n" + threadCount +" Parallel: ");
		runParallel(valueToFind, "ValueToFind", arr, threadCount);
		runParallel(impossibleValue, "ImpossibleValue", arr, threadCount);
	}
	
	public static void printParts(int firstX, int lastX, int[] arr) {
		String outp = "";
		for (int i = 0; i < firstX; i++) {
			outp += "" + arr[i] + ", "; 
		}
		
		outp += "..., ";
		
		for (int i = arr.length-1; i > arr.length - lastX - 1; i--) {
			outp += "" + arr[i] + ", "; 
		}
		
		System.out.println(outp);
	}
	
	
	public static void runThreads(int valueToFind, String msg, int[] arr, int numOfThreads) {
		Long startTime = new Date().getTime();
		int split = arr.length / numOfThreads;
		ThreadedSearch[] ts = new ThreadedSearch[numOfThreads];
		
		for (int i = 0; i < numOfThreads; i++) {
			ts[i] = new ThreadedSearch(split*i,split*(i+1), valueToFind, arr);
		}
						
		for (ThreadedSearch t : ts) {
			t.run();
		}
		
		try {
			for (ThreadedSearch t : ts) {
				t.join();
			}
		} catch (InterruptedException ie) {
			System.out.print(ie);
		}
		
		int[] indexes = new int[numOfThreads];
		long[] times = new long[numOfThreads];
		
		for (int i = 0; i < numOfThreads; i++) {
			indexes[i] = ts[i].getIndex();
			times[i] = ts[i].getEndTime();
		}
		
		
		int index = -1;
		long time;
		
		long minTime = times[0];
		long maxTime = times[0];
		
		// Get max and min time it took
		for (int i = 0; i < times.length; i++) {
			if (times[i] < minTime) {
				minTime = times[i];
			}
			if(times[i] > maxTime) {
				maxTime = times[i];
			}
		}
		
		for (int i : indexes) {
			if (i != -1) {
				index = i;
			}
		}
		
		if (index != -1) {
			System.out.println(msg + " Index: " + index + " | time elapsed: " + (minTime - startTime));
		}else {
			System.out.println(msg + " Index: " + index + " | time elapsed: " + (maxTime - startTime));
		}
	}
	
	
	public static void runParallel(int valueToFind, String msg, int[] arr, int numOfThreads) {
		
		Long startTime = new Date().getTime();
		int split = arr.length / numOfThreads;
		ParallelSearch[] ts = new ParallelSearch[numOfThreads];
		for (int i = 0; i < numOfThreads; i++) {
			int [] subArr = new int[split];
			
			for (int j = 0; j < split; j++) {
				subArr[j] = arr[split*i + j];
			}
			ts[i] = new ParallelSearch(i, valueToFind, subArr);
		}
					
		for (ParallelSearch t : ts) {
			t.run();
		}
		
		try {
			for (ParallelSearch t : ts) {
				t.join();
			}
		} catch (InterruptedException ie) {
			System.out.print(ie);
		}
		
		int[] indexes = new int[numOfThreads];
		long[] times = new long[numOfThreads];
		
		for (int i = 0; i < numOfThreads; i++) {
			indexes[i] = ts[i].getIndex();
			times[i] = ts[i].getEndTime();
		}
		
		int index = -1;
		long time;
		
		long minTime = times[0];
		long maxTime = times[0];
		
		// Get max and min time it took
		for (int i = 0; i < times.length; i++) {
			if (times[i] < minTime) {
				minTime = times[i];
			}
			if(times[i] > maxTime) {
				maxTime = times[i];
			}
		}
		
		for (int i : indexes) {
			if (i != -1) {
				index = i;
			}
		}
		
		if (index != -1) {
			System.out.println(msg + " Index: " + index + " | time elapsed: " + (minTime - startTime));
		}else {
			System.out.println(msg + " Index: " + index + " | time elapsed: " + (maxTime - startTime));
		}
	}

}
