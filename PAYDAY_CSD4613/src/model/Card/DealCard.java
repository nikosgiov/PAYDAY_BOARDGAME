package model.Card;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;
import java.awt.*;

/**
 * DealCard class represents card that is type Deal
 * and provides its methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class DealCard extends Card{

    private int costPrice;
    private int salePrice;

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new deal card with
     * 'image' image, 'message' message, 'costPrice' costPrice,
     *  'salePrice' salePrice and 'Deal' type.
     *
     * @param image
     * @param message
     * @param costPrice
     * @param salePrice
     */
    public DealCard(String image, String message, int costPrice, int salePrice){
        super("Deal",image,message);
        this.costPrice = costPrice;
        this.salePrice = salePrice;
    }

    /**
     * <b>transformer</b>: This method makes the action that Deal card has to do,having
     * access to players and board<br />
     *
     * <b>precondition</b>:p1,p2,b should not be null. <br />
     *
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attribute/es.
     *
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     * @param b The board of our game
     */
    @Override
    public void action(Player p1, Player p2, Board b){
        String name = getImage();
        Object[] options = {"Άγορασε","Αγνόησε τη συμφωνία"};
        Image image = new ImageIcon(this.getClass().getResource("/resources/images/"+name)).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane jp = new JOptionPane();
        int n = jp.showOptionDialog(null,
                getMessage() + "\nΤιμή Αγοράς: " + costPrice + " Ευρώ \nΤιμή Πώλησης: " + salePrice + " Ευρώ \n",
                getType(),
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
        if (n==0){
            p1.walletCheck(costPrice);
            p1.setWallet(p1.getWallet()-costPrice);
            p1.addDealCard(this);
        }
    }

    /**
     * <b>transformer</b>: This method makes sells this dealcard from its owner
     * player p<br />
     *
     *  <b>precondition</b>:p should not be null <br />
     *
     * <b>postcondition</b>: wallet field of p is updated
     *
     * @param p Player that sells his card

     */
    public void saleCard(Player p){
        p.setWallet( p.getWallet()+this.salePrice );
        JOptionPane.showMessageDialog(null, "Card successfully sold!",
                "PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
    }

}
