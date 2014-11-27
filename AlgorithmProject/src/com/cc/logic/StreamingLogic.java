package com.cc.logic;

public class StreamingLogic {
	private static final int numberOfTopGenres = 4;
	private static StreamingLogic instance = new StreamingLogic();
	
	public int[] getTopGenres() {
		return new int[5];
	}
	
	private static StreamingLogic getInstance() {
		return instance;
	}
}
