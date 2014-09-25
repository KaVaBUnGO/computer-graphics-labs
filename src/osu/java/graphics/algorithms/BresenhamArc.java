package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;
import static java.lang.Math.*;

public class BresenhamArc implements DrawingAlgoStrategy {
  public BresenhamArc() {
    System.out.println("using Bresenham's arc algorithm");
  }

  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    Point p1 = points.get(0), p2 = points.get(1), p3 = points.get(2);
    Point p0 = new Point(p1.x + 1, p1.y);
    double ang1 = getAngleBetweenThreePoints(p1, p0, p2);
    double ang2 = getAngleBetweenThreePoints(p1, p0, p3);
    //System.out.println(ang1 + " " + ang2);
    int r = (int) sqrt(pow((p1.x - p2.x), 2) + pow((p1.y - p2.y), 2));
    DrawBresCircle(p1.x, p1.y, r, ang1, ang2, canvas, color);
  }

  public void DrawBresCircle(int x1, int y1, int r, double ang1, double ang2, CartesianImage image,
      Color color) {
    int x = 0, y = r, d = 2 * (1 - r), d1 = 0, d2 = 0;
    System.out.println(r);
    double limit = 0;
    int vector[] = {-1, 1};

    while (true) {
      for (int vx : vector) {
        for (int vy : vector) {
          if (validateAxis(vx * x + x1, vy * y + y1, new Point(x1, y1), ang1, ang2, image))
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

  private boolean validateAxis(int x, int y, Point mid, double ang1, double ang2,
      CartesianImage image) {
    double tAng = getAngleBetweenThreePoints(mid, new Point(mid.x + 1, mid.y), new Point(x, y));
    if ((x >= image.getWidth() / 2) || (x <= -image.getWidth() / 2) || (y >= image.getHeight() / 2)
        || (y <= -image.getWidth() / 2))
      return false;
    if (ang2 < ang1) {
      if ((tAng >= ang2 && tAng <= ang1))
        return false;
    } else {
      if (!(tAng >= ang1 && tAng <= ang2))
        return false;
    }
    return true;
  }

  private double getAngleBetweenThreePoints(Point p1, Point p2, Point p3) {
    double tAngle =
        acos((pow(getSegmentLength(p1, p2), 2) + pow(getSegmentLength(p1, p3), 2) - pow(
            getSegmentLength(p2, p3), 2))
            / (2 * getSegmentLength(p1, p2) * getSegmentLength(p1, p3)));
    if (p3.y < p1.y) {
      tAngle += 2 * (Math.PI - tAngle);
    }
    return tAngle;
  }

  private double getSegmentLength(Point p1, Point p2) {
    return sqrt(pow(p1.x - p2.x, 2) + (pow(p1.y - p2.y, 2)));
  }

  @Override
  public int getTotalPoints() {
    return 3;
  }
}
