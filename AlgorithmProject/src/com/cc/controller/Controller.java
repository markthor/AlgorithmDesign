package com.cc.controller;

import java.io.IOException;

import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;
import com.cc.logic.NaiveLogic;

public class Controller {
	private final static String DATA_PATH = "../data/data_files/genres.txt";
	
	public static void main(String args[]) {		
		/*long start = System.nanoTime(); 
		
		getMostRepresentedGenres(0.10);
		
		long elapsedTime = System.nanoTime() - start;
		System.out.println("Misra: ");
		System.out.println("Time elapsed: " + elapsedTime);
		System.out.println("Memory: " + Runtime.getRuntime().totalMemory());
		*/
		
		
		long start = System.nanoTime();  
		
		getNaive(0.10);
		
		long elapsedTime = System.nanoTime() - start;
		System.out.println("Naive: ");
		System.out.println("Time elapsed: " + elapsedTime);
		System.out.println("Memory: " + Runtime.getRuntime().totalMemory());
	}
	
	private static void getMostRepresentedGenres(double percentage) {
		try {
			String[] result = MisraGriesLogic.getTopStrings(Parser.getGenresAsStream(DATA_PATH), percentage);
			result = MisraGriesLogic.filterResult(Parser.getGenresAsStream(DATA_PATH), result, percentage);
			printArray(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	
	private static void printArray(String[] s) {
		for(int i = 0; i < s.length; i++) {
			if(s[i] != null) System.out.println(s[i]);
		}
	}
}
