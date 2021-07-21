// Shawn O'Neil
// Assignment 3d - Multiple Digit RPN Calculator

import java.awt.*;
import java.awt.event.*;
public class MyPanel extends Panel
{
	private TextField infix, x, postfix, value;
	private Label infixlabel, xlabel, postfixlabel, valuelabel;
	private Manager output;
	
	public MyPanel()
	{
	setLayout(null);
		
	infixlabel = new Label("InFix:");
	infixlabel.setLocation(10,10);
	infixlabel.setSize(50, 25);
	add(infixlabel);
	
	infix = new TextField();
	infix.setLocation(10, 40);
	infix.setSize(200, 25);
	add(infix);	
	
	xlabel = new Label("x:");
	xlabel.setLocation(10,80);
	xlabel.setSize(50, 25);
	add(xlabel);
	
	x = new TextField();
	x.setLocation(10, 110);
	x.setSize(200, 25);
	add(x);
		
	postfixlabel = new Label("PostFix:");
	postfixlabel.setLocation(10,150);
	postfixlabel.setSize(50, 25);
	add(postfixlabel);
	
	postfix = new TextField();
	postfix.setLocation(10, 180);
	postfix.setSize(200, 25);
	add(postfix);
		
	valuelabel = new Label("Value:");
	valuelabel.setLocation(10, 220);
	valuelabel.setSize(50,25);
	add(valuelabel);
	
	value = new TextField();
	value.setLocation(10, 250);
	value.setSize(200, 25);
	add(value);
	
	output = new Manager(infix, x, postfix, value);
	
	infix.addActionListener(output);
	x.addActionListener(output);

	}
}