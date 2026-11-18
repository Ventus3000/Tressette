package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe che si occupa di gestire file audio
 */
public class AudioManager {
	/** Variable di istanza per il singleton */
	private static AudioManager instance;

	/**
	 * Restituisce l'istanza di audio manager se e' gia creato senno la crea
	 * @return istanza di audio manger
	 */
	public static AudioManager getInstance() {
		if (instance == null) {
			instance = new AudioManager();
		}
		return instance;
	}
	
	private AudioManager() {
		
	}
	
	public void play(String filename) {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
}