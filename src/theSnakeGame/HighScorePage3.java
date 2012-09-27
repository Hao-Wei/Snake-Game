package theSnakeGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import fileEditor.SnakeGameScore1;


@SuppressWarnings("serial")
public class HighScorePage3 extends JFrame implements ActionListener
{
	String[] names = new String[10];
	int[] highScores = new int[10];
	int[] highLevels = new int[10];
	
	ObjectInputStream input;
	
	JButton StartPageButton;
	public HighScorePage3()
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		
		StartPageButton = new JButton("main menu");
		StartPageButton.addActionListener(this);
		StartPageButton.setBounds(120, 260, 150, 25);
		setLayout(null);
		add(StartPageButton);
		
		setVisible(true);
		
		UpdateScores.getScores("SnakeGameScore3");
		
		readScore();
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == StartPageButton)
		{
			new StartPage();
		}
		dispose();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g = this.getContentPane().getGraphics();
		int y = 85;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		g.drawString("Name", 95, 50);
		g.drawString("Score", 200, 50);
		g.drawString("Level", 290, 50);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.setColor(new Color(255, 255, 0));
		g.drawString((1) + ".", 70, y);
		g.drawString(names[0], 100, y);
		g.drawString(highScores[0] + "", 200, y);
		g.drawString(highLevels[0] + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 19));
		g.setColor(new Color(192, 192, 192));
		g.drawString((2) + ".", 70, y);
		g.drawString(names[1], 100, y);
		g.drawString(highScores[1] + "", 200, y);
		g.drawString(highLevels[1] + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 18));
		g.setColor(new Color(128, 64, 0));
		g.drawString((3) + ".", 70, y);
		g.drawString(names[2], 100, y);
		g.drawString(highScores[2] + "", 200, y);
		g.drawString(highLevels[2] + "", 300, y);
		y += 18;
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		g.setColor(Color.BLACK);
		for (int counter = 3; counter < 10; counter++)
		{
			g.drawString((counter + 1) + ".", 70, y);
			g.drawString(names[counter], 100, y);
			g.drawString(highScores[counter] + "", 200, y);
			g.drawString(highLevels[counter] + "", 300, y);
			y += 18;
		}
	}
	public void readScore()
	{
		SnakeGameScore1 snakeGameScore1 = null;
		try 
		{
			input = new ObjectInputStream(new FileInputStream("SnakeGameScore3.hao"));
			for (int counter = 0; counter < 10; counter++)
			{
				try 
				{
					snakeGameScore1 = (SnakeGameScore1) input.readObject();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				names[counter] = snakeGameScore1.getName();
				highScores[counter] = snakeGameScore1.getScore();
				highLevels[counter] = snakeGameScore1.getLevel();
			}
			try {
				System.out.println((Integer) input.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			input.close();
		} 
		catch (IOException e) 
		{
			System.out.println("file not found");
			System.exit(1);
		}
	}
}