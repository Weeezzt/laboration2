package org.example;
import java.util.Scanner;

public class PlayGame {
    public void startGame(){
        Boolean running = true;

        Player player = new Player();

        Maze maze = new Maze();
        maze.setMaze(player);

        player.printPlayer();
        maze.printMaze(player);

        Scanner scanner = new Scanner(System.in);

        Monster monster = maze.getMonster();
        Demon demon = maze.getDemon();
        Sword sword = maze.getSword();
        HealthPotion healthPotion = maze.getHealthPotion();
        Exit exit = maze.getExit();

        monster.setPosition(maze);
        demon.setPosition((maze));
        sword.setPosition(maze);
        exit.setPosition(maze);
        healthPotion.setPosition(maze);

        while (running) {
            player.move(maze);

            if (exitFound(player, exit)) {
                maze.printMaze(player);
                running = false; // Stop the game if the player has won
                break;
            }

            // Move the creatures
            monster.move(maze);
            demon.move(maze);
            creatureEncounter(player, monster, demon, maze);
            swordEncounter(player, sword, maze);
            potionEncounter(player, healthPotion, maze);
            maze.printMaze(player); // Print the maze after the move

            if (player.health <= 0) {
                System.out.println("Game Over!");
                running = false;
            }

            // Add some delay
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
        scanner.close();
    }
    public void swordEncounter(Player player, Sword sword, Maze maze) {
        if (player.y == sword.y && player.x == sword.x) {
            System.out.println("You've found a new sword: " + sword.name);
            if (player.sword.attackPower < sword.attackPower) {
                player.sword = sword; // Equip the new sword
                System.out.println("You've equipped " + sword.name);
                System.out.println("It has " + sword.attackPower + " in attack power");
            } else {
                System.out.println("The new sword is not better than your current sword.");
            }
            // Remove the sword from the maze
            maze.getMaze().get(sword.x).set(sword.y, player);
        }
    }
    public void potionEncounter(Player player, HealthPotion potion, Maze maze) {
        if (player.y == potion.y && player.x == potion.x) {
            System.out.println("You've found a health potion!");
            player.health += potion.healing; // Heal the player
            System.out.println("Player's health is now: " + player.health);
            // Remove the potion from the maze
            maze.getMaze().get(potion.x).set(potion.y, player);
        }
    }
    public void creatureEncounter(Player player, Monster monster, Demon demon, Maze maze) {
        if (player.y == monster.y && player.x == monster.x) {
            while (player.health > 0 && monster.health > 0) {
                // Player attacks the monster
                monster.health -= player.sword.attackPower;
                System.out.println("You attacked the monster " + monster.name + "! Monster's health is now: " + monster.health);

                // Check if the monster is dead
                if (monster.health <= 0) {
                    monster.kill(player, maze); // Remove the monster from the maze
                    break;
                }

                // Monster attacks the player
                player.health -= monster.attack;
                System.out.println("The monster attacked you! Your health is now: " + player.health);

                // Check if the player is dead
                if (player.health <= 0) {
                    System.out.println("You have been killed by the monster!");
                    break;
                }
            }
        }
            if (player.y == demon.y && player.x == demon.x) {
                System.out.println("You've encountered a Demon");
                demon.printDetails();
                // Battle continues while both are alive
                while (player.health > 0 && demon.health > 0) {
                    // Player attacks the demon
                    demon.health -= player.sword.attackPower;
                    System.out.println("You attacked the demon " + demon.name + "! Demon's health is now: " + demon.health);

                    // Check if the demon is dead
                    if (demon.health <= 0) {
                        demon.kill(player, maze); // Remove the demon from the maze
                        break;
                    }

                    // Demon attacks the player
                    player.health -= demon.attack;
                    System.out.println("The demon attacked you! Your health is now: " + player.health);

                    // Check if the player is dead
                    if (player.health <= 0) {
                        System.out.println("You have been killed by the demon!");
                        break;
                    }
                }
            }
    }
    public Boolean exitFound(Player player, Exit exit) {
        if (player.y == exit.y && player.x == exit.x) {
            System.out.println("You've found the exit!");
            System.out.println("Game ends... You've won!");

            return true;
        }
        return false;
    }
}

