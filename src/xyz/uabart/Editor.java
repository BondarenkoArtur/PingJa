package xyz.uabart;

import javax.swing.*;
import java.awt.*;

public class Editor {
    private JFrame frame;
    private JTextField textArea;
    private JPanel panel;
    private JButton button;

    public Editor() {
        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Ping this");

        panel.add(button);

        textArea = new JTextField("google.com");
        JScrollPane scrollPane = new JScrollPane(textArea);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.VERTICAL;
        panel.add(scrollPane, c);
        panel.add(button, c);

        frame.getContentPane().add(panel);
        frame.getRootPane().setDefaultButton(button);

        button.addActionListener( e -> Main.pingAdress(textArea.getText()));

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600,100);
        frame.setVisible(true);

    }
}
