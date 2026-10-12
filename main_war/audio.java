package main_war;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class audio {

    private static Clip clip;
    
    public static void tocarSom() {

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                audio.class.getResource("/recursos/sanctuaryGuardians.wav")
            );

            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Erro ao reproduzir o áudio.");
        }
    }
    
    public static void pararSom() {

    if (clip != null && clip.isRunning()) {
        clip.stop();
        clip.close();
    }
}
    
    public static void gameOver(){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                audio.class.getResource("/recursos/badDream.wav")
            );

            Clip clipgameOver = AudioSystem.getClip();
            clipgameOver.open(audio);
            clipgameOver.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Erro ao reproduzir o áudio.");
        }
    }
}



