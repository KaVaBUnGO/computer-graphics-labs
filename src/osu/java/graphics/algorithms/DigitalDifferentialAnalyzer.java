package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;
import osu.java.graphics.util.DrawUtil;


public class DigitalDifferentialAnalyzer implements DrawingAlgoStrategy {

  public DigitalDifferentialAnalyzer(){
    System.out.println("using Digital differential analyzer line algorithm");
  }
  
  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    Point p1 = points.get(0), p2 = points.get(1);
    DrawDDALine(p1.x, p1.y, p2.x, p2.y, canvas, color);
  }

  public void DrawDDALine(int x1, int y1, int x2, int y2, CartesianImage image, Color color) {
    int length = (Math.abs(x2 - x1) >= Math.abs(y2 - y1)) ? Math.abs(x2 - x1) : Math.abs(y2 - y1);
    float dx = (float) (x2 - x1) / length, dy = (float) (y2 - y1) / length;
    float x = (float) (x1 + 0.5 * DrawUtil.sign(dx));
    float y = (float) (y1 + 0.5 * DrawUtil.sign(dy));
    int i = 0;
    while (++i <= length) {
      image.setRGB(Math.round(x), Math.round(y), color.getRGB());
      x += dx;
      y += dy;
    }
  }

  @Override
  public int getTotalPoints() {
    return 2;
  }
}
