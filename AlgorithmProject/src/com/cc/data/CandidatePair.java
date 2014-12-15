package com.cc.data;

public class CandidatePair {
	private Movie f, s;
	
	public CandidatePair(Movie m1, Movie m2){
		f = m1;
		s = m2;
	}
	
	public Movie f(){
		return f;
	}
	
	public Movie s(){
		return s;
	}
	
	public double jaccardSimilarity(){
		double shared = 0;
		double union = 0;
		
		//genres
		for (String g : f.getGenres()) {
			if(s.getGenres().contains(g)){
				shared++;
			}
		}
		union += f.getGenres().size() + s.getGenres().size();
		
		//actors
		for (int g : f.getActors()) {
			if(s.getActors().contains(g)){
				shared++;
			}
		}
		union += f.getActors().size() + s.getActors().size();
		
		//directors
		for (int g : f.getDirectors()) {
			if(s.getDirectors().contains(g)){
				shared++;
			}
		}
		union += f.getDirectors().size() + s.getDirectors().size();
		
		//title
		if (f.getTitleLower() == s.getTitleLower()) {
			shared++;
		}
		union += 2;
		
		union -= shared;
		
		return shared/union;
	}
	
	@Override
	public boolean equals(Object o){
		try {
			CandidatePair c = (CandidatePair) o;
			if((f == c.f() && s == c.s()) || (f == c.s() && s == c.f())) return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return f.hashCode()*s.hashCode()*997;
	}
}
