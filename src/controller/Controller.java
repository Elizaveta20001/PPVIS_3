package controller;
import model.FirstFunction;
import model.SecondFunction;
import view.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Controller {

    private Frame window;
    private FirstFunction firstFunction;
    private SecondFunction secondFunction;
    private Lock lock;
    private List<Thread> threads;

    public Controller(Frame window, Lock lock) {
        this.window = window;
        this.lock = lock;
        this.firstFunction = new FirstFunction(lock, window);
        this.secondFunction = new SecondFunction(1, 2, lock, window);
        this.threads = new ArrayList<>();
    }
    public void startFirstFunctionThread() {
        this.firstFunction = new FirstFunction(lock, window);
        Thread firstFunctionThread = new Thread(firstFunction);
        threads.add(firstFunctionThread);
        firstFunctionThread.start();
    }
    public void startSecondFunctionThread(int n, int k) {
        this.secondFunction = new SecondFunction(n, k, lock, window);
        Thread secondFunctionThread = new Thread(secondFunction);
        threads.add(secondFunctionThread);
        secondFunctionThread.start();
    }
    public void stopThreads() {
        for (Thread thread : threads) {
            thread.interrupt();

        }
        threads.clear();
    }
}
