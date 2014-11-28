package com.cc.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NaiveLogic {
	public static String[] getTopGenres(Iterator<String> stream, double percentage) {
		Map<String, Integer> counters = new HashMap<String, Integer>();
		int total = 0;
		
		while(stream.hasNext()) {
			String genre = stream.next();
			if(counters.containsKey(genre)) {
				counters.put(genre, counters.get(genre)+1);
			} else {
				counters.put(genre, 1);
			}
			total++;
		}
		
		double threshold = total * percentage/100;
		
		return getHighestValueKeysOfMap(counters, threshold, percentage);
	}
	
	private static String[] getHighestValueKeysOfMap(Map<String, Integer> map, double threshold, double percentage) {
		int buckets = (int) (1.0/percentage) + 1;
		
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		String[] result = new String[buckets];

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
