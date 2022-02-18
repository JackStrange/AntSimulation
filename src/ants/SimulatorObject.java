package ants;

public abstract class SimulatorObject {
    public double x;
    public double y;
    public int width;

    public double distance(SimulatorObject target){
        return Math.sqrt(Math.pow(this.x-target.x,2)+Math.pow(this.y-target.y,2));
    }

    public double angle(SimulatorObject target){
        double deltay = this.y-target.y;
        double deltax = target.x-this.x;
        double angle=Math.atan(deltay/deltax)*180.0/Math.PI;
        if(deltax > 0 && deltay > 0) return angle;
        if(deltax < 0) return angle + 180.0;
        return angle + 360.0;
    }
}
