package com.cc.controller;

import java.util.ArrayList;
import java.util.List;

import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;

public class Controller {
	public static void main(String args[]) {
		getMostRepresentedGenre(0.07);
	}
	
	private static void getMostRepresentedGenre(double percentage) {
		List<String> testList = Parser.getGenres("../data/data_files/genres.txt");
		if(testList == null) {
			System.out.println("List is null");
			System.exit(0);
		} else {
			System.out.println("Size of list: " + testList.size());
		}

		String[] result = MisraGriesLogic.getTopGenres(testList.iterator(), percentage);
		for(int i = 0; i < result.length; i++) {
			if(result[i] != null) System.out.println(result[i]);
		}
	}
}
