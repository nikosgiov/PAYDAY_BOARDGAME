package view;

import javax.swing.*;
import model.Dice.Dice;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * DiceWindow class makes a pop up
 * window that let user to roll a dice
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class DiceWindow {

    private static Dialog d;
    private int dicenum;
    private int player;

    /**
     * <b>accessor(selector)</b>:Returns the dicenum of DiceWindow<br />
     *
     * <p><b>Postcondition:</b> Returns the dicenum of DiceWindow</p>
     *
     * @return the dicenum of DiceWindow
     */
    public int getDicenum(){return dicenum;}

    /**Constructor.
     *
     * <b>precondition</b>: playerNumber balue must be 1-2 (not null).<br />
     *
     * <b>Postcondition</b>Creates a new frame with an image of
     * a dice and 2 bu8ttons. Once user clicks thr ROLL button image
     * of dice must get updated.The frame must close once user clicks exit.
     *
     * @param playerNumber
     */
    public DiceWindow(int playerNumber) {
        dicenum = 0;
        Dice dice = new Dice();
        this.player = playerNumber;
        Frame f = new Frame();
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/d1.png"));
        JLabel diceph = new JLabel(img);
        diceph.setBounds(155,100,80,80);
        d = new Dialog(f, "PAYDAY: DICE ROLLER", true);
        d.setLayout(null);
        JButton roller = new JButton("ROLLER");
        roller.setBounds(95,230,200,50);
        JButton exit = new JButton("EXIT");
        exit.setBounds(95,310,200,50);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dicenum!=0) DiceWindow.d.setVisible(false);
            }
        });
        roller.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = dice.roll();
                dicenum = a;
                String str="";
                if (a==1) str = "d1.png";
                if (a==2) str = "d2.png";
                if (a==3) str = "d3.png";
                if (a==4) str = "d4.png";
                if (a==5) str = "d5.png";
                if (a==6) str = "d6.png";
                ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/"+str));
                diceph.setIcon(img);
                roller.setEnabled(false);
            }
        });

        JLabel label = new JLabel("Player "+player+": Roll dice and then exit.");
        label.setBounds(50,50,500,50);
        label.setFont(new Font("testin", Font.PLAIN, 20));
        d.add(label);
        d.add(roller);
        d.add(diceph);
        d.add(exit);
        d.setResizable(false);
        d.setBounds(700,300,400, 400);
        d.setVisible(true);
    }
}
