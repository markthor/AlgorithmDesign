package com.cc.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

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
}