package model.Card;

import model.Board.Board;
import model.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Charity class describes the characteristics of a card
 * that is Charity type.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Charity extends MailCard{

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new deal card with
     * 'image' image, 'message' message, 'amount' amount and 'Charity' type.
     *
     * @param image
     * @param message
     * @param amount
     */
    public Charity(String image, String message,int amount){
        super("Charity",image,message,amount);
    }

    /**
     * <b>transformer</b>: This method makes the action that Charity card has to do,having
     * access to players and board<br />
     *
     * <b>precondition</b>:p1,p2,b should not be null <br />
     *
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attribute/es
     *
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     * @param b The board of our game
     */
    @Override
    public void action(Player p1, Player p2, Board b){
        String name = getImage();
        Object[] options = {"Πλήρωσε "+getAmount()+" Ευρώ στο Jackpot"};
        Image image = new ImageIcon(this.getClass().getResource("/resources/images/"+name)).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane jp = new JOptionPane();
        int n = jp.showOptionDialog(null,
                getMessage(),
                getType(),
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
        p1.walletCheck( getAmount() );
        p1.setWallet(p1.getWallet()-getAmount());
        b.setJackpot(b.getJackpot()+getAmount());
    }

}
