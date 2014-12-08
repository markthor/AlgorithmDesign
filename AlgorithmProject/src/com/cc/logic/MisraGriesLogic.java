package com.cc.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MisraGriesLogic {
	
	public static String[] getTopStrings(BufferedReader stream, double percentage) throws IOException {
		int buckets = (int) (1.0/percentage) + 1;
		System.out.println("Number of max entities in map for Misra Gries is " + buckets + ".");
		// Instantiate the map.
		Map<String, Integer> map = new HashMap<String, Integer>();
		String s;

		while ((s = stream.readLine()) != null) {
			if (map.containsKey(s)) {
				// Increase bucket count if it already exists in map.
				map.put(s, map.get(s) + 1);
			} else {
				if (map.size() < buckets) {
					map.put(s, 1);
				} else {
					shrinkMap(map, buckets);
					map.put(s, 1);
				}
			}
		}

		return getKeysOfMap(map);
	}
	
	public static String[] filterResult(BufferedReader stream, String[] result, double percentage) throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < result.length; i++) {
			map.put(result[i], 0);
		}
		
		int numberOfElements = 0;

		String s;
		while ((s = stream.readLine()) != null) {
			if (map.containsKey(s))
				map.put(s, map.get(s) + 1);
			numberOfElements++;
		}

		int threshold = (int) Math.ceil(numberOfElements * percentage);
		
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry<String, Integer> pair = it.next();
	        if(pair.getValue() < threshold) it.remove();
	    }
	    
	    return getKeysOfMap(map);
		
	}
	
	private static void shrinkMap(Map<String, Integer> map, int buckets) {
		// Iterate through the keys as long as the map is full.
		while(map.size() == buckets) {
			Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Integer> pair = it.next();
		        // Remove key if it gets reduced to zero.
		        if(pair.getValue().equals(1)) {
		        	it.remove();
		        } else {
		        // Reduce value of bucket by 1.
		        pair.setValue(pair.getValue()-1);
		        }
		    }
		}
	}
	
	private static String[] getKeysOfMap(Map<String, Integer> map) {
		Set<String> keySet = map.keySet();
		return keySet.toArray(new String[keySet.size()]);
	}
}
