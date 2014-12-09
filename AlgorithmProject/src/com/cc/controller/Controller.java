package com.cc.controller;

import java.io.IOException;
import java.util.List;

import com.cc.data.Movie;
import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;
import com.cc.logic.NaiveLogic;
import com.cc.logic.ReservoirSample;

public class Controller {
	private final static String DATA_PATH = "../data/data_files/roles_cleaned.txt";
	
	public static void main(String args[]) {
		getMovies();
		//getMostRepresentedGenres(0.002);
	}
	
	private static void getMovies() {
		System.out.println("Reading imdb file and constructing movie objects...");
		List<Movie> movies = Parser.getMovies("../data/data_files/imdb-r.txt");
		System.out.println(movies.size() + " movie objects constructed.");
		System.out.println("Filtering movies with a too low number of actors, directors or genres...");
		Parser.filterMoviesWithLowNumberOfReferences(movies);
		printArray(movies.toArray());
		System.out.println(movies.size() + " movie objects after filtering.");
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
	
	private static void printArray(Object[] s) {
		for(int i = 0; i < s.length; i++) {
			if(s[i] != null) System.out.println(s[i]);
		}
	}
}
