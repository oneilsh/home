import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PanelApplication implements WindowListener
	{
	public static final int PANEL_WIDTH = 400;  // <-- put window size here
	public static final int PANEL_HEIGHT = 300;

	private static PanelApplication panelApplication;
	private Panel panel;
	private Frame frame;

	public static void main(String args[])
		{
		panelApplication = new PanelApplication();
		}

	private PanelApplication()
		{
		panel = new MyPanel();  // <-- your program name here
		panel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		panel.setLocation(0, 0);
		panel.setVisible(true);
		panel.requestFocus();

		frame = new AdjustingFrame("My Program", panel);
		frame.addWindowListener(this);
		frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		frame.setLocation(20, 20);
		frame.setVisible(true);
		frame.setLayout(null);
		if (panel instanceof KeyListener)
			frame.addKeyListener((KeyListener)panel);
		}

	public void windowActivated	(WindowEvent e) {}
	public void windowClosed	(WindowEvent e) {}
	public void windowClosing	(WindowEvent e)
		{
		frame.dispose();
		System.exit(0);
		}
	public void windowDeactivated	(WindowEvent e) {}
	public void windowDeiconified	(WindowEvent e) {}
	public void windowIconified	(WindowEvent e) {}
	public void windowOpened	(WindowEvent e) {}
	}