package model.Position;

import model.Board.Board;
import model.Card.DealCard;
import view.SellCardWindow;
import model.Player.Player;
import java.util.ArrayList;

/**
 * BuyerPosition class describes specific type of
 * position in our board game, called BuyerPosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class BuyerPosition extends Position{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week.<br />
     * <b>Postcondition</b>Creates a new BuyerPosition with
     * 'boardNumber' boardNumber and 'day' day.
     *
     * @param boardNumber
     * @param day
     */
    public BuyerPosition(int boardNumber, String day){
        super(boardNumber,day);
    }

    /**
     * <b>transformer</b>: This method makes the action that a BuyerPosition has to do,having
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
        ArrayList<DealCard> tmp = p1.getCardsOwned();
        if ( tmp.isEmpty() ) return;
        SellCardWindow sellCardWindow = new SellCardWindow(p1);
        int index = sellCardWindow.getChoice();
        DealCard a =(DealCard)p1.popDealCard(index);
        a.saleCard(p1);
    }
}
