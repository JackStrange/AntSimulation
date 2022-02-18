package ants;

import java.util.ArrayList;
import java.util.Random;

public class Ant extends SimulatorObject{
    public double theta, velocity, visionRange, visionRadius;
    public Colony colony;
    private static Random RANDOM = new Random();
    public boolean carrying = false;

    private Ant(double x,
                double y,
                double theta,
                double velocity,
                double visionRange,
                double visionRadius,
                int width,
                Colony colony) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.velocity = velocity;
        this.visionRange = visionRange;
        this.visionRadius = visionRadius;
        this.width = width;
        this.colony = colony;
    }

    public void move(){
        double xchange = Math.cos(Math.PI / 180.0 * theta) * velocity;
        double ychange = -Math.sin(Math.PI / 180.0 * theta) * velocity;

        // Bounce if extending horizontally
        if (x+xchange > 1000){
            x = 1000.0;
            theta = 180.0 - theta;
        }else if(x+xchange < 0) {
            x = 0.0;
            theta = 180.0 - theta;
        }else{
            x += xchange;
        }

        // Bounce if extending vertically
        if (y+ychange > 1000){
            y = 1000.0;
            theta = -theta;
        }else if(y+ychange < 0) {
            y = 0.0;
            theta = -theta;
        }else{
            y += ychange;
        }
        
        // Adjust theta within range
        while(theta < 0){
            theta += 360;
        }
        while(theta > 360){
            theta -= 360;
        }
        theta += ((RANDOM.nextDouble()-0.5) * 2.0)*15.0;
    }

    public double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1-x2,2.0)+Math.pow(y1-y2,2.0));
    }

    public void followFood(ArrayList<Food> foods){
        if(foods.isEmpty()){
            return;
        }
        Food closestFood = null;
        double closestDist = -1.0;
        for(Food food:foods){
            double adjustedAngle = Math.abs(angle(food) - this.theta)+90.0;
            if(closestDist < 0 && distance(food) <= this.visionRange && adjustedAngle < this.visionRadius){
                closestDist = distance(food);
                closestFood = food;
            }else if(distance(food) <= closestDist && adjustedAngle < this.visionRadius/2){
                closestDist = distance(food);
                closestFood = food;
            }
        }
        if(closestFood == null){
            return;
        }
        theta = angle(closestFood);
    }

    // This class makes "default" values easier to handle. Uses the Builder Pattern
    public static class AntBuilder {
        private double x = 0;
        private double y = 0;
        private double theta = 0;
        private double velocity = 5;
        private double visionRange = 40;
        private double visionRadius = 180;
        private int width = 10;
        private Colony colony;
        
        public AntBuilder(){
            x = 0;
            y = 0;
            theta = 0;
            velocity = 10;
            visionRange = 40;
            visionRadius = 180;
        }

        public Ant buildAnt() {
            return new Ant(x,
                           y,
                           theta,
                           velocity, 
                           visionRange, 
                           visionRadius, 
                           width, 
                           colony);
        }

        public AntBuilder x(double x) {
            this.x = x;
            return this;
        }

        public AntBuilder y(double y) {
            this.y = y;
            return this;
        }

        public AntBuilder theta(double theta) {
            this.theta = theta;
            return this;
        }

        public AntBuilder velocity(double velocity) {
            this.velocity = velocity;
            return this;
        }

        public AntBuilder visionRange(double visionRange) {
            this.visionRange = visionRange;
            return this;
        }

        public AntBuilder visionRadius(double visionRadius) {
            this.visionRadius = visionRadius;
            return this;
        }

        public AntBuilder width(int width) {
            this.width = width;
            return this;
        }

        public AntBuilder colony(Colony colony) {
            this.colony = colony;
            this.x = colony.x;
            this.y = colony.y;
            return this;
        }
    }
}
