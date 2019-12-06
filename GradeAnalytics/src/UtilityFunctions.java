import java.lang.Object;
import java.awt.*;
import javax.swing.*;

/**
 * Contains a variety of utility functions.
 * @author Michael Cai
 *
 */

public class JXCanvas extends Canvas {
	int[] nums;
	String[] cat;
	String t;
	int max = 0;
	int width = 0;
	int height = 0;

public JXCanvas(int[] x, String[] y, String a) {
    for(int i = 0; i < x.length; i++){
        if(x[i] > max)
            max = x[i];
    }
    
    nums = x;
    cat = y;
    t = a;
    width = nums.length*100 + nums.length*10;
    height = max*10 + 500;
    setSize(width,height);
}

public void paint(Graphics g) {
    Font title = new Font("TimesNewRoman", Font.BOLD,20);
    FontMetrics fontM = g.getFontMetrics(title);
    g.setFont(title);
    
    g.drawString(t, width/2, 20);
    
    Font label = new Font("TimesNewRoman", Font.BOLD, 15);
    FontMetrics fontM1 = g.getFontMetrics(label);
    g.setFont(label);
    
    for(int j = 0; j < nums.length; j++){
        g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
        g.drawString(cat[j], (nums.length*100)-((nums.length-j)*100) + 22, height - 20);
        g.fillRect(j*100, height - 400, 100, nums[j]*15);
    }
}

public static class Drawing extends Canvas {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("bars");
		
		Canvas canvas = new Drawing();
		canvas.setSize(400, 400);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.create(getX(), getY(), getWidth(), getHeight());
	}


}



}




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
}
