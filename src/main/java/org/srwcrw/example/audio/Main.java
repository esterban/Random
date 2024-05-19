package org.srwcrw.example.audio;

import javax.sound.sampled.*;
import java.io.*;

public class Main {

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        Main main = new Main();

        File outputFile = new File("yoko_test.wav");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        InputStream inputStream = main.getClass().getClassLoader().getResourceAsStream("yoko_screaming_at_art_show.wav");

        byte[] headBuffer = new byte[44];

        inputStream.read(headBuffer, 0, 44);
        fileOutputStream.write(headBuffer);
        inputStream.close();

        


        inputStream = main.getClass().getClassLoader().getResourceAsStream("yoko_screaming_at_art_show.wav");


        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);

        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        SourceDataLine directDL = (SourceDataLine) AudioSystem.getLine(info);

        directDL.open(audioInputStream.getFormat());
        directDL.start();

        byte[] bufferBytes = new byte[BUFFER_SIZE];
        int readBytes = -1;


        while ((readBytes = audioInputStream.read(bufferBytes)) != -1) {
            for (int index = 0; index < bufferBytes.length; ++index) {
                if (index % 2 == 0) {
                    continue;
                }

                bufferBytes[index] = (byte) (bufferBytes[index] * 6);
            }

//            fileOutputStream.write(bufferBytes, 0, bufferBytes.length);
            fileOutputStream.write(bufferBytes);
        }


//        Clip audioClip = (Clip) AudioSystem.getLine(info);
//        audioClip.addLineListener(main);
//        directDL.open(audioInputStream);
//        audioClip.start();

//        Thread.sleep(10000);

        directDL.drain();
        directDL.close();
        audioInputStream.close();
        fileOutputStream.close();
//        byteArrayInputStream.close();
    }

}
