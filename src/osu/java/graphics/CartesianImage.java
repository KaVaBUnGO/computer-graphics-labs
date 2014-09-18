package osu.java.graphics;

import java.awt.image.BufferedImage;

import osu.java.graphics.util.DrawUtil;


public class CartesianImage extends BufferedImage {

  public CartesianImage(int arg0, int arg1, int arg2) {
    super(arg0, arg1, arg2);
  }

  @Override
  public synchronized void setRGB(int x, int y, int rgb) {
    super.setRGB(DrawUtil.toScreenX(x), DrawUtil.toScreenY(y), rgb);
  }

}
