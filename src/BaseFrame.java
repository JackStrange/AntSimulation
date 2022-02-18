import javax.swing.JFrame;

public class BaseFrame /*extends JFrame*/{

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.add(new Environment());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}