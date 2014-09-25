package osu.java.graphics.algorithms;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import osu.java.graphics.CartesianImage;

public interface DrawingAlgoStrategy {
  void drawObject(List<Point> points, CartesianImage canvas, Color color);
  
  int getTotalPoints();
}

