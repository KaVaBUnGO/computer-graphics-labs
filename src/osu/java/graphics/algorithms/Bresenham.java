package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;
import osu.java.graphics.util.DrawUtil;

public class Bresenham implements DrawingAlgoStrategy {

  public Bresenham() {
    System.out.println("using Bresenham's line algorithm");
  }

  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    Point p1 = points.get(0), p2 = points.get(1);
    drawBresenhamLine(p1.x, p1.y, p2.x, p2.y, canvas, color);
  }

  private void drawBresenhamLine(int x1, int y1, int x2, int y2, CartesianImage image, Color color) {
    int temp = 0;
    Boolean change = false;
    int x = x1, y = y1;
    int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
    int s1 = DrawUtil.sign(x2 - x1), s2 = DrawUtil.sign(y2 - y1);
    if (dy > dx) {
      temp = dx;
      dx = dy;
      dy = temp;
      change = true;
    } else {
      change = false;
    }
    int e = 2 * dy - dx;
    for (int i = 1; i < dx; i++) {
      image.setRGB(x, y, color.getRGB());
      while (e >= 0) {
        if (change) {
          x += s1;
        } else {
          y += s2;
        }
        e -= 2 * dx;
      }
      if (change) {
        y += s2;
      } else {
        x += s1;
      }
      e += 2 * dy;
    }
  }

  @Override
  public int getTotalPoints() {
    return 2;
  }


}
