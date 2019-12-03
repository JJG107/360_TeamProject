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
	
}
