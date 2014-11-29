package com.cc.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ReservoirSample {

	public static String[] getTopStrings(BufferedReader stream, double percentage) throws IOException {
		final int threshold = 10;
		final int reservoirSize = (int) (threshold/percentage);
		
		String[] reservoir = new String[reservoirSize];
		//Fill reservoir initially
		for(int i = 0; i < reservoirSize; i++) {
			reservoir[i] = stream.readLine();
		}
		int processedElements = reservoirSize;
		String s;
		Random rng = new Random();
		//Randomly exchange element with a probability equal to something
		while((s = stream.readLine()) != null) {
			processedElements++;
			
			int randomIndex = rng.nextInt(processedElements);
			if (randomIndex < reservoirSize) {
				reservoir[randomIndex] = s;
			}
		}
		
		//Count the occurrences of the strings in the reservoir
		Map<String, Integer> count = new HashMap<String, Integer>();
		for(String cur : reservoir) {
			Integer val = count.get(cur);
			if(val != null)
				count.put(cur, val+1);
			else
				count.put(cur, 1);
		}
		
		//Only return the strings from the reservoir that exceeds the threshold
		List<String> tempResult = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> it = count.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Integer> pair = it.next();
			if(pair.getValue() > threshold) {
				System.out.println(pair.getKey() + "'s count = " + pair.getValue());
				tempResult.add(pair.getKey());
			}
		}
		String[] result = new String[tempResult.size()];
		for(int i = 0; i < result.length; i++)
			result[i] = tempResult.get(i);
		return result;
	}
}