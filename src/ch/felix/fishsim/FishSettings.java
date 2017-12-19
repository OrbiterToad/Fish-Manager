package ch.felix.fishsim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Felix Spörri
 * @project dictionary
 * @date 21.09.2016 11:22
 * @with IntelliJ IDEA
 **/
class FishSettings extends JDialog implements ActionListener {

    private final Fish fish;
    private JButton killFish;
    private JButton save;
    private JTextField name;
    private JSlider speed;


    FishSettings(final Fish fish) {
        this.fish = fish;
        setSize(250, 150);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        name = new JTextField(fish.getName());
        name.setBounds(20, 10, 200, 25);
        add(name);

        JLabel fast = new JLabel("Fast");
        fast.setBounds(20, 40, 200, 25);
        add(fast);

        JLabel slow = new JLabel("Slow");
        slow.setText("Slow");
        slow.setBounds(197, 40, 200, 25);
        add(slow);

        speed = new JSlider();
        speed.setValue(fish.getFishSpeed());
        speed.setBounds(20, 60, 200, 25);
        add(speed);

        save = new JButton("Save");
        save.setBounds(20, 90, 100, 25);
        save.addActionListener(this);
        add(save);

        killFish = new JButton("Kill");
        killFish.setBounds(130, 90, 100, 25);
        killFish.addActionListener(this);
        add(killFish);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                closingThis();
            }
        });
    }

    private void closingThis() {
        fish.setSpeed((byte) speed.getValue());
        fish.setName(name.getText());
        dispose();
        Thread.currentThread().interrupt();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {
            closingThis();
        } else if (ae.getSource() == killFish) {
            fish.setSwimming(false);
            closingThis();
            Fish.removeFishCounter();
        }
    }
}
