package org.example;

import java.util.ArrayList;

public abstract class Item {

    int x;
    int y;

    void position(Maze maze){
        ArrayList<ArrayList<MazeItem>> mazeGrid = maze.getMaze();
        for (int i = 0; i < mazeGrid.size(); i++){
            for(int j = 0; j < mazeGrid.get(i).size(); j++){
                if(this == mazeGrid.get(i).get(j)){
                    this.x= i;
                    this.y = j;
                    return;
                }
            }
        }
    }

    void getPosition(){
        System.out.println("the cordinates are x: " + this.x + " y: " + this.y);
    }

}
