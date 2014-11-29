package com.cc.controller;

import java.io.IOException;

import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;
import com.cc.logic.ReservoirSample;
import com.cc.logic.NaiveLogic;

public class Controller {
	private final static String DATA_PATH = "../data/data_files/roles_cleaned.txt";
	
	public static void main(String args[]) {
		getMostRepresentedGenres(0.002);
	}
	
	private static void getMostRepresentedGenres(double percentage) {
		try {
			String[] result;
			System.out.println("Misra Gries:");
			result = MisraGriesLogic.getTopStrings(Parser.getGenresAsStream(DATA_PATH), percentage);
			result = MisraGriesLogic.filterResult(Parser.getGenresAsStream(DATA_PATH), result, percentage);
			printArray(result);
			System.out.println("\nNaive:");
			result = NaiveLogic.getTopStrings(Parser.getGenresAsStream(DATA_PATH), percentage);
			printArray(result);
			System.out.println("\nReservoir Sampling:");
			printArray(ReservoirSample.getTopStrings(Parser.getGenresAsStream(DATA_PATH), percentage));
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
