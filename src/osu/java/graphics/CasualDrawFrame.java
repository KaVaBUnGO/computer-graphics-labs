package osu.java.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import osu.java.graphics.algorithms.Bresenham;
import osu.java.graphics.algorithms.BresenhamArc;
import osu.java.graphics.algorithms.BresenhamCircle;
import osu.java.graphics.algorithms.DigitalDifferentialAnalyzer;
import osu.java.graphics.algorithms.FillPolygon;
import osu.java.graphics.algorithms.FillPolygonLineByLine;
import osu.java.graphics.algorithms.LineClipping;
import osu.java.graphics.customUI.StatusBar;


@SuppressWarnings("serial")
public class CasualDrawFrame extends JFrame {

  private ImagePanel imagePannel;
  private StatusBar statusBar;
  private JRadioButton ddaButton = new JRadioButton("DDA-line");


  public CasualDrawFrame() {
    initUI();
  }

  private void initUI() {
    JPanel mainVerticalPanel = new JPanel();
    mainVerticalPanel.setLayout(new BoxLayout(mainVerticalPanel, BoxLayout.Y_AXIS));
    mainVerticalPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 5, 30));
    add(mainVerticalPanel);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    // mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    mainVerticalPanel.add(mainPanel);
    // mainVerticalPanel.setBorder(BorderFactory.createEmptyBorder());
    ddaButton.setActionCommand("DDA");
    ddaButton.setSelected(true);
    JRadioButton bresenhamButton = new JRadioButton("Bresenham's line");
    bresenhamButton.setActionCommand("Bresenham");
    JRadioButton bresCircleButton = new JRadioButton("Bresenham's circle");
    bresCircleButton.setActionCommand("BresenhamCircle");
    JRadioButton bresArcButton = new JRadioButton("Bresenham's arc");
    bresArcButton.setActionCommand("BresenhamArc");
    JRadioButton fillSeededButton = new JRadioButton("Fill polygon");
    fillSeededButton.setActionCommand("FillPolygon");
    JRadioButton fillSeededLineButton = new JRadioButton("Fill polygon line algo");
    fillSeededLineButton.setActionCommand("FillPolygonLine");
    JRadioButton lineClippingButton = new JRadioButton("Line Clipping button");
    lineClippingButton.setActionCommand("LineClipping");
    ButtonGroup drawLinesAlgosGroup = new ButtonGroup();
    drawLinesAlgosGroup.add(ddaButton);
    drawLinesAlgosGroup.add(bresenhamButton);
    drawLinesAlgosGroup.add(bresCircleButton);
    drawLinesAlgosGroup.add(bresArcButton);
    drawLinesAlgosGroup.add(fillSeededButton);
    drawLinesAlgosGroup.add(fillSeededLineButton);
    drawLinesAlgosGroup.add(lineClippingButton);


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
        ddaButton.setSelected(true);
        imagePannel.setDrawingAlgo(new DigitalDifferentialAnalyzer());
        imagePannel.clearCanvas();
      }
    });

    imagePannel = new ImagePanel();
    ddaButton.addActionListener(new ChoiceAlgorithmListner());
    bresenhamButton.addActionListener(new ChoiceAlgorithmListner());
    bresCircleButton.addActionListener(new ChoiceAlgorithmListner());
    bresArcButton.addActionListener(new ChoiceAlgorithmListner());
    fillSeededButton.addActionListener(new ChoiceAlgorithmListner());
    fillSeededLineButton.addActionListener(new ChoiceAlgorithmListner());
    lineClippingButton.addActionListener(new ChoiceAlgorithmListner());
    mainPanel.add(imagePannel);

    JPanel rightPanel = new JPanel();
    rightPanel.setAlignmentY(-1f);
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    radioPanel.add(ddaButton);
    radioPanel.add(bresenhamButton);
    radioPanel.add(bresCircleButton);
    radioPanel.add(bresArcButton);
    radioPanel.add(fillSeededButton);
    radioPanel.add(fillSeededLineButton);
    radioPanel.add(lineClippingButton);
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
      } else if (e.getActionCommand().equals("BresenhamCircle")) {
        imagePannel.setDrawingAlgo(new BresenhamCircle());
      } else if (e.getActionCommand().equals("BresenhamArc")) {
        imagePannel.setDrawingAlgo(new BresenhamArc());
      } else if (e.getActionCommand().equals("FillPolygon")) {
        imagePannel.setDrawingAlgo(new FillPolygon());
      } else if (e.getActionCommand().equals("FillPolygonLine")) {
        imagePannel.setDrawingAlgo(new FillPolygonLineByLine());
      } else if (e.getActionCommand().equals("LineClipping")){
        imagePannel.setDrawingAlgo(new LineClipping());
      }
      imagePannel.getCurrentPoints().clear();
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
