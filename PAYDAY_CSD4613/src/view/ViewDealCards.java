package view;

import javax.swing.*;
import model.Card.DealCard;
import model.Player.Player;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * ViewDealCards class makes a pop up
 * window that let user to see his
 * DealCards if he has any. Otherwise he
 * get a message that he doesn't has any.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class ViewDealCards {

    private static Dialog d;

    /**Constructor.
     *
     * <b>precondition</b>: p should not be null <br />
     *
     * <b>Postcondition</b>Creates a new frame and according
     * to the number of DealCards player p has creates buttons
     * for each one.Else he exits immediately
     *
     * @param p
     */
    public ViewDealCards(Player p) {
        ArrayList<DealCard> tmp = p.getCardsOwned();
        if (tmp.isEmpty()){
            JOptionPane.showMessageDialog(null,"Player "+
                    p.getPlayerNumber()+": You dont have any Deal Cards yet.","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Frame f = new Frame();
        d = new Dialog(f, "PAYDAY: CARD SELLER", true);
        d.setLayout(new FlowLayout());
        for (int i=0; i<tmp.size(); i++){
            createbutton( tmp.get(i) );
        }
        JLabel label = new JLabel("Player "+p.getPlayerNumber()+": Click a card to exit.");
        label.setFont(new Font("testin", Font.PLAIN, 20));
        d.add(label);
        d.setBounds(700,300,600, 600);
        d.setVisible(true);
    }

    /**
     * <b>transformer</b>: creates a button according to the DealCard
     * got as an argument.It also implements an actionPerformed inside
     * its block.<br />
     *
     * <b>precondition</b>: dc should not be null <br />
     *
     * <b>postcondition</b>: buttons should appear on the frame.
     *
     * @param dc
     */
    private void createbutton(DealCard dc){
        JButton button = new JButton();
        String name = dc.getImage();
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/images/"+name));
        Image image = img.getImage();
        Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        button.setIcon(img);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewDealCards.d.setVisible(false);
            }
        });
        d.add(button);
    }
}
