package com.cc.data;

import java.util.HashSet;
import java.util.Set;

public class Movie {
	private int id;
	private String title;
	private Set<String> genres;
	private Set<Integer> actors;
	private Set<Integer> directors;
	
	private Set<Integer> hashShingles;
	private int[] signature;
	
	public Movie(int id, String title) {
		this.id = id;
		this.title = title;
		genres = new HashSet<String>();
		actors = new HashSet<Integer>();
		directors = new HashSet<Integer>();
		
		hashShingles = new HashSet<Integer>();
	}
	
	public void addDirector(int id) {
		directors.add(id);
	}
	
	public void addActor(int id) {
		actors.add(id);
	}
	
	public void addGenre(String genre) {
		genres.add(genre);
	}
	
	public void setHashShingles(HashSet<Integer> s){
		hashShingles = s;
	}
	
	public void setSignature(int[] s){
		signature = s;
	}
	
	public Set<Integer> getActors() {
		return actors;
	}
	
	public Set<Integer> getDirectors() {
		return directors;
	}
	
	public Set<String> getGenres() {
		return genres;
	}
	
	public Set<Integer> getHashShingles(){
		return hashShingles;
	}
	
	public int[] getSignature(){
		return signature;
	}
	
	public String toString() {
		return id + " " + title + "\n" + genres.toString() + "\n" + actors.toString() + "\n" + directors.toString();
	}
	
	public String getTitle(){
		return title;
	}

	public String getTitleLower() {
		return title.toLowerCase();
	}
}
