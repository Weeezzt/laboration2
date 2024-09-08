package org.example;

public class Exit extends Item implements MazeItem{
    public String name;

    public Exit(){
        this.name = "Door to Freedom";
    }

    @Override
    public char getSymbol() {
        return 'E';
    }
}
