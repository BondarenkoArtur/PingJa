package xyz.uabart;

import java.awt.image.BufferedImage;

public class Main {
    static Pinger pinger;

    public static void main(String[] args) {
        GUI.createAndShowGUI();

        pinger = new Pinger("127.0.0.1");
        pinger.start();

    }

    static void changeTrayIcon(int number){
        BufferedImage bufferedImage = ImageWorker.toBufferedImage(number);
        GUI.changeTrayIcon(bufferedImage);
    }

    static String getIP(){
        return pinger.pingAdress;
    }

    static void pingGoogle() {
        pinger.stop();
        pinger = new Pinger("google.com");
        pinger.start();
    }

    static void pingAdress(String adress) {
        if (adress != null) {
            pinger.stop();
            pinger = new Pinger(adress);
            pinger.start();
        }
    }

    static void pingerStop(){
        pinger.stop();
    }
}