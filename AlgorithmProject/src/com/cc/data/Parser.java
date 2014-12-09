package com.cc.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Parser {
	public static List<String> getGenres(String filename) {
		File genreFile = new File(filename);
		if(genreFile.exists()) {
			try {
				return Files.readAllLines(genreFile.toPath(), StandardCharsets.UTF_8);			
			}
			catch(IOException exn) {
				exn.printStackTrace();
			}
		}
		return null;
	}
	
	public static BufferedReader getGenresAsStream(String filename) throws FileNotFoundException {
		FileInputStream filestream = new FileInputStream(filename);
		return new BufferedReader(new InputStreamReader(filestream));
	}
	
	/**
	 * Removes movies that does not satisfy certain thresholds for number of actors, directors and genres.
	 * @param movies
	 */
	public static void filterMoviesWithLowNumberOfReferences(List<Movie> movies) {
		Iterator<Movie> it = movies.iterator();
		while(it.hasNext()) {
			Movie m = it.next();
			if(m.getActors().size() < 3 || m.getDirectors().size() < 1 || m.getGenres().size() < 1) it.remove();
		}
	}
	
	public static List<Movie> getMovies(String filename) {
		File file = new File(filename);
		Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
		if (file.exists()) {
			try {
				BufferedReader br = Files.newBufferedReader(file.toPath());
				String s;
				boolean[] readMode = new boolean[4];
				while ((s = br.readLine()) != null) {
					String[] split = s.split(" ");
					if (split.length > 2 && split[1].equals("TABLES")) {
						switch (split[2]) {
						case "`movies`":
							setReadMode(0, readMode);
							break;
						case "`roles`":
							setReadMode(1, readMode);
							break;
						case "`movies_directors`":
							setReadMode(2, readMode);
							break;
						case "`movies_genres`":
							setReadMode(3, readMode);
						}
					} else {
						addDataToMovies(movies, s, readMode);
					}
					
				}
			} catch (IOException exn) {
				exn.printStackTrace();
			}
		}
		
		return new ArrayList<Movie>(movies.values());
		

	}
	
	private static void addDataToMovies(Map<Integer, Movie> movies, String s, boolean[] readMode) {
		//Movie
		if(readMode[0]) {
			addMovieToMap(movies, s);
		}
		//Actor
		if(readMode[1]) {
			addMovieActorReference(movies, s);
		}
		//Director
		if(readMode[2]) {
			addMovieDirectorReference(movies, s);
		}
		//
		if(readMode[3]) {
			addMovieGenreReference(movies, s);
		}
	}
	
	private static void addMovieToMap(Map<Integer, Movie> map, String s) {
		s = s.trim();
		String[] split = s.split(",");
		int id = Integer.parseInt(split[0]);
		String title = split[1];
		Movie m = new Movie(id, title);
		map.put(id, m);
	}
	
	private static void addMovieDirectorReference(Map<Integer, Movie> map, String s) {
		s = s.trim();
		String[] split = s.split(",");
		int movieId = Integer.parseInt(split[0]);
		int directorId = Integer.parseInt(split[1]);
		Movie m = map.get(movieId);
		if(m != null) m.addDirector(directorId);
	}
	
	private static void addMovieActorReference(Map<Integer, Movie> map, String s) {
		s = s.trim();
		String[] split = s.split(",");
		int movieId = Integer.parseInt(split[0]);
		int actorId = Integer.parseInt(split[1]);
		Movie m = map.get(movieId);
		if(m != null) m.addActor(actorId);
	}
	
	private static void addMovieGenreReference(Map<Integer, Movie> map, String s) {
		s = s.trim();
		String[] split = s.split(",");
		int movieId = Integer.parseInt(split[0]);
		String genre = split[1];
		Movie m = map.get(movieId);
		if(m != null) m.addGenre(genre);
	}
	
	private static void setReadMode(int index, boolean[] readMode) {
		for(int i = 0; i < readMode.length; i++) {
			if(i == index) {
				readMode[i] = true;
			} else {
				readMode[i] = false;
			}
		}
	}
}