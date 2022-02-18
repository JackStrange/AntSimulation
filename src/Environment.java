import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import ants.*;

public class Environment extends JPanel implements ActionListener {
    private Timer t;
    private int antCount = 400;

    private Random RANDOM = new Random();

    private ArrayList<Ant> ants = new ArrayList<Ant>();
    private ArrayList<Colony> colonies = new ArrayList<Colony>();

    public Environment() {
        colonies.add(new Colony(500.0,500.0,100,0x0066FF));
        for (int i = 0; i < antCount; i++) {
            Ant newAnt = new Ant.AntBuilder().theta(RANDOM.nextDouble()*360.0).
                                              colony(colonies.get(0)).
                                              buildAnt();
            ants.add(newAnt);
        }
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ant ant:ants){
            ant.move();
        }
        repaint();
    }

}
