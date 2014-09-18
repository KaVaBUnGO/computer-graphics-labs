package osu.java.graphics.util;
import java.awt.Color;

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

}

