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

import fileEditor.SnakeGameScore2;


@SuppressWarnings("serial")
public class LosePage4 extends JFrame implements ActionListener
{
	int score1;
	int score2;
	int level;
	int score;
	String[] names = new String[10];
	String[] names1 = new String[10];
	int[] highScores = new int[10];
	int[] highLevels = new int[10];
	
	ObjectInputStream input;
	
	JButton replayButton;
	JButton veiwHighscoresButton;
	JButton StartPageButton;
	JButton enterHighscoresButton;
	
	boolean submit;
	public LosePage4(int score11,int score12, int level1, boolean submit1)
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		score1 = score11;
		score2 = score12;
		score = score1 + score2;
		level = level1;
		submit = submit1;
		readScore();
		
		replayButton = new JButton("play again");
		veiwHighscoresButton = new JButton("view highscores");
		StartPageButton = new JButton("main menu");
		
		replayButton.addActionListener(this);
		veiwHighscoresButton.addActionListener(this);
		StartPageButton.addActionListener(this);
		
		replayButton.setBounds(30, 230, 150, 25);
		veiwHighscoresButton.setBounds(200, 230, 150, 25);
		StartPageButton.setBounds(120, 260, 150, 25);
		
		enterHighscoresButton = new JButton("submit highscore");
		enterHighscoresButton.addActionListener(this);
		enterHighscoresButton.setBounds(120, 200, 150, 25);
		add(enterHighscoresButton);
		enterHighscoresButton.setVisible(false);
		
		setLayout(null);
		add(replayButton);
		add(veiwHighscoresButton);
		add(StartPageButton);
		
		setVisible(true);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g = this.getContentPane().getGraphics();
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		g.drawString("congratulations!", 20, 50);
		g.drawString("you made it to level " + level, 20, 80);
		g.drawString("blue achived a score of " + score1, 20, 110);
		g.drawString("red achived a score of " + score2, 20, 140);
		g.drawString("total score is " + score, 20, 170);
		if (score > highScores[9] && submit)
		{
			enterHighscoresButton.setVisible(true);
		}
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == replayButton)
		{
			new PlayGame4();
		}
		if (e.getSource() == veiwHighscoresButton)
		{
			new HighScorePage4();
		}
		if (e.getSource() == StartPageButton)
		{
			new StartPage();
		}
		if (e.getSource() == enterHighscoresButton)
		{
			new SaveHighScore(score1, score2, level);
		}
		dispose();
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