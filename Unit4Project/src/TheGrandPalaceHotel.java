/*
 * Kaylyn Phan and Manvika Satish
 * AP CSA
 * Period 3
 * 11/22/2020
 */

import java.io.*;
import java.util.Scanner;

/**
 * The TheGrandPalaceHotel class reads a list of clients, services, costs, and dates from a text file,
 * reprints the data in tabular format, and prints a summary displaying the quantity and total cost
 * of each type of service.
 * @author Kaylyn Phan and Manvika Satish
 */
public class TheGrandPalaceHotel {
	
	/**
	 * Method that returns the double value of a numeric String.
	 * @author Kaylyn Phan
	 * @param doubleString - numeric String
	 * @return the value of the numeric String stored in a double
	 */
	public static double getDoubleValue(String doubleString) {
		double val = 0.0;
		int curr = 0;
		double power;
		int indexOfDecimal = doubleString.indexOf(".");
		//if doubleString stores a number with not decimals
		if (indexOfDecimal == -1) {
			indexOfDecimal = doubleString.length();
		}
		//indexOfDecimal minus 1 equals the highest power of ten
		power = (double)indexOfDecimal - 1;
		
		for (int i = 0; i < doubleString.length(); i++) {
			if (i != indexOfDecimal) {
				curr = (int)doubleString.charAt(i) - 48;
				val += Math.pow(10, power) * curr;
				power--;
			}
		}
		return val;	
	}
	  
	/**
	 * Method that retrieves the client name from a line of data.
	 * @author Manvika Satish
	 * @param data - the line of data
	 * @return the client name
	 */
	public static String getClientName(String data) {
		return data.substring(0, data.indexOf(":"));
	}
	
	/**
	 * Method that retrieves the service type from a line of data.
	 * @author Manvika Satish
	 * @param data - the line of data
	 * @return the service type
	 */
	public static String getServiceType(String data) {
		data = data.substring(data.indexOf(":") + 2);
		return data.substring(0, data.indexOf(","));
	}
	
	/**
	 * Method that retrieves the dollar amount from a line of data.
	 * @author Manvika Satish
	 * @param data - the line of data
	 * @return the dollar amount
	 */
	public static String getDollarAmount(String data) {
		data = data.substring(data.indexOf(",") + 2);
		return data.substring(0, data.indexOf(","));
	}
	
	/**
	 * Method that retrieves the date from a line of data.
	 * @author Manvika Satish
	 * @param data - the line of data
	 * @return the date
	 */
	public static String getDate(String data) {
		return data.substring(data.lastIndexOf(",") + 2);
	}
	
	/**
	 * Method that reads hotel data from a text file and prints the formatted data along with a summary.
	 * @author Kaylyn Phan and Manvika Satish
	 */
	public static void readAndPrintData() {
		String data;
		 String clientName;
		 String serviceType;
		 String dollarAmount;
		 String date;
		      
		 int dinnerCount = 0;
		 int lodgingCount = 0;
		 int conferenceCount = 0;

		 double dinnerCost = 0.0;
		 double lodgingCost = 0.0;
		 double conferenceCost = 0.0;

		 try {
			 File file = new File("Services.txt");
			 Scanner scan = new Scanner(file);
			 while (scan.hasNextLine()) {
				 data = scan.nextLine();
				  
				 clientName = getClientName(data);
				 serviceType = getServiceType(data);
				 dollarAmount = getDollarAmount(data);
				 date = getDate(data);
				  
				 //convert dollarAmount to double for adding
				 double dollarAmountDouble = getDoubleValue(dollarAmount);
				  
				 //add dollar sign and needed zeores to dollarAmount String for printing
				 dollarAmount = "$" + dollarAmount;
				 if (dollarAmount.indexOf(".") < 0) {
					 dollarAmount += ".00";
				 }
				 
				 //update summary values
				 if (serviceType.equals("Dinner")) {
					 dinnerCount++;
					 dinnerCost+=dollarAmountDouble;
				 } else if (serviceType.equals("Lodging")) {
					 lodgingCount++;
					 lodgingCost+=dollarAmountDouble;
				 } else {
					 conferenceCount++;
					 conferenceCost+=dollarAmountDouble;
				 }
				 //print data in tabular format
				 String str = String.format("%15s%15s%15s%15s", clientName, serviceType, dollarAmount, date);
				 System.out.println(str);
			 }
			 scan.close();
		 } catch (FileNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		  
		 //print summary
		 String dinnerSummary = String.format("Dinner\n\tNumber: %d\n\tAmount: %.2f\n", dinnerCount, dinnerCost);
		 String lodgingSummary = String.format("Lodging\n\tNumber: %d\n\tAmount: %.2f\n", lodgingCount, lodgingCost);
		 String conferenceSummary = String.format("Conference\n\tNumber: %d\n\tAmount: %.2f\n", conferenceCount, conferenceCost);
		 System.out.println("***************************Summary**************************");
		 System.out.println(dinnerSummary + lodgingSummary + conferenceSummary);
	}
	  
	 
	  /**
	   * Driver method that calls another method to read and print data.
	   * @author Manvika Satish
	   * @param args - command line arguments
	   */
	 public static void main(String[] args) {
		 readAndPrintData();
	 }
		 	      
}
	
	  
	
	
	
	
	

