package com.coderscampus.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderscampus.application.AbbreviatedMonths;

public class FileService {
	static String[] files = { 
			"model3.csv", 
			"modelS.csv", 
			"modelX.csv" 
	};
	
	static List<String> sales = new ArrayList<>();
	
	public static List<String> readFiles() {
		
		//lets do only one file first
		openFile("model3.csv");
		return sales;
		
		//for(int i = 0; i < files.length; i++)
		//		createFileReader(files[i]);
	}
	
	public static void openFile(String file) {
		//Map<Integer, Integer> salesForModel3 = new HashMap<>();
		//String[] stuff = {"Jul-17", "500"};
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			boolean flag = false;
			while(!((line = br.readLine()) == null)) {
				if (flag) {
					sales.add(line);
					
					//String[] data = line.split(","); 
					//YearMonth ym = createYearMonth(stuff);
					//System.out.println(ym);
					
				}
				else
					flag = true;
			}
			
		} catch(IOException e) {
			e.getMessage();
		}
		//printListContents();
	}

	public static YearMonth createYearMonth(String[] data) {
		String abbMonth = AbbreviatedMonths.fromAbbreviation(data[0].substring(0, 3));
		int year = Integer.parseInt(data[0].substring(4, 6));
		YearMonth yearMonth = YearMonth.of(year+2000, Month.valueOf(abbMonth));
		return yearMonth;
	}
	
	
	
	public static void printListContents() {
		sales.stream()
			 .forEach(x -> System.out.println(x));
	}
	
}
