package osu.java.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.DrawUtil;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    private CartesianImage canvas;

    public ImagePanel() {
        canvas = new CartesianImage(Integer.valueOf(501), Integer.valueOf(501), CartesianImage.TYPE_INT_ARGB);
        fillCanvas(Color.WHITE);
        fillBorder();
        drawCartesianCoordinateSystem();
    }

    private void drawCartesianCoordinateSystem() {
        for (int x = -220; x <= 220; x++) {
            canvas.setRGB(x, 0, Color.BLACK.getRGB());
        }
        for (int y = -220; y <= 220; y++) {
            canvas.setRGB(0, y, Color.BLACK.getRGB());
        }
        DrawUtil.Brez(220, 0, 200, 10, canvas, Color.BLACK);
        DrawUtil.Brez(220, 0, 200, -10, canvas, Color.BLACK);
        DrawUtil.Brez(0, 220, 10, 200, canvas, Color.BLACK);
        DrawUtil.Brez(0, 220, -10, 200, canvas, Color.BLACK);
        repaint();
    }

    private void fillBorder() {
        for (int x = -250; x <= 250; x++) {
            canvas.setRGB(x, -250, Color.BLACK.getRGB());
            canvas.setRGB(x, 250, Color.BLACK.getRGB());
        }
        for (int y = -250; y <= 250; y++) {
            canvas.setRGB(-250, y, Color.BLACK.getRGB());
            canvas.setRGB(250, y, Color.BLACK.getRGB());
        }
        repaint();
    }

    private void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = -250; x <= 250; x++) {
            for (int y = -250; y <= 250; y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(canvas, null, null);
    }

    /**
     * @return the canvas
     */
    public CartesianImage getCanvas() {
        return canvas;
    }

    /**
     * @param canvas
     *            the canvas to set
     */
    public void setCanvas(CartesianImage canvas) {
        this.canvas = canvas;
    }

}