package osu.java.graphics;

import java.awt.image.BufferedImage;

import util.DrawUtil;

public class CartesianImage extends BufferedImage {

    public CartesianImage(int arg0, int arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public synchronized void setRGB(int x, int y, int rgb) {
        //System.out.println(x + " " + DrawUtil.toScreenX(x) + " | " + y + " " + DrawUtil.toScreenY(y));
        super.setRGB(DrawUtil.toScreenX(x), DrawUtil.toScreenY(y), rgb);
    }

}
