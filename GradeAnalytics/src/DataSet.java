import java.util.ArrayList;

// Note may need to change from int return type to classes.
public class DataSet {

	private ArrayList<Float> data;
	private float minValue;
	private float maxValue;
	
	// Default constructor
	public DataSet()
	{
		data = new ArrayList<Float>();
		minValue = 0;
		maxValue = 0;
	}
	
	public ArrayList<Float> getData()
	{
		return data;
	}
	
	public int setBoundaries(float min, float max)
	{
		minValue = min;
		maxValue = max;
		return 0;
	}
	
	public int appendSingleValue(float value)
	{
		if (value > minValue && value < maxValue)
		{
			data.add(value);
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public int createDataFromFile(ArrayList<Float> newData)
	{
		if (newData.size() > 0)
		{
			data = newData;
			return 0;
		}
		else
		{
			return 1;
		}
		
	}
	
	public int appendDataFromFile(ArrayList<Float> addedData)
	{
		if (addedData.size() > 0)
		{
			data.addAll(addedData);
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public float getMin()
	{
		float min = 0; // Change later
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
			return -1; // This should be an error to return
		}
		return min;
	}
}
