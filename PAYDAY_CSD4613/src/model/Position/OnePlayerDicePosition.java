package model.Position;

import model.Board.Board;
import model.Player.Player;

/**
 * OnePlayerDicePosition class describes specific type of
 * position in our board game, called OnePlayerDicePosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public abstract class OnePlayerDicePosition extends DicePosition{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week. money also must be a positivie value<br />
     * <b>Postcondition</b>Creates a new OnePlayerDicePosition with
     * 'boardNumber' boardNumber, 'day' day and 'money' money.
     *
     * @param boardNumber
     * @param day
     * @param money
     */
    public OnePlayerDicePosition(int boardNumber, String day,int money){
        super(boardNumber,day,money);
    }

    /**
     * <b>transformer</b>: This method makes the action that a OnePlayerDicePosition has to do,having
     * access to players and board.<br />
     *
     *  <b>precondition</b>:p1,p2,b should not be null <br />
     *
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attribute/es
     *
     * @param b The board of our game
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     */
    public abstract void performAction(Board b, Player p1, Player p2);
}
