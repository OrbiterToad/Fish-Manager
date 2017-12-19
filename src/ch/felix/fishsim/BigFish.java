package ch.felix.fishsim;

import javax.swing.*;

/**
 * @author Felix
 * @project FishManager
 * @with IntelliJ IDEA
 **/
public class BigFish extends Fish {
    BigFish(String name, short x, short y, Boolean swimDirection, byte fishSpeed, JFrame frame) {
        super(name, x, y, swimDirection, fishSpeed, frame);
        super.setImgFishLeft(new ImageIcon("img/fishB.png"));
        super.setImgFishRight(new ImageIcon("img/fishB1.png"));
    }
}
