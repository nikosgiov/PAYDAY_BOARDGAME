package model.Card;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;
import java.awt.*;

/**
 * MoveToDealBuyer class represents card that is type MoveToDealBuyer
 * and provides its methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class MoveToDealBuyer extends MailCard{
    private Board board;

    /**Constructor.
     *
     * <b>Postcondition</b>Creates a new MoveToDealBuyer card with
     * 'MoveToDealBuyer type, 'image' image, 'message' message and 'board' board.
     *
     * @param image
     * @param message
     * @param board
     */
    public MoveToDealBuyer(String image, String message, Board board) {
        super("MoveToDealBuyer",image,message,0);
        this.board = board;

    }

    /**
     * <b>transformer</b>: This method makes the action that a MoveToDealBuyer
     * card has to do,having access to players and board.<br />
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
        int day = findNearPos(p1);
        String name = getImage();
        Object[] options = {"Εντάξει"};
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
        if (day!=0) p1.setPosition(day,board.dayofWeek(day) );
    }

    /**
     * <b>transformer</b>: This method takes a player and returns the
     * index of the tile Deal/Buyer closest to him or zero if there is no one<br />
     *
     * <b>precondition</b>:p should not be null <br />
     *
     * <b>postcondition</b>: returns the index of nearest position of Deal/Buyer or 0 if does not exist.
     *
     * @param p Player of the game
     *
     * @return the index of nearest position of Deal/Buyer if does not exist
     */
    private int findNearPos(Player p){
        int position = p.getPosition();
        int[] pos = new int[11];
        int[] posDistance = new int[11];
        int i=1;
        int j=0;
        while (i != 30){
            if ( board.getTileByDay(i).getImage().equals("deal.png") || board.getTileByDay(i).getImage().equals("buyer.png") )
                pos[j++] = board.getTileByDay(i).getBoardNumber();
            i++;
        }
        for (i=0; i<11; i++){
            posDistance[i] = pos[i] - position;
        }
        int min = Integer.MAX_VALUE;
        for (i=0; i<10; i++){
            if ( posDistance[i]>0 && min>posDistance[i] ) min = posDistance[i];
        }
        if (min==Integer.MAX_VALUE) return 0;
        for (i=0; i<11; i++){
            if (min==posDistance[i]) return pos[i];
        }
        return 0;
    }
}
