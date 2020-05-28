package view;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel {
    public JButton buttonStart;
    private JButton buttonStop;
    private JButton onComing;
    private JButton outComing;
    Buttons()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        buttonStart = new JButton("Start");
        buttonStart.setPreferredSize(new Dimension(200,100));

        buttonStop = new JButton("Stop and clear");
        buttonStop.setPreferredSize(new Dimension(200,100));

        onComing = new JButton("+");
        onComing.setPreferredSize(new Dimension(200,100));

        outComing = new JButton("-");
        outComing.setPreferredSize(new Dimension(200,100));

        this.add(buttonStart);
        this.add(buttonStop);
        this.add(Box.createHorizontalGlue());
        this.add(onComing);
        this.add(outComing);
    }
    public JButton getMainButton() {
        return buttonStart;
    }
    public JButton getButtonStop() { return buttonStop; }
    public JButton getOnComing() {
        return onComing;
    }
    public JButton getOutComing() {
        return outComing;
    }
}
