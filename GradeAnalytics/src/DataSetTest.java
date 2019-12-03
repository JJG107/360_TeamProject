import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
	
}
