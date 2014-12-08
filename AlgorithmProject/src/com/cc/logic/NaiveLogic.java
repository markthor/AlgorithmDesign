package com.cc.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NaiveLogic {

	public static String[] getTopStrings(BufferedReader stream, double percentage) throws IOException {
		Map<String, Integer> counters = new HashMap<String, Integer>();
		int total = 0;
		String s;
		while((s=stream.readLine()) != null) {
			if(counters.containsKey(s)) {
				counters.put(s, counters.get(s)+1);
			} else {
				counters.put(s, 1);
			}
			total++;
		}
		System.out.println("The number of entries in map for Naive solution is " + counters.size() + ".");
		double threshold = total * percentage;
		
		return getHighestValueKeysOfMap(counters, threshold, percentage);
	}
	
	private static String[] getHighestValueKeysOfMap(Map<String, Integer> map, double threshold, double percentage) {
		int max = (int) (1.0/percentage) + 1;
		
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		String[] result = new String[max];

		int i = 0;
		while(it.hasNext()) {
			Map.Entry<String, Integer> pair = it.next();
			if(pair.getValue() > threshold) {
				result[i] = pair.getKey();
				i++;
			}
		}
		return result;
	}
}