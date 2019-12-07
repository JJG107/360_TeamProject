import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 
 * @author Michael, Greyson, James, Priya
 * 
 * This class represents a data set. It contains methods for the manipulation of data sets,
 * including loading data from files, entering data from the keyboard, calculating data metrics,
 * and producing graphs/reports based on data.
 */
public class DataSet {

	private ArrayList<Float> data;
	private float lowerBound;
	private float upperBound;
	private float minGrade;
	private float maxGrade;
	private ArrayList<String> errorLog;

	/**
	 * Default Constructor
	 */
	public DataSet()
	{
		data = new ArrayList<Float>();
		errorLog = new ArrayList<String>();
		lowerBound = 0;
		upperBound = 100;
		minGrade = 100;
		maxGrade = 0;
	}

	/**
	 * Returns the entire dataset.
	 * 
	 * @return An ArrayList of floats containing the dataset.
	 */
	public ArrayList<Float> getData()
	{
		return data;
	}

	/**
	 * Gets the count of how many data points there are.
	 * 
	 * @return An indicating the count.
	 */
	public int getDataCount()
	{
		return data.size();
	}

	/**
	 * Sets the boundaries for the dataset.
	 * 
	 * @param min The minimum grade allowed.
	 * @param max The maximum grade allowed.
	 * @return A string indicating whether boundaries were added with/without existing data being removed 
	 */
	public String setBoundaries(String min, String max)
	{
		String message = "The boundaries are not floats or ints";
		if (UtilityFunctions.checkIfStringIsFloat(min) &&
			UtilityFunctions.checkIfStringIsFloat(max))
		{
			message = "Boundaries Set";
			lowerBound = Float.parseFloat(min);
			upperBound = Float.parseFloat(max);		
	
			if(data.size() == 0)
			{
				minGrade = upperBound;
				maxGrade = lowerBound;
			}
			
			int removed = removeOutOfBoundaryData();
			if (removed > 0)
			{
				message = "Data points out of bounds, " + removed + " data points were deleted";
				addError(message);
			}
		}
		else
		{
			addError(message);
		}
		return message;
	}

	/**
	 * Appends a single float to the dataset.
	 * 
	 * @param value The float to append to the dataset.
	 * @return A string indicating whether the value falls within the boundaries.
	 */
	public String appendSingleValue(String stringValue)
	{
		String message;
		if (UtilityFunctions.checkIfStringIsFloat(stringValue))
		{
			float value = Float.parseFloat(stringValue);
			if (value >= lowerBound && value <= upperBound)
			{
				if(value < minGrade)
					minGrade = value;
				if(value > maxGrade)
					maxGrade = value;
				
				data.add(value);
				message = "Data value \"" + value + "\" added";
			}
			else
			{
				message = "The value \"" + value + "\" is not within the current bounds";
				addError(message);

			}
		}
		else
		{
			message = "The value \"" + stringValue + "\" is not a float or int";
		}

		return message;
	}

	/**
	 * Creates a new dataset with the passed value.
	 * 
	 * @param fileName The name of the file to load.
	 * @return A string indicating the success of the function.
	 */
	public String createDataFromFile(String fileName)
	{
		String message;
		File file = new File(fileName);

		// Create the dataset from the file
		ArrayList<Float> newData;
		int fileType = validateName(fileName);
		if (!file.exists())
		{
			message = "The file \"" + fileName + "\" does not exist";
			addError(message);
		}
		else if (fileType == 0)
		{
			message = "The file \"" + fileName + "\" is not of type .csv or .txt";
			addError(message);
		}
		else
		{
			try
			{
				if (fileType == 1)
				{
					newData = readTxtFile(file);
				}
				else 
				{
					newData = readCsvFile(file);
				}

				// Validate the read in file
				if (newData.size() > 0)
				{
					float currentMax = maxGrade;
					float currentMin = minGrade;
					
					boolean withinBounds = true;
					ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
					for (int i = 0; i < newData.size(); i++)
					{
						if ((newData.get(i) < lowerBound) || (newData.get(i) > upperBound))
						{
							withinBounds = false;
							notWithinBounds.add(i);
						}
						
						if(newData.get(i) < minGrade)
							minGrade = newData.get(i);
						if(newData.get(i) > maxGrade)
							maxGrade = newData.get(i);
					}
					if (withinBounds)
					{
						data = newData;
						message = "Created new dataset from the file \"" + fileName + "\"";
					}
					else
					{
						maxGrade = currentMax;
						minGrade = currentMin;
						
						message = "The following data indices are not within bounds: ";
						for (int i = 0; i < notWithinBounds.size(); i++)
						{
							message += notWithinBounds.get(i);
							if (i < notWithinBounds.size() - 1)
							{
								message += ", ";
							}
						}
						message += "\nPlease edit these values and try to load the file \""
									+ fileName + "\" again.";
						addError(message);
					}
				}
				else
				{
					message = "No data in the file \"" + fileName + "\" to create dataset";
					addError(message);
				}
			}
			catch (NumberFormatException e)
			{
				message = "The file \"" + fileName + "\" does not contain only numbers";
				addError(message);
			}
		}
		return message;
	}

