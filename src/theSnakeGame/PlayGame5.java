package theSnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class PlayGame5 extends JFrame implements KeyListener
{
	protected int move1;
	protected int move2;
	Timer timer;
	protected boolean pause;
	protected int[][] grid = new int[50][50];
	protected int snakeLength1;
	protected int snakeLength2;
	protected boolean start;
	protected boolean grow;
	protected int grow1;
	protected boolean grow2;
	protected int grow3;

	protected int[] x1 = new int [200];
	protected int[] y1 = new int [200];
	
	protected int[] x2 = new int [200];
	protected int[] y2 = new int [200];
	
	protected int[] last1 = new int[2];
	protected int[] last2 = new int[2];
	
	protected int fruitx;
	protected int fruity;
	
	protected int fruit1x;
	protected int fruit1y;
	
	protected boolean stop;
	protected int level;
	protected int lastLevel;
	protected int level1;
	protected int lastLevel1;
	protected int changeLevel;
	
	protected int time;
	protected boolean once;
	protected boolean once1;
	protected boolean first;
	public PlayGame5()
	{
		super("The Snake Game");
		reset();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GREEN);
		addKeyListener(this);
		setSize(516, 536);
		setVisible(true);
		repaint();
		play();
	}
	synchronized public void play()
	{
		int delay = 0;
		int period = 80;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(!pause)
				{
					once = false;
					once1 = false;
					time++;
					if (grow)
					{
						grow1++;
						if (grow1 == 3)
						{
							grow1 = 0;
							grow = false;
						}
						snakeLength1++;
					}
					if (grow2)
					{
						grow3++;
						if (grow3 == 3)
						{
							grow3 = 0;
							grow2 = false;
						}
						snakeLength2++;
					}
					if(level == 10 || level1 == 10)
						stop = true;
					contact();
					setLoc1();
					setLoc2();
					repaint();
				}
			}
		}, delay, period);
	}
	synchronized public void paint(Graphics g)
	{
		g = this.getContentPane().getGraphics();
		if(first)
		{
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, 800, 800);
			first = false;
			super.paint(g);
		}
		
		if (!stop)
		{
			if (start)
			{
				start = false;
				g.setColor(Color.BLUE);
				g.fillRect(pos(0), pos(0), 10, 10);
				g.fillRect(pos(1), pos(0), 10, 10);
				g.fillRect(pos(2), pos(0), 10, 10);
				g.setColor(Color.RED);
				g.fillRect(pos(2) + 2, pos(0) + 2, 6, 6);
				
				g.setColor(Color.RED);
				g.fillRect(pos(49), pos(49), 10, 10);
				g.fillRect(pos(48), pos(49), 10, 10);
				g.fillRect(pos(47), pos(49), 10, 10);
				g.setColor(Color.BLUE);
				g.fillRect(pos(47) + 2, pos(49) + 2, 6, 6);
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, 800, 800);
			}
			else if(time < 10)
			{
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, 800, 800);
				
				g.setColor(Color.BLUE);
				for (int counter = 0; counter < snakeLength1; counter++)
					g.fillRect(pos(x1[counter]), pos(y1[counter]), 10, 10);
				g.setColor(Color.RED);
				g.fillRect(pos(x1[snakeLength1 - 1]) + 2, pos(y1[snakeLength1 - 1]) + 2, 6, 6);
				
				g.setColor(Color.RED);
				for (int counter = 0; counter < snakeLength2; counter++)
					g.fillRect(pos(x2[counter]), pos(y2[counter]), 10, 10);
				g.setColor(Color.BLUE);
				g.fillRect(pos(x2[snakeLength2 - 1]) + 2, pos(y2[snakeLength2 - 1]) + 2, 6, 6);
				
				last1[0] = x1[0];
				last1[1] = y1[0];
				
				last2[0] = x2[0];
				last2[1] = y2[0];
				
				lastLevel1 = level1;
				lastLevel = level;
			}
			else
			{
				g.setColor(Color.GREEN);
				g.fillRect(pos(last1[0]), pos(last1[1]), 10, 10);
				g.fillRect(pos(last2[0]), pos(last2[1]), 10, 10);

				last1[0] = x1[0];
				last1[1] = y1[0];
				
				last2[0] = x2[0];
				last2[1] = y2[0];
				
				g.drawString("red: " + lastLevel1, 100, 10);
				g.drawString("blue: " + lastLevel, 10, 10);
				lastLevel1 = level1;
				lastLevel = level;
				
				g.setColor(Color.BLUE);
				for (int counter = 0; counter < snakeLength1; counter++)
					g.fillRect(pos(x1[counter]), pos(y1[counter]), 10, 10);
				g.setColor(Color.RED);
				g.fillRect(pos(x1[snakeLength1 - 1]) + 2, pos(y1[snakeLength1 - 1]) + 2, 6, 6);
				
				g.setColor(Color.RED);
				for (int counter = 0; counter < snakeLength2; counter++)
					g.fillRect(pos(x2[counter]), pos(y2[counter]), 10, 10);
				g.setColor(Color.BLUE);
				g.fillRect(pos(x2[snakeLength2 - 1]) + 2, pos(y2[snakeLength2 - 1]) + 2, 6, 6);
			}
			
			g.setColor(Color.BLUE);
			g.fillRect(pos(fruitx), pos(fruity), 10, 10);
			g.setColor(Color.RED);
			g.fillRect(pos(fruit1x), pos(fruit1y), 10, 10);
			g.setColor(Color.BLACK);
			g.drawString("red: " + level1, 100, 10);
			g.drawString("blue: " + level, 10, 10);
		}
		else
		{
			this.dispose();
			timer.cancel();
			new FinishPage(level1, level);
		}
	}
	synchronized public int pos(int blah)
	{
		return blah * 10;
	}
	synchronized public void setLoc1()
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
						for (int counter2 = 1; counter2 < snakeLength1; counter2++)
						{
							if (grid[counter][counter1] == counter2 + 1 && !once)
							{
								once = true;
								x1[counter2 - 1] = counter;
								y1[counter2 - 1] = counter1;
								grid[counter][counter1] = counter2;
							}
						}
					}
				}
			if (move1 == 1)
			{
				x1[snakeLength1 - 1] = x1[snakeLength1 - 2];
				if (y1[snakeLength1 - 2] - 1 > -1)
					y1[snakeLength1 - 1] = y1[snakeLength1 - 2] - 1;
				else
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] > 0 && grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] != 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] == 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] += snakeLength1;
			}
			else if (move1 == 2)
			{
				x1[snakeLength1 - 1] = x1[snakeLength1 - 2];
				if (y1[snakeLength1 - 2] + 1 < 50)
					y1[snakeLength1 - 1] = y1[snakeLength1 - 2] + 1;
				else
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] > 0 && grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] != 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] == 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] += snakeLength1;
			}
			else if (move1 == 3)
			{
				y1[snakeLength1 - 1] = y1[snakeLength1 - 2];
				if (x1[snakeLength1 - 2] - 1 > -1)
					x1[snakeLength1 - 1] = x1[snakeLength1 - 2] - 1;
				else
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] > 0 && grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] != 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] == 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] += snakeLength1;
			}
			else if (move1 == 4)
			{
				y1[snakeLength1 - 1] = y1[snakeLength1 - 2];
				if (x1[snakeLength1 - 2] + 1 < 50)
					x1[snakeLength1 - 1] = x1[snakeLength1 - 2] + 1;
				else
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] > 0 && grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] != 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				if (grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] == 101 && time > 10)
				{
					level1 = -1;
					stop = true;
				}
				grid[x1[snakeLength1 - 1]][y1[snakeLength1 - 1]] += snakeLength1;
			}

		}
	}
	synchronized public void setLoc2()
	{
		for (int counter = 0; counter < grid.length; counter ++)
			for (int counter1 = 0; counter1 < grid.length; counter1 ++)
			{
				if (grid[counter][counter1] == 1 + 100)
				{
					grid[counter][counter1] = 0;
				}
				if (grid[counter][counter1] > 1 + 100)
				{
					boolean once = false;
					for (int counter2 = 1; counter2 < snakeLength2; counter2++)
					{
						if (grid[counter][counter1] == counter2 + 1 + 100 && !once)
						{
							once = true;
							x2[counter2 - 1] = counter;
							y2[counter2 - 1] = counter1;
							grid[counter][counter1] = counter2 + 100;
						}
					}
				}
			}
		if (move2 == 1)
		{
			x2[snakeLength2 - 1] = x2[snakeLength2 - 2];
			if (y2[snakeLength2 - 2] - 1 > -1)
				y2[snakeLength2 - 1] = y2[snakeLength2 - 2] - 1;
			else
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] > 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] == 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] += snakeLength2 + 100;
		}
		else if (move2 == 2)
		{
			x2[snakeLength2 - 1] = x2[snakeLength2 - 2];
			if (y2[snakeLength2 - 2] + 1 < 50)
				y2[snakeLength2 - 1] = y2[snakeLength2 - 2] + 1;
			else
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] > 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] == 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] += snakeLength2 + 100;
		}
		else if (move2 == 3)
		{
			y2[snakeLength2 - 1] = y2[snakeLength2 - 2];
			if (x2[snakeLength2 - 2] - 1 > -1)
				x2[snakeLength2 - 1] = x2[snakeLength2 - 2] - 1;
			else
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] > 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] == 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] += snakeLength2 + 100;
		}
		else if (move2 == 4)
		{
			y2[snakeLength2 - 1] = y2[snakeLength2 - 2];
			if (x2[snakeLength2 - 2] + 1 < 50)
				x2[snakeLength2 - 1] = x2[snakeLength2 - 2] + 1;
			else
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] > 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			if (grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] == 1 && time > 10)
			{
				level = -1;
				stop = true;
			}
			grid[x2[snakeLength2 - 1]][y2[snakeLength2 - 1]] += snakeLength2 + 100;
		}
	}
	synchronized public void contact()
	{
		if (x1[snakeLength1 - 1] == fruitx && y1[snakeLength1 - 1] == fruity)
		{
			changeLevel = 2;
			snakeLength1++;
			grow = true;
			fruitx = -2;
			fruity = -2;
		}
		
		if (x2[snakeLength2 - 1] == fruitx && y2[snakeLength2 - 1] == fruity)
		{
			level = -1;
			stop = true;
		}
		
		if (changeLevel == 2)
		{
			level++;
			changeLevel = 0;
			do
			{
				fruitx = (int) (Math.random() * 46) + 1;
				fruity = (int) (Math.random() * 46) + 1;
			}while (fruitx == fruit1x && fruity == fruit1y);
		}
		
		if (x2[snakeLength2 - 1] == fruit1x && y2[snakeLength2 - 1] == fruit1y)
		{
			changeLevel = 1;
			snakeLength2++;
			grow2 = true;
			fruit1x = -2;
			fruit1y = -2;
		}
		if (x1[snakeLength1 - 1] == fruit1x && y1[snakeLength1 - 1] == fruit1y)
		{
			level1 = -1;
			stop = true;
		}
		
		if (changeLevel == 1)
		{
			level1++;
			changeLevel = 0;
			do
			{
				fruit1x = (int) (Math.random() * 46) + 1;
				fruit1y = (int) (Math.random() * 46) + 1;
			}while (fruitx == fruit1x && fruity == fruit1y);
		}
	}
	synchronized public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == 38 && move2 != 2 && !once1)
		{
			move2 = 1;
			once1 = true;
		}
		else if (e.getKeyCode() == 40 && move2 != 1 && !once1)
		{
			move2 = 2;
			once1 = true;
		}
		else if (e.getKeyCode() == 37 && move2 != 4 && !once1)
		{
			move2 = 3;
			once1 = true;
		}
		else if (e.getKeyCode() == 39 && move2 != 3 && !once1)
		{
			move2 = 4;
			once1 = true;
		}
	}
	
	public void keyReleased(KeyEvent e) 
	{
		
	}
	synchronized public void keyTyped(KeyEvent e) 
	{	
		if (e.getKeyChar() == 'w' && move1 != 2 && !once)
		{
			move1 = 1;
			once = true;
		}
		else if (e.getKeyChar() == 's' && move1 != 1 && !once)
		{
			move1 = 2;
			once = true;
		}
		else if (e.getKeyChar() == 'a' && move1 != 4 && !once)
		{
			move1 = 3;
			once = true;
		}
		else if (e.getKeyChar() == 'd' && move1 != 3 && !once)
		{
			move1 = 4;
			once = true;
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
	synchronized public void reset()
	{
		last1[0] = 10;
		last1[1] = 10;
		last2[0] = 10;
		last2[1] = 10;
		
		level = 1;
		lastLevel = 1;
		level1 = 1;
		lastLevel1 = 1;
		move1 = 4;
		move2 = 3;
		snakeLength1 = 3;
		snakeLength2 = 3;
		start = true;
		for (int counter = 0; counter < y1.length; counter++)
			x1[counter] = 0;
		for (int counter = 0; counter < x1.length; counter++)
			y1[counter] = 0;
		
		for (int counter = 0; counter < x2.length; counter++)
			x2[counter] = 0;
		for (int counter = 0; counter < x2.length; counter++)
			y2[counter] = 0;
		
		for (int counter = 0; counter < grid.length; counter ++)
			for (int counter1 = 0; counter1 < grid.length; counter1 ++)
				grid[counter][counter1] = 0;
		grid[0][0] = 1;
		grid[1][0] = 2;
		grid[2][0] = 3;
		
		grid[49][49] = 101;
		grid[48][49] = 102;
		grid[47][49] = 103;
		
		fruitx = (int) (Math.random() * 46) + 1;
		fruity = (int) (Math.random() * 46) + 1;
		fruit1x = (int) (Math.random() * 46) + 1;
		fruit1y = (int) (Math.random() * 46) + 1;
		changeLevel = 0;
		stop = false;
		time = 0;
		once = false;
		once1 = false;
		repaint();
	}
}
