package model.Position;

import model.Board.Board;
import model.Card.Bill;
import model.Card.MailCard;
import model.Player.Player;
import javax.swing.*;
import java.util.ArrayList;

/**
 * PayDayPosition class describes specific type of
 * position in our board game, called PayDayPosition.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class PayDayPosition extends Position{

    private int salary;

    /**Constructor.
     *
     *  <b>precondition</b>: Salary must be a positive number
    *   and not null.<br />
     * <b>Postcondition</b>Creates a new PayDayPosition with
     * '31' boardNumber, 'Wednesday' day and 'salary' salary.
     *
     * @param salary
     */
    public PayDayPosition(int salary){
        super(31, "Wednesday");
        this.salary = salary;
    }

    /**
     * <b>transformer</b>: This method makes the action that a PayDayPosition has to do,having
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
        p1.updateNumofPaydays();
        payment(p1);
        loanPayment(p1);
        if( !( b.getMonths()==p1.getNumOfPaydays() ) )
            p1.setPosition(0,"Start");
    }

    /**
     * <b>transformer</b>: This method pays the salary of player p that is given.<br />
     *
     *  <b>precondition</b>:p should not be null <br />
     *
     * <b>postcondition</b>: p's wallet should be incremented.
     *
     * @param p Player of the game
     */
    private void payment(Player p){
        p.setWallet(p.getWallet()+salary);
        JOptionPane.showMessageDialog(null, "It's pay day! 3500 are yours!",
                "PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
        Bill a = null;
        ArrayList<MailCard> tmp = p.getCardsToPay();
        for (int i=0; i< tmp.size(); i++){
            a = (Bill)p.popPayCard();
            a.pay(p);
        }
    }

    /**
     * <b>transformer</b>: This method forces player p to pay the taxes
     * of his loan and to pay an amount if he wants to.If he does not have
     * any loans then does nothing.<br />
     *
     *  <b>precondition</b>:p should not be null <br />
     *
     * <b>postcondition</b>: p's wallet should be decreased if he had loans.
     *
     * @param p Player of the game
     */
    private void loanPayment(Player p){
        if ( p.getDebts()!=0 ){
            //MSG
            JOptionPane optPane = new JOptionPane("You have to pay 10% of your loans",JOptionPane.YES_OPTION);
            optPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            optPane.setIcon(null);
            JPanel buttonPanel = (JPanel)optPane.getComponent(1);
            JButton buttonOk = (JButton)buttonPanel.getComponent(0);
            buttonOk.setText("Pay");
            JDialog d = optPane.createDialog(null,"PAYDAY: Message");
            d.show();
            //MSG
            int payment = (int)(p.getDebts()*0.1);
            p.walletCheck(payment);
            boolean exit = false;
            int inputk=0;
            while (!exit) {
                inputk = Integer.parseInt(JOptionPane.showInputDialog(
                        "Pay the amount of loan that you want(else put 0): ", 1000));
                if (inputk % 1000 == 0) exit = true;
                else JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                        "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
            }
            p.setDebts(p.getDebts()-inputk);
        }
    }

}
