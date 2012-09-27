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

import fileEditor.SnakeGameScore2;

@SuppressWarnings("serial")
public class SaveHighScore extends JFrame implements ActionListener
{
	String[] names = new String[10];
	String[] names1 = new String[10];
	int[] highScores = new int[10];
	int[] highLevels = new int[10];
	
	ObjectInputStream input;
	ObjectOutputStream output;
	
	int score1;
	int score2;
	int level;
	int score;
	String name;
	String name1;
	int version;
	boolean changed;
	boolean updated;
	
	JLabel label1;
	JButton button1;
	JTextField textField1;
	JTextField textField2;
	
	public SaveHighScore(int score11,int score12, int level1)
	{
		super("The Snake Game");
		System.out.println(4);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		
		score1 = score11;
		score2 = score12;
		score = score1 + score2;
		level = level1;
		
		setLayout(null);
		
		button1 = new JButton("submit");
		button1.addActionListener(this);	
		button1.setBounds(120, 230, 150, 25);
		add(button1);
		
		textField1 = new JTextField("player1 name");
		textField1.setBounds(45, 200, 150, 25);
		add(textField1);
		
		textField2 = new JTextField("player2 name");	
		textField2.setBounds(195, 200, 150, 25);
		add(textField2);
		
		label1 = new JLabel("submit your highscore");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label1.setBounds(40, 90, 400, 40);
		add(label1);
		
		setVisible(true);
		
		//updated = UpdateScores.getScores("SnakeGameScore4");
		//if(updated)
			readScore();
	}
	public void addScore()
	{
		SnakeGameScore2 snakeGameScore2;
		try 
		{
			output = new ObjectOutputStream(new FileOutputStream("SnakeGameScore4.hao"));
			
			for (int counter = 0; counter < 10; counter++)
			{
				snakeGameScore2 = new SnakeGameScore2(names[counter], names1[counter], highScores[counter], highLevels[counter]);
				output.writeObject(snakeGameScore2);
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
		SnakeGameScore2 snakeGameScore2 = null;
		try 
		{
			input = new ObjectInputStream(new FileInputStream("SnakeGameScore4.hao"));
			for (int counter = 0; counter < 10; counter++)
			{
				try 
				{
					snakeGameScore2 = (SnakeGameScore2) input.readObject();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				names[counter] = snakeGameScore2.getName();
				names1[counter] = snakeGameScore2.getName2();
				highScores[counter] = snakeGameScore2.getScore();
				highLevels[counter] = snakeGameScore2.getLevel();
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
		
		name1 = textField2.getText();
		if (!(name == null || name.equals("")) && !(name1 == null || name1.equals("")))
		{		
			for (int counter = 9; counter > change; counter--)
			{
				highScores[counter] = highScores[counter - 1];
				names[counter] = names[counter - 1];
				names1[counter] = names1[counter - 1];
				highLevels[counter] = highLevels[counter - 1];
			}
			highScores[change] = score;
			highLevels[change] = level;
			names[change] = name;
			names1[change] = name1;
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
				//UpdateScores.sendScores("SnakeGameScore4", name + " and " + name1);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "unable to connect to server");
		}
		
		new LosePage4(score1, score2, level, false);
		dispose();
	}
}
