import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;



/**
 * This class extends JComponent and draws cube. It also has methods
 * that passes new x and y-coordinates values from FrameCubeViewer class to 
 * Cube class, as well as a method that increments or decrements x and y-coordinates
 * values for bouncing cube mode.
 * @author Kevin Park
 *
 */
public class CubeComponent extends JComponent
{
	private static int width;
	private static int height;
	private Cube cube;
	private double x = 0.0;
	private double y = 0.0;
	private double accelX = 2;
	private double accelY = 2;
	private final int CXOFFSETR = 130;
	private final int CXOFFSETL = 0;
	private final int CYOFFSETB = 100;
	private final int YOFFSETT = 30;
	private final static double SXOFFSET = 0.4;
	private final static double SYOFFSET = 0.15;
	private boolean isStar = false;
	private final double STARYOFFSETT = 9.73;
	private final double STARXOFFSETR = 24;
	private final double STARYOFFSETB = 12;
	private final double STARXOFFSETL = 2;
	
	
	/**
	 * This constructor sets width and height of frame to zero and 
	 * passes new x and y-coordinates values to Cube class.
	 */
	public CubeComponent(int x, int y, boolean isStar)		
	{				
		this.x = (double)x;
		this.y = (double)y;
		this.isStar = isStar;
		CubeComponent.width = 0;
		CubeComponent.height = 0;
		cube = new Cube(this.x, this.y);
	}

	/**
	 * This method takes the current x, y positions and reassigns x, y positions if they're at
	 * risk of infinite loops. If x, y positions are subject to infinite loops, they're reassigned
	 * to the edge of the frame.  
	 */
	public void setXY(int x, int y)
	{	
		if (isStar == false)
		{
			if (x > (width - CXOFFSETR))
				this.x = width - CXOFFSETR;
			else
				this.x = x;
			
			if (y > (height - CYOFFSETB))
				this.y = height - CYOFFSETB;
			else if ( y < YOFFSETT)
				this.y = YOFFSETT;
			else
				this.y = y;
		}
		else
		{
			if (x < (cube.getRadius()-STARXOFFSETL))
				this.x = cube.getRadius()-STARXOFFSETL;
			else if (x > (width - (cube.getRadius()+STARXOFFSETR)))
				this.x = width - (cube.getRadius()+(STARXOFFSETR+2));
			else
				this.x = x;
			
			if (y < (cube.getRadius()+STARYOFFSETT))
				this.y = cube.getRadius()+(STARYOFFSETT+2);
			else if (y > (height - (cube.getRadius()-STARYOFFSETB)))
				this.y = height - (cube.getRadius()-(STARYOFFSETB+3));
			else
				this.y = y;
		}
		
		cube.setStarCoordinates((double)this.x, (double)this.y);
	}
	
	/**
	 * This method changes the speed of the cube or the star as requested. If sets the
	 * maximum and minimum speed. 
	 */
	public void setaccelXY(double accelX, double accelY)
	{
		int maxBound = 64;
		double minBound = 0.015625;
		double spdUp = 2.0;
		double spdDown = 0.5;
		
		if (Math.abs(this.accelX) < maxBound && Math.abs(this.accelY) < maxBound && Math.abs(this.accelX) > minBound && Math.abs(accelY) > minBound)
		{
			this.accelX = this.accelX * accelX;
			this.accelY = this.accelY * accelY;
		}

		else if ((Math.abs(this.accelX) >= maxBound && Math.abs(this.accelY) >= maxBound) && (accelX == spdDown && accelY == spdDown))
		{
			this.accelX = this.accelX * accelX;
			this.accelY = this.accelY * accelY;
		}

		else if ((Math.abs(this.accelX) <= minBound && Math.abs(this.accelY) <= minBound) && (accelX == spdUp && accelY == spdUp))
		{
			this.accelX = this.accelX * accelX;
			this.accelY = this.accelY * accelY;
		}
	}
	
	/**
	 * This method is called by the system automatically, it calls the draw methods in Cube.java.
	 * @return void
	 * @param Graphics2D
	 */
	public void paintComponent(Graphics g)
	{
	
		CubeComponent.width = getWidth();	
		CubeComponent.height = getHeight();
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (isStar == false)
			cube.drawCube(g2);
		else
			cube.drawStar(g2);
	}
	
	/**
	 * This method passes x and y-coordinates when mouse is pressed.
	 * @return void
	 * @param x coordinate when mouse is clicked
	 * @param y coordinate when mouse is clicked
	 */
	public void getCorner(int xClicked, int yClicked)
	{
		cube.getCorner(xClicked, yClicked);	
	}
	
	/**
	 * This method passes x and y-coordinates when mouse is released
	 * and repaints cube.
	 * @return void
	 * @param x coordinate when mouse is released
	 * @param y coordinate when mouse is released
	 */
	public void moveCube(int xReleased, int yReleased)
	{
		cube.moveCube(xReleased, yReleased);
		repaint();
	}
	
	/**
	 * This method changes x, y coordinates for the cube or the star when
	 * bouncing off the wall and repaints cube.
	 * @return void
	 */
	public void bounce()
	{
		if (isStar == false)
		{
			if (x < CXOFFSETL || x > (width - CXOFFSETR))	
				accelX = -accelX;							
			if (y < YOFFSETT || y > (height - CYOFFSETB))	
				accelY = -accelY;						
			x += accelX;		//Increment x values
			y += accelY;		//Increment y values.
			cube = new Cube(x, y);
			repaint();
		}
		else
		{
			if (x < (cube.getRadius()-STARXOFFSETL) || x > (width - (cube.getRadius()+STARXOFFSETR)))
				accelX = -accelX;
			if (y < (cube.getRadius()+STARYOFFSETT) || y > (height - (cube.getRadius()-STARYOFFSETB)))
				accelY = -accelY;
			x += accelX;
			y += accelY;
			cube = new Cube(x, y);
			repaint();
		}
	}
	
	/**
	 * This method sets the current status of the object, the cube or the star.
	 */
	public void setIsStar(boolean isStar)
	{
		this.isStar = isStar;
		setXY((int)x, (int)y);
		repaint();
	}
	
}
