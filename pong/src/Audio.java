import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Audio {
	File musicFile = new File("hit.wav");
	AudioInputStream audioStream;
	static Clip clip;	
	
	Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioStream = AudioSystem.getAudioInputStream(musicFile);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
	}
	public static void play() {
		clip.start();
	}
}
