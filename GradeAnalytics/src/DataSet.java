import java.util.ArrayList;


public class DataSet {

	private ArrayList<Float> data;
	private float minValue;
	private float maxValue;
	
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
			return -1;
		}
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
}
