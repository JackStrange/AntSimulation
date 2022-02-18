import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*; // VSCode won't pick up java.awt packages. I will fix this later.

public class Environment extends JPanel implements ActionListener {
    private Timer t;
    private int x = 0;
    private int y = 0;

    public Environment(){
        t = new Timer(1000/30, this);
        t.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.fillOval(x, y, 100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x+=20;
        y+=20;
        repaint();
    }
    
}
