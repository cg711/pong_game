import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanel panel;
	JButton button;

	GameFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException { //constructor
		panel = new GamePanel();
//		button = new JButton("TEST");
//		button.setBounds(200 , 100, 100, 50);
//		this.add(button);
//		button.addActionListener(e -> System.out.println("Hello!"));
		this.add(panel);
		this.setTitle("🕹️ Pong Game 🕹️");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	
}
