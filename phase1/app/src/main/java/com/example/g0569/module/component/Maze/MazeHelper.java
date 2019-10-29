package com.example.g0569.module.component.Maze;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.Maze.Components.NPC;
import com.example.g0569.module.component.Maze.MazeItem.Wall;
import com.example.g0569.module.game.Game;

public class MazeHelper {
    private static int[][] maze = {{1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1}};

    public static void loadMaze(Item[][] MyMazeItem, Game game) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (maze[i][j] == 0) {
                    ;
                } else if (maze[i][j] == 1) {
                    MyMazeItem[i][j] = new Wall(game, 2 * j, 2 * i);
                } else if (maze[i][j] == 2) {
                    MyMazeItem[i][j] = new NPC(game, 2 * j, 2 * i);
                }
            }
        }
    }
}
