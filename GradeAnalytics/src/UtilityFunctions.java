import java.util.ArrayList;

import java.lang.Object;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

//public class JXCanvas extends Canvas {
//	
//	FontMetrics fm;
//	int titleHeight;
//	int labelWidth;
//	
//	
//	int padding = 6;
//	int top;
//	int bottom;
//	
//public static class Drawing extends Canvas {
//	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("bars");
//		
//		Canvas canvas = new Drawing();
//		canvas.setSize(400, 400);
//		frame.add(canvas);
//		frame.pack();
//		frame.setVisible(true);
//	}
//	
//	public void paint(Graphics g) {
//		g.create(getX(), getY(), getWidth(), getHeight());
//	}
//}
//}

/**
 * Contains a variety of utility functions.
 * @author Michael Cai
 *
 */
public class UtilityFunctions {
	
	int position; int increment; int right; int left; int bottom; int top; int min;
	int padding = 4;
	int[] nums;
	String[] cat;
	String t;
	int max = 0;
	int width = 0;
	int height = 0;
	int titleHeight;
	String title;
	FontMetrics fm;
	int dist1, dist2, dist3, dist4, dist5, dist6, dist7, dist8, dist9, dist10;

	   
	   //distribution1
for (int i = 1; i < dist1; i++) {
	if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	   //distribution2
	   for (int i = 1; i < dist2; i++)
	   {
	      if (i % 10 == 0)
	      {
	         System.out.print("X");
	      }
	      System.out.print("*");
	   }
	   
	   //distribution 3
	   for (int i = 1; i < dist3; i++) {
		      if (i % 10 == 0){
		         System.out.print("X");
		      }
		      System.out.print("*");                
		   }
	   
	 //distribution4
	   for (int i = 1; i < dist4; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution5
	   for (int i = 1; i < dist5; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution6
	   for (int i = 1; i < dist6; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution7
	   for (int i = 1; i < dist7; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution8
	   for (int i = 1; i < dist8; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution9
	   for (int i = 1; i < dist9; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   
	 //distribution10
	   for (int i = 1; i < dist10; i++) {
	      if (i % 10 == 0){
	         System.out.print("X");
	      }
	      System.out.print("*");                
	   }
	   

	   System.out.println("\n\nBar Chart");
	   System.out.println("Chart 1: " + dist1);
	System.out.println("Chart 2: " + dist2);
	System.out.println("Chart 3: " + dist3);
	System.out.println("Chart 4: " + dist4);
	System.out.println("Chart 5: " + dist5);
	System.out.println("Chart 6: " + dist6);
	System.out.println("Chart 7: " + dist7);
	System.out.println("Chart 8: " + dist8);
	System.out.println("Chart 9: " + dist9);
	System.out.println("Chart 10: " + dist10);
	    
	 }
	   

	   
	
	


	/**
	 * Determines the largest integer in an
	 * array of integers
	 * @param intArray The array to search
	 * @return The largest integer
	 */
	public static int findHighestIntIndex(int[] intArray)
	{
		int highest = intArray[0];
		for (int i = 1; i < intArray.length; i++)
		{
			if (intArray[i] > highest)
			{
				highest = intArray[i];
			}
		}
		return highest;
	}
	
	/**
	 * Repeated a string n times and returns the
	 * concatenated string.
	 * @param toRepeat
	 * @param n
	 * @return
	 */
	public static String repeatStringNTimes(String toRepeat, int n)
	{
		String repeated = "";
		for (int i = 0; i < n; i++)
		{
			repeated += toRepeat;
		}
		return repeated;
	}
	
	/**
	 * Check to see if a string can be converted to a float
	 * @param toCheck The string to check
	 * @return A boolean indicating if it can be converted
	 */
	public static boolean checkIfStringIsFloat(String toCheck)
	{
		boolean isFloat = false;
		try
		{
			Float.parseFloat(toCheck);
			isFloat = true;
		}
		catch (NumberFormatException e) {}
		
		return isFloat;
	}
	
	/**
	 * Transforms a list of strings into one string
	 * separated by line breaks.
	 * @param list The list to transform.
	 * @return A string containing all of the
	 * list's strings.
	 */
	public static String makeListOfStringsOneString(ArrayList<String> list)
	{
		String toReturn = "";
		for (int i = 0; i < list.size(); i++)
		{
			toReturn += list.get(i) + "\n";
		}
		return toReturn;
	}
}