	/**
	 * Appends data to the current dataset from an ArrayList.
	 * 
	 * @param fileName The name of the file to load.
	 * @return A string that indicates the success of the function.
	 */
	public String appendDataFromFile(String fileName)
	{
		String message;
		File file = new File(fileName);

		// Create the dataset from the file
		ArrayList<Float> newData;
		int fileType = validateName(fileName);
		if (!file.exists())
		{
			message = "The file \"" + fileName + "\" does not exist";
			addError(message);
		}
		else if (fileType == 0)
		{
			message = "The file \"" + fileName + "\" is not of type .csv or .txt";
			addError(message);
		}
		else
		{
			try
			{
				if (fileType == 1)
				{
					newData = readTxtFile(file);
				}
				else 
				{
					newData = readCsvFile(file);
				}

				// Validate the read in file
				if (newData.size() > 0)
				{
					float currentMin = minGrade;
					float currentMax = maxGrade;
					
					boolean withinBounds = true;
					ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
					for (int i = 0; i < newData.size(); i++)
					{
						if ((newData.get(i) < lowerBound) || (newData.get(i) > upperBound))
						{
							withinBounds = false;
							notWithinBounds.add(i);
						}
						
						if(newData.get(i) < minGrade)
							minGrade = newData.get(i);
						if(newData.get(i) > maxGrade)
							maxGrade = newData.get(i);
					}
					if (withinBounds)
					{
						data.addAll(newData);
						message = "Appended data from \"" + fileName +  "\" to dataset";
					}
					else
					{
						maxGrade = currentMax;
						minGrade = currentMin;
						
						message = "The following data indexes are not within bounds: ";
						for (int i = 0; i < notWithinBounds.size(); i++)
						{
							message += notWithinBounds.get(i);
							if (i < notWithinBounds.size() - 1)
							{
								message += ", ";
							}
						}
						message += "\nPlease edit these values and try to load the file \"" 
									+ fileName + "\" again.";
						addError(message);
					}
				}
				else
				{
					message = "No data in the file \"" + fileName + "\" to append to dataset";
					addError(message);
				}
			}
			catch (NumberFormatException e)
			{
				message = "The file \"" + fileName +  "\" does not contain only numbers";
				addError(message);
			}
		}
		return message;
	}

	/**
	 * Retrieves the minimum grade in the dataset.
	 * 
	 * @return A string which indicates the minimum grade.
	 */
	public String getMin()
	{
		if (getDataCount() != 0)
		{
			return "Min grade: " + minGrade;
		}
		else
		{
			String error = "There is no data to retieve a min from";
			errorLog.add(error);
			return error;
		}
	}

	/**
	 * Retrieves the maximum grade in the dataset.
	 * 
	 * @return A string which indicates the maximum grade.
	 */
	public String getMax()
	{
		if (getDataCount() != 0)
		{
			return "Max grade: " + maxGrade;
		}
		else
		{
			String error = "There is no data to retieve a max from";
			errorLog.add(error);
			return error;
		}
	}

	/**
	 * Deletes the first instance of the entered grade.
	 * 
	 * @param gradeToDelete The grade to delete.
	 * @return A string indicating the success of the function.
	 */
	public String deleteGrade(String gradeToDelete)
	{
		String message;
		if (UtilityFunctions.checkIfStringIsFloat(gradeToDelete))
		{
			float floatToDelete = Float.parseFloat(gradeToDelete);
			if (data.size() > 0)
			{
				boolean found = false;
				message = "That datapoint does not exist";
				for (int i = 0; i < data.size(); i++)
				{
					if (data.get(i) == floatToDelete)
					{
						found = true;
						data.remove(i);
						message = "Successfully removed";
					}
				}
				if (!found)
				{	
					addError(message);
				}
			}
			else
			{
				message = "No data in dataset to delete grade";
				addError(message);
			}
		}
		else
		{
			message = "The grade to delete \"" + "\" is not a float or int";
		}
		return message;
	}

