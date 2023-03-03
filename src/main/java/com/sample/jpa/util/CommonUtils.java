package com.sample.jpa.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommonUtils {

	public static void main(String[] args) {
		System.out.println("Current LocalDateTime: "+ getLocalTimeDateFromEpoch(1677782057000L) );
		System.out.println("Epoch: "+ Instant.now().getEpochSecond());
		System.out.println("Epoch: "+ Instant.now().toEpochMilli());
	}
	
	public static LocalDateTime getLocalTimeDateFromEpoch(Long epoch) {
		LocalDateTime localDateTime = Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
		return localDateTime;
	}
}
