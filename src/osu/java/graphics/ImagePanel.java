package osu.java.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import osu.java.graphics.algorithms.DigitalDifferentialAnalyzer;
import osu.java.graphics.algorithms.DrawingAlgoStrategy;
import osu.java.graphics.customUI.StatusBar;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

  private CartesianImage canvas;
  private Deque<CartesianImage> lastImages = new LinkedList<CartesianImage>();
  private Point p = null;
  private DrawingAlgoStrategy drawingAlgo;
  private StatusBar statusBar;
  
  public ImagePanel() {
    canvas =
        new CartesianImage(501, 501, CartesianImage.TYPE_INT_ARGB);
    setDrawingAlgo(new DigitalDifferentialAnalyzer());
    clearCanvas();
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public void clearCanvas(){
    fillCanvas(Color.WHITE);
    drawCartesianCoordinateSystem();
  }
  
  private void drawCartesianCoordinateSystem() {
    for (int x = -220; x <= 220; x++) {
      canvas.setRGB(x, 0, Color.BLACK.getRGB());
    }
    for (int y = -220; y <= 220; y++) {
      canvas.setRGB(0, y, Color.BLACK.getRGB());
    }
    getDrawingAlgo().drawObject(new Point(220, 0), new Point(200, 10), canvas, Color.BLACK);
    getDrawingAlgo().drawObject(new Point(220, 0), new Point(200, -10), canvas, Color.BLACK);
    getDrawingAlgo().drawObject(new Point(0, 220), new Point(10, 200), canvas, Color.BLACK);
    getDrawingAlgo().drawObject(new Point(0, 220), new Point(-10, 200), canvas, Color.BLACK);
    repaint();
  }
 //�������
  private void fillCanvas(Color c) {
    int color = c.getRGB();
    for (int x = -250; x <= 250; x++) {
      for (int y = -250; y <= 250; y++) {
        canvas.setRGB(x, y, color);
      }
    }
    repaint(); // Для программной инициализации перерисовки компонента 

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(canvas, null, null);
    setSize(501, 501);
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
      lastImages.add(canvas.copy());
      p = e.getPoint();
      getStatusBar().setText("First point: ("+e.getX()+" ,"+e.getY()+")");
      
    } else {
      paintCanvas(lastImages.peek());
      getDrawingAlgo().drawObject(p, e.getPoint(), canvas, Color.BLUE);
      p = null;
      lastImages.poll();
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
      if (lastImages.size() > 0) {
        paintCanvas(lastImages.peek());
      }
      getDrawingAlgo().drawObject(p, e.getPoint(), canvas, Color.RED);
      getStatusBar().setText("First point: ("+p.x+" ,"+p.y+");"+" Second point: ("+e.getX()+" ,"+e.getY()+");");
    }
  }

  private void paintCanvas(CartesianImage newCanvas) {
    for (int i = -250; i < 250; i++) {
      for (int j = -250; j < 250; j++) {
        canvas.setRGB(i, j, newCanvas.getRGB(i, j));
      }
    }
  }

  /**
   * @return the drawingAlgo
   */
  public DrawingAlgoStrategy getDrawingAlgo() {
    return drawingAlgo;
  }

  /**
   * @param drawingAlgo the drawingAlgo to set
   */
  public void setDrawingAlgo(DrawingAlgoStrategy drawingAlgo) {
    this.drawingAlgo = drawingAlgo;
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
