package br.com.fiap.finalchallenge.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.TimeZone;

public class Util {
	
	
	public static boolean isValid(long timestamp) {
		if((System.currentTimeMillis() - timestamp) <= 600000) {
			return true;
		
		}
		return false;
	}
	
	public static LocalDateTime convertToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
	}
	
	public static Long converToTimeStamp(LocalDateTime localDateTime) {
		return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
	}

}
