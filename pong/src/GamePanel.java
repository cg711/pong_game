import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class GamePanel extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 30;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	static final boolean COMPUTER_MODE = false;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	JButton button;
	//File musicFile = new File("hit.wav");
	//AudioInputStream audioStream;
	//Clip clip;	
	
	GamePanel() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		newPaddles();
		newBall();
		//setMusic();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
//		button = new JButton("TEST");
//		button.setBounds(GAME_WIDTH - 20, 20, GAME_WIDTH, GAME_HEIGHT);
//		button.addActionListener(e -> System.out.println("Hello!"));
		//this.add(button);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	//public void setMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	//	audioStream = AudioSystem.getAudioInputStream(musicFile);
	//	clip = AudioSystem.getClip();
	//	clip.open(audioStream);
	//}
	public void newBall() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER/2,BALL_DIAMETER/2);
	}
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//bounce ball off top and window edges
		if(ball.y <= 0)
			ball.setYDirection(-ball.yVelocity);
		if(ball.y >= GAME_HEIGHT - BALL_DIAMETER)
			ball.setYDirection(-ball.yVelocity);
		//bounces ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity); //could just mult by -1 lol
			ball.xVelocity++; //for more challenge ;)
			if(ball.yVelocity>0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity); //could just mult by -1 lol
			ball.xVelocity++; //for more challenge ;)
			if(ball.yVelocity>0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		//stops paddles at window edges
		if(paddle1.y <= 0) 
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y <= 0) 
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		//give a player 1 point and creates new paddles and ball
		if(ball.x <= 0) {//p2 scores
			score.player2++;
			newPaddles();
			newBall();
			System.out.println(score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println(score.player1);
		}
	}
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				try {
					checkCollision();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				delta--;
				//System.out.println("TEST");
			}
		}
	}
	public class AL extends KeyAdapter{ //AL = Action Listener
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);

		}
	}
}
