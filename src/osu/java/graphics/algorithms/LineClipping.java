package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;
import osu.java.graphics.util.DrawUtil;

public class LineClipping implements DrawingAlgoStrategy {



  public LineClipping() {
    System.out.println("using line clipping algorithm");
  }

  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    System.out.println("lalala");
    Point r1 = points.get(0), r2 = points.get(1), l1 = points.get(2), l2 = points.get(3);
    drawRectangle(r1, r2, canvas, color);
    drawLineClipping(r1, r2, l1, l2, canvas, color);
  }

  private void drawLineClipping(Point r1, Point r2, Point l1, Point l2, CartesianImage image,
      Color color) {
    Bresenham br = new Bresenham();
    br.drawObject(DrawUtil.getPointList(l1, l2), image, color);
    List<Point> nl = CohenSutherland.CohenSutherlandLineClip(r1, r2, l1, l2);
    if (nl != null){
      br.drawObject(nl, image, Color.GREEN);
    }
  }

  private void drawRectangle(Point p1, Point p2, CartesianImage image, Color color) {
    Bresenham br = new Bresenham();
    br.drawObject(DrawUtil.getPointList(p1, new Point(p1.x, p2.y+2)), image, color);
    br.drawObject(DrawUtil.getPointList(p1, new Point(p2.x, p1.y)), image, color);
    br.drawObject(DrawUtil.getPointList(new Point(p1.x, p2.y), p2), image, color);
    br.drawObject(DrawUtil.getPointList(p2,new Point(p2.x, p1.y+2)), image, color);
  }

  @Override
  public int getTotalPoints() {
    return 4;
  }


}
