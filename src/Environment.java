import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import ants.Ant;

public class Environment extends JPanel implements ActionListener {
    private Timer t;
    private int antCount = 400;

    private Random RANDOM = new Random();

    private ArrayList<Ant> ants;

    public Environment() {
        ants = new ArrayList<Ant>();
        for (int i = 0; i < antCount; i++) {
            Ant newAnt = new Ant.AntBuilder().x(RANDOM.nextDouble() * 1000).
                                              y(RANDOM.nextDouble() * 1000).
                                              theta(RANDOM.nextDouble()*360.0).
                                              buildAnt();
            ants.add(newAnt);
        }
        t = new Timer(1000/30, this);
        t.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        for (Ant ant : ants) {
            g2D.fillOval((int) ant.x-ant.width/2, (int) ant.y-ant.width/2, ant.width, ant.width);
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
