import java.lang.Object;
import java.awt.*;
import javax.swing.*;

/**
 * Contains a variety of utility functions.
 * @author Michael Cai
 *
 */
	
//public static class Drawing extends Canvas {
//	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("bars");
//		
//		Canvas canvas = new Drawing();
//		canvas.setSize(400, 400);
//		frame.add(canvas);
//		frame.pack();
//		frame.setVisible(true);
//	}
//	
//	public void paint(Graphics g) {
//		g.create(getX(), getY(), getWidth(), getHeight());
//	}
//}
//}

// public class JXCanvas extends Canvas {
// 	int[] nums;
// 	String[] cat;
// 	String t;
// 	int max = 0;
// 	int width = 0;
// 	int height = 0;

// public JXCanvas(int[] x, String[] y, String a) {
//     for(int i = 0; i < x.length; i++){
//         if(x[i] > max)
//             max = x[i];
//     }
    
//     nums = x;
//     cat = y;
//     t = a;
//     width = nums.length*100 + nums.length*10;
//     height = max*10 + 500;
//     setSize(width,height);
// }

// public void paint(Graphics g) {
//     Font title = new Font("TimesNewRoman", Font.BOLD,20);
//     FontMetrics fontM = g.getFontMetrics(title);
//     g.setFont(title);
    
//     g.drawString(t, width/2, 20);
    
//     Font label = new Font("TimesNewRoman", Font.BOLD, 15);
//     FontMetrics fontM1 = g.getFontMetrics(label);
//     g.setFont(label);
    
//     for(int j = 0; j < nums.length; j++){
//         g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
//         g.drawString(cat[j], (nums.length*100)-((nums.length-j)*100) + 22, height - 20);
//         g.fillRect(j*100, height - 400, 100, nums[j]*15);
//     }
// }

// public static class Drawing extends Canvas {
	
// 	public static void main(String[] args) {
// 		JFrame frame = new JFrame("bars");
		
// 		Canvas canvas = new Drawing();
// 		canvas.setSize(400, 400);
// 		frame.add(canvas);
// 		frame.pack();
// 		frame.setVisible(true);
// 	}
	
// 	public void paint(Graphics g) {
// 		g.create(getX(), getY(), getWidth(), getHeight());
// 	}


// }



}




public class UtilityFunctions {
	
	int position: int increment; int right; int left; int bottom; int top;
	int min;
	int padding = 4;
	int[] nums;
	String[] cat;
	String t;
	int max = 0;
	int width = 0;
	int height = 0;
	int titleHeight; String title;
	FontMetrics fm;
	
	

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
	
	//graph stuff
	
	public void JXCanvas(int[] x, String[] y, String a) {
		
		this.title = title;
		
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
	
	private void setSize(int width2, int height2) {
		// TODO Auto-generated method stub
	}

	//paints the bar graph
	public void paint(Graphics g) {
		fm = getFontMetrics(getFont());
		titleHeight = fm.getHeight();
		g.drawString(title, (size().width - fm.stringWidth(title))/2, top);
		
		//draws the min and max values
		g.drawString(new Integer(min).toString(), padding, bottom);
	    g.drawString(new Integer(max).toString(), padding, top + titleHeight);
	}
	
	public void clearGraph() {
	}
	
	void bars(Graphics g, double x1, double y1, double x2, double y2) {
        // Draw a bar from the point (x1,y1) to (x2,y2).
        
     int a1, b1;   // pixels
     int a2, b2;   // pixels
     
     int width = getSize().width;     // Width of the canvas.
     int height = getSize().height;   // Height of the canvas. 
     
     a1 = (int)( (x1 + 5) / 10 * width );
     b1 = (int)( (5 - y1) / 10 * height );
     a2 = (int)( (x2 + 5) / 10 * width );
     b2 = (int)( (5 - y2) / 10 * height );
     
     g.drawLine(a1,b1,a2,b2);
     
  }
	
	
	
}
