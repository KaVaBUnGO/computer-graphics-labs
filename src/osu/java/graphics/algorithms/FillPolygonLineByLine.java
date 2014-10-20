package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.Stack;
import osu.java.graphics.CartesianImage;


public class FillPolygonLineByLine implements DrawingAlgoStrategy {
  public FillPolygonLineByLine() {
    System.out.println("using filling seeded line by line algorithm");
  }

  @Override
  public void drawObject(List<Point> points, CartesianImage canvas, Color color) {
    Point p1 = points.get(0);
    filling(p1, canvas, color);
  }

  public void filling(Point p, CartesianImage image, Color color) {
    Stack<Point> st = new Stack<Point>();
    Point pixel = new Point();
    int fillColor = Color.GREEN.getRGB(), borderColor = Color.BLUE.getRGB();
    int tempX = 0, xRight = 0, xLeft = 0, xIn = 0;
    boolean flag = false;
    st.push(p);
    while (!st.empty()) {
      pixel = st.pop();
      image.setRGB(pixel.x, pixel.y, fillColor);
      tempX = pixel.x;
      pixel.x++;
      while (image.getRGB(pixel.x, pixel.y) != borderColor) {
        image.setRGB(pixel.x, pixel.y, fillColor);
        pixel.x++;
      }
      xRight = pixel.x - 1;
      pixel.x = tempX;
      pixel.x--;
      while (image.getRGB(pixel.x, pixel.y) != borderColor) {
        image.setRGB(pixel.x, pixel.y, fillColor);
        pixel.x--;
      }
      xLeft = pixel.x + 1;
      // Ищем затравку на строке выше
      pixel.x = xLeft;
      pixel.y++;
      while (pixel.x <= xRight) {
        flag = false;
        while (image.getRGB(pixel.x, pixel.y) != fillColor
            && image.getRGB(pixel.x, pixel.y) != borderColor && pixel.x < xRight) {
          if (flag = false)
            flag = true;
          pixel.x++;
        }
        if (flag) {
          if (pixel.x == xRight && image.getRGB(pixel.x, pixel.y) != fillColor
              && image.getRGB(pixel.x, pixel.y) != borderColor)
            st.push(pixel);
          else
            st.push(new Point(pixel.x - 1, pixel.y));
          flag = false;
        }
        xIn = pixel.x;
        while ((image.getRGB(pixel.x, pixel.y) == fillColor || image.getRGB(pixel.x, pixel.y) == borderColor)
            && (pixel.x < xRight))
          pixel.x++;
        if (pixel.x == xIn)
          pixel.x++;
      }
      pixel.x = xLeft;
      pixel.y -= 2;
      while (pixel.x <= xRight) {
        flag = false;
        while (image.getRGB(pixel.x, pixel.y) != fillColor
            && image.getRGB(pixel.x, pixel.y) != borderColor && pixel.x < xRight) {
          if (flag = false)
            flag = true;
          pixel.x++;
        }
        if (flag) {
          if (pixel.x == xRight && image.getRGB(pixel.x, pixel.y) != fillColor
              && image.getRGB(pixel.x, pixel.y) != borderColor)
            st.push(pixel);
          else
            st.push(new Point(pixel.x - 1, pixel.y));
          flag = false;
        }
        xIn = pixel.x;
        while ((image.getRGB(pixel.x, pixel.y) == fillColor || image.getRGB(pixel.x, pixel.y) == borderColor)
            && (pixel.x < xRight))
          pixel.x++;
        if (pixel.x == xIn)
          pixel.x++;
      }
    }
  }

  @Override
  public int getTotalPoints() {
    return 1;
  }
}
