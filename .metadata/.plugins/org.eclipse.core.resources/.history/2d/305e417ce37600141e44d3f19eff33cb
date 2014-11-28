package com.cc.logic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StreamingLogic {
	private static final int NUMBER_OF_GENRES = 4;
	private static final int NUMBER_OF_BUCKETS = NUMBER_OF_GENRES + 1;
	private static StreamingLogic instance = new StreamingLogic();
	
	public int[] getTopGenres(Iterator<Integer> stream) {
		// Instantiate the map.
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(stream.hasNext()) {
			Integer id = stream.next();
			if(map.containsKey(id)) {
				// Increase bucket count if it already exists in map.
				map.put(id, map.get(id)+1);
			} else {
				if(map.size() < NUMBER_OF_BUCKETS) {
					map.put(stream.next(), 1);
				} else {
					shrinkMap(map);
					map.put(stream.next(), 1);
				}
			}
		}
		
		return getHighestValueKeysOfMap(map);
		
	}
	
	private void shrinkMap(Map<Integer, Integer> map) {
		Iterator it = map.entrySet().iterator();
		// Iterate through the keys as long as the map is full.
		while(map.size() == NUMBER_OF_BUCKETS) {
		    while (it.hasNext()) {
		        Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
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
	
	private int[] getHighestValueKeysOfMap(Map<Integer, Integer> map) {
		Iterator it = map.entrySet().iterator();
		int[] result = new int[NUMBER_OF_GENRES];
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		int i = 0;
		while(it.hasNext()) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			if(pair.getValue() < min) {
				min = pair.getValue();
				minIndex = i;
			}
			if(i < NUMBER_OF_GENRES) {
				result[i] = pair.getKey();
			} else {
				if(!(pair.getValue() == min))
					result[minIndex] = pair.getKey();
			}
		}
		return result;
	}
	
	private static StreamingLogic getInstance() {
		return instance;
	}
}
