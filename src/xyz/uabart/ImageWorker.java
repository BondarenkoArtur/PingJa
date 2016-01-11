package xyz.uabart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageWorker {

    private static void save(BufferedImage image, String ext) {
        String fileName = "savingAnImage";
        File file = new File(fileName + "." + ext);
        try {
            ImageIO.write(image, ext, file);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
    }

    public static BufferedImage toBufferedImage(int number) {
        int w = 16;
        int h = 16;
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        if (number == 0)
            g2.setColor(Color.gray);
        else if (number < 50)
            g2.setColor(Color.green);
        else if (number < 100)
            g2.setColor(Color.yellow);
        else
            g2.setColor(Color.red);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.BLACK);
        if (number >= 0 && number < 10) {
            g2.setFont(new Font("Default", Font.PLAIN, 12));
            g2.drawString(number + "", 5, h - 4); //hardcode
        }
        else if (number >= 10 && number < 100) {
            g2.setFont(new Font("Default", Font.PLAIN, 12));
            g2.drawString(number + "", 1, h - 4); //hardcode
        }
        else if (number >= 100 && number < 1000) {
            g2.setFont(new Font("Default", Font.PLAIN, 9));
            g2.drawString(number + "", 1, h - 5); //hardcode
        }
        else {
            g2.setFont(new Font("Default", Font.PLAIN, 9));
            g2.drawString("999", 1, h - 5); //hardcode
        }

        g2.dispose();
        return dest;
    }
}
