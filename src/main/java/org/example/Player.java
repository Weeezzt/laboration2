package org.example;

import java.util.Scanner;

public class Player implements MazeItem, Moveable{
    Scanner scanner = new Scanner(System.in);

    String name;
    int health;
    int attackPwr;
    Sword sword;

    int x = 0; //Row position
    int y = 1; // Col position

    public Player(){
        System.out.println("What do you want to name your player?");

        this.name = scanner.next();
        this.health = 100;
        this.attackPwr = 40;
        this.sword = new Sword("Rusty dagger");

    }

    @Override
    public boolean move(Maze maze){
        int newX = 0;
        int newY = 0;
        while(true){
            newX = this.x;
            newY = this.y;
            System.out.println("Where do you want to move?");
            System.out.println("(R)ight, (L)eft, (U)p or (D)own");
            String direction = scanner.next();
            switch (direction.toLowerCase()){
                case "r":  // Move right
                    newY = this.y + 1;
                    break;
                case "l":  // Move left
                    newY = this.y - 1;
                    break;
                case "u":  // Move up
                    newX = this.x - 1;
                    break;
                case "d":  // Move down
                    newX = this.x + 1;
                    break;
                default:
                    System.out.println("Invalid direction. Please enter R, L, U, or D.");
                    continue;
            }
            // Check if the new position is within bounds
            if (newX >= 0 && newX < maze.getHeight() && newY >= 0 && newY < maze.getWidth()) {
                // Check if the new position is not a wall
                if (!maze.isWall(newX, newY)) {
                    // Set the player's current position to OpenSpace
                    maze.setItemAt(this.x, this.y, new OpenSpace());

                    // Move the player to the new position
                    maze.setItemAt(newX, newY, this);
                    this.x = newX;
                    this.y = newY;

                    return true;
                }
            }else {
                System.out.println("Can't move " + direction + " - Out of bounds!");
            }
            return false;
        }

    }

    @Override
    public char getSymbol() {
        return 'P';
    }

    public void printPosition() {
        System.out.println(name + " is at position (" + x + ", " + y + ")");
    }

    public void printPlayer(){
            System.out.println("Player Name: " + name);
            System.out.println("Health: " + health);
            printPosition();
            System.out.print("Sword: " + sword.name);

            System.out.println();
    }

}
