package com.cc.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StreamingLogic {
	
	public static String[] getTopGenres(Iterator<String> stream, double percentage) {
		int buckets = (int) (1.0/percentage) + 1;
		// Instantiate the map.
		Map<String, Integer> map = new HashMap<String, Integer>();
		while(stream.hasNext()) {
			String genre = stream.next();
			if(map.containsKey(genre)) {
				// Increase bucket count if it already exists in map.
				map.put(genre, map.get(genre)+1);
			} else {
				if(map.size() < buckets) {
					map.put(stream.next(), 1);
				} else {
					shrinkMap(map, buckets);
					map.put(stream.next(), 1);
				}
			}
		}
		
		return getHighestValueKeysOfMap(map, buckets);
		
	}
	
	private static void shrinkMap(Map<String, Integer> map, int buckets) {
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		// Iterate through the keys as long as the map is full.
		while(map.size() == buckets) {
		    while (it.hasNext()) {
		        Map.Entry<String, Integer> pair = it.next();
		        // Remove key if it gets reduced to zero.
		        if(pair.getValue().equals(1)) {
		        	map.remove(pair.getKey());
		        } else {
		        // Reduce value of bucket by 1.
		        pair.setValue(pair.getValue()-1);
		        }
		    }
		}
	}
	
	private static String[] getHighestValueKeysOfMap(Map<String, Integer> map, int buckets) {
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		String[] result = new String[buckets];
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		int i = 0;
		while(it.hasNext()) {
			Map.Entry<String, Integer> pair = it.next();
			if(pair.getValue() < min) {
				min = pair.getValue();
				minIndex = i;
			}
			if(i < buckets - 1) {
				result[i] = pair.getKey();
			} else {
				if(!(pair.getValue() == min))
					result[minIndex] = pair.getKey();
			}
			i++;
		}
		return result;
	}
}
