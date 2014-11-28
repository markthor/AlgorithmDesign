package com.cc.data;

import java.io.File;
import java.io.IOException;
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
}