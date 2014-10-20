package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.Stack;
import osu.java.graphics.CartesianImage;

public class FillPolygon implements DrawingAlgoStrategy {
	public FillPolygon() {
		System.out.println("using fill polygon algorithm");
	}

	@Override
	public void drawObject(List<Point> points, CartesianImage canvas,
			Color color) {
		Point p1 = points.get(0);
		filling(p1, canvas, color);
	}

	public void filling(Point p, CartesianImage image, Color color) {
		Stack<Point> st = new Stack<Point>();
		Point pixel = new Point();
		st.push(p);
		while (!st.empty()) {
			pixel = (Point) st.pop();
			if (image.getRGB(pixel.x, pixel.y) != Color.GREEN.getRGB())
				image.setRGB(pixel.x, pixel.y, Color.GREEN.getRGB());
			if (image.getRGB(pixel.x + 1, pixel.y) != color.getRGB()
					&& image.getRGB(pixel.x + 1, pixel.y) != Color.BLUE
							.getRGB())
				st.push(new Point(pixel.x + 1, pixel.y));
			if (image.getRGB(pixel.x - 1, pixel.y) != Color.GREEN.getRGB()
					&& image.getRGB(pixel.x - 1, pixel.y) != Color.BLUE
							.getRGB())
				st.push(new Point(pixel.x - 1, pixel.y));
			if (image.getRGB(pixel.x, pixel.y + 1) != Color.GREEN.getRGB()
					&& image.getRGB(pixel.x, pixel.y + 1) != Color.BLUE
							.getRGB())
				st.push(new Point(pixel.x, pixel.y + 1));
			if (image.getRGB(pixel.x, pixel.y - 1) != Color.GREEN.getRGB()
					&& image.getRGB(pixel.x, pixel.y - 1) != Color.BLUE
							.getRGB())
				st.push(new Point(pixel.x, pixel.y - 1));
		}
	}

	@Override
	public int getTotalPoints() {
		return 1;
	}
}