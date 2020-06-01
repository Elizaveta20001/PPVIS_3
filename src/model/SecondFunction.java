package model;

import view.Frame;

import java.util.concurrent.locks.Lock;

public class SecondFunction implements Runnable {
    private static final int FUNCTION_ID = 1;
    private int n;
    private int k;
    private Lock lock;
    private Frame frame;
    private int sleepTime;
    private SortArray sortArray;

    public SecondFunction(int n, int k, Lock lock, Frame frame) {
        this.n = n;
        this.k = k;
        this.lock = lock;
        this.frame = frame;
        sleepTime = 1000;
    }
    @Override
    public void run() {
        for (int currentSize = 2; currentSize < n; currentSize++) {
            int commonTime = 0;
            for (int currentArrayCount = 1; currentArrayCount < k; currentArrayCount++)
            {
                sortArray = new SortArray(currentSize);
                commonTime += sortArray.getSortingTime();
            }
            double averageTime = (double) commonTime / k /10;
            lock.lock();
            try {
                Point point = new Point((double) currentSize / 10, averageTime);
                frame.getGraphic().addValue(FUNCTION_ID, point);
                frame.getMainPointsTable().addNewPoint(point);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                currentSize = n;
            }
        }
    }

}
