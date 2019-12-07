import java.awt.*;
import java.util.ArrayList;

/**
 * Contains a variety of utility functions.
 * @authors Michael Cai, Priya Ganguly
 *
 */
public class UtilityFunctions {
	
	int highestScore;
	int maxX = 30;
	String returned = "";

	public String makeGraph(int counts[], int ranges[]) {
		System.out.println("Each X represents 10 students.");
		System.out.println("----------------------------------------------------");
		
		//distribution1
		for (int i = 0; i <= ranges[1]; i++) {
			int numX = counts[1]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[1] + '\n';
		}
		
		//distribution2
		for (int i = ranges[1] + 1; i <= ranges[2]; i++) {
			int numX = counts[2]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[2] + '\n';
		}
		
		//distribution3
		for (int i = ranges[2] + 1; i <= ranges[3]; i++) {
			int numX = counts[3]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[3] + '\n';
		}
		
		//distribution4
		for (int i = ranges[3] + 1; i <= ranges[4]; i++) {
			int numX = counts[4]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[4] + '\n';
		}
		
		//distribution5
		for (int i = ranges[4] + 1; i <= ranges[5]; i++) {
			int numX = counts[5]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[5] + '\n';
		}
		
		//distribution6
		for (int i = ranges[5] + 1; i <= ranges[6]; i++) {
			int numX = counts[6]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[6] + '\n';
		}
		
		//distribution7
		for (int i = ranges[6] + 1; i <= ranges[7]; i++) {
			int numX = counts[7]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[7] + '\n';
		}
		
		//distribution8
		for (int i = ranges[7] + 1; i <= ranges[8]; i++) {
			int numX = counts[8]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[8] + '\n';
		}
		
		//distribution9
		for (int i = ranges[8] + 1; i <= ranges[9]; i++) {
			int numX = counts[9]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[9] + '\n';
		}
		
		//distribution10
		for (int i = ranges[9] + 1; i <= ranges[10]; i++) {
			int numX = counts[10]/highestScore * maxX;
			for(int k = 1; k <= numX; k++)
				returned += "X";
			returned += ranges[10] + '\n';
		}
		returned += "\n\nGrade Analytics Bar Chart" + '\n';
		return returned;
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
