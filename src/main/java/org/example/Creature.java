package org.example;

import java.util.Random;

public class Creature extends Item implements Moveable{
    protected Random random = new Random();
    protected int health;
    protected int attack;
    protected String name;
    private Boolean isAlive = true;

    public Creature(String name) {
        this.name = name;
        this.health = random.nextInt(60 - 40 + 1) + 40; // Example values; you can adjust as needed
        this.attack = random.nextInt(40 - 10 + 1) + 10;
    }

    @Override
    public boolean move(Maze maze) {
        if(!isAlive){
            return false;
        }
        int newX = this.x;
        int newY = this.y;

        // Randomly select a direction
        String[] directionList = {"r", "l", "u", "d"};
        String direction = directionList[random.nextInt(4)];

        switch (direction.toLowerCase()) {
            case "r":
                newY = this.y + 1;
                break;
            case "l":
                newY = this.y - 1;
                break;
            case "u":
                newX = this.x - 1;
                break;
            case "d":
                newX = this.x + 1;
                break;
            default:
                System.out.println("Invalid direction: " + direction);
                return false;
        }


        if (newX >= 0 && newX < maze.getHeight() && newY >= 0 && newY < maze.getWidth() && newY != 11) {
            if (maze.isOpenSpace(newX, newY)) {

                maze.getMaze().get(this.x).set(this.y, new OpenSpace());


                this.x = newX;
                this.y = newY;

                // Check if the current object is a Demon or Monster and set it accordingly
                if (this instanceof Demon) {
                    maze.getMaze().get(this.x).set(this.y, (Demon) this);
                } else if (this instanceof Monster) {
                    maze.getMaze().get(this.x).set(this.y, (Monster) this);
                }

                System.out.println(name + " moved " + direction);
                return true;
            } else {
                System.out.println(name + " can't move " + direction + " - There's a wall!");
            }
        } else {
            System.out.println(name + " can't move " + direction + " - Out of bounds!");
        }

        return false;
    }

    public void kill(Player player, Maze maze){
        isAlive = false; // Mark the creature as dead
        maze.getMaze().get(this.x).set(this.y, player); // Remove the creature from the maze
        System.out.println(name + " has been killed and removed from the maze.");
    }



    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Health: " + health);
        System.out.println("Attack: " + attack);
    }
}
