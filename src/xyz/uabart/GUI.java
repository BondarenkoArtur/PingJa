package xyz.uabart;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GUI {
    static TrayIcon trayIcon;

    static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(ImageWorker.toBufferedImage(0));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a popup menu components
        MenuItem googleItem = new MenuItem("Ping google.com");
        MenuItem editItem = new MenuItem("Edit Address");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to popup menu
        popup.add(googleItem);
        popup.add(editItem);
        popup.addSeparator();
        popup.add(aboutItem);
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "PingJa by uaBArt\n2016"));

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "PingJa by uaBArt\n2016"));

        exitItem.addActionListener(e -> {
            Main.pingerStop();
            System.exit(0);
        });

        editItem.addActionListener(e -> new Editor());

        googleItem.addActionListener(e -> Main.pingGoogle());

    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    static void changeTrayIcon(Image image){
        trayIcon.setImage(image);
        trayIcon.setToolTip(Main.getIP());
    }
}
