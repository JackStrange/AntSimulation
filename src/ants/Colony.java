package ants;

public class Colony extends SimulatorObject{
    public int colour;

    public Colony(double x, double y, int width, int colour){
        this.x = x;
        this.y = y;
        this.width = width;
        this.colour = colour;
    }
}
