package osu.java.graphics;

import javax.swing.JFrame;

public class CasualDrawFrame extends JFrame {

    public CasualDrawFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Hello in Casual drow main window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        ImagePanel ip = new ImagePanel();
        add(ip);
    }

}
