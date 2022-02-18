import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import ants.*;

public class Environment extends JPanel implements ActionListener {
    private Timer t;
    private int antCount = 400;
    private int foodCountPC = 500; // Food per food cluster

    private Random RANDOM = new Random();

    private ArrayList<Ant> ants = new ArrayList<Ant>();
    private ArrayList<Colony> colonies = new ArrayList<Colony>();
    private ArrayList<Food> foods = new ArrayList<Food>();

    public Environment() {
        colonies.add(new Colony(500.0,500.0,100,0x0066FF));
        for (int i = 0; i < antCount; i++) {
            Ant newAnt = new Ant.AntBuilder().theta(RANDOM.nextDouble()*360.0).
                                              colony(colonies.get(0)).
                                              buildAnt();
            ants.add(newAnt);
        }
        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // This code forms "clusters" that food land in
                // It spawns a cluster at wherever the user clicks
                // It then generates food objects in the vicinity (normally distributed)
                double x = e.getX();
                double y = e.getY();
                for (int j = 0; j < foodCountPC; j++){
                    // Generates a normally distributed radius. Capped at 50 pixels away
                    double radius = 0;
                    do{
                        radius = Math.abs(RANDOM.nextGaussian());
                    }while(radius > 2.0);
                    radius *= 25.0;
                    // Generate a random angle, and adjust x and y values
                    double theta = RANDOM.nextDouble()*360.0;
                    double deltax = Math.cos(Math.PI/180.0*theta)*radius;
                    double deltay = -Math.sin(Math.PI/180.0*theta)*radius;
                    foods.add(new Food(x+deltax, y+deltay, 10));
                }
            }
        });

        t = new Timer(1000/30, this);
        t.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        for (Ant ant : ants) {
            g2D.fillOval((int) ant.x-ant.width/2, (int) ant.y-ant.width/2, ant.width, ant.width);
        }
        for (Colony colony : colonies) {
            g2D.setColor(new Color(colony.colour));
            g2D.fillOval((int) colony.x-colony.width/2, (int) colony.y-colony.width/2, colony.width, colony.width);
        }
        g2D.setColor(Color.GREEN);
        for (Food food : foods) {
            g2D.fillOval((int) food.x-food.width/2, (int) food.y-food.width/2, food.width, food.width);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ant ant:ants){
            ant.move();
        }
        repaint();
    }

}
