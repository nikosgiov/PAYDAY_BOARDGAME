package model.Position;

import model.Board.Board;
import model.Player.Player;

/**
 * Mail2Position class describes specific type of
 * position in our board game, called Mail2Position.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Mail2Position extends CardPosition{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week.<br />
     * <b>Postcondition</b>Creates a new Mail2Position with
     * 'boardNumber' boardNumber and 'day' day.
     *
     * @param boardNumber
     * @param day
     */
    public Mail2Position(int boardNumber, String day){
        super(boardNumber,day);
    }

    /**
     * <b>transformer</b>: This method makes the action that a Mail2Position has to do,having
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
    @Override
    public void  performAction(Board b, Player p1, Player p2){
        b.drawOneCard(false).action(p1, p2, b);
    }

}
