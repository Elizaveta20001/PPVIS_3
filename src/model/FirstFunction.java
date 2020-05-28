package model;
import view.Frame;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.locks.Lock;
public class FirstFunction implements Runnable {

    public double x;
    public double beginI;
    public double endI;
    private Lock lock;
    private Point secondPoint;
    public static final int FUNCTION_ID = 0;
    private double step;
    private Frame frame;
    private int sleepTime;

    public FirstFunction(Lock lock, Frame frame) {
        this.lock = lock;
        beginI = 0;
        this.endI = 100;
        this.step = 1;
        this.frame = frame;
        sleepTime = 1000;
    }
    public double function(double x) {
        return 4 * x - 7;
    }

    @Override
    public void run() {
        double beginX = beginI;
        double endX = endI;
        double tempFx = 0;
        for (double x = beginX; x <= endX; x += step) {
            tempFx = function(x) / 10;
            tempFx = Math.round(tempFx * 10d) / 10d;
            x = Math.round(x * 10d) / 10d;
            lock.lock();
            try {
                secondPoint = new Point(x / 10, tempFx);
                frame.getGraphic().addValue(FUNCTION_ID, secondPoint);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
