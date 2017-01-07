import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;




/**
 * This class is the frame the component that has buttons and cube is put on.
 * It also detects button clicks, mouse clicks, and has a timer for bouncing cube.
 * @author Kevin
 *
 */
public class FrameCubeViewer 
{
	final static int FRAME_WIDTH = 600;	
	final static int FRAME_HEIGHT = 700;
	final static JButton jumpBtn = new JButton("JUMP");	
	final static JButton glideBtn = new JButton("GLIDE");		
	final static JButton slowerBtn = new JButton("SLOWER");
	final static JButton fasterBtn = new JButton("FASTER");
	final static JButton transformBtn = new JButton("TRANSFORM");
	final static int STARTX = 200;
	final static int STARTY = 50;
	private static CubeComponent component;
	private static boolean isStar = false;

	
	/**
	 * This is the main method that creates frame and detects events.
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();	
		component = new CubeComponent(STARTX, STARTY, isStar);
			

		/**
		 * This is an inner class that bounces cube at every defined 
		 * time interval.
		 * @author Kevin Park
		 *
		 */
		class ClockListener implements ActionListener
	    {
			/**
			 * This method overrides actionPerformed method in ActionListener class.
			 * @return void
			 */
			public void actionPerformed(ActionEvent event) 
			{
				component.bounce();
			}
	     }
		
	    ActionListener listener = new ClockListener();	
	    final int MILLISECS = 5;	
	    final Timer timer = new Timer (MILLISECS, listener);	
	
	    /**
	     * This is an inner class that detects mouse clicks for translating cube.
	     * It has two methods, one for mouse being pressed and the other for
	     * when mouse is released.
	     * @author Kevin Park
	     *
	     */
		class MousePressListener implements MouseListener
		{
			/**
			 * This method overrides mousePressed method in MouseListener class.
			 * @return void
			 */
			public void mousePressed(MouseEvent event)	
			{
                if (jumpBtn.isSelected())	
                {
					int xClicked = event.getX();
					int yClicked = event.getY();	
					component.getCorner(xClicked, yClicked);	
                }											
			}
			
			/**
			 * This method overrides mouseReleased method in MouseListener class.
			 * @return void
			 */
			public void mouseReleased(MouseEvent event)	
			{
                if (jumpBtn.isSelected())	
                {
					int xReleased = event.getX();
					int yReleased = event.getY();
					component.setXY(xReleased, yReleased);
					component.moveCube(xReleased, yReleased);	
																
                }												
			}
			
			public void mouseClicked(MouseEvent event) {}	
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		}
		
		/**
		 * This in an inner class that checks mode selection and 
		 * stops timer accordingly.
		 * @author Kevin Park
		 *
		 */
		class StopBouncingListener implements ActionListener
		{
			/**
			 * This method overrides actionPerformed method in ActionListener class.
			 * @return void
			 */
			public void actionPerformed(ActionEvent e)	
			{											
				jumpBtn.setSelected(true);
				glideBtn.setSelected(false);
				timer.stop();
			}
		}
		
		/**
		 * This is an inner class that checks mode selection and 
		 * restarts timer accordingly (Timer starts automatically by default when
		 * program begins). 
		 * @author Kevin Park
		 *
		 */
		class StartBouncingListener implements ActionListener
		{
			/**
			 * This method overrides actionPerformed method in ActinoListener class.
			 * @return void
			 */
			public void actionPerformed(ActionEvent e)	
			{											
				jumpBtn.setSelected(false);
				glideBtn.setSelected(true);
				timer.start();
			}
		}
		
		class SlowerButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				double fasterVar = 2.0;
				component.setaccelXY(fasterVar, fasterVar);
			}
		}
		
		class FasterButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
					double slowerVar = 0.5;
					component.setaccelXY(slowerVar, slowerVar);
			}
		}
		
		class TransformListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isStar == false)
				{
					isStar = true;
					component.setIsStar(isStar);
				}
				else
				{
					isStar = false;
					component.setIsStar(isStar);
				}
			}
		}
		
		jumpBtn.addActionListener(new StopBouncingListener());	
		glideBtn.addActionListener(new StartBouncingListener());
		fasterBtn.addActionListener(new SlowerButtonListener());
		slowerBtn.addActionListener(new FasterButtonListener());
		transformBtn.addActionListener(new TransformListener());
		component.addMouseListener(new MousePressListener());	
		
		JPanel panel = new JPanel();	//Create panel to put a panel containing buttons and JComponent onto.
		JPanel modePanel = new JPanel();	//Create panel to put buttons onto.
		panel.setLayout(new BorderLayout());	//Use BorderLayout.
		
		modePanel.add(jumpBtn);	
		modePanel.add(glideBtn);
		modePanel.add(slowerBtn);
		modePanel.add(fasterBtn);
		modePanel.add(transformBtn);
		panel.add(modePanel, BorderLayout.NORTH);		
		panel.add(component, BorderLayout.CENTER);		

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Cube");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);		
		frame.add(panel);	
		frame.setVisible(true);
	}
	

}









