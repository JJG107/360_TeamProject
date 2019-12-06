import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

class DataSetTest {

	/**
	 * Tests basic class functionality
	 */
	@Test
	void BasicConstruction() 
	{
		DataSet testSet = new DataSet();
		testSet.setBoundaries(-100, 100);
		String result = testSet.appendSingleValue(0);
		assertEquals(result, "Data added");
	}

	/**
	 * Tests getting the minimum
	 */
	@Test
	void gettingMinimum() 
	{
		DataSet testSet = new DataSet();
		testSet.setBoundaries(-100, 100);
		String result = testSet.appendSingleValue(-1);
		assertEquals(result, "Data added");
		result = testSet.appendSingleValue(1);
		assertEquals(result, "Data added");
		String min = testSet.getMin();
		assertEquals(Float.parseFloat(min), -1);
	}

	/**
	 * Tests getting the maximum
	 */
	@Test
	void gettingMaximum() 
	{
		DataSet testSet = new DataSet();
		testSet.setBoundaries(-100, 100);
		String result = testSet.appendSingleValue(-1);
		assertEquals(result, "Data added");
		result = testSet.appendSingleValue(1);
		assertEquals(result, "Data added");
		String max = testSet.getMax();
		assertEquals(Float.parseFloat(max), 1);
	}

	/**
	 * Tests errors with minimum and maximum
	 */
	@Test
	void gettingMinAndMaxWithNoData() 
	{
		DataSet testSet = new DataSet();
		String max = testSet.getMax();
		String min = testSet.getMin();
		assertEquals(min, "No data in dataset to get min");
		assertEquals(max, "No data in dataset to get max");
		assertEquals(testSet.getErrorLog().size(), 2);
	}

	/**
	 * Tests appending out of the bounds
	 */
	@Test
	void AppendOutOfBounds() 
	{
		DataSet testSet = new DataSet();
		testSet.setBoundaries(-100, 100);
		String result = testSet.appendSingleValue((float)-100.1);
		assertEquals(result, "Appended data not within bounds");
		result = testSet.appendSingleValue((float)100.1);
		assertEquals(result, "Appended data not within bounds");
		assertEquals(testSet.getErrorLog().size(), 2);
	}

	/**
	 * Tests appending data on the edge of the bounds
	 */
	@Test
	void AppendDataInBounds()
	{
		DataSet testSet = new DataSet();
		testSet.setBoundaries(-100, 100);
		String result = testSet.appendSingleValue((float)-100);
		assertEquals(result, "Data added");
		result = testSet.appendSingleValue((float)100);
		assertEquals(result, "Data added");
		assertEquals(testSet.getDataCount(), 2);
	}

	/**
	 * Tests deleting a grade
	 */
	@Test
	void DeletingAGrade()
	{
		DataSet testSet = new DataSet();
		String result = testSet.appendSingleValue((float)0);
		assertEquals(result, "Data added");
		result = testSet.appendSingleValue((float)100);
		assertEquals(result, "Data added");
		assertEquals(testSet.getDataCount(), 2);
		result = testSet.deleteGrade(100);
		assertEquals(result, "Successfully removed");
	}

	/**
	 * Tests deleting a grade when there is no data
	 */
	@Test
	void DeletingNoData()
	{
		DataSet testSet = new DataSet();
		String result = testSet.deleteGrade(-100);
		assertEquals(result, "No data in dataset to delete grade");
	}

	/**
	 * Tests deleting a grade that does not exist
	 */
	@Test
	void DeletingAGradeDoesNotExist()
	{
		DataSet testSet = new DataSet();
		String result = testSet.appendSingleValue((float)100);
		assertEquals(result, "Data added");
		result = testSet.appendSingleValue((float)100);
		assertEquals(result, "Data added");
		assertEquals(testSet.getDataCount(), 2);
		result = testSet.deleteGrade(-100);
		assertEquals(result, "That datapoint does not exist");
		assertEquals(testSet.getDataCount(), 2);
	}

