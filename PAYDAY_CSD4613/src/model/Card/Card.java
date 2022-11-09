package model.Card;

import model.Board.Board;
import model.Player.Player;

import java.io.Serializable;


/**
 * Card class describes the characteristics of a card
 * and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public abstract class Card implements Serializable {
    private String type;
    private String image;
    private String message;

    /**
     * <b>accessor(selector)</b>:Returns the type of the card <br />
     *
     * <p><b>Postcondition:</b> Returns the type of the card </p>
     *
     * @return the type of the card
     */
    public String getType() {return type;}

    /**
     * <b>accessor(selector)</b>:Returns the name of the card's image <br />
     *
     * <p><b>Postcondition:</b> Returns the name of the card's image </p>
     *
     * @return the name of the card's image
     */
    public String getImage() {return image;}

    /**
     * <b>accessor(selector)</b>:Returns the message of the card <br />
     *
     * <p><b>Postcondition:</b> returns the message of the card </p>
     *
     * @return the message of the card
     */
    public String getMessage() {return message;}

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new card with
     * 'type' type, 'image' image and 'message' message.
     *
     * @param type
     * @param image
     * @param message
     */
    public Card(String type, String image, String message) {
        this.type = type;
        this.image = image;
        this.message = message;
    }

    /**
     * <b>transformer</b>: This method makes the action that card has to do,having
     * access to players and board<br />
     *  <b>precondition</b>:p1,p2,b should not be null <br />
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attrivute/es
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     * @param b The board of our game
     */
    public abstract void action(Player p1, Player p2, Board b);

}
