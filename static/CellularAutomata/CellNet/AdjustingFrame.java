/*
	This 'Frame' adjusts itself around a pre-sized 'Panel',
	which it then places within the frame.
*/

import java.awt.*;

public class AdjustingFrame extends Frame
	{
	private Panel panel;
	private Insets in;
	
	public AdjustingFrame(String title, Panel p)
		{
		super(title);
		panel = p;
		}
	
	public void paint(Graphics g)
		{
		if (in == null)
			{
			in = getInsets();
			setSize(panel.getSize().width + in.left + in.right,
					panel.getSize().height + in.top + in.bottom);
			panel.setVisible(true);
			panel.requestFocus();
			add(panel);
			panel.setLocation(in.left, in.top);
			}
		}
	}
