package SongHi;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyPanel extends JPanel implements Runnable {
    public static int SoLan = 0;
    Thread thread = null;
    JFrame f = new JFrame();
    MyList mList = null;

//    public MyPanel(JFrame f) {
//        this.f = f;
//    }

    public MyPanel(MyList ml) {
        f.setSize(400, 400);
        mList = ml;
        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paint(Graphics g) {
//        try {
        mList.draw(g);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        SoLan++;
        System.out.println("So lan " + SoLan);
    }

    public void stop() {
        thread = null;
    }

    public void run() {
        while (thread != null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint(); // extends JPanel implements Runnable
        }
        thread = null;
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
