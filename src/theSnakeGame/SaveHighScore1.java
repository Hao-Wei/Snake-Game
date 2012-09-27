package theSnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fileEditor.SnakeGameScore1;

@SuppressWarnings("serial")
public class SaveHighScore1 extends JFrame implements ActionListener
{
	String[] names = new String[10];
	int[] highScores = new int[10];
	int[] highLevels = new int[10];
	
	ObjectInputStream input;
	ObjectOutputStream output;
	
	int score;
	int level;
	String name;
	int game;
	int version;
	boolean changed;
	boolean updated;
	
	JButton button1;
	JTextField textField1;
	JLabel label1;
	public SaveHighScore1(int score1, int level1, int x)
	{
		super("The Snake Game");
		
		System.out.println(x);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		
		score = score1;
		level = level1;
		game = x;
		
		setLayout(null);
		button1 = new JButton("submit");
		button1.addActionListener(this);	
		button1.setBounds(120, 230, 150, 25);
		add(button1);
		
		textField1 = new JTextField("name");
		textField1.setBounds(120, 200, 150, 25);
		add(textField1);
		
		label1 = new JLabel("submit your highscore");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label1.setBounds(50, 90, 400, 40);
		add(label1);
		
		setVisible(true);
		
//		if (game == 1)
//			updated = UpdateScores.getScores("SnakeGameScore1");
//		else if (game == 2)
//			updated = UpdateScores.getScores("SnakeGameScore2");
//		else
//			updated = UpdateScores.getScores("SnakeGameScore3");
//		if(updated)
			readScore();
	}
	public void addScore()
	{
		SnakeGameScore1 snakeGameScore1;
		try 
		{
			if (game == 1)
				output = new ObjectOutputStream(new FileOutputStream("SnakeGameScore1.hao"));
			else if (game == 2)
				output = new ObjectOutputStream(new FileOutputStream("SnakeGameScore2.hao"));
			else
				output = new ObjectOutputStream(new FileOutputStream("SnakeGameScore3.hao"));
			
			for (int counter = 0; counter < 10; counter++)
			{
				snakeGameScore1 = new SnakeGameScore1(names[counter], highScores[counter], highLevels[counter]);
				output.writeObject(snakeGameScore1);
			}
			output.writeObject(version+1);
			if( output != null)
			{
				output.close();
			}
		} catch (IOException e) 
		{
			System.out.println("IO Error");
			System.exit(1);
		}
	}
	public void readScore()
	{
		SnakeGameScore1 snakeGameScore1 = null;
		try 
		{
			if (game == 1)
				input = new ObjectInputStream(new FileInputStream("SnakeGameScore1.hao"));
			else if (game == 2)
				input = new ObjectInputStream(new FileInputStream("SnakeGameScore2.hao"));
			else
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
				version = (Integer) input.readObject();
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
	public void addHighScore()
	{
		int change = 9;
		for (int counter = 8; counter > -1; counter--)
		{
			if (score > highScores[counter])
			{
				change = counter;
			}
		}
		
		name = textField1.getText();
		if (!(name == null || name.equals("")))
		{		
			for (int counter = 9; counter > change; counter--)
			{
				highScores[counter] = highScores[counter - 1];
				names[counter] = names[counter - 1];
				highLevels[counter] = highLevels[counter - 1];
			}
			highScores[change] = score;
			highLevels[change] = level;
			names[change] = name;
		}
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(updated)
		{
			if(score < highScores[9])
				changed = false;
			else
				changed = true;
			if(changed)
			{
				addHighScore();
				addScore();
				if (game == 1)
					UpdateScores.sendScores("SnakeGameScore1", name);
				else if (game == 2)
					UpdateScores.sendScores("SnakeGameScore2", name);
				else
					UpdateScores.sendScores("SnakeGameScore3", name);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "unable to connect to server");
		}
		
		if (game == 1)
			new LosePage(score, level, false);
		if (game == 2)
			new LosePage2(score, level, false);
		if (game == 3)
			new LosePage3(score, level, false);
		dispose();
	}
}
