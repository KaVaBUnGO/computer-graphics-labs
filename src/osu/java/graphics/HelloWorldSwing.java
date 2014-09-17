package osu.java.graphics;

import javax.swing.*;

public class HelloWorldSwing {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MyFunnyDrower!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        
        ImagePanel ip = new ImagePanel();
        frame.add(ip);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}