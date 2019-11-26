package com.example.g0569.mazegame.model;

import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

/** The type Maze generator. */
public class MazeGenerator {

  /**
   * Generate a n by m maze represented in List.
   *
   * @param n the n
   * @param m the m
   * @return the list of the maze
   */
  private Stack<Coordinate> stack = new Stack<>();

  private Random rand = new Random();
  private int[][] maze;
  private int gridWidth;
  private int gridHeight;

  MazeGenerator(int x, int y) {
    maze = new int[y][x];
    gridWidth = x;
    gridHeight = y;
  }

  public static int[][] generate(int n, int m) {
    MazeGenerator newMaze = new MazeGenerator(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);

      newMaze.getStack().push(new Coordinate(0, 0));
      while (!newMaze.getStack().empty()) {
        Coordinate next = newMaze.getStack().pop();
        if (newMaze.validNextNode(next)) {
          newMaze.getMaze()[(int) next.getY()][(int) next.getX()] = 1;
          ArrayList<Coordinate> neighbors = newMaze.findNeighbors(next);
          newMaze.randomlyAddNodesToStack(neighbors);
        }

    }
    return newMaze.getMaze();
  }

  private boolean validNextNode(Coordinate coor) {
    int numNeighboringOnes = 0;
    for (int y = (int) coor.getY() - 1; y < (int) coor.getY() + 2; y++) {
      for (int x = (int) coor.getX() - 1; x < (int) coor.getX() + 2; x++) {
        if (pointOnGrid(x, y) && pointNotCoor(coor, x, y) && maze[y][x] == 1) {
          numNeighboringOnes++;
        }
      }
    }
    return (numNeighboringOnes < 3) && maze[(int) coor.getY()][(int) coor.getX()] != 1;
  }

  private Boolean pointOnGrid(int x, int y) {
    return x >= 0 && y >= 0 && x < gridWidth && y < gridHeight;
  }

  private Boolean pointNotCorner(Coordinate coor, int x, int y) {
    return (x == coor.getIntX() || y == coor.getIntY());
  }

  private Boolean pointNotCoor (Coordinate coor, int x, int y) {
    return !(x == coor.getIntX() && y == coor.getIntY());
  }

  private ArrayList<Coordinate> findNeighbors(Coordinate coor) {
    ArrayList<Coordinate> neighbors = new ArrayList<>();
    for (int y = coor.getIntY()-1; y < coor.getIntY()+2; y++) {
      for (int x = coor.getIntX()-1; x < coor.getIntX()+2; x++) {
        if (pointOnGrid(x, y) && pointNotCorner(coor, x, y)
                && pointNotCoor(coor, x, y)) {
          neighbors.add(new Coordinate(x, y));
        }
      }
    }
    return neighbors;
  }

  private void randomlyAddNodesToStack(ArrayList<Coordinate> coors) {
    int targetIndex;
    while (!coors.isEmpty()) {
      targetIndex = rand.nextInt(coors.size());
      stack.push(coors.remove(targetIndex));
    }
  }

  public int[][] getMaze() {
    return maze;
  }

  public Stack<Coordinate> getStack() {
    return stack;
  }
}