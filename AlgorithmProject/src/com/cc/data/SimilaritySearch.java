package com.cc.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cc.controller.Controller;

public class SimilaritySearch {
	// Unified set, containing all shingles
	private static HashSet<Integer> unifiedSet = new HashSet<Integer>();
	
	private static final int NUMBER_OF_PERMUTATIONS = 8;
	
	
	public static void main(String[] args) {
		List<Movie> m = Controller.getMovies();
		HashSet<Integer> r = run(m);
	}
	
	private static HashSet<Integer> run(List<Movie> movies){
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
		System.out.println();
		for (int i = 0; i < 8; i++) {
			System.out.print(movies.get(0).getSignature()[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < 8; i++) {
			System.out.print(movies.get(2).getSignature()[i] + " ");
		}
		
		return null;
	}
	
	
	private static void createShingles(List<Movie> movies) {
		for (Movie m : movies) {
			HashSet<Integer> hashShingles = new HashSet<Integer>();
			
			//Genre
			hashShingles.addAll(createAndHashGenresShingles(m.getGenres()));
			
			//Actors
			//hashShingles.addAll(createAndHashActorShingles(m.getActors()));
			
			//Directos
			//hashShingles.addAll(createAndHashDirectorShingles(m.getDirectors()));
			
			//Set the movies shingles
			m.setHashShingles(hashShingles);
			
			//Add all shingles to unified set
			addToUnified(hashShingles);
		}
	}
	
	private static void addToUnified(HashSet<Integer> shingles){
		unifiedSet.addAll(shingles);
	}

	/*public static HashSet<Integer> start(List<Movie> movies){
		
		
		//Create all shingles
		HashSet<Integer> unifiedSet = new HashSet<Integer>();
		for (Movie m : movies) {
			HashSet<Integer> hashShingles = new HashSet<Integer>();
			
			//Genre
			hashShingles.addAll(createAndHashGenresShingles(m.getGenres()));
			
			//Actors
			//hashShingles.addAll(createAndHashActorShingles(m.getActors()));
			
			//Directos
			//hashShingles.addAll(createAndHashDirectorShingles(m.getDirectors()));
			
			m.setHashShingles(hashShingles);
			//Add all to unified set
			addToUnified(unifiedSet, hashShingles);
		}
		System.out.println("So far, so good");
		
		Integer[] unifiedArray = new Integer[unifiedSet.size()]; 
		unifiedSet.toArray(unifiedArray);
		
		//Ger permutations
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < unifiedSet.size(); i++) {
			indexes.add(i);
		}
		
		Integer[][] permutations = getPermutations(indexes);
		
		for (int i = 0; i < movies.size(); i++) {
			Movie m = movies.get(i);
			int[] s = new int[NUMBER_OF_PERMUTATIONS];
			for (int j = 0; j < NUMBER_OF_PERMUTATIONS; j++) {
				for (int h = 0; h < permutations[j].length; h++) {
					//System.out.println("Herinde");
					if (m.getHashShingles().contains(unifiedArray[permutations[j][h]])) {
						s[j] = permutations[j][h];
						//System.out.println("Inside");
						break;
					}
				}
			}
			//System.out.println("Igennem");
			m.setSignature(s);
			//System.out.println(s[0] + "-" + s[1] + "-" + s[2] + "-" + s[3] + "-" + s[4]);
		}
		
		
		return null;
		
		/*hash with A/D in front, universel set with everything, min hash(permutations/?)
		
		
		
		
		
		String t = input.split(";")[1];
		String[] tokens = t.split("-----");
		
		HashSet<String[]> shingles = createShingles(tokens);
		
		HashSet<Integer> hashedShingles = new HashSet<Integer>();
		for (String[] strings : shingles) {
			int hash = 0;
			for (String string : strings) {
				hash += string.hashCode();
			}
			hashedShingles.add(hash);
		}
		
		return hashedShingles;*/
	/*
	}*/
	
	
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
	
	
	/*
	private String[] hashShingles(String[] shingles) {
		// TODO Auto-generated method stub
		
	}
	
	private int minHash(String seed, String[] hashShingles) {
		// TODO Auto-generated method stub
		
		return null;
	}
	*/
	
	private static void printHashSet(HashSet<Integer> s){
		System.out.println();
		for (Integer e : s) {
			System.out.println(e);
		}
	}
	

	
	private static Integer[][] getPermutations(List<Integer> a){
		Integer[][] result = new Integer[NUMBER_OF_PERMUTATIONS][a.size()];
		
		for (int i = 0; i < NUMBER_OF_PERMUTATIONS; i++) {
			//List<Integer> l = new ArrayList<Integer>();
			//l.addAll(a);
			
			//result[i] = Collections.shuffle(l.toArray());
			Collections.shuffle(a);
			
			for(int j = 0; j < a.size(); j++){
				result[i][j] = a.get(j);
			}
		}
		
		return result;
	}
}
