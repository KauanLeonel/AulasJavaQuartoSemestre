package br.com.Banco.app;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproduzirAudios {

    public static void reproduzirAudios(String caminho) {

        try {
             File arquivo = new File(caminho);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Aqui o programa nã;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
