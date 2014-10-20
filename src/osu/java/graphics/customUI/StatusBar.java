package osu.java.graphics.customUI;

import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusBar extends JLabel {

  public StatusBar() {
    super();
    setMessage("Ready");
  }

  public void setMessage(String message) {
    setText(" " + message);
  }
  
  @Override
  public void paintComponents(Graphics g) {
    super.paintComponents(g);
    super.setSize(800, 20);
  }
}
