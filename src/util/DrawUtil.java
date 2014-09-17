package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DrawUtil {

	public static final int BLACK = Color.black.getRGB();
	public static final int BLUE = Color.blue.getRGB();

	public static void DrowLine(int x1, int y1, int x2, int y2,
			BufferedImage image) {
		x1 = toScreenX(x1);
		x2 = toScreenX(x2);
		y1 = toScreenY(y1);
		y2 = toScreenY(y2);
		int length = (Math.abs(x2 - x1) >= Math.abs(y2 - y1)) ? Math.abs(x2
				- x1) : Math.abs(y2 - y1);
		float dx = (float) (x2 - x1) / length, dy = (float) (y2 - y1) / length;
		float x = (float) (x1 + 0.5 * sign(dx));
		float y = (float) (y1 + 0.5 * sign(dy));
		int i = 0;
		while (++i <= length) {
			System.out.println(Math.round(x) + " " + Math.round(y));
			image.setRGB(Math.round(x), Math.round(y), BLACK);
			System.out.println(Math.round(x) + " " + Math.round(y));
			x += dx;
			y += dy;
		}
	}

	public static void Brez(int x1, int y1, int x2, int y2,
			BufferedImage image, Color color) {
		int temp = 0;
		Boolean change = false;
		int x = x1, y = y1;
		int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
		int s1 = sign(x2 - x1), s2 = sign(y2 - y1);
		if (dy > dx) {
			temp = dx;
			dx = dy;
			dy = temp;
			change = true;
		} else {
			change = false;
		}
		int e = 2 * dy - dx;
		for (int i = 1; i < dx; i++) {
			image.setRGB(toScreenX(x), toScreenY(y), color.getRGB());
			System.out.println(e + " ");
			while (e >= 0) {
				if (change) {
					x += s1;
				} else {
					y += s2;
				}
				e -= 2 * dx;
			}
			if (change) {
				y += s2;
			} else {
				x += s1;
			}
			e +=  2 * dy;
		}
	}

	public static int toScreenX(int x) {
		return x + 250;
	}

	public static int toScreenY(int y) {
		return 250 - y;
	}

	private static int sign(float x) {
		return (x < 0 ? -1 : (x > 0 ? 1 : 0));
	}

}