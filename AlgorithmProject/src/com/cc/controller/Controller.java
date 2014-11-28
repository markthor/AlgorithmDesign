package com.cc.controller;

import java.util.ArrayList;
import java.util.List;

import com.cc.data.Parser;
import com.cc.logic.MisraGriesLogic;

public class Controller {
	public static void main(String args[]) {
		
		List<String> testList = new ArrayList<String>();
		testList.add("Porn");
		testList.add("Porn");
		testList.add("Porn");
		testList.add("Comedy");
		testList.add("Romance");
		testList.add("Adventure");
		testList.add("Thriller");
		testList.add("Horror");
		testList.add("Porn");
		testList.add("Comedy");
		testList.add("Adventure");
		testList.add("Thriller");
		testList.add("Horror");
		testList.add("Porn");
		testList.add("Comedy");
		testList.add("Thriller");
		testList.add("Thriller");
		testList.add("Thriller");
		testList.add("Thriller");
		testList.add("Romance");
		testList.add("Romance");
		testList.add("Romance");
		testList.add("Comedy");
		testList.add("Dungeon");
		testList = Parser.getGenres("../data/data_files/genres.txt");
		if(testList == null) {
			System.out.println("List is null");
			System.exit(0);
		} else {
			System.out.println("Size of list: " + testList.size());
		}

		
		String[] result = MisraGriesLogic.getTopGenres(testList.iterator(), 0.15);
		for(int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

	}
}
