import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;



public class Ball extends Rectangle{
	
	//File file = new File("hit.wav");
	//AudioInputStream audioStream;
	//Clip clip;

	Audio sound;
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Ball(int x, int y, int width, int height) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super(x, y, width, height);
		//sound = new Audio();
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection * initialSpeed);
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection * initialSpeed);
	}
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
		//Audio.play();
	}
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
		//Audio.play();
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g ) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
	//public void setMusic() {
		//audioStream = AudioSystem.getAudioInputStream(file);
		//Clip clip = AudioSystem.getClip();
		//clip.open(audioStream);
	//}
}
