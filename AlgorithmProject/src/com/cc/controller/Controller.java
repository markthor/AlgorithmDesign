package com.cc.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;

import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;

public class Controller {
	public static void main(String args[]) {
		getMostRepresentedGenres(0.07);
	}
	
	private static void getMostRepresentedGenres(double percentage) {
		try {
			BufferedReader genres = Parser.getGenresAsStream("../data/data_files/genres.txt");
	
			String[] result = MisraGriesLogic.getTopGenres(genres, percentage);
			for(int i = 0; i < result.length; i++) {
				if(result[i] != null) System.out.println(result[i]);
			}
		} catch(FileNotFoundException exn) {
			exn.printStackTrace();
		}
	}
}
