package org.example;

public class HealthPotion extends Item implements MazeItem{
    String name;
    public int healing;

    public HealthPotion(){
        this.healing = 40;
        this.name = "Health Flask";
    }
    @Override
    public char getSymbol() {
        return 'H';
    }
}
