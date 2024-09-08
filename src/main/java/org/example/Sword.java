package org.example;

import java.util.Random;

public class Sword extends Item implements MazeItem {

    private Random random = new Random();
    public String name;
    public int attackPower;

    public Sword(String name){
        this.name = name;
        this.attackPower = random.nextInt(80 - 50 + 1) + 50;
        if(name.equals("Rusty dagger")){
            this.attackPower = 30;
        }
    }

    @Override
    public char getSymbol() {
        return 'S';
    }

    void printSword(){
        System.out.println("Sword Name: " + this.name);
        System.out.println("Attack Power: " + this.attackPower);
        System.out.println("Position - x: " + this.x + ", y: " + this.y);
    }
}
