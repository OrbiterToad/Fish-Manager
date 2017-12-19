package ch.felix.fishsim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static ch.felix.fishsim.Numbers.getMaxRight;


/**
 * @author Felix Spörri
 * @project dictionary
 * @date 19.09.2016 12:55
 * @with IntelliJ IDEA
 **/
class Fish extends JButton implements Runnable, ActionListener {

    private static int stepCounter;
    private static short fishCounter;
    private static int turnCounter;
    private static int heightChangeCounter;

    private static int swimUp;
    private static int swimDown;

    private boolean swimming = true;

    private String name;

    private Boolean swimDirection;
    private short width;
    private short y;
    private short x;
    private byte fishSpeed;

    private Thread currentFish;
    private ImageIcon imgFishRight = new ImageIcon("img/fish.png");
    private ImageIcon imgFishLeft = new ImageIcon("img/fish2.png");

    Fish(String name, short x, short y, Boolean swimDirection, byte fishSpeed, JFrame frame) {
        this.name = name;

        this.swimDirection = swimDirection;
        this.x = x;
        this.y = y;
        this.fishSpeed = fishSpeed;

        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        addActionListener(this);

        if (y == 66 && x == 66) {
            width = 100;
            setBounds(x, y, width, 25);
            setText("¯\\_(シ)_/¯");
        } else {
            width = 25;
            setBounds(x, y, width, 25);
        }


        if (this.swimDirection) {
            setIcon(imgFishLeft);
        } else {
            setIcon(imgFishRight);
        }

        frame.add(this);

    }

    static int getStepCounter() {
        return stepCounter;
    }

    private static void addStepCounter() {
        Fish.stepCounter++;
    }

    static short getFishCounter() {
        return fishCounter;
    }

    private static void addFishCounter() {
        Fish.fishCounter++;
    }

    static void removeFishCounter() {
        Fish.fishCounter--;
    }

    static int getTurnCounter() {
        return turnCounter;
    }

    private static void addTurnCounter() {
        Fish.turnCounter++;
    }

    static int getHeightChangeCounter() {
        return heightChangeCounter;
    }

    private static void addHeightChangeCounter() {
        Fish.heightChangeCounter++;
    }

    static int getSwimUp() {
        return swimUp;
    }

    private static void addUp() {
        Fish.swimUp++;
    }

    static int getSwimDown() {
        return swimDown;
    }

    private static void addDown() {
        Fish.swimDown++;
    }

    public void setImgFishRight(ImageIcon imgFishRight) {
        this.imgFishRight = imgFishRight;
    }

    public void setImgFishLeft(ImageIcon imgFishLeft) {
        this.imgFishLeft = imgFishLeft;
    }

    void swim() {
        if (currentFish == null) {
            currentFish = new Thread(this, "fish");
            currentFish.start();
        }
        addFishCounter();
    }

    public void run() {
        fishMovement();
    }

    /**
     * Grande Algorythmus
     */
    private void fishMovement() {
        int nr = x;
        boolean back = this.swimDirection;

        while (swimming) {
            setToolTipText(name);
            if (nr > getMaxRight()) {
                back = true;
                setIcon(imgFishLeft);
            } else if (nr < Numbers.getleft()) {
                back = false;
                setIcon(imgFishRight);
            }
            back = changeDirection(back);
            y = changeHeight(y);

            if (back) {
                nr = nr - 1;
            } else {
                nr = nr + 1;
            }
            addStepCounter();
            setBounds(nr, y, width, 25);
            try {
                Thread.sleep(fishSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
        currentFish = null;
    }

    private short changeHeight(short y) {
        Random rand = new Random();
        int num = rand.nextInt(30) + 2;
        if (num == 10) {
            num = rand.nextInt(2) + 1;
            if ((num == 1) && ((y + 1) < Numbers.getMaxBot())) {
                addDown();
                addHeightChangeCounter();
                return (short) (y + 1);
            } else if (y - 1 > Numbers.getTop()) {
                addHeightChangeCounter();
                addUp();
                return (short) (y - 1);
            }
        }
        return y;
    }

    private boolean changeDirection(boolean currentDirection) {
        Random rand = new Random();
        int num = rand.nextInt(200) + 1;

        if ((num == 10) && currentDirection) {
            setIcon(imgFishRight);
            addTurnCounter();
            return false;
        } else if (num == 10) {
            setIcon(imgFishLeft);
            addTurnCounter();
            return true;
        } else return currentDirection;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this) {
            new FishSettings(this);
        }
    }

    void setSwimming(boolean swimming) {
        this.swimming = swimming;
    }

    void setSpeed(byte speed) {
        this.fishSpeed = speed;
    }

    byte getFishSpeed() {
        return fishSpeed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}