	/**
	 * Creates a distribution of the grades indicating the
	 * average grade for every 10% bracket.
	 * 
	 * @return An array of strings indicating the average grade for
	 * each bracket.
	 */
	public String[] createDistribution()
	{
		String[] toReturn = new String[10];
		float[] distribution = new float[10];
		int[] count = new int[10];
		float totalRange = upperBound - lowerBound;
		// Determine what falls within what range
		for (int i = 0; i < data.size(); i++)
		{
			float rawValue = data.get(i);
			float percent = (rawValue - lowerBound) / totalRange;
			if (percent >= .9)
			{
				distribution[9] += rawValue;
				count[9]++;
			}
			else if (percent >= .8)
			{
				distribution[8] += rawValue;
				count[8]++;
			}
			else if (percent >= .7)
			{
				distribution[7] += rawValue;
				count[7]++;
			}
			else if (percent >= .6)
			{
				distribution[6] += rawValue;
				count[6]++;
			}
			else if (percent >= .5)
			{
				distribution[5] += rawValue;
				count[5]++;
			}
			else if (percent >= .4)
			{
				distribution[4] += rawValue;
				count[4]++;
			}
			else if (percent >= .3)
			{
				distribution[3] += rawValue;
				count[3]++;
			}
			else if (percent >= .2)
			{
				distribution[2] += rawValue;
				count[2]++;
			}
			else if (percent >= .1)
			{
				distribution[1] += rawValue;
				count[1]++;
			}
			else
			{
				distribution[0] += rawValue;
				count[0]++;
			}
		}
		// Find Averages
		for (int i = 0; i < 10; i++)
		{
			if (count[i] != 0)
			{
				toReturn[i] = "" + (distribution[i] / count[i]);
			}
			else
			{
				toReturn[i] = "N/A";
			}
		}

		return toReturn;
	}

	/**
	 * Gets a count of how many grades fall within 10% bounds. This is used to determine 
	 * the size of each bar in the graph.
	 * 
	 * @return A an array of ints for the counts in each range.
	 */
	public int[] getGraphCount()
	{
		int[] count = new int[10];
		float totalRange = upperBound - lowerBound;
		// Determine what falls within what range
		for (int i = 0; i < data.size(); i++)
		{
			float rawValue = data.get(i);
			float percent = (rawValue - lowerBound) / totalRange;
			if (percent >= .9)
			{
				count[9]++;
			}
			else if (percent >= .8)
			{
				count[8]++;
			}
			else if (percent >= .7)
			{
				count[7]++;
			}
			else if (percent >= .6)
			{
				count[6]++;
			}
			else if (percent >= .5)
			{
				count[5]++;
			}
			else if (percent >= .4)
			{
				count[4]++;
			}
			else if (percent >= .3)
			{
				count[3]++;
			}
			else if (percent >= .2)
			{
				count[2]++;
			}
			else if (percent >= .1)
			{
				count[1]++;
			}
			else
			{
				count[0]++;
			}
		}

		return count;
	}

	/**
	 * Calculates boundaries for each 10% bar in the graph.
	 * 
	 * @return An array of floats which indicate the 10% bounds.
	 */
	public float[] getGraphRanges()
	{
		float[] ranges = new float[11];
		float totalRange = upperBound - lowerBound;
		float shortRange = totalRange / 10;
		// The ranges of each percentage
		for(int i = 0; i < 11; i++)
		{
			ranges[i] = lowerBound + shortRange * i;
		}
		return ranges;
	}

	/**
	 * Computes the mean of the dataset.
	 * 
	 * @return A String indicating the mean.
	 */
	public String getMean()
	{
		String message;
		float total = 0;
		for (int i = 0; i < data.size(); i++)
		{
			total += data.get(i);
		}
		if (data.size() != 0)
		{
			total = (total / data.size());
			message = "The mean is: " + total;
		}
		else
		{
			message = "No data in dataset to get mean";
			addError(message);
		}
		return message;
	}

	/**
	 * Computes the median of the dataset.
	 * 
	 * @return A String indicating the median.
	 */
	public String getMedian()
	{
		String message;
		float median;
		if (data.size() > 0)
		{
			data.sort(null);
			if (data.size() % 2 == 0)
			{
				float median1 = data.get(data.size() / 2 - 1);
				float median2 = data.get(data.size() / 2);
				median = (median1 + median2) / 2;
			}
			else
			{
				median = data.get(data.size() / 2);
			}

			message = "The median is: " + median;
		}
		else
		{
			message = "No data in dataset to get median";
			addError(message);
		}
		return message;
	}

