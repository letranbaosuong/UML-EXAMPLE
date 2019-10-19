import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.annotation.Inherited;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.*;

public class Clock extends JPanel implements Runnable {
    Thread thread = null;
    SimpleDateFormat formatter = new SimpleDateFormat("s", Locale.getDefault());
    Date curreDate;
    int xcenter = 100, ycenter = 100, lastxs = 0, lastys = 0, lastxm = 0, lastym = 0, lastxh = 0, lastyh = 0;

    private void drawStructure(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.setColor(Color.blue);
        g.drawOval(xcenter - 50, ycenter - 50, 100, 100);
        g.setColor(Color.darkGray);
        g.drawString("9", xcenter - 45, ycenter + 5);
        g.drawString("3", xcenter + 38, ycenter + 5);
        g.drawString("12", xcenter - 9, ycenter - 34);
        g.drawString("6", xcenter - 3, ycenter + 45);
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 230, 230);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 230, 230);
        int xhour, yhour, xminute, yminute, xsecond, ysecond, hour, minute, second;
        drawStructure(g);
        curreDate = new Date();
        formatter.applyPattern("s");
        second = Integer.parseInt(formatter.format(curreDate));
        formatter.applyPattern("m");
        minute = Integer.parseInt(formatter.format(curreDate));
        formatter.applyPattern("h");
        hour = Integer.parseInt(formatter.format(curreDate));
        xhour = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 30 + xcenter);
        yhour = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 30 + ycenter);
        xminute = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * 40 + xcenter);
        yminute = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * 40 + ycenter);
        xsecond = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 45 + xcenter);
        ysecond = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 45 + ycenter);
        System.out.println(xhour + " - " + yhour + " - " + xminute + " - " + yminute + " - " + xsecond + " - " + ysecond);
        g.setColor(Color.black);
        if (xsecond != lastxs || ysecond != lastys) {
            g.drawLine(xcenter, ycenter, xsecond, ysecond);
        }
        if (xminute != lastxm || yminute != lastym) {
            g.drawLine(xcenter, ycenter - 1, xminute, yminute);
            g.drawLine(xcenter - 1, ycenter, xminute, yminute);
        }
        if (xhour != lastxh || yhour != lastyh) {
            g.drawLine(xcenter, ycenter - 1, xhour, yhour);
            g.drawLine(xcenter - 1, ycenter, xhour, yhour);
        }
    }

    public void stop() {
        thread = null;
    }

    public void run() {
        while (thread != null) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
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
