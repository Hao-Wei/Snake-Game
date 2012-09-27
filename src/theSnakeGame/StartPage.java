package theSnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class StartPage extends JFrame implements ActionListener
{
	JLabel label1;
	JLabel label2;
	JLabel label3;
	
	JButton playButton;
	JButton veiwHighscoresButton;
	JButton instructionButton;
	public StartPage()
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 390);
		getContentPane().setBackground(Color.GREEN);
		label1 = new JLabel("the amazing");
		label2 = new JLabel("SNAKE GAME!!!");
		label3 = new JLabel("by: Hao Wei");
		
		label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
		label2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		label3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		label1.setBounds(80, 50, 300, 40);
		label2.setBounds(40, 90, 400, 40);
		label3.setBounds(140, 130, 300, 40);
		
		playButton = new JButton("Play");
		veiwHighscoresButton = new JButton("view highscores");
		instructionButton = new JButton("instructions");
	
		playButton.addActionListener(this);
		veiwHighscoresButton.addActionListener(this);
		instructionButton.addActionListener(this);
		
		playButton.setBounds(120, 200, 150, 25);
		veiwHighscoresButton.setBounds(120, 230, 150, 25);
		instructionButton.setBounds(120, 260, 150, 25);
		
		setLayout(null);
		add(label1);
		add(label2);
		add(label3);
		
		add(playButton);
		add(veiwHighscoresButton);
		add(instructionButton);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == playButton)
		{
			new Play();
		}
		if (e.getSource() == veiwHighscoresButton)
		{
			//new ChooseHighScorePage();
		}
		if (e.getSource() == instructionButton)
		{
			new InstructionPage();
		}
		this.dispose();
	}
}
