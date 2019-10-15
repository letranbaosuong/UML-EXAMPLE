import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setBounds(130, 130, 230, 230);
        Clock clock = new Clock();
        windows.setBackground(Color.cyan);
        windows.getContentPane().add(clock);
        windows.setVisible(true);
        clock.start();
    }
}
