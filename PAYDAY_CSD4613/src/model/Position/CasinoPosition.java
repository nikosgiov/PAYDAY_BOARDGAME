package model.Position;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;

/**
 * CasinoPosition class describes specific type of
 * position in our board game, called CasinoPosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class CasinoPosition extends Position{

    private int amount;

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week. amount also must be a positivie value<br />
     * <b>Postcondition</b>Creates a new CasinoPosition with
     * 'boardNumber' boardNumber, 'day' day and 'amount' amount.
     *
     * @param boardNumber
     * @param day
     * @param amount
     */
    public CasinoPosition(int boardNumber, String day,int amount){
        super(boardNumber,day);
        this.amount = amount;
    }

    /**
     * <b>transformer</b>: This method makes the action that a CasinoPosition has to do,having
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
        if ( (p1.dice.getLastdice()%2)==0 ){
            JOptionPane.showMessageDialog(null,"Player "+
                    p1.getPlayerNumber()+": You won! You get 500","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            p1.setWallet(p1.getWallet()+amount);
        }
        else{
            //MSG
            JOptionPane optPane = new JOptionPane("Player "+p1.getPlayerNumber()+
                    ": You lost! You have to pay 500",JOptionPane.YES_OPTION);
            optPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            optPane.setIcon(null);
            JPanel buttonPanel = (JPanel)optPane.getComponent(1);
            JButton buttonOk = (JButton)buttonPanel.getComponent(0);
            buttonOk.setText("Pay");
            JDialog d = optPane.createDialog(null,"PAYDAY: Message");
            d.show();
            //MSG
            p1.walletCheck(amount);
            p1.setWallet(p1.getWallet()-amount);
        }
    }

}
