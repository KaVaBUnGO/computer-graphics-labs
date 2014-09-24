package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;

import osu.java.graphics.CartesianImage;

public class BresenhamCircle implements DrawingAlgoStrategy {
	public BresenhamCircle() {
		System.out
				.println("using Digital differential analyzer circle algorithm");
	}

	@Override
	public void drawObject(Point p1, Point p2, CartesianImage canvas,
			Color color) {
		int r = (int) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)
				* (p1.y - p2.y));
		DrawBresCircle(p1.x, p1.y, r, canvas, color);
	}

	public void DrawBresCircle(int x1, int y1, int r, CartesianImage image,
			Color color) {
		int x = 0, y = r, d = 2 * (1 - r), d1 = 0, d2 = 0, d3 = 0;
		System.out.println(r);

		while (y > 0) {
			image.setRGB(x+x1, y+y1, color.getRGB());
			image.setRGB(x+x1, -y+y1, color.getRGB());
			image.setRGB(-x+x1, y+y1, color.getRGB());
			image.setRGB(-x+x1, -y+y1, color.getRGB());
			if (d < 0) {
				d1 = 2 * d + 2 * y - 1;
				if (d1 <= 0) {
					x++;
					d += 2 * x + 1;
				} else {
					x++;
					y--;
					d += 2 * x - 2 * y + 2;
				}
			}
			if (d > 0) {
				d2 = 2 * d - 2 * x - 1;
				if (d2 <= 0) {
					x++;
					y--;
					d += 2 * x - 2 * y + 2;
				} else {
					y--;
					d = d - 2 * y + 1;
				}
			}
			if (d == 0) {
				x++;
				y--;
				d += 2 * x - 2 * y + 2;
			}
		}

		/*
		 * x=0 y=R d=2(1ЦR) ѕредел=0 1 Plot(x,y) if y<=ѕредел then 4 if d<0 then
		 * 2 if d>0 then 3 if d=0 then 20 2 d1=2*d+2*y-1 if d1<=0 then 10 if
		 * d1>0 then 20 3 d2=2*d2*x-1 if d2<=0 then 20 if d2>0 then 30 10 x=x+1
		 * d=d+2*x+1 go to 1 20 x=x+1 y=yЦ1 d=d+2*xЦ2*y+2 go to 1 30 y=yЦ1
		 * d=dЦ2*y+1 go to 1 4 finish
		 */

	}

}
