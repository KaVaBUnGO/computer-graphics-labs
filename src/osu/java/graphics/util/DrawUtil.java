package osu.java.graphics.util;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class DrawUtil {

  public static final int BLACK = Color.black.getRGB();
  public static final int BLUE = Color.blue.getRGB();

  public static int toScreenX(int x) {
    return x + 250;
  }

  public static int toScreenY(int y) {
    return 250 - y;
  }

  public static int toCartesianX(int x) {
    return x + 250;
  }

  public static int toCartesianY(int y) {
    return 250 - y;
  }

  public static int sign(float x) {
    return (x < 0 ? -1 : (x > 0 ? 1 : 0));
  }

  public static List<Point> getPointList(Point p1, Point p2) {
    List<Point> points = new ArrayList<Point>();
    points.add(p1);
    points.add(p2);
    return points;
  }
  
  public static List<Point> getPointList(Point p1, Point p2, Point p3) {
    List<Point> points = new ArrayList<Point>();
    points.add(p1);
    points.add(p2);
    points.add(p3);
    return points;
  }

}
