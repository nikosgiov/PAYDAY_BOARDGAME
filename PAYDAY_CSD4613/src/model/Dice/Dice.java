package model.Dice;

import java.io.Serializable;
import java.util.Random;

/**
 * Dice class creates a Dice entity with all
 * the characteristics of a dice.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */

public class Dice implements Serializable {

    private int lastdice;

    /**
     * <b>Transformer:</b> Rolls the dice.
     * <Postcondition:</b> lastdice field is initialized.
     * @return the number that dice showed
     */
    public int roll(){
        lastdice = new Random().nextInt(6)+1;
        return lastdice;
    }

    /**
     * <b>accessor(selector)</b>:Returns the last number that dice showed <br />
     *
     * <p><b>Postcondition:</b> returns the last number of the dice </p>
     *
     * @return the last number of the dice
     */
    public int getLastdice(){return this.lastdice;}
}
