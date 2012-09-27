package theSnakeGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Play extends JFrame implements ActionListener
{
	JLabel label2;
	JLabel label3;
	JButton play1;
	JButton play2;
	JButton play3;
	JButton play4;
	JButton play5;
	JButton back;
	public Play()
	{
		super("The Snake Game");
		int y1 = 35;
		int y2 = 190;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(390, 420);
		getContentPane().setBackground(Color.GREEN);
		setLayout(null);
		
		label2 = new JLabel("Single Player Modes");
		label2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label2.setBounds(70, y1, 300, 40);
		add(label2);
		
		label3 = new JLabel("Double Player Modes");
		label3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label3.setBounds(70, y2, 300, 40);
		add(label3);
		
		play1 = new JButton("normal mode");
		play1.addActionListener(this);	
		play1.setBounds(120, y1 + 50, 150, 25);
		add(play1);
		
		play2 = new JButton("obsticle mode");
		play2.addActionListener(this);	
		play2.setBounds(120, y1 + 80, 150, 25);
		add(play2);
		
		play3 = new JButton("2 snake mode");
		play3.addActionListener(this);	
		play3.setBounds(120, y1 + 110, 150, 25);
		add(play3);
		
		play4 = new JButton("team mode");
		play4.addActionListener(this);	
		play4.setBounds(120, y2 + 50, 150, 25);
		add(play4);
		
		play5 = new JButton("vs mode");
		play5.addActionListener(this);	
		play5.setBounds(120, y2 + 80, 150, 25);
		add(play5);
		
		back = new JButton("back");
		back.addActionListener(this);	
		back.setBounds(120, 330, 150, 25);
		add(back);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == play1)
		{
			new PlayGame();
		}
		if (e.getSource() == play2)
		{
			new PlayGame2();
		}
		if (e.getSource() == play3)
		{
			new PlayGame3();
		}
		if (e.getSource() == play4)
		{
			new PlayGame4();
		}
		if (e.getSource() == play5)
		{
			new PlayGame5();
		}
		if (e.getSource() ==back)
		{
			new StartPage();
		}
		this.dispose();
	}
}
