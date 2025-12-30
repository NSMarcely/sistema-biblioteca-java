package br.com.library.model.interfaces;

import java.util.concurrent.ThreadLocalRandom;

//Les's use default method, because we can apply it only
//to numeric code  in which it applies to the class User and Book
public interface Identification {
	default String generator(byte digit){
		//The interval was defined here
		long minimum = (long) Math.pow(10, digit - 1);
		long maximum = (long) Math.powExact(10, digit) - 1;
		long result = ThreadLocalRandom.current().nextLong(minimum, maximum);
		return String.valueOf(result);
		//return long.toString(result);
		
	}
}
