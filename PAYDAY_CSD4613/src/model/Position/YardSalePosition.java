package model.Position;

import model.Board.Board;
import view.DiceWindow;
import model.Player.Player;
import javax.swing.*;

/**
 * YardSalePosition class describes specific type of
 * position in our board game, called YardSalePosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class YardSalePosition extends OnePlayerDicePosition{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week. money also must be a positivie value<br />
     * <b>Postcondition</b>Creates a new YardSalePosition with
     * 'boardNumber' boardNumber, 'day' day and 'money' money.
     *
     * @param boardNumber
     * @param day
     * @param money
     */
    public YardSalePosition(int boardNumber, String day,int money){
        super(boardNumber,day,money);
    }

    /**
     * <b>transformer</b>: This method makes the action that a YardSalePosition has to do,having
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
        DiceWindow dice = new DiceWindow(p1.getPlayerNumber());
        int cash = dice.getDicenum() * 100;
        p1.walletCheck(cash);
        p1.setWallet(p1.getWallet()-cash);
        JOptionPane.showMessageDialog(null, "Player "+p1.getPlayerNumber()+": You lose "
                        +cash+" Euros!You automatically draw a deal card.",
                "PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
        b.drawOneCard(true).action(p1, p2, b);
    }

}
