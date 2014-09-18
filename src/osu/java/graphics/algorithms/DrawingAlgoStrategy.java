package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import osu.java.graphics.CartesianImage;

public interface DrawingAlgoStrategy {
  void drawObject(Point p1, Point p2, CartesianImage canvas, Color color);
}
