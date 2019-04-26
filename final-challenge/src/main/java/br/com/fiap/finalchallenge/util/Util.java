package br.com.fiap.finalchallenge.util;
public class Util {
	
	
	public static boolean isValid(long timestamp) {
		if((System.currentTimeMillis() - timestamp) <= 60000) {
			return true;
		
		}
		return false;
	}

}
