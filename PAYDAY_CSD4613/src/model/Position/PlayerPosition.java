package model.Position;

import model.Board.Board;
import model.Player.Player;

/**
 * PlayerPosition class describes specific type of
 * position in our board game, called PlayerPosition,
 * that obviously concerns the position of a player.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class PlayerPosition extends Position{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week.<br />
     * <b>Postcondition</b>Creates a new PlayerPosition with
     * 'boardNumber' boardNumber and 'day' day.
     *
     * @param boardNumber
     * @param day
     */
    public PlayerPosition(int boardNumber, String day){
        super(boardNumber,day);
    }

    /**
     * <b>transformer</b>: This method does nothing
     * and it is supposed to not called.<br />
     *
     * <b>postcondition</b>: nothing is happening.
     *
     * @param b The board of our game
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     */
    @Override
    public void performAction(Board b, Player p1, Player p2) {return;}
}
