package model.Position;

import model.Board.Board;
import model.Player.Player;

import java.io.Serializable;

/**
 * Position class describes the characteristics of a position
 * in our board game and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public abstract class Position implements Serializable {

    private int boardNumber;
    private String day;

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber and day should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week.<br />
     * <b>Postcondition</b>Creates a new position with
     * 'boardNumber' boardNumber and 'day' day.
     *
     * @param boardNumber
     * @param day
     */
    public Position(int boardNumber, String day){
        this.boardNumber=boardNumber;
        this.day=day;
    }

    /**
     * <b>transformer</b>: This method makes the action that a Position has to do,having
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
    public abstract void  performAction(Board b, Player p1, Player p2);

    /**
     * <b>accessor(selector)</b>:Returns the boardNumber of position <br />
     *
     * <p><b>Postcondition:</b> returns the boardNumber of position </p>
     *
     * @return the boardNumber of position
     */
    public int getBoardNumber(){return  boardNumber;}

    /**
     * <b>accessor(selector)</b>:Returns the day of position <br />
     *
     * <p><b>Postcondition:</b> returns the day of position </p>
     *
     * @return the day of position
     */
    public String getDay(){return day;}

    /**
     * <b>transformer(mutative)</b>: sets the boardNumber of position <br />
     * <p><b>Postcondition:</b> the boardNumber has been set</p>
     *
     * @param boardNumber
     */
    public void setBoardNumber(int boardNumber){this.boardNumber=boardNumber;}

    /**
     * <b>transformer(mutative)</b>: sets the day of position <br />
     * <p><b>Postcondition:</b> the day has been set</p>
     *
     * @param day
     */
    public void setDay(String day){this.day=day;}


}
