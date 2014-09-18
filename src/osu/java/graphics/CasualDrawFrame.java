package osu.java.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import osu.java.graphics.algorithms.Bresenham;
import osu.java.graphics.algorithms.DigitalDifferentialAnalyzer;


@SuppressWarnings("serial")
public class CasualDrawFrame extends JFrame {

  private ImagePanel imagePannel;
  
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

    JRadioButton ddaButton = new JRadioButton("DDA-line");
    ddaButton.setActionCommand("DDA");
    ddaButton.setSelected(true);
    JRadioButton bresenhamButton = new JRadioButton("Bresenham's line");
    bresenhamButton.setActionCommand("Bresenham");

    ButtonGroup drawLinesAlgosGroup = new ButtonGroup();
    drawLinesAlgosGroup.add(ddaButton);
    drawLinesAlgosGroup.add(bresenhamButton);
    
    JButton close = new JButton("Close");
    imagePannel= new ImagePanel();

    ddaButton.addActionListener(new ChoiceAlgorithmListner());
    bresenhamButton.addActionListener(new ChoiceAlgorithmListner());
    
    mainPanel.add(imagePannel);
    mainPanel.add(Box.createRigidArea(new Dimension(30, 0)));
    JPanel rightPanel = new JPanel();
    rightPanel.setAlignmentY(-1f);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.add(ddaButton);
    radioPanel.add(bresenhamButton);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Drawing line algorithms"));

    rightPanel.add(radioPanel, BorderLayout.LINE_START);
    rightPanel.add(close);
    mainPanel.add(rightPanel);

    setTitle("Hello in Casual drow main window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);
  }

  private class ChoiceAlgorithmListner implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DDA")){
          imagePannel.setDrawingAlgo(new DigitalDifferentialAnalyzer());
        }else if (e.getActionCommand().equals("Bresenham")){
          imagePannel.setDrawingAlgo(new Bresenham());
        }
    }
}


}
