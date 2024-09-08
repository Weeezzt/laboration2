package org.example;

import java.util.ArrayList;

public class Maze {

    ArrayList<ArrayList<MazeItem>> maze = new ArrayList<>();
    private Monster monster;
    private Demon demon;
    private Sword sword;
    private HealthPotion healthPotion;
    private Exit exit;




    void setMaze(Player player){

        monster = new Monster("Hydra");
        demon = new Demon("Azazel");
        sword = new Sword("Dawnsealer");
        healthPotion = new HealthPotion();
        exit = new Exit();
        // Initialize all rows with "wall" as the default value
        for (int i = 0; i < 12; i++) {
            ArrayList<MazeItem> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(new Wall());
            }
            maze.add(row);
        }

        for (int i = 1; i < 9; i++) {
            maze.get(1).set(i, new OpenSpace());  // row 1 is all "ok"
        }
        maze.get(0).set(1, player);
        maze.get(1).set(5, new OpenSpace());
        maze.get(2).set(5,  new OpenSpace());
        maze.get(3).set(5,  new OpenSpace());
        maze.get(4).set(5,  new OpenSpace());
        maze.get(4).set(6,  new OpenSpace());
        maze.get(4).set(7,  new OpenSpace());
        maze.get(5).set(7,  new OpenSpace());
        maze.get(6).set(7,  new OpenSpace());
        maze.get(6).set(5,  new OpenSpace());
        maze.get(6).set(4,  new OpenSpace());
        maze.get(6).set(6, monster);
        maze.get(5).set(4,  new OpenSpace());
        maze.get(5).set(3, sword);
        maze.get(5).set(2,  new OpenSpace());
        maze.get(6).set(2,  new OpenSpace());
        maze.get(7).set(2, healthPotion);
        maze.get(7).set(1,  new OpenSpace());
        maze.get(8).set(1,  new OpenSpace());
        maze.get(9).set(1,  new OpenSpace());

        for (int i = 2; i < 7; i++) {
            if(i == 5) maze.get(9).set(i, demon);
            else maze.get(9).set(i,  new OpenSpace());
        }
        maze.get(10).set(6,  new OpenSpace());
        maze.get(11).set(6,  exit);



    }

    //Check if there is Open space at a certain spot
    public boolean isOpenSpace(int x, int y){
       return maze.get(x).get(y) instanceof OpenSpace;
    }

    //Check if there is a Wall at a certain spot
    public boolean isWall(int x, int y){
        return maze.get(x).get(y) instanceof Wall;
    }

    // Get the height of the maze
    public int getHeight() {
        return maze.size();
    }

    // Get the width of the maze
    public int getWidth() {
        return maze.get(0).size();
    }

    void printMaze(Player player) {
        for (int i = 0; i < maze.size(); i++) {
            for (int j = 0; j < maze.get(i).size(); j++) {
                MazeItem item = maze.get(i).get(j);

                // Check if this position is the player's position
                if (i == player.x && j == player.y) {
                    System.out.print("\u001B[34mP\u001B[0m\t"); // Blue P for Player
                } else if (item instanceof Monster) {
                    System.out.print("\u001B[31mM\u001B[0m\t"); // Red M for Monster
                } else if (item instanceof Demon) {
                    System.out.print("\u001B[33mD\u001B[0m\t"); // Yellow D for Demon
                } else if (item instanceof HealthPotion) {
                    System.out.print("\u001B[35mH\u001B[0m\t"); // Pink H for HealthPotion
                } else if (item instanceof Sword) {
                    System.out.print("\u001B[36mS\u001B[0m\t"); // Cyan S for Sword
                } else {
                    // Print other items normally
                    System.out.print(item.getSymbol() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public ArrayList<ArrayList<MazeItem>> getMaze() {
        return maze;
    }

    public Monster getMonster() { return monster; }
    public Demon getDemon() { return demon; }
    public Sword getSword() { return sword; }
    public HealthPotion getHealthPotion() { return healthPotion; }
    public Exit getExit(){ return exit; }

    // Replace an item at a specific position in the maze
    public void setItemAt(int x, int y, MazeItem item) {
        maze.get(x).set(y, item);
    }

}
