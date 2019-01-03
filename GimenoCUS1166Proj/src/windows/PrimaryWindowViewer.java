package windows;

import javax.swing.JFrame;

public class PrimaryWindowViewer 
{
	public PrimaryWindowViewer(int userID, int usertype)
	{
	      JFrame frame = new PrimaryWindow(userID, usertype);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	}
}