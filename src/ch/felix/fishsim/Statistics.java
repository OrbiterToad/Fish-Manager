package ch.felix.fishsim;

import javax.swing.*;

import static ch.felix.fishsim.Numbers.MAX_RIGHT;
import static ch.felix.fishsim.Numbers.getleft;


/**
 * @author Felix Spörri
 * @project dictionary
 * @date 20.09.2016 08:54
 * @with IntelliJ IDEA
 **/

class Statistics extends JDialog implements Runnable {

    private static Statistics instance = null;

    private JLabel fishCounter;
    private JLabel stepCounter;
    private JLabel turnCounter;
    private JLabel heightChangeCounter;
    private JLabel upOrdown;

    private boolean runnable;

    private Thread t;

    private JSlider upOrDownSlider;

    private Statistics() {

        runnable = true;
        setSize(350, 250);
        setLocation(getleft() + MAX_RIGHT + 35, 0);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        fishCounter = new JLabel();
        fishCounter.setBounds(20, 20, 300, 25);
        add(fishCounter);

        stepCounter = new JLabel();
        stepCounter.setBounds(20, 50, 300, 25);
        add(stepCounter);

        turnCounter = new JLabel();
        turnCounter.setBounds(20, 80, 300, 25);
        add(turnCounter);

        heightChangeCounter = new JLabel();
        heightChangeCounter.setBounds(20, 110, 300, 25);
        add(heightChangeCounter);

        upOrdown = new JLabel();
        upOrdown.setBounds(20, 140, 300, 25);
        add(upOrdown);

        upOrDownSlider = new JSlider();
        upOrDownSlider.setValue(50);
        upOrDownSlider.setEnabled(false);
        upOrDownSlider.setBounds(20, 170, 300, 25);
        add(upOrDownSlider);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
                t = null;
                instance = null;
            }
        });

    }

    static Statistics getInstance() {
        if (instance == null) instance = new Statistics();
        return instance;
    }

    void start() {
        if (null == t) {
            t = new Thread(this, "Moded");
            t.start();
        }
    }

    public void run() {
        while (runnable) {
            try {
                fishCounter.setText(String.valueOf("Fish count: " + Fish.getFishCounter()));
                stepCounter.setText(String.valueOf("Fish steps: " + Fish.getStepCounter()));
                turnCounter.setText(String.valueOf("Fish 180 turns: " + Fish.getTurnCounter()));
                heightChangeCounter.setText(String.valueOf("Fish height changes " + Fish.getHeightChangeCounter()));

                upOrdown.setText(String.valueOf("Up: " + Fish.getSwimUp() + " Down: " + Fish.getSwimDown()));
                upOrDownSlider.setValue(getPercent(Fish.getHeightChangeCounter(), Fish.getSwimUp()));
                this.repaint();
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getPercent(int max, int percent) {
        float full = (float) max;
        float up = (float) percent;

        float num = 100 / full * up;
        return (short) num;
    }
}
