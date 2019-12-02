package com.example.g0569.mazegame.model;

import com.example.g0569.base.model.Item;
import com.example.g0569.mazegame.MazeContract;
import com.example.g0569.utils.Constants;
import com.example.g0569.utils.Coordinate;
import com.example.g0569.utils.Inventory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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

  private int[][] maze;
  private int gridWidth;
  private int gridHeight;

  private MazeGenerator(int x, int y) {
    maze = new int[y][x];
    gridWidth = x;
    gridHeight = y;
  }

  /**
   * Randomly generates a mazeGrid array
   * @param n number of NPC in the maze
   * @return the generated mazeGrid
   */
  static int[][] generate(int n) {
    MazeGenerator newMaze = new MazeGenerator(Constants.GRID_WIDTH, Constants.GRID_HEIGHT);

    newMaze.getStack().push(new Coordinate(0, 0));
    while (!newMaze.getStack().empty()) {
      Coordinate next = newMaze.getStack().pop();
      if (newMaze.validNextNode(next)) {
        newMaze.getMaze()[next.getIntY()][next.getIntX()] = 1;
        ArrayList<Coordinate> neighbors = newMaze.findNeighbors(next);
        newMaze.randomlyAddNodesToStack(neighbors);
      }
    }
    newMaze.updateNPCMaze(newMaze.maze, n);
    return newMaze.getMaze();
  }

  /**
   * helper method of generate method
   * randomly place represented NPC of assigned number into mazeGrid
   * @param mazeGrid the mazeGrid that is generated without NPC
   * @param npcNum the number of NPCs that are placed into the mazeGrid
   */
  private void updateNPCMaze(int[][] mazeGrid, int npcNum) {
    if (mazeGrid != null){
      int i = 0;
      while (i < npcNum) {
        int y = (int) (Math.random() * Constants.GRID_HEIGHT);
        int x = (int) (Math.random() * Constants.GRID_WIDTH);
        if (mazeGrid[y][x] == 1) {
          i += 1;
          mazeGrid[y][x] = 2;
        }
      }
    }
  }

  /**
   * helper method of generate method
   * Check whether the node is valid to be assigned as a path.
   * @param coor the coordinate of the node to be checked
   * @return whether the node is valid to be assigned.
   */
  private boolean validNextNode(Coordinate coor) {
    int numNeighboringOnes = 0;
    for (int y = coor.getIntY() - 1; y < coor.getIntY() + 2; y++) {
      for (int x = coor.getIntX() - 1; x < coor.getIntX() + 2; x++) {
        if (pointOnGrid(x, y) && pointNotCoor(coor, x, y) && maze[y][x] == 1) {
          numNeighboringOnes++;
        }
      }
    }
    return (numNeighboringOnes < 3) && maze[coor.getIntY()][coor.getIntX()] != 1;
  }

  /**
   * Check if the point coordinate is inside the grid
   * @param x x coordinate of the grid
   * @param y y coordinate of the grid
   * @return if the point is in the grid
   */
  private Boolean pointOnGrid(int x, int y) {
    return x >= 0 && y >= 0 && x < gridWidth && y < gridHeight;
  }

  /**
   * Check whether the point is at the corner
   * @param coor the current added coordinate
   * @param x the x coordinate that is checking
   * @param y the y coordinate that is checking
   * @return if the point is  at the corner
   */
  private Boolean pointNotCorner(Coordinate coor, int x, int y) {
    return (x == coor.getIntX() || y == coor.getIntY());
  }

  /**
   * Check if the x, y is the same as the current coordinate.
   * @param coor the current added coordinate
   * @param x the x coordinate that is checking
   * @param y the y coordinate that is checking
   * @return if the point is the current coordinate;
   */
  private Boolean pointNotCoor(Coordinate coor, int x, int y) {
    return !(x == coor.getIntX() && y == coor.getIntY());
  }

  /**
   * Find the arraylist of neighbour of the
   * @param coor the coordinate that is finding for its available neighbours.
   * @return all the available neighbours of that coordinate.
   */
  private ArrayList<Coordinate> findNeighbors(Coordinate coor) {
    ArrayList<Coordinate> neighbors = new ArrayList<>();
    for (int y = coor.getIntY() - 1; y < coor.getIntY() + 2; y++) {
      for (int x = coor.getIntX() - 1; x < coor.getIntX() + 2; x++) {
        if (pointOnGrid(x, y) && pointNotCorner(coor, x, y) && pointNotCoor(coor, x, y)) {
          neighbors.add(new Coordinate(x, y));
        }
      }
    }
    return neighbors;
  }

  /**
   * randomly add a coordinate to the stack
   * @param coors the arraylist of coordinates that is chosen from
   */
  private void randomlyAddNodesToStack(ArrayList<Coordinate> coors) {
    Random rand = new Random();
    int targetIndex;
    while (!coors.isEmpty()) {
      targetIndex = rand.nextInt(coors.size());
      stack.push(coors.remove(targetIndex));
    }
  }

  /**
   * Get the current maze
   * @return the maze
   */
  public int[][] getMaze() {
    return maze;
  }

  /**
   * Get the current stack
   * @return the current state of the stack
   */
  private Stack<Coordinate> getStack() {
    return stack;
  }
}
