package osu.java.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;

import osu.java.graphics.util.DrawUtil;

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

    JRadioButton standartButton = new JRadioButton("Standart algo");
    standartButton.setActionCommand("standart");
    standartButton.setSelected(true);

    JRadioButton brezButton = new JRadioButton("Brezenhem algo");
    brezButton.setActionCommand("brezenhem");

    ButtonGroup drawLinesAlgosGroup = new ButtonGroup();
    drawLinesAlgosGroup.add(standartButton);
    drawLinesAlgosGroup.add(brezButton);

    JButton ok = new JButton("OK");
    JButton close = new JButton("Close");
    ImagePanel ip = new ImagePanel();

    mainPanel.add(ip);
    mainPanel.add(Box.createRigidArea(new Dimension(30, 0)));
    JPanel rightPanel = new JPanel();
    rightPanel.setAlignmentY(-1f);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.add(standartButton);
    radioPanel.add(brezButton);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Line algos"));

    rightPanel.add(radioPanel, BorderLayout.LINE_START);
    rightPanel.add(ok);
    rightPanel.add(close);
    mainPanel.add(rightPanel);

    setTitle("Hello in Casual drow main window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);
    drow(ip);
  }

  private void drow(ImagePanel ip) {
    
  }

  public void actionPerformed(ActionEvent e) {

  }

}
