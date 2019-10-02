package SongHi;

import java.awt.*;
import java.util.ArrayList;

public class MyList {
    private final ArrayList mList = new ArrayList();
    private final int xStart = 90;
    private final int yStart = 40;
    private final int cSize = 20;

    public void add(MyLine ml) {
        mList.add(ml);
    }

    public void addData() {
        mList.add(new MyLine(new int[]{2, 3, 6, 7}, 0));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 1));
        mList.add(new MyLine(new int[]{2, 3, 6, 7}, 2));
        mList.add(new MyLine(new int[]{0, 9}, 3));
        mList.add(new MyLine(new int[]{}, 4));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 5));
        mList.add(new MyLine(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 6));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 7));
        mList.add(new MyLine(new int[]{2, 3, 6, 7}, 8));
        mList.add(new MyLine(new int[]{0, 9}, 9));
        mList.add(new MyLine(new int[]{2, 3, 6, 7}, 10));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 11));
        mList.add(new MyLine(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 12));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 13));
        mList.add(new MyLine(new int[]{1, 4, 5, 8}, 14));
    }

    public void printOut() {
        for (int i = 0; i < mList.size() - 1; i++) {
            MyLine ml = (MyLine) mList.get(i);
            System.out.print("<");
            for (int j = 0; j < ml.getX().length - 1; j++) {
                System.out.print(ml.getX()[j] + (j < ml.getX().length - 1 ? "," : ""));
                System.out.println(">" + ml.getY());
            }
        }
    }

    public void draw(Graphics g) {
        int iColor = (MyPanel.SoLan / cSize) % 2;
        g.setColor(iColor == 0 ? Color.pink : Color.red);
        int iLan = MyPanel.SoLan % (mList.size() * cSize);

        if (iLan == 0) {
            try {
                Thread.sleep(1000);
                g.clearRect(0, 0, 400, 400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int ii = (iLan / cSize) % mList.size();

        for (int i = 0; i < ii; i++) {
            MyLine ml = (MyLine) mList.get(i);
            for (int j = 0; j < ml.getX().length - 1; j = j + 2) {
                int cc = iLan % cSize;
                g.fillRect(ml.getX()[j] * cSize + xStart, ml.getY() * cSize + yStart, (ml.getX()[j + 1] - ml.getX()[j]) * cSize, cc + 1);
            }
        }
    }
}
