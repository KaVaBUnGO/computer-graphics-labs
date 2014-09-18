package osu.java.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import osu.java.graphics.util.DrawUtil;
import osu.java.graphics.util.Line;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

  private CartesianImage canvas;
  Point p = null;
  Line tempLine = null;

  public ImagePanel() {
    canvas =
        new CartesianImage(Integer.valueOf(501), Integer.valueOf(501), CartesianImage.TYPE_INT_ARGB);
    fillCanvas(Color.WHITE);
    drawCartesianCoordinateSystem();
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  private void drawCartesianCoordinateSystem() {
    for (int x = -220; x <= 220; x++) {
      canvas.setRGB(x, 0, Color.BLACK.getRGB());
    }
    for (int y = -220; y <= 220; y++) {
      canvas.setRGB(0, y, Color.BLACK.getRGB());
    }
    DrawUtil.Brez(220, 0, 200, 10, canvas, Color.BLACK);
    DrawUtil.Brez(220, 0, 200, -10, canvas, Color.BLACK);
    DrawUtil.Brez(0, 220, 10, 200, canvas, Color.BLACK);
    DrawUtil.Brez(0, 220, -10, 200, canvas, Color.BLACK);
    repaint();
  }

  private void fillCanvas(Color c) {
    int color = c.getRGB();
    for (int x = -250; x <= 250; x++) {
      for (int y = -250; y <= 250; y++) {
        canvas.setRGB(x, y, color);
      }
    }
    repaint();

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setSize(500, 500);
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(canvas, null, null);
  }

  /**
   * @return the canvas
   */
  public CartesianImage getCanvas() {
    return canvas;
  }

  /**
   * @param canvas the canvas to set
   */
  public void setCanvas(CartesianImage canvas) {
    this.canvas = canvas;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    e.translatePoint(-250, -250 - 2 * (e.getY() - 250));
    if (p == null) {
      p = e.getPoint();
    } else {
      if (tempLine != null) {
        DrawUtil.Brez(tempLine.getP1().x, tempLine.getP1().y, tempLine.getP2().x,
            tempLine.getP2().y, canvas, Color.WHITE);
        tempLine = null;
      }
      DrawUtil.Brez(p.x, p.y, e.getX(), e.getY(), canvas, Color.GREEN);
      p = null;
    }

  }



  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    e.translatePoint(-250, -250 - 2 * (e.getY() - 250));
    if (p != null) {
      if (tempLine != null) {
        DrawUtil.Brez(tempLine.getP1().x, tempLine.getP1().y, tempLine.getP2().x,
            tempLine.getP2().y, canvas, Color.WHITE);
      }
      DrawUtil.Brez(p.x, p.y, e.getX(), e.getY(), canvas, Color.LIGHT_GRAY);
      tempLine = new Line(p, e.getPoint());
    }
  }

}
