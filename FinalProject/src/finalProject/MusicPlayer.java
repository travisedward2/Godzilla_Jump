package finalProject;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class MusicPlayer {
	Player audioPlayer = null;
	Clip rocket;
	AudioInputStream audioInputStream;
	URL url;
	@SuppressWarnings("deprecation")
	public MusicPlayer(File file) throws Exception{
		url=file.toURL();
		audioPlayer = Manager.createRealizedPlayer(url);
	}
	
	public MusicPlayer() {
		
	}
	public void bgm() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/bgm.wav"));
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			try {
				clip.open(audioInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public void play(){
		
		audioPlayer.start();	
		try {
		this.audioPlayer=Manager.createRealizedPlayer(url);
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void play(int x){
		audioPlayer.start();	
	}
	public void stop(){
		audioPlayer.stop();
		audioPlayer.close();
		try {
			this.audioPlayer=Manager.createRealizedPlayer(url);
		} catch (NoPlayerException e) {
		} catch (CannotRealizeException e) {
		} catch (IOException e) {
		}
	}
}
