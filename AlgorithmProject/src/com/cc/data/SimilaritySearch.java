package com.cc.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cc.controller.Controller;;

public class SimilaritySearch {
	// Unified set, containing all shingles
	private static HashSet<Integer> unifiedSet = new HashSet<Integer>();
	
	private static final int NUMBER_OF_PERMUTATIONS = 16;
	private static final double T = 0.6;
	//private static final double T = 0.0;
	
	public static void main(String[] args) {
		System.out.println("Start");
		List<Movie> m = Controller.getMovies();
		
		HashSet<CandidatePair> r = run(m);
		
		System.out.println("There are " + r.size() + " movies with jaccard value above " + T);
		for (CandidatePair c : r) {
			System.out.println("Movies " + c.f().getTitle() + " And " + c.s().getTitle() + " have jaccard similarity: " + c.jaccardSimilarity());
		}
		
		System.out.println("Done");
	}
	
	private static HashSet<CandidatePair> run(List<Movie> movies){
		//Create all shingles
		createShingles(movies);
		
		
		//Make unified set into array in order to get indexes
		Integer[] unifiedArray = new Integer[unifiedSet.size()];
		unifiedSet.toArray(unifiedArray);
		
		//Prepare permutation array, with all indexes
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < unifiedSet.size(); i++) {
			indexes.add(i);
		}
		
		//Get permutations
		Integer[][] permutations = getPermutations(indexes);
		
		//Make movie signatures
		for (int i = 0; i < movies.size(); i++) {
			Movie m = movies.get(i);
			int[] s = new int[NUMBER_OF_PERMUTATIONS];
			for (int j = 0; j < NUMBER_OF_PERMUTATIONS; j++) {
				for (int h = 0; h < permutations[j].length; h++) {
					if (m.getHashShingles().contains(unifiedArray[permutations[j][h]])) {
						s[j] = permutations[j][h];
						break;
					}
				}
			}
			m.setSignature(s);
		}
		
		//similarity search
		HashSet<CandidatePair> cp = getCandidatePairs(movies);
		
		HashSet<CandidatePair> resultPairs = new HashSet<CandidatePair>();
		// Check all candidate pairs
		for (CandidatePair c : cp) {
			if (T < c.jaccardSimilarity()) {
				resultPairs.add(c);
			}
		}
		
		return resultPairs;
	}
	
	private static void createShingles(List<Movie> movies) {
		for (Movie m : movies) {
			HashSet<Integer> hashShingles = new HashSet<Integer>();
			
			//Genre
			hashShingles.addAll(createAndHashGenresShingles(m.getGenres()));
			
			//Actors
			hashShingles.addAll(createAndHashActorShingles(m.getActors()));
			
			//Directos
			hashShingles.addAll(createAndHashDirectorShingles(m.getDirectors()));
			
			//Titles
			hashShingles.add(m.getTitleLower().hashCode());
			
			//Set the movies shingles
			m.setHashShingles(hashShingles);
			
			//Add all shingles to unified set
			addToUnified(hashShingles);
		}
	}
	
	private static void addToUnified(HashSet<Integer> shingles){
		unifiedSet.addAll(shingles);
	}
	
	private static HashSet<Integer> createAndHashGenresShingles(Set<String> genres) {
		// TODO Auto-generated method stub
		HashSet<Integer> shingles = new HashSet<Integer>();
		
		for (String s : genres) {
			shingles.add(s.hashCode());
		}
		
		return shingles;
	}
	
	
	private static HashSet<Integer> createAndHashActorShingles(Set<Integer> actors) {
		// TODO Auto-generated method stub
		HashSet<Integer> shingles = new HashSet<Integer>();
		
		for (Integer a : actors) {
			shingles.add(("A" + a).hashCode());
		}
		
		return shingles;
	}
	
	
	private static HashSet<Integer> createAndHashDirectorShingles(Set<Integer> directors) {
		// TODO Auto-generated method stub
		HashSet<Integer> shingles = new HashSet<Integer>();
		
		for (Integer d : directors) {
			shingles.add(("D" + d).hashCode());
		}
		
		return shingles;
	}
	
	private static Integer[][] getPermutations(List<Integer> u){
		Integer[][] result = new Integer[NUMBER_OF_PERMUTATIONS][u.size()];
		
		for (int i = 0; i < NUMBER_OF_PERMUTATIONS; i++) {
			//Shuffle the universe
			Collections.shuffle(u);
			
			//Add the new order as a permutation
			for(int j = 0; j < u.size(); j++){
				result[i][j] = u.get(j);
			}
		}
		
		return result;
	}
	
	
	private static HashSet<CandidatePair> getCandidatePairs(List<Movie> movies) {
		int b = 8;
		int r = 2;
		assert(b + r == NUMBER_OF_PERMUTATIONS);
		
		//List with all hashmaps for the bands
		List<Map<Integer,List<Movie>>> bands = new ArrayList<Map<Integer,List<Movie>>>();
		
		// Stringbuilder is being reused for performance
		StringBuilder sb = new StringBuilder();
		
		//Loop through all the bands first
		for (int i = 0; i < b; i++) {
			//Instantiate the bands hashmap
			HashMap<Integer,List<Movie>> band = new HashMap<Integer,List<Movie>>();
			
			//Loop through all movies and calculate their bucket
			for (Movie m : movies) {
				//Reset stringbuilder
				sb.setLength(0);
				
				int[] mSignature = m.getSignature();
				
				//Create signature
				for (int j = 0; j < r; j++) {
					sb.append(mSignature[j]);
				}
				
				//Hash signature
				int hash = sb.toString().hashCode();
				
				if(band.containsKey(hash)){ // Add signature to bucket
					band.get(hash).add(m);
				} else { // Create bucket and add signature
					ArrayList<Movie> a = new ArrayList<Movie>();
					a.add(m);
					band.put(hash, a);
				}
			}
			
			//Add hashmap to list of hashmaps
			bands.add(band);
		}
		
		return findPairs(bands);
	}

	private static HashSet<CandidatePair> findPairs(List<Map<Integer, List<Movie>>> bands) {
		HashSet<CandidatePair> cp = new HashSet<CandidatePair>();
		
		//Loop through all bands
		for (Map<Integer, List<Movie>> m : bands) {
			
			//Loop through all buckets in the band
			for (List<Movie> l : m.values()) {
				
				//Create candidate pairs
				for (int i = 0; i < l.size() - 1; i++) {
					for (int j = 1; j < l.size(); j++) {
						if(l.get(i) != l.get(j)){
							cp.add(new CandidatePair(l.get(i), l.get(j)));
						}
					}
				}
			}
		}
		
		return cp;
	}
}
