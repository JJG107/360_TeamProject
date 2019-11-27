import java.util.ArrayList;


public class DataSet {

	private ArrayList<Float> data;
	private float minValue;
	private float maxValue;
	private String errorLog;
	
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
	public int appendSingleValue(float value)
	{
		int successCode = 0;
		if (value > minValue && value < maxValue)
		{
			data.add(value);
			successCode = 1;
		}
		else
		{
			successCode = -1;
		}
		return successCode;
	}
	
	/**
	 * Creates a new dataset with the passed value.
	 * @param newData The data to create a new dataset from.
	 * @return An int indicating the success of the function.
	 */
	public int createDataFromFile(ArrayList<Float> newData)
	{
		int successCode = 0;
		if (newData.size() > 0)
		{
			data = newData;
			successCode = 1;
		}
		else
		{
			successCode = -1;
		}
		return successCode;
		
	}
	
	/**
	 * Appends data to the current dataset from an ArrayList.
	 * @param addedData The ArrayList to add to the dataset.
	 * @return An int that indicates the success of the function.
	 */
	public int appendDataFromFile(ArrayList<Float> addedData)
	{
		int successCode = 0;
		if (addedData.size() > 0)
		{
			data.addAll(addedData);
			successCode = 1;
		}
		else
		{
			successCode = -1;
		}
		return successCode;
	}
	
	/**
	 * Retrieves the minimum grade in the dataset.
	 * @return A float which indicates the minimum grade.
	 * If the value returned is below the minValue then there is no data.
	 */
	public float getMin()
	{
		float min; // Change later
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
		}
		else
		{
			min = minValue - 1; // Change later?
		}
		return min;
	}
	
	/**
	 * Retrieves the maximum grade in the dataset.
	 * @return A float which indicates the maximum grade.
	 * If the value returned is above the maxValue then there is no data.
	 */
	public float getMax()
	{
		float max; // Change later
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
		}
		else
		{
			max = maxValue + 1; // Change later?
		}
		return max;
	}
	
	/**
	 * Deletes the first instance of the entered grade.
	 * @param gradeToDelete The grade to delete.
	 * @return An int indicating the success of the function.
	 */
	public int deleteGrade(float gradeToDelete)
	{
		int successCode = 0;
		if (data.size() > 0)
		{
			for (int i = 0; i < data.size(); i++)
			{
				if (data.get(i) == gradeToDelete)
				{
					data.remove(i);
					successCode = 1;
				}
			}
		}
		else
		{
			successCode = -1;
		}
		return successCode;
	}
	
	/**
	 * Creates a distribution of the grades indicating the
	 * average grade for every 10% bracket.
	 * @return An array of floats indicating the average grade for
	 * each bracket.
	 */
	public float[] createDistribution()
	{
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
			distribution[i] = distribution[i] / count[i];
		}
		
		return distribution;
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
	public float getMean()
	{
		float total = 0;
		for (int i = 0; i < data.size(); i++)
		{
			total += data.get(i);
		}
		total = total / data.size();
		return total;
	}
	
	/**
	 * Gets the median of the dataset.
	 * @return A float indicating the median.
	 */
	public float getMedian()
	{
		float median;
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
		return median;
	}
	
	/**
	 * Gets the mode of the dataset.
	 * @return A an arrayList of floats indicating the mode/s.
	 */
	public ArrayList<Float> getMode()
	{
		data.sort(null);
		ArrayList<Float> modes = new ArrayList<Float>();
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
		for (int i = 0; i < count.size(); i++)
		{
			if (count.get(i) == maxCount)
			{
				modes.add(values.get(i));
			}
		}
		
		return modes;
	}
}
