package osu.java.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import util.DrawUtil;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private BufferedImage canvas;

	public ImagePanel() {
		canvas = new BufferedImage(Integer.valueOf(500), Integer.valueOf(500),
				BufferedImage.TYPE_INT_ARGB);
		fillCanvas(Color.WHITE);
		fillBorder();
		drawCartesianCoordinateSystem();
	}

	private void drawCartesianCoordinateSystem() {
		for (int x = 30; x < canvas.getWidth()-30; x++) {
			canvas.setRGB(x, 250, Color.BLACK.getRGB());
		}
		for (int y = 30; y < canvas.getHeight()-30; y++) {
			canvas.setRGB(250, y, Color.BLACK.getRGB());
		}
		DrawUtil.Brez(220, 0, 200, 10, canvas, Color.BLACK);
		DrawUtil.Brez(220, 0 , 200, -10, canvas, Color.BLACK);
		DrawUtil.Brez(0, 220 , 10 , 200, canvas, Color.BLACK);
		DrawUtil.Brez(0, 220 , -10 , 200, canvas, Color.BLACK);
		repaint();
	}

	private void fillBorder() {
		for (int x = 0; x < canvas.getWidth(); x++) {
			canvas.setRGB(x, 0, Color.BLACK.getRGB());
			canvas.setRGB(x, canvas.getHeight() - 1, Color.BLACK.getRGB());
		}
		for (int y = 0; y < canvas.getHeight(); y++) {
			canvas.setRGB(0, y, Color.BLACK.getRGB());
			canvas.setRGB(canvas.getWidth() - 1, y, Color.BLACK.getRGB());
		}
		repaint();

	}

	private void fillCanvas(Color c) {
		int color = c.getRGB();
		for (int x = 0; x < canvas.getWidth(); x++) {
			for (int y = 0; y < canvas.getHeight(); y++) {
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
	public BufferedImage getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(BufferedImage canvas) {
		this.canvas = canvas;
	}

}