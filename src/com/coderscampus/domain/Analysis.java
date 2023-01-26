package com.coderscampus.domain;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.coderscampus.service.FileService;

public class Analysis {
	
	int max = 0;
	int min = Integer.MAX_VALUE;
	Integer[] sum = new Integer[4];
	String model;
	YearMonth maxMonth;
	YearMonth minMonth;
	String[] years = {"2016","2017","2018","2019"};
	
	Map<String, Integer> bucket = new HashMap<>();
	List<String> sales = new ArrayList<>();

	public Analysis(String fileName, String model) {
		FileService fs = new FileService();
		sales = fs.readFiles(fileName);
		this.model = model;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}


	public YearMonth getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(YearMonth maxMonth) {
		this.maxMonth = maxMonth;
	}

	public YearMonth getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(YearMonth minMonth) {
		this.minMonth = minMonth;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] years) {
		this.years = years;
	}

	public Map<String, Integer> getBucket() {
		return bucket;
	}

	public void setBucket(Map<String, Integer> bucket) {
		this.bucket = bucket;
	}

	public List<String> getSales() {
		return sales;
	}

	public void setSales(List<String> sales) {
		this.sales = sales;
	}
	
	private void theStreamForTotalSales() {
		
		//creates a stream to only look at  given year
		for (String year: years) {
			
			List<String> filtered = sales.stream().filter(x -> x.contains("-" + year.subSequence(2, 4)))
					.map(x -> x.substring(7)).collect(Collectors.toList());
			//creates a stream to make a list of integers
			List<Integer> newList = filtered.stream().map(p -> Integer.parseInt(p)).collect(Collectors.toList());
			//with list of integers we can now sum them
			Integer sum = newList.stream().collect(Collectors.summingInt(Integer::intValue));
			bucket.put(year, sum);
			
		}	
		printToConsole(model);
	}
	
	private void bestAndWorstMonths() {
		
		for(String data: sales) {
			String[] separatedValues = data.split(",");
			if(Integer.parseInt(separatedValues[1]) > max) {	
				max = Integer.parseInt(separatedValues[1]);
				//this can be optimized. Don't need to reassign maxMonth everytime
				maxMonth = FileService.createYearMonth(separatedValues);
			}

			
			if (Integer.parseInt(separatedValues[1]) < min ) {
				min = Integer.parseInt(separatedValues[1]);
				//this can be optimized. Don't need to reassign maxMonth everytime
				minMonth = FileService.createYearMonth(separatedValues);
			}
				
		}
		System.out.println("The best month for " + model + " was: " + maxMonth);
		System.out.println("The worst month for " + model + " was: " + minMonth);
		System.out.println();
	}
	
	private void printToConsole(String model) {
		System.out.println(model + " Yearly Sales Report\n" + "---------------------------");
		for(String year: years) {
			if(bucket.get(year) != 0)
			System.out.println(year + "-> " + bucket.get(year));
		}	
	}
	
	public void performAnalysis() {
		theStreamForTotalSales();
		bestAndWorstMonths();
		
	}
	
	

}
