import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Contains a variety of utility functions.
 * @author Michael Cai
 *
 */
public class UtilityFunctions {

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
	
	public static String[] decimalFormatString(String[] stringsToFormat)
	{
		String pattern = "0.0";
	    DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String[] toReturn = new String[stringsToFormat.length];
		for (int i = 0; i < stringsToFormat.length; i++)
		{
			if (!(stringsToFormat[i].compareTo("N/A") == 0))
			{
				float floatToFormat = Float.parseFloat(stringsToFormat[i]);
				toReturn[i] = "" + decimalFormat.format(floatToFormat);
			}
			else
			{
				toReturn[i] = "N/A";
			}
		}
		return toReturn;
	}
}
