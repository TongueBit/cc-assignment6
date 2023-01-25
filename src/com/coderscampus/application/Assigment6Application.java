package com.coderscampus.application;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.coderscampus.service.FileService;

public class Assigment6Application {
	
	static int max = 0;
	static int min = Integer.MAX_VALUE;
	static YearMonth maxMonth;
	static YearMonth minMonth;
	
	static String[] years = {"2016","2017","2018","2019"};

	static Map<String, Integer> model3Buckets = new HashMap<>();
	static Map<String, String> model3 = new HashMap<>();
	
	public static Integer theStreamForTotalSales(List<String> l, String s) {
		//code for finding best sales month AND putting everything into a HashMap
		for(String data: l) {
			String[] separatedValues = data.split(",");
			if(Integer.parseInt(separatedValues[1]) > max) {	
				max = Integer.parseInt(separatedValues[1]);
				maxMonth = FileService.createYearMonth(separatedValues);
			}
			if (Integer.parseInt(separatedValues[1]) < min ) {
				min = Integer.parseInt(separatedValues[1]);
				minMonth = FileService.createYearMonth(separatedValues);
			}
			//adds a string of date, # sold to hashmap
			model3.put(separatedValues[0], separatedValues[1]);

		}
		//creates a stream to only look at  given year
		List<String> filtered = l.stream()
								 .filter(x -> x.contains("-" + s.subSequence(2, 4)))
								 .map(x-> x.substring(7))
								 .collect(Collectors.toList());
		//creates a stream to make a list of integers
		List<Integer> newList = filtered.stream()
                						.map(p -> Integer.parseInt(p))
                						.collect(Collectors.toList());
		//with list of integers we can now sum them
		Integer sum = newList.stream()
				  			 .collect(Collectors.summingInt(Integer::intValue));
		return sum;							 
	}
		
	public static void printToConsole() {
		System.out.println("Model 3 Yearly Sales Report\n" + "---------------------------");
		for(String year: years) {
			if(model3Buckets.get(year) != null)
			System.out.println(year + "-> " + model3Buckets.get(year));
		}
		System.out.println("The best month for Model 3 was: " + maxMonth);
		System.out.println("The worst month for Model 3 was: " + minMonth);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> modelS = FileService.readFiles();
		for(int i = 0; i < years.length; i++) {
			Integer sum = theStreamForTotalSales(modelS, years[i]);
			if(sum != 0) {
				model3Buckets.put(years[i], sum);
			}
		}
		printToConsole();
	}
	

}
