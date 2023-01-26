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

	 List<String> sales = new ArrayList<>();
	
	public List<String> readFiles(String file) {
		openFile(file);
		return sales;	
	}
	
	public void openFile(String file) {
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			boolean flag = false;
			while(!((line = br.readLine()) == null)) {
				if (flag) {
					sales.add(line);	
				} else
					flag = true;
			}	
		} catch(IOException e) {
			e.getMessage();
		}
	}

	public static YearMonth createYearMonth(String[] data) {
		String abbMonth = AbbreviatedMonths.fromAbbreviation(data[0].substring(0, 3));
		int year = Integer.parseInt(data[0].substring(4, 6));
		YearMonth yearMonth = YearMonth.of(year+2000, Month.valueOf(abbMonth));
		return yearMonth;
	}
}
