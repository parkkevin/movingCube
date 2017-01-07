import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


/**
 * This class draws the cube, calculates for closest corner of cube
 * to the spot mouse is being clicked and calculates for new location
 * for cube to move to when mouse is released.
 * @author Kevin Park
 *
 */
public class Cube 
{
	private static int xPoint;
	private static int yPoint;
	private static int sqrSize = 100;
	private static int cubeSideOffset = 30;
	private int first1x;
	private int first1y;
	private int first2x;
	private int first2y;
	private int sec2x;
	private int sec2y;
	private int third1x;
	private int third1y;
	private int third2x;
	private int third2y;
	private int four1x;
	private int four1y;
	private int four2x;
	private int four2y;
	int numOfPoints = 6;
	int closest;
	double[] array = new double[numOfPoints];
	
	private final static int STARPOINTS = 10;
	private final static double FACTOR = 2.63;
	private final static double ALFA = 18*Math.PI/180;
	private final static double BETA = 54*Math.PI/180;
	private double zeroPrmX = 0, zeroPrmY = 0, onePrmX = 0, onePrmY = 0, twoPrmX = 0, twoPrmY = 0;
	private double threePrmX = 0, threePrmY = 0, fourPrmX = 0, fourPrmY = 0, fivePrmX = 0, fivePrmY = 0;
	private double sixPrmX = 0, sixPrmY = 0, eightPrmX = 0, eightPrmY = 0, ninePrmX = 0, ninePrmY = 0;
	private final static double XOFFSET = 0.4;
	private final static double YOFFSET = 0.15;
	private final static double RADIUS = 65;
	private final static double[] X = {0,Math.cos(BETA)/FACTOR, Math.cos(ALFA ),
        Math.cos(ALFA )/FACTOR,	Math.cos(BETA),0,
        -Math.cos(BETA),-Math.cos(ALFA)/FACTOR,
        -Math.cos(ALFA ),-Math.cos(BETA)/FACTOR,0};
	private final static double[] Y = {-1,-Math.sin(BETA)/FACTOR, -Math.sin(ALFA ),
        Math.sin(ALFA )/FACTOR,	Math.sin(BETA),1/FACTOR,
        Math.sin(BETA),Math.sin(ALFA )/FACTOR,
        -Math.sin(ALFA ),-Math.sin(BETA)/FACTOR,-1};
	private int[] xCoordOfStar; // x coordinates of the vertices of the star as pixel
	private int[] yCoordOfStar; // x coordinates of the vertices of the star as pixel	
	
	/**
	 * This constructor sets x and y-coordinates values for all
	 * corners of cube.
	 * @param x
	 * @param y
	 */
	public Cube(double x, double y)
	{
		xPoint = (int)x;
		yPoint = (int)y;
		first1x = xPoint + cubeSideOffset;
		first1y = yPoint - cubeSideOffset;
		first2x = xPoint + (sqrSize+cubeSideOffset);
		first2y = yPoint - cubeSideOffset;
		sec2x = xPoint + sqrSize;
		sec2y = yPoint;
		third1x = xPoint + cubeSideOffset;
		third1y = yPoint + (sqrSize-cubeSideOffset);
		third2x = xPoint + (sqrSize+cubeSideOffset);
		third2y = yPoint + (sqrSize-cubeSideOffset);
		four1x = xPoint;
		four1y = yPoint + sqrSize;
		four2x = xPoint + sqrSize;
		four2y = yPoint + sqrSize; 
		
		xCoordOfStar= new int[X.length];
		yCoordOfStar= new int[Y.length];
        setStarCoordinates(x, y);
        setPrimeCoordinates(x, y);
	}
	
	/**
	 * This method passes x, y values for calculation of closest corner
	 * of cube to mouse clicked location and stores the result to a variable.
	 * @return void
	 * @param x-coordinate of mouse clicked location
	 * @param y-coordinate of mouse clicked location
	 */
	public void getCorner(int x, int y)
	{
		closest = calDist(x, y);
	}
	
