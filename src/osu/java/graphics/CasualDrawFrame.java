package osu.java.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import util.DrawUtil;

@SuppressWarnings("serial")
public class CasualDrawFrame extends JFrame {

    public CasualDrawFrame() {
        initUI();
    }

    private void initUI() {
        JPanel mainVerticalPanel = new JPanel();
        mainVerticalPanel.setLayout(new BoxLayout(mainVerticalPanel, BoxLayout.Y_AXIS));
        add(mainVerticalPanel);
        mainVerticalPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainVerticalPanel.add(mainPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        JPanel rightPanel = new JPanel();
        rightPanel.setAlignmentY(1f);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JButton ok = new JButton("OK");
        JButton close = new JButton("Close");
        ImagePanel ip = new ImagePanel();
		DrawUtil.DrowLine(10, -200, 30, 190, ip.getCanvas());
		DrawUtil.DrowLine(-10, 0, -250, -120, ip.getCanvas());
		DrawUtil.Brez(0, 0, -240, -120, ip.getCanvas(), Color.RED);
		DrawUtil.Brez(30, -200, 50, 190, ip.getCanvas(), Color.RED);
        mainPanel.add(ip);
        mainPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        rightPanel.add(ok);
        rightPanel.add(close);
        mainPanel.add(rightPanel);

        setTitle("Hello in Casual drow main window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

}
