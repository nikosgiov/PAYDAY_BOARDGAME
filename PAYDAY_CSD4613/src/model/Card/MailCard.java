package model.Card;

import model.Board.Board;
import model.Player.Player;

/**
 * MailCard class represents card that is type Mail
 * and provides its methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public abstract class MailCard extends Card{

    private int amount;

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new mail card with
     * 'type' type, 'image' image, 'message' message and 'amount' amount.
     *
     * @param type
     * @param image
     * @param message
     * @param amount
     */
    public MailCard(String type, String image, String message,int amount){
        super(type,image,message);
        this.amount=amount;
    }

    /**
     * <b>accessor(selector)</b>:Returns the amount of the mail card <br />
     *
     * <p><b>Postcondition:</b> returns the amount of the mail card </p>
     *
     * @return the amount of the mail card
     */
    public int getAmount() {return amount;}

    /**
     * <b>transformer</b>: This method makes the action that card has to do,having
     * access to players and board.<br />
     *
     *  <b>precondition</b>:p1,p2,b should not be null.<br />
     *
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attribute/es
     *
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     * @param b The board of our game
     */
    public abstract void action(Player p1, Player p2, Board b);
}
