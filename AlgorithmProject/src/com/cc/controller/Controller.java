package com.cc.controller;

import java.util.ArrayList;
import java.util.List;

import com.cc.logic.StreamingLogic;

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

		
		String[] result = StreamingLogic.getTopGenres(testList.iterator(), 0.15);
		for(int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

	}
}
