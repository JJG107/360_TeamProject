import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataSet {

	private ArrayList<Float> data;
	private float minValue;
	private float maxValue;
	private ArrayList<String> errorLog;

	/**
	 * Default Constructor
	 */
	public DataSet()
	{
		data = new ArrayList<Float>();
		errorLog = new ArrayList<String>();
		minValue = 0;
		maxValue = 100;
	}

	/**
	 * Returns the entire dataset.
	 * @return An ArrayList of floats containing the dataset.
	 */
	public ArrayList<Float> getData()
	{
		return data;
	}

	/**
	 * Gets the count of how many data points there are.
	 * @return An indicating the count.
	 */
	public int getDataCount()
	{
		return data.size();
	}

	/**
	 * Sets the boundaries for the dataset.
	 * @param min The minimum grade allowed.
	 * @param max The maximum grade allowed. 
	 */
	public void setBoundaries(float min, float max)
	{
		minValue = min;
		maxValue = max;
	}

	/**
	 * Appends a single float to the dataset.
	 * @param value The float to append to the dataset.
	 * @return A string indicating whether the value falls within the boundaries.
	 */
	public String appendSingleValue(float value)
	{
		String message;
		if (value >= minValue && value <= maxValue)
		{
			data.add(value);
			message = "Data added";
		}
		else
		{
			message = "Appended data not within bounds";
			addError(message);

		}
		return message;
	}

	/**
	 * Creates a new dataset with the passed value.
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
			message = "File does not exist";
			addError(message);
		}
		else if (fileType == 0)
		{
			message = "File is not of type csv or txt";
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
					boolean withinBounds = true;
					ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
					for (int i = 0; i < newData.size(); i++)
					{
						if ((newData.get(i) < minValue) || (newData.get(i) > maxValue))
						{
							withinBounds = false;
							notWithinBounds.add(i);
						}
					}
					if (withinBounds)
					{
						data = newData;
						message = "Created Dataset";
					}
					else
					{
						message = "The following data indexes are not within bounds: ";
						for (int i = 0; i < notWithinBounds.size(); i++)
						{
							message += notWithinBounds.get(i);
							if (i < notWithinBounds.size() - 1)
							{
								message += ", ";
							}
						}
						addError(message);
					}
				}
				else
				{
					message = "No data in the file to create dataset";
					addError(message);
				}
			}
			catch (NumberFormatException e)
			{
				message = "The file does not contain only numbers";
				addError(message);
			}
		}
		return message;
	}

	/**
	 * Appends data to the current dataset from an ArrayList.
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
			message = "File does not exist";
			addError(message);
		}
		else if (fileType == 0)
		{
			message = "File is not of type csv or txt";
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
					boolean withinBounds = true;
					ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
					for (int i = 0; i < newData.size(); i++)
					{
						if ((newData.get(i) < minValue) || (newData.get(i) > maxValue))
						{
							withinBounds = false;
							notWithinBounds.add(i);
						}
					}
					if (withinBounds)
					{
						data.addAll(newData);
						message = "Appended To Dataset";
					}
					else
					{
						message = "The following data indexes are not within bounds: ";
						for (int i = 0; i < notWithinBounds.size(); i++)
						{
							message += notWithinBounds.get(i);
							if (i < notWithinBounds.size() - 1)
							{
								message += ", ";
							}
						}
						addError(message);
					}
				}
				else
				{
					message = "No data in the file to append to dataset";
					addError(message);
				}
			}
			catch (NumberFormatException e)
			{
				message = "The file does not contain only numbers";
				addError(message);
			}
		}
		return message;
	}

	/**
	 * Retrieves the minimum grade in the dataset.
	 * @return A string which indicates the minimum grade.
	 */
	public String getMin()
	{
		String message;
		float min;
		if (data.size() > 0)
		{
			min = data.get(0);
			for (int i = 0; i < data.size(); i++)
			{
				if (data.get(i) < min)
				{
					min = data.get(i);
				}
			}
			message = "" + min;
		}
		else
		{
			message = "No data in dataset to get min";
			addError(message);
		}
		return message;
	}

	/**
	 * Retrieves the maximum grade in the dataset.
	 * @return A string which indicates the maximum grade.
	 */
	public String getMax()
	{
		String message;
		float max;
		if (data.size() > 0)
		{
			max = data.get(0);
			for (int i = 0; i < data.size(); i++)
			{
				if (data.get(i) > max)
				{
					max = data.get(i);
				}
			}
			message = "" + max;
		}
		else
		{
			message = "No data in dataset to get max";
			addError(message);
		}
		return message;
	}

	/**
	 * Deletes the first instance of the entered grade.
	 * @param gradeToDelete The grade to delete.
	 * @return A string indicating the success of the function.
	 */
	public String deleteGrade(float gradeToDelete)
	{
		String message;
		if (data.size() > 0)
		{
			boolean found = false;
			message = "That datapoint does not exist";
			for (int i = 0; i < data.size(); i++)
			{
				if (data.get(i) == gradeToDelete)
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
		return message;
	}

	/**
	 * Creates a distribution of the grades indicating the
	 * average grade for every 10% bracket.
	 * @return An array of strings indicating the average grade for
	 * each bracket.
	 */
	public String[] createDistribution()
	{
		String[] toReturn = new String[10];
		float[] distribution = new float[10];
		int[] count = new int[10];
		float totalRange = maxValue - minValue;
		// Determine what falls within what range
		for (int i = 0; i < data.size(); i++)
		{
			float rawValue = data.get(i);
			float percent = (rawValue - minValue) / totalRange;
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
	 * Gets a count of how many grades fall within 10% bounds.
	 * @return A an array of ints for the counts in each range.
	 */
	public int[] getGraphCount()
	{
		int[] count = new int[10];
		float totalRange = maxValue - minValue;
		// Determine what falls within what range
		for (int i = 0; i < data.size(); i++)
		{
			float rawValue = data.get(i);
			float percent = (rawValue - minValue) / totalRange;
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
	 * Gets the ranges for the graph.
	 * @return An array of floats which indicate the 10% bounds.
	 */
	public float[] getGraphRanges()
	{
		float[] ranges = new float[11];
		float totalRange = maxValue - minValue;
		float shortRange = totalRange / 10;
		// The ranges of each percentage
		for(int i = 0; i < 11; i++)
		{
			ranges[i] = minValue + shortRange * i;
		}
		return ranges;
	}

	/**
	 * Gets the mean of the dataset.
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
	 * Gets the median of the dataset.
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
	 * Gets the mode of the dataset.
	 * @return An arrayList of strings indicating the mode/s.
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
			message = "No data to get mode from";
			addError(message);
		}

		return message;
	}

	/**
	 * Adds an error to the error log.
	 * @param error The error to add to the log.
	 */
	public void addError(String error)
	{
		errorLog.add(error);
	}

	/**
	 * Retrieves the error log.
	 * @return An ArrayList of all errors.
	 */
	public ArrayList<String> getErrorLog()
	{
		return errorLog;
	}

	/**
	 * Retrieves the last error in the error log.
	 * @return A string indicating the last error.
	 */
	public String getLastError()
	{
		return errorLog.get(errorLog.size() - 1);
	}

	/**
	 * Validates that a file name is a csv or a txt file.
	 * @param fileName The name of the file to validate
	 * @return An integer indicating the type
	 * 0 - other
	 * 1 - txt
	 * 2 - csv
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
	 * Reads in a txt file of data points.
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
		catch (FileNotFoundException e)
		{

		}
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
	 * Reads in a csv file of data points.
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
		catch (FileNotFoundException e)
		{

		}
		finally
		{
			if (scan != null)
			{
				scan.close();
			}
		}
		return toReturn;
	}
}
