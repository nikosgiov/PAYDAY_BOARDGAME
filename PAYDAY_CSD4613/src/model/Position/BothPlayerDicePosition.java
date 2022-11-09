package model.Position;

import model.Board.Board;
import view.DiceWindow;
import model.Player.Player;
import javax.swing.*;

/**
 * BothPlayerDicePosition class describes specific type of
 * position in our board game, called BothPlayerDicePosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class BothPlayerDicePosition extends DicePosition{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week. money also must be a positivie value<br />
     * <b>Postcondition</b>Creates a new BothPlayerDicePosition with
     * 'boardNumber' boardNumber, 'day' day and 'money' money.
     *
     * @param boardNumber
     * @param day
     * @param money
     */
    public BothPlayerDicePosition(int boardNumber, String day,int money){
        super(boardNumber,day,money);
    }

    /**
     * <b>transformer</b>: This method makes the action that a BothPlayerDicePosition has to do,having
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
        int d1 = dice.getDicenum();
        dice = new DiceWindow(p2.getPlayerNumber());
        int d2 = dice.getDicenum();
        while (d1==d2) {
            dice = new DiceWindow(p1.getPlayerNumber());
            d1 = dice.getDicenum();
            dice = new DiceWindow(p2.getPlayerNumber());
            d2 = dice.getDicenum();
        }
        if (d1 > d2){
            JOptionPane.showMessageDialog(null,"Player "+
                    p1.getPlayerNumber()+": You won!","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            p1.setWallet(p1.getWallet()+getMoney());
        }else {
            JOptionPane.showMessageDialog(null,"Player "+
                    p2.getPlayerNumber()+": You won!","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            p2.setWallet(p2.getWallet()+getMoney());
        }
    }

}
