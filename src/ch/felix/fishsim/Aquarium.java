package ch.felix.fishsim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import static ch.felix.fishsim.Numbers.*;


/**
 * @author Felix Spörri
 * @project dictionary
 * @date 19.09.2016 16:02
 * @with IntelliJ IDEA
 **/
public class Aquarium extends JFrame implements ActionListener {

    private JButton addFish;
    private JButton showStatistics;

    private Aquarium() {
        setSize(getMaxRight() + 50, getMaxBot() + 100);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        addFish = new JButton("Add");
        addFish.setBounds(15, getMaxBot() + 40, 100, 25);
        addFish.addActionListener(this);

        showStatistics = new JButton("Statistics");
        showStatistics.setBounds(130, getMaxBot() + 40, 100, 25);
        showStatistics.addActionListener(this);

        add(addFish);
        add(showStatistics);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        long startTime = System.nanoTime();
        run(10);

        long endTime = System.nanoTime();

        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);

        System.out.print("Time in Nano: ");
        System.out.println(endTime - startTime);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
            }
        });


    }

    public static void main(String[] args) {
        new Aquarium();
    }

    private void run(int number) {
        Statistics.getInstance().start();
        while (number != 0) {
            number--;

            if (getRandom(20, 1) == 1) {
                BigFish bigFish = new BigFish("Big Fish" + number, getRandom(getMaxRight(), getleft()),
                        getRandom(getMaxBot(), getTop()), getRandom(), (byte) getRandom(80, 20), this);
                bigFish.swim();
            } else {
                Fish fish = new Fish("Fish" + number, getRandom(getMaxRight(), getleft()), getRandom(getMaxBot(),
                        getTop()), getRandom(), (byte) getRandom(80, 20), this);
                fish.swim();
            }
        }
    }

    private short getRandom(int max, int min) {
        Random rand = new Random();
        return (short) (rand.nextInt(max) + min);
    }

    private boolean getRandom() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        return randomNumber == 1;
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addFish) {
            Fish fish = new Fish("Fish", getRandom(getMaxRight(), getleft()), getRandom(getMaxBot(), getTop()),
                    getRandom(), (byte) getRandom(80, 20), this);
            fish.swim();
        } else if (ae.getSource() == showStatistics) {
            Statistics.getInstance().start();
        }
    }
}