	/**
	 * Computes the mode(s) of the dataset.
	 * 
	 * @return An arrayList of strings indicating the mode(s).
	 */
	public String getMode()
	{
		String message;
		data.sort(null);
		if (data.size() > 0)
		{
			ArrayList<Integer> count = new ArrayList<Integer>();
			ArrayList<Float> values = new ArrayList<Float>();
			int currentCount = 1;
			float currentValue = data.get(0);
			for (int i = 1; i < data.size(); i++)
			{
				if (currentValue == data.get(i))
				{
					currentCount++;
				}
				else
				{
					count.add(currentCount);
					values.add(currentValue);
					currentCount = 1;
					currentValue = data.get(i);
				}
			}
			count.add(currentCount);
			values.add(currentValue);

			// Clone the counts, sort, and find max
			ArrayList<Integer> sortedCounts = (ArrayList<Integer>) count.clone();
			sortedCounts.sort(null);
			int maxCount = sortedCounts.get(sortedCounts.size() - 1);
			message = "The mode(s) are: ";
			for (int i = 0; i < count.size(); i++)
			{
				if (count.get(i) == maxCount)
				{
					message += values.get(i);
					message += ", ";
				}
			}
			// Remove last comma
			message = message.substring(0, message.length() - 2);
		}
		else
		{
			message = "No data in the dataset to get mode from";
			addError(message);
		}

		return message;
	}

	/**
	 * Adds an error to the error log.
	 * 
	 * @param error The error to add to the log.
	 */
	public void addError(String error)
	{
		errorLog.add(error);
	}

	/**
	 * Retrieves the error log.
	 * 
	 * @return An ArrayList of all errors.
	 */
	public ArrayList<String> getErrorLog()
	{
		return errorLog;
	}

	/**
	 * Retrieves the last error in the error log.
	 * 
	 * @return A string indicating the last error.
	 */
	public String getLastError()
	{
		return errorLog.get(errorLog.size() - 1);
	}
	
	/**
	 * Generates a report of all of the data
	 * 
	 * @return A string indicating the success
	 * of the function.
	 */
	public String generateReport()
	{
		String message;
		File report = new File("report.txt");
		if (data.size() == 0)
		{
			message = "There is no data to generate a report";
		}
		else
		{
			try
			{
				report.createNewFile();
				PrintStream writer = new PrintStream(report);
				String toAppend;
				
				// Max
				toAppend = getMaxBoundary();
				writer.println(toAppend);
				
				// Min
				toAppend = getMinBoundary();
				writer.println(toAppend);
				
				// The Dataset
				toAppend = getDataSetAsString();
				writer.println(toAppend);
				
				// Simple Stats
				// Count, Max, Min, Mean, Median, Mode
				toAppend = "Data Count: " + getDataCount();
				writer.println(toAppend);
				toAppend = "Max: " + maxGrade;
				writer.println(toAppend);
				toAppend = "Min: " + minGrade;
				writer.println(toAppend);
				toAppend = getMean();
				writer.println(toAppend);
				toAppend = getMedian();				
				writer.println(toAppend);
				toAppend = getMode();				
				writer.println(toAppend);
				
				// Distribution
				toAppend = "Distribution:\n";
				writer.println(toAppend);
				String[] distribution = createDistribution();

				// Format the distribution as x% - y%: z
				int min, max;
				for (int i = 0; i < distribution.length; i++)
				{
					toAppend = "";
					min = i * 10;
					max = i * 10 + 10;
					toAppend += min + "% - " + max + "%: ";
					toAppend += distribution[i];
					writer.println(toAppend);
				}
				
				// Graph
				int[] graphCounts = getGraphCount();
				float[] graphRanges = getGraphRanges();
				int biggestCount = UtilityFunctions.findHighestIntIndex(graphCounts);
				int starsToDraw;
				for (int i = 0; i < graphCounts.length; i++)
				{
					toAppend = "";
					toAppend += graphRanges[i] + " - " + graphRanges[i+1] + "\t|";
					starsToDraw = graphCounts[i] / biggestCount;
					toAppend += UtilityFunctions.repeatStringNTimes("*", starsToDraw);
					writer.println(toAppend);
				}
				
				// Error Log
				writer.println("\nError Log\n");
				ArrayList<String> errors = getErrorLog();
				for (int i = 0; i < errors.size(); i++)
				{
					writer.println(errors.get(i));
				}
				message = "Report successfully written to \"report.txt\"";
				writer.close();
			}
			catch (Exception e)
			{
				message = "An IO exception occurred";
			}
			
		}
		return message;
	}
	
