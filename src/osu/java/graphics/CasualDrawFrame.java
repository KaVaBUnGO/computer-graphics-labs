package osu.java.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import osu.java.graphics.algorithms.Bresenham;
import osu.java.graphics.algorithms.DigitalDifferentialAnalyzer;
import osu.java.graphics.customUI.StatusBar;


@SuppressWarnings("serial")
public class CasualDrawFrame extends JFrame {

  private ImagePanel imagePannel;
  private StatusBar statusBar;

  public CasualDrawFrame() {
    initUI();
  }

  private void initUI() {
    JPanel mainVerticalPanel = new JPanel();
    mainVerticalPanel.setLayout(new BoxLayout(mainVerticalPanel, BoxLayout.Y_AXIS));
    mainVerticalPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
    add(mainVerticalPanel);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    // mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    mainVerticalPanel.add(mainPanel);
    //mainVerticalPanel.setBorder(BorderFactory.createEmptyBorder());
    JRadioButton ddaButton = new JRadioButton("DDA-line");
    ddaButton.setActionCommand("DDA");
    ddaButton.setSelected(true);
    JRadioButton bresenhamButton = new JRadioButton("Bresenham's line");
    bresenhamButton.setActionCommand("Bresenham");

    ButtonGroup drawLinesAlgosGroup = new ButtonGroup();
    drawLinesAlgosGroup.add(ddaButton);
    drawLinesAlgosGroup.add(bresenhamButton);

    JButton closeButton = new JButton("Close");
    closeButton.setMnemonic(KeyEvent.VK_ESCAPE);
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });

    JButton clearButton = new JButton("Clear");
    clearButton.setMnemonic(KeyEvent.VK_C);
    clearButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        imagePannel.clearCanvas();
      }
    });

    imagePannel = new ImagePanel();
    ddaButton.addActionListener(new ChoiceAlgorithmListner());
    bresenhamButton.addActionListener(new ChoiceAlgorithmListner());

    mainPanel.add(imagePannel);

    JPanel rightPanel = new JPanel();
    rightPanel.setAlignmentY(-1f);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.add(ddaButton);
    radioPanel.add(bresenhamButton);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Drawing line algorithms"));

    rightPanel.add(radioPanel, BorderLayout.LINE_START);
    rightPanel.add(clearButton);
    rightPanel.add(closeButton);
    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
    mainPanel.add(rightPanel);

    statusBar = new StatusBar();
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    imagePannel.setStatusBar(statusBar);
    add(statusBar, BorderLayout.SOUTH);

    setTitle("Hello in Casual drow main window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);
  }

  private class ChoiceAlgorithmListner implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("DDA")) {
        imagePannel.setDrawingAlgo(new DigitalDifferentialAnalyzer());
      } else if (e.getActionCommand().equals("Bresenham")) {
        imagePannel.setDrawingAlgo(new Bresenham());
      }
    }
  }

  /**
   * @return the statusBar
   */
  public StatusBar getStatusBar() {
    return statusBar;
  }

  /**
   * @param statusBar the statusBar to set
   */
  public void setStatusBar(StatusBar statusBar) {
    this.statusBar = statusBar;
  }


}
