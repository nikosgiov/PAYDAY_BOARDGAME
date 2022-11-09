package model.Card;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;
import java.awt.*;

/**
 * GetFromNeighbor class describes the characteristics of a card
 * that is GetFromNeighbor type.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class GetFromNeighbor extends MailCard {

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new GetFromNeighbor card with
     * 'image' image, 'message' message, 'amount' amount and 'GetFromNeighbor' type.
     *
     * @param image
     * @param message
     * @param amount
     */
    public GetFromNeighbor(String image, String message,int amount){
        super("GetFromNeighbor",image,message,amount);
    }

    /**
     * <b>transformer</b>: This method makes the action that GetFromNeighbor card has to do,having
     * access to players and board.<br />
     *
     * <b>precondition</b>:p1,p2,b should not be null.<br />
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
        Object[] options = {"Πάρε "+getAmount()+" Ευρώ από τον αντίπαλο"};
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
        p2.walletCheck(getAmount());
        p1.setWallet(p1.getWallet()+getAmount());
        p2.setWallet(p2.getWallet()-getAmount());
    }

}