	/**
	 * Creates a string to represent the data in
	 * four columns.
	 * 
	 * @return A string that represents the data in
	 * four columns.
	 */
	public String getDataSetAsString()
	{
		String dataAsString = "";
		ArrayList<Integer> sortedData = (ArrayList<Integer>) data.clone();
		sortedData.sort(null);
		int columnSize = sortedData.size() / 4; // Split into four columns
		int itemsPrinted;
		for (int i = 0; i < columnSize; i++)
		{
			itemsPrinted = 0;
			for (int j = 0; j < sortedData.size(); j++)
			{
				if ((j % columnSize) == i)
				{
					itemsPrinted++;
					dataAsString += sortedData.get(j);
					if (itemsPrinted != 3)
					{
						dataAsString += "\t";
					}
				}
			}
			dataAsString += "\n";
		}
		return dataAsString;
	}

	/**
	 * Validates that a selected file name is a .csv or a .txt file.
	 * 
	 * @param fileName The name of the file to validate
	 * @return An integer indicating the type
	 * (0 - other)
	 * (1 - txt)
	 * (2 - csv)
	 */
	private int validateName(String fileName)
	{
		int extension = 0;
		int extensionPeriod = fileName.lastIndexOf(".");
		if (extensionPeriod > 0)
		{
			String extensionString = fileName.substring(extensionPeriod + 1);
			if (extensionString.compareTo("txt") == 0)
			{
				extension = 1;
			}
			else if (extensionString.compareTo("csv") == 0)
			{
				extension = 2;
			}
		}
		return extension;
	}

	/**
	 * Reads in a .txt file of data points.
	 * 
	 * @param fileToRead The name of the file to read in.
	 * @return An ArrayList of floats from the file.
	 */
	private ArrayList<Float> readTxtFile(File fileToRead)
	{
		Scanner scan = null;
		ArrayList<Float> toReturn = new ArrayList<Float>();
		try
		{
			scan = new Scanner(fileToRead);
			while (scan.hasNextLine())
			{
				toReturn.add(Float.parseFloat(scan.nextLine()));
			}
		}
		catch (FileNotFoundException e){}
		finally
		{
			if (scan != null)
			{
				scan.close();
			}
		}
		return toReturn;
	}

	/**
	 * Reads in a .csv file of data points.
	 * 
	 * @param fileToRead The name of the file to read in.
	 * @return An ArrayList of floats from the file.
	 */
	private ArrayList<Float> readCsvFile(File fileToRead)
	{
		Scanner scan = null;
		ArrayList<Float> toReturn = new ArrayList<Float>();
		try
		{
			scan = new Scanner(fileToRead);
			scan.useDelimiter(",");
			while (scan.hasNextLine())
			{
				toReturn.add(Float.parseFloat(scan.next()));
			}
		}
		catch (FileNotFoundException e) {}
		finally
		{
			if (scan != null)
			{
				scan.close();
			}
		}
		return toReturn;
	}
	
	/**
	 * Removes data based on the changed bounds. Saves a separate error message for each data
	 * point that was removed.
	 * 
	 * @return An int indicating how much data was deleted.
	 */
	private int removeOutOfBoundaryData()
	{
		int removedEntries = 0;
		String message;
		
		ArrayList<Float> toRemove = new ArrayList<Float>();
		for (int i = 0; i < data.size(); i++)
		{
			if ((data.get(i) < lowerBound) || (data.get(i) > upperBound))
			{
				toRemove.add(data.get(i));
				removedEntries++;
				
				message = "The grade value\"" + data.get(i) + "\" is not within the new bounds, " +
						" it will be removed from the current dataset";
				addError(message);
			}
		}
		data.removeAll(toRemove);
		return removedEntries;
	}
	
	/**
	 * Retrieves the minimum boundary.
	 * 
	 * @return A string indicating the minimum boundary.
	 */
	private String getMinBoundary()
	{
		return "Minimum Boundary: " + lowerBound;
	}
	
	/**
	 * Retrieves the maximum boundary.
	 * 
	 * @return A string indicating the maximum boundary.
	 */
	private String getMaxBoundary()
	{
		return "Maximum Boundary: " + upperBound;
	}
}
