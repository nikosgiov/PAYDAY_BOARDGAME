package model.Position;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;

/**
 * LoterryPosition class describes specific type of
 * position in our board game, called LoterryPosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class LoterryPosition extends BothPlayerDicePosition{

    /**Constructor.
     *
     *  <b>precondition</b>: boardNumber,day and money should not be null.
     *  Also boardnumber must be a value from 1-31 and day a String
     *  from one of the 7 days of the week. money also must be a positivie value<br />
     * <b>Postcondition</b>Creates a new LoterryPosition with
     * 'boardNumber' boardNumber, 'day' day and 'money' money.
     *
     * @param boardNumber
     * @param day
     * @param money
     */
    public LoterryPosition(int boardNumber, String day,int money){
        super(boardNumber,day,money);
    }

    /**
     * <b>transformer</b>: This method makes the action that a LoterryPosition has to do,having
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
        boolean exit = false;
        int input1=1;
        while (!exit) {
            try {
                input1 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Player " + p1.getPlayerNumber() + ": Select a number (1-6)"));
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                        "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (input1>=1 && input1<=6) exit = true;
            else JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                    "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
        }
        exit = false;
        int input2=1;
        while (!exit) {
            try {
                input2 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Player " + p2.getPlayerNumber() + ": Select a number (1-6)", 1));
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                        "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (input2>=1 && input2<=6) exit = true;
            else JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                    "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
        }
        int dice = p1.dice.roll();
        while(dice!=input1 && dice!=input2){
            dice = p1.dice.roll();
        }
        if (dice==input1){
            JOptionPane.showMessageDialog(null,"Player "+
                    p1.getPlayerNumber()+" wins!","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            p1.setWallet(p1.getWallet()+getMoney());
        }
        else{
            JOptionPane.showMessageDialog(null,"Player "+
                    p2.getPlayerNumber()+" wins!","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            p2.setWallet(p2.getWallet()+getMoney());
        }
    }

}
