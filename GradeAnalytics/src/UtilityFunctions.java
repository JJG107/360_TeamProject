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
	int highestScore;

	public void makeGraph(int highestScore, int count, int dist1, int dist2, int dist3, 
		int dist4, int dist5, int dist6, int dist7, int dist8, int dist9, int dist10) {
		System.out.println("Each X represents 10 students.");
		System.out.println("----------------------------------------------------");
		
		int maxX = 30;
		
		//distribution1
		for (int i = 1; i <= dist1; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist1");
		}
		
		//distribution2
		for (int i = 1; i <= dist2; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist2");
		}
		
		//distribution3
		for (int i = 1; i <= dist3; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist3");
		}
		
		//distribution4
		for (int i = 1; i <= dist4; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist4");
		}
		
		//distribution5
		for (int i = 1; i <= dist5; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist5");
		}
		
		//distribution6
		for (int i = 1; i <= dist6; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist6");
		}
		
		//distribution7
		for (int i = 1; i <= dist7; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist7");
		}
		
		//distribution8
		for (int i = 1; i <= dist8; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist8");
		}
		
		//distribution9
		for (int i = 1; i <= dist9; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist9");
		}
		
		//distribution10
		for (int i = 1; i <= dist10; i++) {
			int numX = count/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				System.out.print("X");
			System.out.println("dist10");
		}
		
		System.out.println("\n\nGrade Analytics Bar Chart");
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
	
	
	
	public void JXCanvas(int[] x, String[] y, String a) {
		
		this.title = title;
		
	    for(int i = 0; i < x.length; i++){
	        if(x[i] > max)
	            max = x[i];
	    }
	    
	    nums = x;
	    cat = y;
	    t = a;
	    width = nums.length*100 + nums.length*10;
	    height = max*10 + 500;
	    setSize(width,height);
	}
	
	private void setSize(int width2, int height2) {
		// TODO Auto-generated method stub
	}

	//paints the bar graph
	public void paint(Graphics g, int width) {
		fm = getFontMetrics(getFont());
		titleHeight = fm.getHeight();
		g.drawString(title, (width - fm.stringWidth(title))/2, top);
		
		//draws the min and max values
		g.drawString(new Integer(min).toString(), padding, bottom);
	    g.drawString(new Integer(max).toString(), padding, top + titleHeight);
	}
	
	private Object size() {
		// TODO Auto-generated method stub
		return null;
	}

	private FontMetrics getFontMetrics(Object font) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearGraph() {
	}
	
	void bars(Graphics g, double x1, double y1, double x2, double y2, int width, int height) {
        // Draw a bar from the point (x1,y1) to (x2,y2).
        
     int a1, b1;   // pixels
     int a2, b2;   // pixels
     
     a1 = (int)( (x1 + 5) / 10 * width );
     b1 = (int)( (5 - y1) / 10 * height );
     a2 = (int)( (x2 + 5) / 10 * width );
     b2 = (int)( (5 - y2) / 10 * height );
     
     g.drawLine(a1,b1,a2,b2);
     
  }
}