	/**
	 * Tests creating a simple distribution
	 */
	@Test
	void CreateDistribution()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)52);
		assertEquals(testSet.getDataCount(), 2);
		String[] distribution = testSet.createDistribution();
		assertEquals(Float.parseFloat(distribution[5]), 51);
		assertEquals(distribution[8], "N/A");
	}

	/**
	 * Tests getting a simple graph count
	 */
	@Test
	void CreateGraphCount()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)52);
		assertEquals(testSet.getDataCount(), 2);
		int[] count = testSet.getGraphCount();
		assertEquals(count[5], 2);
		assertEquals(count[8], 0);
	}

	/**
	 * Tests getting a simple graph range
	 */
	@Test
	void GetDefaultRanges()
	{
		float start = 0;
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)52);
		assertEquals(testSet.getDataCount(), 2);
		float[] ranges = testSet.getGraphRanges();
		for (int i = 0; i < ranges.length; i++)
		{
			assertEquals(ranges[i], start);
			start += 10;
		}
	}

	/**
	 * Tests getting the mean
	 */
	@Test
	void GetMean()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)45);
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)55);
		testSet.appendSingleValue((float)70);		
		assertEquals(testSet.getMean(), "The mean is: 52.0");
	}

	/**
	 * Tests getting the mean with no data
	 */
	@Test
	void GetMeanNoData()
	{
		DataSet testSet = new DataSet();		
		assertEquals(testSet.getMean(), "No data in dataset to get mean");
	}

	/**
	 * Tests getting the median
	 */
	@Test
	void GetMedian()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)45);
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)55);
		testSet.appendSingleValue((float)70);		
		assertEquals(testSet.getMedian(), "The median is: 50.0");
	}

	/**
	 * Tests getting the median with no data
	 */
	@Test
	void GetMedianNoData()
	{
		DataSet testSet = new DataSet();		
		assertEquals(testSet.getMedian(), "No data in dataset to get median");
	}

	/**
	 * Tests getting the mode
	 */
	@Test
	void GetMode()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)45);
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)55);
		testSet.appendSingleValue((float)70);		
		assertEquals(testSet.getMode(), "The mode(s) are: 40.0");
	}

	/**
	 * Tests getting the mode with multiple modes
	 */
	@Test
	void GetModes()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)40);
		testSet.appendSingleValue((float)45);
		testSet.appendSingleValue((float)50);
		testSet.appendSingleValue((float)55);
		testSet.appendSingleValue((float)55);
		testSet.appendSingleValue((float)70);		
		assertEquals(testSet.getMode(), "The mode(s) are: 40.0, 55.0");
	}

	/**
	 * Tests getting the mode with no data
	 */
	@Test
	void GetModeNoData()
	{
		DataSet testSet = new DataSet();		
		assertEquals(testSet.getMode(), "No data to get mode from");
	}

	// Create Section
	/**
	 * Tests creating a data set from a text file
	 */
	@Test
	void CreateFromTxtFile()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("0\n1\n2\n3\n4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.txt");
		assertEquals(result, "Created Dataset");
		assertEquals(testSet.getDataCount(), 5);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}

	/**
	 * Tests creating a dataset from a csv file
	 */
	@Test
	void CreateFromCsvFile()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.csv");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("0,1,2,3,4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.csv");
		assertEquals(result, "Created Dataset");
		assertEquals(testSet.getDataCount(), 5);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}

	/**
	 * Tests creating a dataset from an invalid file type
	 */
	@Test
	void CreateFromInvalidFileType()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.ppt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("0,1,2,3,4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.ppt");
		assertEquals(result, "File is not of type csv or txt");
		file.delete();
	}

	/**
	 * Tests creating a dataset from a nonexistent file
	 */
	@Test
	void CreateFromNonexistentFile()
	{
		DataSet testSet = new DataSet();
		String result = testSet.createDataFromFile("TestFile.txt");
		assertEquals(result, "File does not exist");
	}

	/**
	 * Tests creating a dataset that is out of bounds
	 */
	@Test
	void CreateOutOfBounds()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("-1\n1\n2\n3\n101");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.txt");
		assertEquals(result, "The following data indexes are not within bounds: 0, 4");
		file.delete();
	}

	/**
	 * Tests creating a dataset from improperly formatted file
	 */
	@Test
	void CreateNonNumbers()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("4\n1\n2\nb\n11");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.txt");
		assertEquals(result, "The file does not contain only numbers");
		file.delete();
	}

	/**
	 * Tests creating data and seeing if pre-existing
	 * data is erased.
	 */
	@Test
	void CreateWithExistingData()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(20);
		File file = new File("TestFile.csv");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("0,1,2,3,4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.csv");
		assertEquals(result, "Created Dataset");
		assertEquals(testSet.getDataCount(), 5);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}
	
	/**
	 * Tests reading in a multi-line csv
	 */
	@Test
	void CreateWithMultlineCsv()
	{
		DataSet testSet = new DataSet();
		File file = new File("TestFile.csv");
		try 
		{
			//Write Content
			file.createNewFile();
			PrintStream writer = new PrintStream(file);
			writer.println("0,1,2,");
			writer.println("3,4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.createDataFromFile("TestFile.csv");
		assertEquals(result, "Created Dataset");
		assertEquals(testSet.getDataCount(), 5);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}
	
	// Append Section
	/**
	 * Tests appending from a txt file
	 */
	@Test
	void AppendFromTxtFile()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("1\n2\n3\n4\n5");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.txt");
		assertEquals(result, "Appended To Dataset");
		assertEquals(testSet.getDataCount(), 6);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}

	/**
	 * Tests appending from a csv file
	 */
	@Test
	void AppendFromCsvFile()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.csv");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("1,2,3,4,5");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.csv");
		assertEquals(result, "Appended To Dataset");
		assertEquals(testSet.getDataCount(), 6);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}
	
	/**
	 * Tests appending from a blank file
	 */
	@Test
	void AppendFromBlankFile()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.csv");
		try 
		{
			//Write Content
			file.createNewFile();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.csv");
		assertEquals(result, "No data in the file to append to dataset");
		assertEquals(testSet.getDataCount(), 1);
		ArrayList<Float> testData = testSet.getData();
		for (int i = 0; i < testData.size(); i++)
		{
			assertEquals(testData.get(i), i);
		}
		file.delete();
	}

	/**
	 * Tests appending from an invalid file type
	 */
	@Test
	void AppendFromInvalidFileType()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.ppt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("0,1,2,3,4");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.ppt");
		assertEquals(result, "File is not of type csv or txt");
		assertEquals(testSet.getDataCount(), 1);
		file.delete();
	}
	
	/**
	 * Tests appending from a nonexistent file
	 */
	@Test
	void AppendFromNonexistentFile()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		String result = testSet.appendDataFromFile("TestFile.txt");
		assertEquals(result, "File does not exist");
		assertEquals(testSet.getDataCount(), 1);
	}
	
	/**
	 * Tests appending from a file that contains data
	 * which is out of bounds
	 */
	@Test
	void AppendOutOfBoundsFromFile()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("-1\n1\n2\n3\n101");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.txt");
		assertEquals(result, "The following data indexes are not within bounds: 0, 4");
		assertEquals(testSet.getDataCount(), 1);
		file.delete();
	}

	/**
	 * Tests appending data which is not float or int
	 * values
	 */
	@Test
	void AppendNonNumbers()
	{
		DataSet testSet = new DataSet();
		testSet.appendSingleValue(0);
		File file = new File("TestFile.txt");
		try 
		{
			//Write Content
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write("4\n1\n2\nb\n11");
			writer.close();
		}
		catch (IOException e) 
		{
			// Do nothing since it's a test
		}
		String result = testSet.appendDataFromFile("TestFile.txt");
		assertEquals(result, "The file does not contain only numbers");
		assertEquals(testSet.getDataCount(), 1);
		file.delete();
	}
}
