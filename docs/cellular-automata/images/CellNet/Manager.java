//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Manager extends EventPanel implements MouseListener, ActionListener
	{
	private Board board1;
	private BoardCanvas bcanvas;
	private Button change, iterate, act1, act2, random, clear, fill, torus, CLR, openBoard, saveBoard;
	private TextField genField, randField;
	private Label changeLabel, genchangeLabel, genLabel, fillLabel, topolLabel;
	public int selection, fillselection;

	public Manager(Board theboard, Button thechange, Button theiterate, Label thelabel, BoardCanvas thecanvas, Button theact1, Button theact2, Button therandom, Button theclear, Button thefill, Label thefillLabel, Button thetorus, Label thetopolLabel, TextField therandField, Button theCLR, Button theopenBoard, Button thesaveBoard)
		{
		selection = 3;
		fillselection = 12;
		board1 = theboard;
		bcanvas = thecanvas;
		change = thechange;
		iterate = theiterate;
		act1 = theact1;
		act2 = theact2;
		CLR = theCLR;
		openBoard = theopenBoard;
		saveBoard = thesaveBoard;
		random = therandom;
		clear = theclear;
		fill = thefill;
		changeLabel = thelabel;
		fillLabel = thefillLabel;
		torus = thetorus;
		topolLabel = thetopolLabel;
		randField = therandField;
		}

		public void mouseEntered(MouseEvent e)
			{
			}

		public void mouseExited(MouseEvent e)
			{
			}

		public void mouseDragged(MouseEvent e)
			{
			}

		public void mouseClicked(MouseEvent e)
			{
			}

		public void mousePressed(MouseEvent e)
			{
			int xCoord = e.getX();
			int yCoord = e.getY();
			//System.out.println("Mouse Pressed");
			if (10 < xCoord && xCoord < 610 && 10 < yCoord && yCoord < 610)
				{
				int x = (int)((xCoord-10)/6);
				int y = (int)((yCoord-10)/6);
			//	System.out.println("Within Box: " +xCoord+","+yCoord+","+x+","+y+" , " +manager.selection);
				if(selection == 1)
					board1.addBlank(x,y);
			//	else if(selection == 2)
			//		board1.addExpected(x,y);
			//	else if(selection == 3)
			//		board1.addInput(x,y);
			//	else if(selection == 4)
			//		board1.addNueron(x,y);
			//	else if(selection == 5)
			//		board1.addOutput(x,y);
				else if(selection == 2)
					board1.addLifeCell(x,y);
				else if(selection == 3)
					board1.stateChange(x,y);
				else if(selection == 4)
					board1.addBrainCell(x,y);
				}
			bcanvas.repaint();
		}



	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == change)
			{
			if(selection < 4)
				selection++;
			else selection = 1;

			if(selection == 1)
				changeLabel.setText("Blank Cell");
		//	else if(selection == 2)
		//		changeLabel.setText("Expected");
		//	else if(selection == 3)
		//		changeLabel.setText("Input Cell");
		//	else if(selection == 4)
		//		changeLabel.setText("Nueron");
		//	else if(selection == 5)
		//		changeLabel.setText("Output Cell");
			else if(selection == 2)
				changeLabel.setText("Life Cell");
			else if(selection == 3)
				changeLabel.setText("State Change");
			else if(selection == 4)
				changeLabel.setText("Brain Cell");
			}

	//	else if(e.getSource() == iterate)
	//		{
	//		board1.computeNext();
	//		board1.updateNow();
	//		bcanvas.repaint();
	//		}

		else if(e.getSource() == act1)
			{
			board1.act1();
			bcanvas.repaint();
			}

		else if(e.getSource() == act2)
			{
			board1.act2();
			bcanvas.repaint();
			}
			
		else if(e.getSource() == CLR)
			{
			board1.closeFile();
			}
						
		else if(e.getSource() == openBoard)
			{
			board1.openBoard();
			}	
					
		else if(e.getSource() == saveBoard)
			{
			board1.saveBoard();
			}

		else if(e.getSource() == random)
			{
			board1.randomizeLowHigh(convert(randField.getText()));
			bcanvas.repaint();
			}

		else if(e.getSource() == clear)
			{
			board1.clear();
			bcanvas.repaint();
			}

		else if(e.getSource() == torus)
			{
			board1.switchtorus();
			if(topolLabel.getText() == "Torus")
				topolLabel.setText("Plane");
			else if(topolLabel.getText() == "Plane")
				topolLabel.setText("Torus");
			}

		else if(e.getSource() == fill)
			{
			if(fillselection == 13)
				{
				fillselection = 0;
				}

			if(fillselection == 0)
				{
				board1.initLife();
				fillLabel.setText("Life");
				}
			else if(fillselection == 1)
				{
				board1.initLife2();
				fillLabel.setText("Life2");
				}
			else if(fillselection == 2)
				{
				board1.initRace();
				fillLabel.setText("Race");
				}
			else if(fillselection == 3)
				{
				board1.initRace2();
				fillLabel.setText("Race2");
				}
			else if(fillselection == 4)
				{
				board1.initRace3();
				fillLabel.setText("Race3");
				}
			else if(fillselection == 5)
				{
				board1.initRace4();
				fillLabel.setText("Race4");
				}
			else if(fillselection == 6)
				{
				board1.initRand();
				fillLabel.setText("Rand");
				}
			else if(fillselection == 7)
				{
				board1.initRand2();
				fillLabel.setText("Rand2");
				}
			else if(fillselection == 8)
				{
				board1.initRand3();
				fillLabel.setText("Rand3");
				}
			else if(fillselection == 9)
				{
				board1.initRand4();
				fillLabel.setText("Rand4");
				}
			else if(fillselection == 10)
				{
				board1.initBrain();
				fillLabel.setText("Brain");
				}
			else if(fillselection == 11)
				{
				board1.initAge();
				fillLabel.setText("Age");
				}
			else if(fillselection == 12)
				{
				board1.initFiveRace();
				fillLabel.setText("5Race");
				}
			fillselection++;
			bcanvas.repaint();
			}

		}

	public void step()
		{
		board1.computeNext();
		board1.updateNow();
		}

	private int convert(String s)
		{
		try
			{
			return (new Integer(s)).intValue();
			}
		catch (Throwable t)
			{
			return -9999;
			}
		}
	}
