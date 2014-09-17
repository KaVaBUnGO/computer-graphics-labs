package osu.java.graphics;

import javax.swing.*;

public class CasualDrawStarter {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CasualDrawFrame cDrow = new CasualDrawFrame();
                cDrow.setVisible(true);
            }
        });
    }
}