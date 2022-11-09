package model.Player;

import model.Card.*;

import java.io.Serializable;
import java.util.ArrayList;
import model.Dice.Dice;
import model.Position.*;
import javax.swing.*;


/**
 * Player class describes the characteristics of a player
 * in our board game and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Player implements Serializable {
    private int wallet;
    private int numOfPaydays;
    private int bills;
    private int debts;
    private int playerNumber;
    private ArrayList<DealCard> cardsOwned = new ArrayList<DealCard>();
    private ArrayList<MailCard> cardsToPay = new ArrayList<MailCard>();
    public Dice dice;
    private PlayerPosition position;

    /**Constructor.
     *
     *  <b>precondition</b>: playerNumber should not be null <br />
     * <b>Postcondition</b>Creates a new Player with
     * 'playerNumber' playerNumber, with 3500 euros in his wallet,
     * position at the begining of the board and with zero bills
     * and debts.
     *
     * @param playerNumber
     */
    public Player(int playerNumber){
        dice = new Dice();
        this.playerNumber = playerNumber;
        this.wallet = 3500;
        this.debts = 0;
        this.bills = 0;
        numOfPaydays = 0;
        position = new PlayerPosition(0,"Start");
    }

    /**
     * <b>accessor(selector)</b>:Returns the cardsToPay of player <br />
     *
     * <p><b>Postcondition:</b> returns the cardsToPay of player </p>
     *
     * @return the cardsToPay of player
     */
    public ArrayList<MailCard> getCardsToPay(){return cardsToPay;}

    /**
     * <b>accessor(selector)</b>:Returns the numOfPaydays of player <br />
     *
     * <p><b>Postcondition:</b> returns the numOfPaydays of player </p>
     *
     * @return the numOfPaydays of player
     */
    public int getNumOfPaydays() {
        return numOfPaydays;
    }

    /**
     * <b>transformer</b>: Increments the numOfPaydays of player<br />
     *
     * <b>postcondition</b>: numOfPaydays is incremented.
     */
    public void updateNumofPaydays() {
        numOfPaydays++;
    }

    /**
     * <b>accessor(selector)</b>:Returns the playerNumber of player <br />
     *
     * <p><b>Postcondition:</b> returns the playerNumber of player </p>
     *
     * @return the playerNumber of player
     */
    public int getPlayerNumber(){return playerNumber;}

    /**
     * <b>transformer(mutative)</b>: sets the boardNumber and day of player <br />
     * <p><b>Postcondition:</b> the boardNumber and day of player have been set</p>
     *
     * @param boardNumber
     * @param day
     */
    public void setPosition(int boardNumber, String day) {
        this.position.setBoardNumber(boardNumber);
        this.position.setDay(day);
    }

    /**
     * <b>accessor(selector)</b>:Returns the boardNumber of player <br />
     *
     * <p><b>Postcondition:</b> returns the boardNumber of player </p>
     *
     * @return the boardNumber of player
     */
    public int getPosition(){return position.getBoardNumber();}

    /**
     * <b>accessor(selector)</b>:Returns the day of player <br />
     *
     * <p><b>Postcondition:</b> returns the day of player </p>
     *
     * @return the day of player
     */
    public String getposition(){return position.getDay();}

    /**
     * <b>accessor(selector)</b>:Returns the wallet of player <br />
     *
     * <p><b>Postcondition:</b> returns the wallet of player </p>
     *
     * @return the wallet of player
     */
    public int getWallet(){return this.wallet;}

    /**
     * <b>transformer(mutative)</b>: sets the wallet of player <br />
     *
     * <p><b>Postcondition:</b> the wallet of player has been set</p>
     *
     * @param wallet
     */
    public void setWallet(int wallet){this.wallet=wallet;}

    /**
     * <b>accessor(selector)</b>:Returns the debts of player <br />
     *
     * <p><b>Postcondition:</b> returns the debts of player </p>
     *
     * @return the debts of player
     */
    public int getDebts(){return this.debts;}

    /**
     * <b>transformer(mutative)</b>: sets the debts of player <br />
     *
     * <p><b>Postcondition:</b> the debts of player have been set</p>
     *
     * @param debts
     */
    public void setDebts(int debts){this.debts=debts;}

    /**
     * <b>accessor(selector)</b>:Returns the cardsOwned field of player <br />
     *
     * <p><b>Postcondition:</b> Returns the cardsOwned field of player </p>
     *
     * @return the cardsOwned field of player
     */
    public ArrayList<DealCard> getCardsOwned(){return cardsOwned;}

    /**
     * <b>transformer/Observer</b>: Checks if player's wallet is sufficient
     * to pay the 'amount' and if it's not gives him loan<br />
     *
     * <b>postcondition</b>: either player's wallet/loan stay the same or get updated
     *
     * @param amount
     */
    public void walletCheck(int amount){
        int loan = 0;
        while (this.getWallet()<amount){
            this.setDebts(this.getDebts()+1000);
            this.setWallet(this.getWallet()+1000);
            loan+=1000;
        }
        if (loan!=0) {
            JOptionPane.showMessageDialog(null, "You have got " + loan + " Euros Loan.",
                    "PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * <b>transformer</b>: Adds MailCard 'mc' to cardsToPay
     * field of player.<br />
     *
     * <b>postcondition</b>: cardsToPay contains 'mc'
     *
     * @param mc
     */
    public void addPayCard(MailCard mc){
        cardsToPay.add(mc);
    }

    /**
     * <b>transformer(mutative)</b>: sets the bills of player <br />
     *
     * <p><b>Postcondition:</b> the bills of player have been set</p>
     *
     * @param bills
     */
    public void setBills(int bills) {this.bills = bills;}

    /**
     * <b>accessor(selector)</b>:Returns the bills field of player <br />
     *
     * <p><b>Postcondition:</b> Returns the bills field of player </p>
     *
     * @return the bills field of player
     */
    public int getBills(){return this.bills;}

    /**
     * <b>transformer</b>: Removes the last Card from cardsToPay of player and returns it.
     * If cardsToPay is empty returns null.<br />
     *
     * <p><b>Postcondition:</b> last Card from cardsToPay of player is removed and returned</p>
     *
     * @return the last Card from cardsToPay of player or null if cardsToPay is empty
     */
    public Card popPayCard(){
        if ( this.cardsToPay.isEmpty() ){
            return null;
        }
        Card ret = cardsToPay.get(cardsToPay.size()-1);
        cardsToPay.remove(cardsToPay.size()-1);
        return ret;
    }

    /**
     * <b>transformer</b>: Adds DealCard 'dc' to cardsOwned
     * field of player.<br />
     *
     * <b>postcondition</b>: cardsToPay contains 'dc'
     *
     * @param dc
     */
    public void addDealCard(DealCard dc){
        cardsOwned.add(dc);
    }

    /**
     * <b>transformer</b>: Removes the last Card from cardsOwned of player and returns it.
     * If cardsOwned is empty returns null.<br />
     *
     * <p><b>Postcondition:</b> last Card from cardsOwned of player is removed and returned</p>
     *
     * @return the last Card from cardsOwned of player or null if cardsOwned is empty
     */
    public Card popDealCard(int index){
        if (cardsOwned.isEmpty() ) return null;
        Card ret = cardsOwned.get(index);
        cardsOwned.remove(index);
        return ret;
    }
}
