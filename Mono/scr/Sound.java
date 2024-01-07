package Mono.scr;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;;
public class Sound{
    public static void main(String[] args) {
        String filepath = "1011-Reflect_drums_120_BPM.wav";
        PlayMusic(filepath);
        JOptionPane.showMessageDialog(null,null);

    }
    public static void PlayMusic(String location){
        try {
            File musicPath = new File(location);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            
         catch (Exception e) {
           System.out.println(e); 
        }
    }
    
}
