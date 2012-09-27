package theSnakeGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class InstructionPage extends JFrame implements ActionListener
{
	JButton StartPageButton;
	public InstructionPage()
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		
		StartPageButton = new JButton("main menu");
		StartPageButton.addActionListener(this);
		StartPageButton.setBounds(120, 260, 150, 25);
		setLayout(null);
		add(StartPageButton);
		setSize(390, 390);
		setVisible(true);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g = this.getContentPane().getGraphics();
		g.drawString("fdhdhdg", 10, 10);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == StartPageButton)
		{
			new StartPage();
		}
		dispose();
	}
}
