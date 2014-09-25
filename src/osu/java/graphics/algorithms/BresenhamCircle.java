package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;
import static java.lang.Math.*;

public class BresenhamCircle implements DrawingAlgoStrategy {
  public BresenhamCircle() {
    System.out.println("using Bresenham's circle algorithm");
  }

  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    Point p1 = points.get(0), p2 = points.get(1);
    int r = (int) sqrt(pow((p1.x - p2.x), 2) + pow((p1.y - p2.y), 2));
    DrawBresCircle(p1.x, p1.y, r, canvas, color);
  }

  public void DrawBresCircle(int x1, int y1, int r, CartesianImage image, Color color) {
    int x = 0, y = r, d = 2 * (1 - r), d1 = 0, d2 = 0;
    double limit = 0;
    int vector[] = {-1, 1};

    while (true) {
      for (int vx : vector) {
        for (int vy : vector) {
          if (validateAxis(vx * x + x1, vy * y + y1, image))
            image.setRGB(vx * x + x1, vy * y + y1, color.getRGB());
        }
      }
      if (y <= limit)
        break;
      if (d < 0) {
        d1 = 2 * (d + y) - 1;
        if (d1 <= 0) {
          x++;
          d += 2 * x + 1;
        } else {
          x++;
          y--;
          d += 2 * (x - y + 1);
        }
      } else if (d > 0) {
        d2 = 2 * (d - x) - 1;
        if (d2 <= 0) {
          x++;
          y--;
          d += 2 * (x - y + 1);
        } else {
          y--;
          d = d - 2 * y + 1;
        }
      } else {
        x++;
        y--;
        d += 2 * (x - y + 1);
      }
    };
  }

  private boolean validateAxis(int x, int y, CartesianImage image) {
    return ((x >= image.getWidth() / 2) || (x <= -image.getWidth() / 2)
        || (y >= image.getHeight() / 2) || (y <= -image.getWidth() / 2)) ? false : true;
  }

  @Override
  public int getTotalPoints() {
    return 2;
  }
}
