package osu.java.graphics.algorithms;

import java.awt.Point;
import java.util.List;

import osu.java.graphics.util.DrawUtil;

public class CohenSutherland {

  /* Битовые маски используются для разделелния канваса на 9 частей */
  private final static byte INSIDE = 0; // 0000
  private final static byte LEFT = 1; // 0001
  private final static byte RIGHT = 2; // 0010
  private final static byte BOTTOM = 4;// 0100
  private final static byte TOP = 8; // 1000



  /** Вычисление битовой маски точки */
  public static byte computeOutCode(int x, int y, Point r1, Point r2) {

    int left = Math.min(r1.x, r2.x);
    int right = Math.max(r1.x, r2.x);
    int top = Math.max(r1.y, r2.y);
    int bottom = Math.min(r1.y, r2.y);

    byte code = INSIDE;

    if (x < left) {
      code |= LEFT;
    } else if (x > right) {
      code |= RIGHT;
    } else if (y < bottom) {
      code |= BOTTOM;
    } else if (y > top) {
      code |= TOP;
    }

    return code;
  }

  public static List<Point> CohenSutherlandLineClip(Point r1, Point r2, Point p0, Point p1) {

    int left = Math.min(r1.x, r2.x);
    int right = Math.max(r1.x, r2.x);
    int top = Math.max(r1.y, r2.y);
    int bottom = Math.min(r1.y, r2.y);

    int x0 = p0.x;
    int y0 = p0.y;
    int x1 = p1.x;
    int y1 = p1.y;

    byte outcode0 = computeOutCode(x0, y0, r1, r2);
    byte outcode1 = computeOutCode(x1, y1, r1, r2);
    boolean accept = false;

    while (true) {
      if ((outcode0 | outcode1) == 0) {
        accept = true;
        break;
      } else if ((outcode0 & outcode1) != 0) {
        break;
      } else {
        int x, y;

        byte outcodeOut = (outcode0 != 0) ? outcode0 : outcode1;

        if ((outcodeOut & TOP) != 0) {
          x = x0 + (x1 - x0) * (top - y0) / (y1 - y0);
          y = top;
        } else if ((outcodeOut & BOTTOM) != 0) {
          x = x0 + (x1 - x0) * (bottom - y0) / (y1 - y0);
          y = bottom;
        } else if ((outcodeOut & RIGHT) != 0) {
          y = y0 + (y1 - y0) * (right - x0) / (x1 - x0);
          x = top;
        } else if ((outcodeOut & LEFT) != 0) {
          y = y0 + (y1 - y0) * (left - x0) / (x1 - x0);
          x = left;
        } else {
          x = (int)Double.NaN;
          y = (int)Double.NaN;
        }

        if (outcodeOut == outcode0) {
          x0 = x;
          y0 = y;
          outcode0 = computeOutCode(x0, y0, r1, r2);
        } else {
          x1 = x;
          y1 = y;
          outcode1 = computeOutCode(x1, y1, r1, r2);
        }
      }
    }

    return (accept) ? DrawUtil.getPointList(new Point(x0, y0), new Point(x1, y1)) : null;
  }


}
