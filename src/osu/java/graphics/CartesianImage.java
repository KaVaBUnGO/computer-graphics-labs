package osu.java.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

import osu.java.graphics.util.DrawUtil;


public class CartesianImage extends BufferedImage {

  public CartesianImage(int width, int height, int imageType) {
    super(width, height, imageType);
  }

  /*
   * Constructs a new BufferedImage with a specified ColorModel and Raster
   */
  public CartesianImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied,
      Hashtable<?, ?> properties) {
    super(cm, raster, isRasterPremultiplied, properties);
  }

  @Override
  public synchronized void setRGB(int x, int y, int rgb) {
    super.setRGB(DrawUtil.toScreenX(x), DrawUtil.toScreenY(y), rgb);
  }

  @Override
  public int getRGB(int x, int y) {
    return super.getRGB(DrawUtil.toCartesianX(x), DrawUtil.toCartesianY(y));
  }


  public CartesianImage copy() {
    CartesianImage b = new CartesianImage(getWidth(), getHeight(), getType());
    Graphics g = b.getGraphics();
    g.drawImage(this, 0, 0, null);
    g.dispose(); // Избавляется от этого графического контекста
    return b;
  }

}