	/**
	 * This method resets the x, y-coordinates to a new location cube is to be moved to.
	 * @return void
	 * @param x-coordinate of mouse released location
	 * @param y-coordinate of mouse released location
	 */
	public void moveCube(int x, int y)
	{
		if (closest == 1) 
		{ 
			xPoint = x - cubeSideOffset;
			yPoint = y + cubeSideOffset;
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
		else if (closest == 2) 
		{ 
			xPoint = x - (sqrSize+cubeSideOffset);
			yPoint = y + cubeSideOffset;
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
		else if (closest == 3) 
		{ 
			xPoint = x - (sqrSize+cubeSideOffset);
			yPoint = y - (sqrSize-cubeSideOffset);
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
		else if (closest == 4) 
		{ 
			xPoint = x - sqrSize;
			yPoint = y - sqrSize;
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
		else if (closest == 5) 
		{
			xPoint = x;
			yPoint = y - sqrSize;
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
		else 
		{ 
			xPoint = x; 
			yPoint = y; 
			first1x = xPoint + cubeSideOffset;
			first1y = yPoint - cubeSideOffset;
			first2x = xPoint + (sqrSize+cubeSideOffset);
			first2y = yPoint - cubeSideOffset;
			sec2x = xPoint + sqrSize;
			sec2y = yPoint;
			third1x = xPoint + cubeSideOffset;
			third1y = yPoint + (sqrSize-cubeSideOffset);
			third2x = xPoint + (sqrSize+cubeSideOffset);
			third2y = yPoint + (sqrSize-cubeSideOffset);
			four1x = xPoint;
			four1y = yPoint + sqrSize;
			four2x = xPoint + sqrSize;
			four2y = yPoint + sqrSize;
		}
	}
	
	/**
	 * This method draws the cube, by drawing two squares first and then connecting
	 * corners of square with lines.
	 * @param Graphics2D
	 */
	public void drawCube(Graphics2D g2)
	{
		Rectangle frontSqr = new Rectangle(xPoint, yPoint, sqrSize, sqrSize);
		Rectangle rearSqr = new Rectangle(first1x, first1y, sqrSize, sqrSize);
		Point2D.Double leftUpTop = new Point2D.Double(first1x, first1y);
		Point2D.Double leftUpBottom = new Point2D.Double(xPoint, yPoint);
		Point2D.Double rightUpTop = new Point2D.Double(first2x, first2y);
		Point2D.Double rightUpBottom = new Point2D.Double(sec2x, sec2y);
		Point2D.Double leftBelowTop = new Point2D.Double(third1x, third1y);
		Point2D.Double leftBelowBottom = new Point2D.Double(xPoint, four1y);
		Point2D.Double rightBelowTop = new Point2D.Double(third2x, third2y);
		Point2D.Double rightBelowBottom = new Point2D.Double(four2x, four2y);
		
		Line2D.Double leftTop = new Line2D.Double(leftUpTop, leftUpBottom);
		Line2D.Double rightTop = new Line2D.Double(rightUpTop, rightUpBottom);
		Line2D.Double leftBottom = new Line2D.Double(leftBelowTop, leftBelowBottom);
		Line2D.Double rightBottom = new Line2D.Double(rightBelowTop, rightBelowBottom);
		
		g2.draw(frontSqr);
		g2.draw(rearSqr);
		g2.draw(leftTop);
		g2.draw(rightTop);
		g2.draw(leftBottom);
		g2.draw(rightBottom);
	}
	
	/**
	 * This method stores x, y-coordinates of each corner of cube and stores them
	 * to array. And with values now stored in array it calculates which corner of cube is 
	 * closest to location where mouse is being clicked.
	 * @param x-coordinate of mouse clicked location
	 * @param y-coordinate of mouse clicked location
	 * @return
	 */
	public int calDist(int x, int y)
	{
		array[0] = Math.sqrt((Math.pow((x - xPoint), 2)) + (Math.pow((y - yPoint), 2)));
		array[1] = Math.sqrt((Math.pow((x - first1x), 2)) + (Math.pow((y - first1y), 2)));
		array[2] = Math.sqrt((Math.pow((x - first2x), 2)) + (Math.pow((y - first2y), 2)));
		array[3] = Math.sqrt((Math.pow((x - third2x), 2)) + (Math.pow((y - third2y), 2)));
		array[4] = Math.sqrt((Math.pow((x - four2x), 2)) + (Math.pow((y - four2y), 2)));
		array[5] = Math.sqrt((Math.pow((x - four1x), 2)) + (Math.pow((y - four1y), 2)));
		
		double smallest = array[0];
		int smallestArray = 0;
		
		for(int counter = 1; counter < array.length; counter++)
		{															
			if (array[counter] < smallest)
			{
				smallest = array[counter];
				smallestArray = counter;
			}
		}
		return smallestArray;
	}
	
	public void setStarCoordinates(double x, double y)
	{
		int i; 
		for (i = 0; i < X.length; i++)
		{
			xCoordOfStar[i] = (int)(RADIUS*X[i]+x);
			yCoordOfStar[i] = (int)(RADIUS*Y[i]+y);
		}	
		
		setPrimeCoordinates(x, y);
	}
	
	/**
	 * This method sets the coordinates of the rear side of the star.
	 */
	public void setPrimeCoordinates(double x, double y)
	{
        zeroPrmX = (xCoordOfStar[0] + RADIUS*XOFFSET);
        zeroPrmY = (yCoordOfStar[0] - RADIUS*YOFFSET);
        onePrmX = (xCoordOfStar[1] + RADIUS*XOFFSET);
        onePrmY = (yCoordOfStar[1] - RADIUS*YOFFSET);
        twoPrmX = (xCoordOfStar[2] + RADIUS*XOFFSET);
        twoPrmY = (yCoordOfStar[2] - RADIUS*YOFFSET);
        threePrmX = (xCoordOfStar[3] + RADIUS*XOFFSET);
        threePrmY = (yCoordOfStar[3] - RADIUS*YOFFSET);
        fourPrmX = (xCoordOfStar[4] + RADIUS*XOFFSET);
        fourPrmY = (yCoordOfStar[4] - RADIUS*YOFFSET);
        fivePrmX = (xCoordOfStar[5] + RADIUS*XOFFSET);
        fivePrmY = (yCoordOfStar[5] - RADIUS*YOFFSET);
        sixPrmX = (xCoordOfStar[6] + RADIUS*XOFFSET);
        sixPrmY = (yCoordOfStar[6] - RADIUS*YOFFSET);
        eightPrmX = (xCoordOfStar[8] + RADIUS*XOFFSET);
        eightPrmY = (yCoordOfStar[8] - RADIUS*YOFFSET);
        ninePrmX = (xCoordOfStar[9] + RADIUS*XOFFSET);
        ninePrmY = (yCoordOfStar[9] - RADIUS*YOFFSET);
       
	}
	
	/**
	 * This method draws the star. The Prm(prime) points are the rear side of the star for the 3D drawing effect. 
	 */
	public void drawStar(Graphics2D g2) 
	{
		double mZeroPrm = calcSlope(xCoordOfStar[9], xCoordOfStar[0], yCoordOfStar[9], yCoordOfStar[0]);
		double bZeroPrm = calcB(xCoordOfStar[9], yCoordOfStar[9], mZeroPrm);
		double bEightPrm = calcB(eightPrmX, eightPrmY, 0);
		double mFour = calcSlope(xCoordOfStar[4], xCoordOfStar[5], yCoordOfStar[4], yCoordOfStar[5]);
		double mSixPrm = calcSlope(sixPrmX, fivePrmX, sixPrmY, fivePrmY);
		double bFour = calcB(xCoordOfStar[4], yCoordOfStar[4], mFour);
		double bSixPrm = calcB(sixPrmX, sixPrmY, mSixPrm);
		
		Point2D.Double zeroPrm = new Point2D.Double(zeroPrmX, zeroPrmY);
		Point2D.Double onePrm = new Point2D.Double(onePrmX, onePrmY);
		Point2D.Double twoPrm = new Point2D.Double(twoPrmX, twoPrmY);
		Point2D.Double threePrm = new Point2D.Double(threePrmX, threePrmY);
		Point2D.Double fourPrm = new Point2D.Double(fourPrmX, fourPrmY);
		Point2D.Double sixPrm = new Point2D.Double(sixPrmX, sixPrmY);
		Point2D.Double eightPrm = new Point2D.Double(eightPrmX, eightPrmY);
		Point2D.Double zero = new Point2D.Double(xCoordOfStar[0], yCoordOfStar[0]);
		Point2D.Double one = new Point2D.Double(xCoordOfStar[1], yCoordOfStar[1]);
		Point2D.Double two = new Point2D.Double(xCoordOfStar[2], yCoordOfStar[2]);
		Point2D.Double three = new Point2D.Double(xCoordOfStar[3], yCoordOfStar[3]);
		Point2D.Double four = new Point2D.Double(xCoordOfStar[4], yCoordOfStar[4]);
		Point2D.Double six = new Point2D.Double(xCoordOfStar[6], yCoordOfStar[6]);
		Point2D.Double eight = new Point2D.Double(xCoordOfStar[8], yCoordOfStar[8]);
		Point2D.Double interEightPrmZero = new Point2D.Double(calcX(bZeroPrm, bEightPrm, mZeroPrm, 0), calcY(calcX(bZeroPrm, bEightPrm, mZeroPrm, 0), mZeroPrm, bZeroPrm));
		Point2D.Double interFourSixPrm = new Point2D.Double(calcX(bFour, bSixPrm, mFour, mSixPrm), calcY(calcX(bFour, bSixPrm, mFour, mSixPrm), mFour, bFour));
		
		Line2D.Double zeroToZeroPrm = new Line2D.Double(zero, zeroPrm);
		Line2D.Double zeroPrmToOnerm = new Line2D.Double(zeroPrm, onePrm);
		Line2D.Double oneToOnePrm = new Line2D.Double(one, onePrm);
		Line2D.Double onePrmToTwoPrm = new Line2D.Double(onePrm, twoPrm);
		Line2D.Double twoToTwoPrm = new Line2D.Double(two, twoPrm);
		Line2D.Double twoPrmToThreePrm = new Line2D.Double(twoPrm, threePrm);
		Line2D.Double threePrmToThree = new Line2D.Double(three, threePrm);
		Line2D.Double threePrmToFourPrm = new Line2D.Double(threePrm, fourPrm);
		Line2D.Double fourToFourPrm = new Line2D.Double(four, fourPrm);
		Line2D.Double sixToSixPrm = new Line2D.Double(six, sixPrm);
		Line2D.Double eightToEightPrm = new Line2D.Double(eight, eightPrm);
		Line2D.Double eightPrmToInterEightPrmZero = new Line2D.Double(eightPrm, interEightPrmZero);
		Line2D.Double sixPrmToInterFourSixPrm = new Line2D.Double(sixPrm, interFourSixPrm);
		
		g2.draw(zeroToZeroPrm);
		g2.draw(zeroPrmToOnerm);
		g2.draw(oneToOnePrm);
		g2.draw(onePrmToTwoPrm);
		g2.draw(twoToTwoPrm);
		g2.draw(twoPrmToThreePrm);
		g2.draw(threePrmToThree);
		g2.draw(threePrmToFourPrm);
		g2.draw(threePrmToFourPrm);
		g2.draw(fourToFourPrm);
		g2.draw(sixToSixPrm);
		g2.draw(eightToEightPrm);
		g2.draw(eightPrmToInterEightPrmZero);
		g2.draw(sixPrmToInterFourSixPrm);
		
		Polygon poly = new Polygon(xCoordOfStar, yCoordOfStar, STARPOINTS);
		g2.drawPolygon(poly);
	}
	
	/**
	 * This method calculates the slope of a line.
	 * @param x1: x1 point of a line.
	 * @param x2: x2 point of a line.
	 * @param y1: y1 point of a line.
	 * @param y2: y2 point of a line.
	 * @return
	 */
	public double calcSlope(double x1, double x2, double y1, double y2)
	{
		return (y2-y1) / (x2-x1);
	}
	
	/**
	 * This method calculates the y-intercept, B, in y = mx + B.
	 * @param x: x point of a line.
	 * @param y: y point of a line.
	 * @param m: the slope.
	 * @return
	 */
	public double calcB(double x, double y, double m)
	{
		return y - m*x;
	}
	
	/**
	 * This method calculates the x coordinate of the intersection of two lines.
	 * @param b1: y-intercept of the line 1.
	 * @param b2: y-intercept of the line 2.
	 * @param m1: the slope of the line 1.
	 * @param m2: the slope of the line 2.
	 * @return
	 */
	public double calcX(double b1, double b2, double m1, double m2)
	{
		return (b2-b1) / (m1-m2);
	}
	
	/**
	 * This method calculates the y coordinate of the intersection of two lines.
	 * @param x: x coordinate of the line that y being calculated belongs
	 * @param m: the slope of the line that y being calculated belongs.
	 * @param b: y-intercept of the line that y being calculated belongs.
	 * @return
	 */
	public double calcY(double x, double m, double b)
	{
		return m*x + b;
	}
	
	/**
	 * This method returns the radius of the star.
	 * @return
	 */
	public double getRadius()
	{
		return RADIUS;
	}

}












