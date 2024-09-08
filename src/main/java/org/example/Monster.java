package org.example;

public class Monster extends Creature implements MazeItem{
    public Monster(String name){
        super(name);
    }

    @Override
    public char getSymbol() {
        return 'M';
    }

    public void printDetails() {
        System.out.println("Monster Details:");
        super.printDetails();
    }
}
