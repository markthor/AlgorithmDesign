package com.cc.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NaiveLogic {

	public static String[] getTopStrings(Iterator<String> stream, double percentage) {
		Map<String, Integer> counters = new HashMap<String, Integer>();
		int total = 0;
		
		while(stream.hasNext()) {
			String s = stream.next();
			if(counters.containsKey(s)) {
				counters.put(s, counters.get(s)+1);
			} else {
				counters.put(s, 1);
			}
			total++;
		}
		
		double threshold = total * percentage/100;
		
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
