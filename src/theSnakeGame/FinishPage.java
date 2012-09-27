package theSnakeGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FinishPage extends JFrame implements ActionListener
{
	JButton replayButton;
	JButton StartPageButton;
	
	int score1, score2;
	public FinishPage(int a, int b)
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		
		replayButton = new JButton("play again");
		StartPageButton = new JButton("main menu");
		
		replayButton.addActionListener(this);
		StartPageButton.addActionListener(this);
		
		replayButton.setBounds(120, 230, 150, 25);
		StartPageButton.setBounds(120, 260, 150, 25);
		
		setLayout(null);
		add(replayButton);
		add(StartPageButton);
		
		score1 = a;
		score2 = b;
		
		setVisible(true);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g = this.getContentPane().getGraphics();
		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		if (score1 > score2)
			g.drawString("blue wins!!!", 100, 125);
		else if (score1 < score2)
			g.drawString("red wins!!!", 100, 125);
		else if (score1 == score2)
			g.drawString("tie!!!", 100, 125);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == replayButton)
		{
			new PlayGame5();
		}
		if (e.getSource() == StartPageButton)
		{
			new StartPage();
		}
		dispose();
	}
}
