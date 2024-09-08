package org.example;

public class Demon extends Creature implements  MazeItem{
    public Demon(String name) {
        super(name);
    }

    @Override
    public char getSymbol() {
        return 'D';
    }

    public void printDetails() {
        System.out.println("Demon Details:");
        super.printDetails();
    }
}
