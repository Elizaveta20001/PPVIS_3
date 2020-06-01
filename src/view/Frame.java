package view;

import controller.Controller;
import model.FirstFunction;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class Frame {
    private JFrame frame;
    private Controller controller;
    private int width = 1500;
    private int height = 800;
    private TaskPanel taskPanel;
    private PointsTable mainPointsTable;
    private Buttons buttons;
    private Graphic graphic;
    private FirstFunction calc;
    private JScrollPane scroll;
    private ReentrantLock lock;

    public Graphic getGraphic() {
        return graphic;
    }
    public PointsTable getMainPointsTable() {
        return mainPointsTable;
    }
    public Frame() {
        lock = new ReentrantLock();
        controller = new Controller(Frame.this, lock);
        frame = new JFrame();
        taskPanel = new TaskPanel();
        buttons = new Buttons();
    }
    public JFrame buildFrame() {
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout());

        graphic = new Graphic(controller);
        mainPointsTable = new PointsTable(this);

        scroll = new JScrollPane(graphic);
        scroll.setPreferredSize(new Dimension(605, 505));
        scroll.setAutoscrolls(true);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        frame.add(mainPointsTable.buildTable(), BorderLayout.WEST);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(taskPanel.buildComponent(), BorderLayout.SOUTH);
        frame.add(buttons,BorderLayout.BEFORE_FIRST_LINE);

        HoldAndDragListener listener = new HoldAndDragListener(graphic);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scroll.getViewport().addMouseListener(listener);
        scroll.getViewport().addMouseMotionListener(listener);

        ZoomListener zoomListener = new ZoomListener(Frame.this, graphic);
        scroll.addMouseWheelListener(zoomListener);

        buttons.getMainButton().addActionListener(event -> {
            controller.stopThreads();
            mainPointsTable.clearTable();
            if(graphic.getFunctionsData().isEmpty())
            {
                graphic.init();
            }
            startDrawing();
        });
        buttons.getButtonStop().addActionListener(event -> {
            controller.stopThreads();
            mainPointsTable.clearTable();
            graphic.clear();
        });
        buttons.getOnComing().addActionListener(actionEvent -> {
            zoomListener.onComingAction();
        });
        buttons.getOutComing().addActionListener(actionEvent -> {
            zoomListener.outComingAction();
        });
        return frame;
    }

    public void startDrawing() {
        controller.startFirstFunctionThread();
        controller.startSecondFunctionThread(taskPanel.getN(), taskPanel.getK());
    }
}