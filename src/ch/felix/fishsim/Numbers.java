package ch.felix.fishsim;

/**
 * @author Felix Spörri
 * @project dictionary
 * @date 20.09.2016 09:40
 * @with IntelliJ IDEA
 **/
final class Numbers {

    private static short TOP = 10;                //10    DO NOT CHANGE
    private static short LEFT = 15;               //15    DO NOT CHANGE
    private static short MAX_BOT = 322;           //200private
    static short MAX_RIGHT = 350;         //350

    public static void setTop(short TOP) {
        Numbers.TOP = TOP;
    }

    public static void setLEFT(short LEFT) {
        Numbers.LEFT = LEFT;
    }

    public static short getTop() {
        return TOP;
    }

    public static short getleft() {
        return LEFT;
    }

    public static short getMaxBot() {
        return MAX_BOT;
    }

    public static void setMaxBot(short maxBot) {
        MAX_BOT = maxBot;
    }

    public static short getMaxRight() {
        return MAX_RIGHT;
    }

    public static void setMaxRight(short maxRight) {
        MAX_RIGHT = maxRight;
    }


}
