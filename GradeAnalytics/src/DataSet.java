import java.util.ArrayList;

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
		minValue = 0;
		maxValue = 0;
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
	 * Gets the count of how many datapoints there are.
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
	 * @return An int indicating whether the value falls within the boundaries.
	 */
	public String appendSingleValue(float value)
	{
		String message;
		if (value > minValue && value < maxValue)
		{
			data.add(value);
			message = "Data added";
		}
		else
		{
			message = "Appended data not within bounds";
			errorLog.add(message);
			
		}
		return message;
	}

	/**
	 * Creates a new dataset with the passed value.
	 * @param newData The data to create a new dataset from.
	 * @return An int indicating the success of the function.
	 */
	public String createDataFromFile(ArrayList<Float> newData)
	{
		String message;
		if (newData.size() > 0)
		{
			boolean withinBounds = true;
			ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
			for (int i = 0; i < newData.size(); i++)
			{
				if (!(newData.get(i) < minValue) || !(newData.get(i) > maxValue))
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
				message = "The following data is not within bounds: ";
				for (int i = 0; i < notWithinBounds.size(); i++)
				{
					message += notWithinBounds.get(i);
					if (i < notWithinBounds.size() - 1)
					{
						message += ", ";
					}
				}
				errorLog.add(message);
			}
		}
		else
		{
			message = "No data in the file to create dataset";
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Appends data to the current dataset from an ArrayList.
	 * @param addedData The ArrayList to add to the dataset.
	 * @return An int that indicates the success of the function.
	 */
	public String appendDataFromFile(ArrayList<Float> addedData)
	{
		String message;
		if (addedData.size() > 0)
		{
			boolean withinBounds = true;
			ArrayList<Integer> notWithinBounds = new ArrayList<Integer>();
			for (int i = 0; i < addedData.size(); i++)
			{
				if (!(addedData.get(i) < minValue) || !(addedData.get(i) > maxValue))
				{
					withinBounds = false;
					notWithinBounds.add(i);
				}
			}
			if (withinBounds)
			{
				data.addAll(addedData);
				message = "Appended to Dataset";
			}
			else
			{
				message = "The following data is not within bounds: ";
				for (int i = 0; i < notWithinBounds.size(); i++)
				{
					message += notWithinBounds.get(i);
					if (i < notWithinBounds.size() - 1)
					{
						message += ", ";
					}
				}
				errorLog.add(message);
			}
		}
		else
		{
			message = "No data in the file to append to dataset";
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Retrieves the minimum grade in the dataset.
	 * @return A float which indicates the minimum grade.
	 * If the value returned is below the minValue then there is no data.
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
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Retrieves the maximum grade in the dataset.
	 * @return A float which indicates the maximum grade.
	 * If the value returned is above the maxValue then there is no data.
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
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Deletes the first instance of the entered grade.
	 * @param gradeToDelete The grade to delete.
	 * @return An int indicating the success of the function.
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
				errorLog.add(message);
			}
		}
		else
		{
			message = "No data in dataset to delete grade";
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Creates a distribution of the grades indicating the
	 * average grade for every 10% bracket.
	 * @return An array of floats indicating the average grade for
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
			if (percent > .9)
			{
				distribution[9] += rawValue;
				count[9]++;
			}
			else if (percent > .8)
			{
				distribution[8] += rawValue;
				count[8]++;
			}
			else if (percent > .7)
			{
				distribution[7] += rawValue;
				count[7]++;
			}
			else if (percent > .6)
			{
				distribution[6] += rawValue;
				count[6]++;
			}
			else if (percent > .5)
			{
				distribution[5] += rawValue;
				count[5]++;
			}
			else if (percent > .4)
			{
				distribution[4] += rawValue;
				count[4]++;
			}
			else if (percent > .3)
			{
				distribution[3] += rawValue;
				count[3]++;
			}
			else if (percent > .2)
			{
				distribution[2] += rawValue;
				count[2]++;
			}
			else if (percent > .1)
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
	 * Gets a count of how many grades fall within 10% bounds;
	 * @return A an array of ints 
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
			if (percent > .9)
			{
				count[9]++;
			}
			else if (percent > .8)
			{
				count[8]++;
			}
			else if (percent > .7)
			{
				count[7]++;
			}
			else if (percent > .6)
			{
				count[6]++;
			}
			else if (percent > .5)
			{
				count[5]++;
			}
			else if (percent > .4)
			{
				count[4]++;
			}
			else if (percent > .3)
			{
				count[3]++;
			}
			else if (percent > .2)
			{
				count[2]++;
			}
			else if (percent > .1)
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
		for(int i = 0; i < 10; i++)
		{
			ranges[i] = minValue + shortRange * i;
		}
		return ranges;
	}

	/**
	 * Gets the mean of the dataset.
	 * @return A float indicating the mean.
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
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Gets the median of the dataset.
	 * @return A float indicating the median.
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
			errorLog.add(message);
		}
		return message;
	}

	/**
	 * Gets the mode of the dataset.
	 * @return A  arrayList of floats indicating the mode/s.
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
					if (i < count.size() - 1)
					{
						message += ", ";
					}
				}
			}
		}
		else
		{
			message = "No data to get mode from";
			errorLog.add(message);
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
}
