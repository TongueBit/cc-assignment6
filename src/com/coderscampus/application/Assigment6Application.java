package com.coderscampus.application;

import com.coderscampus.domain.Analysis;

public class Assigment6Application {
	
	
	
	
	
	 public static void main(String[] args) { // TODO Auto-generated method stub
	 
		Analysis model3 = new Analysis("model3.csv", "Model 3");
		model3.performAnalysis();
		
		Analysis modelS = new Analysis("modelS.csv", "Model S");
		modelS.performAnalysis();	
		
		Analysis modelX = new Analysis("modelX.csv", "Model X");
		modelX.performAnalysis();
	
	 }
}
