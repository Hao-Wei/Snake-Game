package theSnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class PlayGame2 extends JFrame implements KeyListener
{
	protected int snakeLength = 3;
	protected static int [][] grid = new int[40][40];
	protected int[] x = new int [200];
	protected int[] y = new int [200];
	protected int[] last = new int[2];
	
	protected static int move = 4;
	
	protected int obsticle;
	protected int[] obsticlex  = new int [100];
	protected int[] obsticley  = new int [100];
	
	protected int score;
	protected int lastScore;
	protected int fruitx;
	protected int fruity;
	
	protected boolean stop;
	protected boolean start = true;
	static boolean once;
	protected int Tcounter;
	protected int Mcounter = 100;
	protected static boolean pause;
	protected boolean grow;
	protected int grow1;
	protected static int bonus = 1000;
	protected int level = 1;
	protected int lastLevel;
	protected int delay = 1000;
	protected int period = 1;
	Timer timer;
	protected boolean first;
	public PlayGame2()
	{
		super("The Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setSize(406, 426);
		getContentPane().setBackground(Color.GREEN);
		repaint();
		setVisible(true);
		reset();
		play();
	}
	synchronized public void play()
	{
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(!pause)
				{
					if (Tcounter == Mcounter)
					{
						if (grow)
						{
							grow1++;
							if (grow1 == 3)
							{
								grow1 = 0;
								grow = false;
							}
							snakeLength++;
						}
						once = false;
						contact();
						setLoc();
						
						repaint();
						Tcounter = 0;
						
					}
					else
						Tcounter++;
				}
			}
		}, delay, period);
	}
	synchronized public void contact()
	{
		if (x[snakeLength - 1] == fruitx && y[snakeLength - 1] == fruity)
		{
			level++;
			score += 1000 + 100 * level + bonus;
			bonus = 1000;
			if (obsticle > 0)
			{
				for (int counter = 0; counter < obsticle; counter++)
					do
					{
						fruitx = (int) (Math.random() * 36) + 1;
					}while(fruitx == obsticlex[counter]);
				for (int counter = 0; counter < obsticle; counter++)
				{
					do
					{
						fruity =  (int) (Math.random() * 36) + 1;
					}while(fruity == obsticley[counter]);
				}
			}
			else
			{
				fruitx = (int) (Math.random() * 36) + 1;
				fruity = (int) (Math.random() * 36) + 1;
			}
			obsticle++;
			do
			{
				obsticlex[obsticle - 1] = (int) (Math.random() * 36) + 1;
			}while(obsticlex[obsticle - 1] == fruitx);
			
			do
			{
				obsticley[obsticle - 1] = (int) (Math.random() * 36) + 1;
			}while(obsticley[obsticle - 1] == fruity);
			
			snakeLength++;
			grow = true;
			Mcounter -= 3;
			grid[obsticlex[obsticle - 1]][obsticley[obsticle - 1]] = 100;
		}
		if (x[snakeLength - 1] == -1 || x[snakeLength - 1] == 39 || y[snakeLength - 1] == -1 || y[snakeLength - 1] == 39)
		{
			stop = true;
		}
			
	}
	synchronized public void hit()
	{
		for (int counter = 0; counter < grid.length; counter ++)
			for (int counter1 = 0; counter1 < grid.length; counter1 ++)
			{
				if (grid[counter][counter1] > snakeLength && grid[counter][counter1] != 100)
				{
					stop = true;
				}
			}
	}
	synchronized public void setLoc()
	{
		if (!stop)
		{
			for (int counter = 0; counter < grid.length; counter ++)
				for (int counter1 = 0; counter1 < grid.length; counter1 ++)
				{
					if (grid[counter][counter1] == 1)
					{
						grid[counter][counter1] = 0;
					}
					if (grid[counter][counter1] > 1)
					{
						boolean once = false;
						for (int counter2 = 1; counter2 < snakeLength; counter2++)
						{
							if (grid[counter][counter1] == counter2 + 1 && !once)
							{
								once = true;
								x[counter2 - 1] = counter;
								y[counter2 - 1] = counter1;
								grid[counter][counter1] = counter2;
							}
						}
					}
				}
			if (move == 1)
			{
				x[snakeLength - 1] = x[snakeLength - 2];
				if (y[snakeLength - 2] - 1 > -1)
					y[snakeLength - 1] = y[snakeLength - 2] - 1;
				else
				{
					stop = true;
				}
				grid[x[snakeLength - 1]][y[snakeLength - 1]] += snakeLength;
			}
			else if (move == 2)
			{
				x[snakeLength - 1] = x[snakeLength - 2];
				if (y[snakeLength - 2] + 1 < 40)
					y[snakeLength - 1] = y[snakeLength - 2] + 1;
				else
				{
					stop = true;
				}
				grid[x[snakeLength - 1]][y[snakeLength - 1]] += snakeLength;
			}
			else if (move == 3)
			{
				y[snakeLength - 1] = y[snakeLength - 2];
				if (x[snakeLength - 2] - 1 > -1)
					x[snakeLength - 1] = x[snakeLength - 2] - 1;
				else
				{
					stop = true;
				}
				grid[x[snakeLength - 1]][y[snakeLength - 1]] += snakeLength;
			}
			else if (move == 4)
			{
				y[snakeLength - 1] = y[snakeLength - 2];
				if (x[snakeLength - 2] + 1 < 40)
					x[snakeLength - 1] = x[snakeLength - 2] + 1;
				else
				{
					stop = true;
				}
				grid[x[snakeLength - 1]][y[snakeLength - 1]] += snakeLength;
			}
			hit();
		}
	}
	synchronized public void paint(Graphics g)
	{
		g = this.getContentPane().getGraphics();
		if(first)
		{
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, 500, 500);
			first = false;
			super.paint(g);
		}
		
		if (!stop)
		{
			bonus -= 5;
			if (start)
			{
				start = false;
				g.setColor(Color.BLUE);
				g.fillRect(pos(0), pos(0), 10, 10);
				g.fillRect(pos(1), pos(0), 10, 10);
				g.fillRect(pos(2), pos(0), 10, 10);
				g.setColor(Color.RED);
				g.fillRect(pos(2) + 2, pos(0) + 2, 6, 6);
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, 500, 500);
			}
			else
			{
				g.setColor(Color.GREEN);
				g.fillRect(pos(last[0]), pos(last[1]), 10, 10);
				last[0] = x[0];
				last[1] = y[0];
				g.drawString("score: " + lastScore, 10, 10);
				g.drawString("level: " + lastLevel, 100, 10);
				lastScore = score;
				lastLevel = level;
				g.setColor(Color.BLUE);
				for (int counter = 0; counter < snakeLength; counter++)
					g.fillRect(pos(x[counter]), pos(y[counter]), 10, 10);
				g.setColor(Color.RED);
				g.fillRect(pos(x[snakeLength - 1]) + 2, pos(y[snakeLength - 1]) + 2, 6, 6);
				for (int counter = 0; counter < obsticle; counter++)
					g.fillRect(pos(obsticlex[counter]), pos(obsticley[counter]), 10, 10);
			}
			
			g.setColor(Color.BLUE);
			g.fillRect(pos(fruitx), pos(fruity), 10, 10);
			g.setColor(Color.BLACK);
			g.drawString("score: " + score, 10, 10);
			g.drawString("level: " + level, 100, 10);
		}
		else
		{
			this.dispose();
			timer.cancel();
			new LosePage2(level, score, true);
		}
	}
	synchronized public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode( )== 38 && move != 2 && !once && move != 1)
		{
			move = 1;
			once = true;
			bonus -= 20;
		}
		else if (e.getKeyCode( )== 40 && move != 1 && !once && move != 2)
		{
			move = 2;
			once = true;
			bonus -= 20;
		}
		else if (e.getKeyCode( )== 37 && move != 4 && !once && move != 3)
		{
			move = 3;
			once = true;
			bonus -= 20;
		}
		else if (e.getKeyCode( )== 39 && move != 3 && !once && move != 4)
		{
			move = 4;
			once = true;
			bonus -= 20;
		}
		else if (e.getKeyChar() == 'p')
		{
			if (pause)
				pause = false;
			else
				pause = true;
			for (int counter = 0; counter < grid.length; counter ++)
			{
				for (int counter1 = 0; counter1 < grid.length; counter1 ++)
				{
					System.out.print(grid[counter][counter1] + " ");
				}
				System.out.println("");
			}
		}
	}
	public void keyReleased(KeyEvent arg0)
	{
		
	}
	public void keyTyped(KeyEvent arg0) 
	{
		
	}
	synchronized public int pos(int blah)
	{
		return blah * 10;
	}
	synchronized public void reset()
	{
		last[0] = 10;
		last[1] = 10;
		move = 4;
		snakeLength = 3;
		start = true;
		for (int counter = 0; counter < x.length; counter++)
			x[counter] = 0;
		for (int counter = 0; counter < x.length; counter++)
			y[counter] = 0;
		for (int counter = 0; counter < grid.length; counter ++)
			for (int counter1 = 0; counter1 < grid.length; counter1 ++)
				grid[counter][counter1] = 0;
		grid[0][0] = 1;
		grid[1][0] = 2;
		grid[2][0] = 3;
		fruitx = (int) (Math.random() * 36) + 1;
		fruity = (int) (Math.random() * 36) + 1;
		score = 0;
		lastScore = 0;
		Tcounter = 0;
		Mcounter = 100;
		bonus = 1000;
		level = 1;
		lastLevel = 1;
		obsticle = 0;
		repaint();
	}
}