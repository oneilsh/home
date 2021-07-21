//Shawn O'Neil
//Do what you want with my code, as long as my name stays on it.
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends EventPanel implements ActionListener
{

	private Tree tree;
	private Label buttonLabel;
	private Button ang1Label, ang2Label, ang3Label, ang4Label, len1Label, len2Label, len3Label, len4Label;
	private TextField ang1Field, ang2Field, ang3Field, ang4Field, len1Field, len2Field, len3Field, len4Field, changeField;
	private Button oneb, twob, threeb, fourb;
	private double change;
	private boolean sel1, sel2, sel3, sel4, sel5, sel6, sel7, sel8;



	public MyPanel()
		{
		setLayout(null);
		tree = new Tree(-45, 80, 45, 45, .7, .7, .7, .7);
		change = 5;
		sel1 = sel2 = sel3 = sel4 = sel5 = sel6 = sel7 = sel8 = false;

		buttonLabel = new Label("Numb. of Branches");
		buttonLabel.setLocation(700, 50);
		buttonLabel.setSize(100, 25);
		add(buttonLabel);


		oneb = new Button("1");
		oneb.setLocation(700, 80);
		oneb.setSize(45, 25);
		add(oneb);

		twob = new Button("2");
		twob.setLocation(750, 80);
		twob.setSize(45, 25);
		add(twob);

		threeb = new Button("3");
		threeb.setLocation(700, 110);
		threeb.setSize(45, 25);
		add(threeb);

		fourb = new Button("4");
		fourb.setLocation(750, 110);
		fourb.setSize(45, 25);
		add(fourb);

		changeField = new TextField("5");
		changeField.setLocation(700, 170);
		changeField.setSize(80, 25);
		add(changeField);

		ang1Label = new Button("Ang 1:");
		ang1Label.setLocation(700, 200);
		ang1Label.setSize(45, 25);
		add(ang1Label);

		ang1Field = new TextField("-45");
		ang1Field.setLocation(750, 200);
		ang1Field.setSize(40, 25);
		add(ang1Field);

		ang2Label = new Button("Ang 2:");
		ang2Label.setLocation(700, 230);
		ang2Label.setSize(45, 25);
		add(ang2Label);

		ang2Field = new TextField("80");
		ang2Field.setLocation(750, 230);
		ang2Field.setSize(40, 25);
		add(ang2Field);

		ang3Label = new Button("Ang 3:");
		ang3Label.setLocation(700, 260);
		ang3Label.setSize(45, 25);
		add(ang3Label);

		ang3Field = new TextField("45");
		ang3Field.setLocation(750, 260);
		ang3Field.setSize(40, 25);
		add(ang3Field);

		ang4Label = new Button("Ang 4:");
		ang4Label.setLocation(700, 290);
		ang4Label.setSize(45, 25);
		add(ang4Label);

		ang4Field = new TextField("45");
		ang4Field.setLocation(750, 290);
		ang4Field.setSize(40, 25);
		add(ang4Field);


		len1Label = new Button("Len 1:");
		len1Label.setLocation(700, 340);
		len1Label.setSize(45, 25);
		add(len1Label);

		len1Field = new TextField(".7");
		len1Field.setLocation(750, 340);
		len1Field.setSize(40, 25);
		add(len1Field);

		len2Label = new Button("Len 2:");
		len2Label.setLocation(700, 370);
		len2Label.setSize(45, 25);
		add(len2Label);

		len2Field = new TextField(".7");
		len2Field.setLocation(750, 370);
		len2Field.setSize(40, 25);
		add(len2Field);

		len3Label = new Button("Len 3:");
		len3Label.setLocation(700, 400);
		len3Label.setSize(45, 25);
		add(len3Label);

		len3Field = new TextField(".7");
		len3Field.setLocation(750, 400);
		len3Field.setSize(40, 25);
		add(len3Field);

		len4Label = new Button("Len 4:");
		len4Label.setLocation(700, 430);
		len4Label.setSize(45, 25);
		add(len4Label);

		len4Field = new TextField(".7");
		len4Field.setLocation(750, 430);
		len4Field.setSize(40, 25);
		add(len4Field);



		oneb.addActionListener(this);
		twob.addActionListener(this);
		threeb.addActionListener(this);
		fourb.addActionListener(this);
		ang1Field.addActionListener(this);
		ang2Field.addActionListener(this);
		ang3Field.addActionListener(this);
		ang4Field.addActionListener(this);
		len1Field.addActionListener(this);
		len2Field.addActionListener(this);
		len3Field.addActionListener(this);
		len4Field.addActionListener(this);
		ang1Label.addActionListener(this);
		ang2Label.addActionListener(this);
		ang3Label.addActionListener(this);
		ang4Label.addActionListener(this);
		len1Label.addActionListener(this);
		len2Label.addActionListener(this);
		len3Label.addActionListener(this);
		len4Label.addActionListener(this);
		changeField.addActionListener(this);
		}


	public void actionPerformed(ActionEvent e)
		{
		if(e.getSource() == oneb)
			{
			tree.setBranches(1);
			}
		if(e.getSource() == twob)
			{
			tree.setBranches(2);
			}
		if(e.getSource() == threeb)
			{
			tree.setBranches(3);
			}
		if(e.getSource() == fourb)
			{
			tree.setBranches(4);
			}
		if(e.getSource() == changeField)
			{
			change = convertdouble(changeField.getText());
			if(sel1)
				{
				tree.setStats(convertdouble(ang1Field.getText())+change, convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				ang1Field.setText(""+(convertdouble(ang1Field.getText())+change));
				}
			if(sel2)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText())+change, convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				ang2Field.setText(""+(convertdouble(ang2Field.getText())+change));
				}
			if(sel3)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText())+change, convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				ang3Field.setText(""+(convertdouble(ang3Field.getText())+change));
				}
			if(sel4)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText())+change, convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				ang4Field.setText(""+(convertdouble(ang4Field.getText())+change));
				}
			if(sel5)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText())+change, convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				len1Field.setText(""+(convertdouble(len1Field.getText())+change));
				}
			if(sel6)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText())+change, convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
				len2Field.setText(""+(convertdouble(len2Field.getText())+change));
				}
			if(sel7)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText())+change, convertdouble(len4Field.getText()));
				len3Field.setText(""+(convertdouble(len3Field.getText())+change));
				}
			if(sel8)
				{
				tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText())+change);
				len4Field.setText(""+(convertdouble(len4Field.getText())+change));
				}
			}
		if(e.getSource() == ang1Label)
			{
			sel1 = !sel1;
			if(!sel1) ang1Field.setBackground(Color.white);
			else ang1Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == ang2Label)
			{
			sel2 = !sel2;
			if(!sel2) ang2Field.setBackground(Color.white);
			else ang2Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == ang3Label)
			{
			sel3 = !sel3;
			if(!sel3) ang3Field.setBackground(Color.white);
			else ang3Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == ang4Label)
			{
			sel4 = !sel4;
			if(!sel4) ang4Field.setBackground(Color.white);
			else ang4Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == len1Label)
			{
			sel5 = !sel5;
			if(!sel5) len1Field.setBackground(Color.white);
			else len1Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == len2Label)
			{
			sel6 = !sel6;
			if(!sel6) len2Field.setBackground(Color.white);
			else len2Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == len3Label)
			{
			sel7 = !sel7;
			if(!sel7) len3Field.setBackground(Color.white);
			else len3Field.setBackground(new Color(255, 100, 100));
			}
		if(e.getSource() == len4Label)
			{
			sel8 = !sel8;
			if(!sel8) len4Field.setBackground(Color.white);
			else len4Field.setBackground(new Color(255, 100, 100));
			}

		if(e.getSource() == ang1Field || e.getSource() == ang2Field || e.getSource() == ang3Field || e.getSource() == ang4Field ||e.getSource() == len1Field || e.getSource() == len2Field || e.getSource() == len3Field || e.getSource() == len4Field)
			{
			tree.setStats(convertdouble(ang1Field.getText()), convertdouble(ang2Field.getText()), convertdouble(ang3Field.getText()), convertdouble(ang4Field.getText()), convertdouble(len1Field.getText()), convertdouble(len2Field.getText()), convertdouble(len3Field.getText()), convertdouble(len4Field.getText()));
			}
		repaint();
		}



	public void paint(Graphics g)
		{
		//System.out.println("MyPanel Painting");
		ScaledGraphics sg = new ScaledGraphics(g, 700);
		tree.paint(sg);
		}

	private int convert(String s)
			{
			try
				{
				return (new Integer(s)).intValue();
				}
			catch (Throwable t)
				{
				return 50;
				}
			}

	private double convertdouble(String s)
			{
			try
				{
				return (new Double(s)).doubleValue();
				}
			catch (Throwable t)
				{
				return .7;
				}
			}

}



