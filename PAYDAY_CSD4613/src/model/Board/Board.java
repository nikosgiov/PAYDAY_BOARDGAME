package model.Board;

import model.Card.*;
import model.Position.*;
import model.Tile.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Random;

/**
 * Board class describes the characteristics of a board
 * and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Board implements Serializable {

    private int months;
    private int jackpot;
    Vector<Tile> tiles = new Vector<Tile>();
    Vector<Card> mailCards = new Vector<Card>();
    Vector<Card> dealCards = new Vector<Card>();

    /**
     * <b>transformer</b>: This method makes the action that Bill card has to do,having
     * access to players and board<br />
     *
     *  <b>precondition</b>:path and type must exist and obviously not be null <br />
     *
     * <b>postcondition</b>: Mailcards or Dealcards should be imported to mailcards/dealcards
     * field
     *
     * @param path path of the cards you want to read
     * @param type type of card you want to read
     */
    public void readFile(String path, String type) {
        BufferedReader br = null;
        String sCurrentLine;
        try {
            InputStream inDict = Board.class.getResourceAsStream(path);
            br = new BufferedReader( new InputStreamReader(inDict,"UTF-8") );
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        int count = 0;
        int splitCount = 0;
        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    String[][] tmp = new String[1][];
                    tmp[0] = sCurrentLine.split(",");
                    if ( tmp[0][1].equals("Αdvertisement") ){
                        mailCards.add( new Advertisement(tmp[0][5], tmp[0][2], Integer.parseInt(tmp[0][4])) );
                    }else if ( tmp[0][1].equals("Bill") ){
                        mailCards.add( new Bill(tmp[0][5],tmp[0][2],Integer.parseInt(tmp[0][4])) );
                    }else if ( tmp[0][1].equals("Charity") ){
                        mailCards.add( new Charity(tmp[0][5],tmp[0][2],Integer.parseInt(tmp[0][4])) );
                    }else if ( tmp[0][1].equals("PayTheNeighbor") ){
                        mailCards.add( new PayTheNeighbor(tmp[0][5],tmp[0][2],Integer.parseInt(tmp[0][4])) );
                    }else if ( tmp[0][1].equals("MadMoney") ){
                        mailCards.add( new GetFromNeighbor(tmp[0][5],tmp[0][2],Integer.parseInt(tmp[0][4])) );
                    }else if ( tmp[0][1].equals("MoveToDealBuyer") ){
                        mailCards.add( new MoveToDealBuyer(tmp[0][5],tmp[0][2],this) );
                    }

                } else {
                    String[][] tmp = new String[1][];
                    tmp[0] = sCurrentLine.split(",");
                    dealCards.add( new DealCard(tmp[0][5],tmp[0][2],Integer.parseInt(tmp[0][3]),Integer.parseInt(tmp[0][4])) );
                }
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**Constructor.
     *
     *  <b>precondition</b>: months should not be null <br />
     *
     * <b>Postcondition</b>Creates a new Board with
     * 'imonths' months and initializes the whole board
     * by adding tiles cards and jackpot.
     *
     * @param months
     */
    //resources/dealcards.csv
    public Board(int months){
        readFile("/resources/dealcards.csv", "Deal");
        readFile("/resources/mailcards.csv", "Mail");
        init_board();
        this.months = months;
    }

    /**
     * <b>transformer</b>: initializes jacpot and tiles of our board<br />
     *
     * <b>postcondition</b>: jackpot field should be zero and tiles field
     * should not be empty.
     */
    public void init_board(){

        tiles_init();
        this.jackpot = 0;
    }

    /**
     * <b>accessor(selector)</b>:Returns the months of board <br />
     *
     * <p><b>Postcondition:</b> returns the months of board </p>
     *
     * @return the months of board
     */
    public int getMonths(){return this.months;}


    /**
     * <b>Observer:</b> Returns the day of week that corresponds to the given day of month.
     *
     *  <b>precondition</b>: dayOfMonths should be a value between 1-31 <br />
     *
     * <b>Postcondition:</b> Returns the day of week that corresponds to the given day of month.
     *
     * @param dayOfMonth
     *
     * @return the day of week that corresponds to the given day of month.
     */
    public static String dayofWeek(int dayOfMonth){
        if(dayOfMonth==1||dayOfMonth==8||dayOfMonth==15||dayOfMonth==22||dayOfMonth==29){
            return "Monday";
        }
        if(dayOfMonth==2||dayOfMonth==9||dayOfMonth==16||dayOfMonth==23||dayOfMonth==30){
            return "Tuesday";
        }
        if(dayOfMonth==3||dayOfMonth==10||dayOfMonth==17||dayOfMonth==24||dayOfMonth==31){
            return "Wednesday";
        }
        if(dayOfMonth==4||dayOfMonth==11||dayOfMonth==18||dayOfMonth==25){
            return "Thursday";
        }
        if(dayOfMonth==5||dayOfMonth==12||dayOfMonth==19||dayOfMonth==26){
            return "Friday";
        }
        if(dayOfMonth==6||dayOfMonth==13||dayOfMonth==20||dayOfMonth==27){
            return "Saturday";
        }
        if(dayOfMonth==7||dayOfMonth==14||dayOfMonth==21||dayOfMonth==28){
            return "Sunday";
        }
        return null;
    }

    /**
     * <b>Transformer:</b> Initializes the tiles of our board randomly.
     * <b>Postcondition:</b> The tiles field is not empty.
     */
    private void tiles_init(){
        ArrayList<Integer> availableDays = new ArrayList<Integer>();
        for(int i=1; i<31; i++){    //numbers from 1 to 30
            availableDays.add(i);
        }
        for (int i=0; i<4; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new Mail1Position( c,dayofWeek(c) );
            availableDays.remove(index);
            tiles.add(new Tile("mail1.png",test) );
        }
        for (int i=0; i<4; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new Mail2Position( c,dayofWeek(c) );
            availableDays.remove(index);
            tiles.add(new Tile("mail2.png",test) );
        }
        for (int i=0; i<5; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new DealPosition(c,dayofWeek(c) );
            availableDays.remove(index);
            tiles.add(new Tile("deal.png",test) );
        }
        for (int i=0; i<2; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new SweeptakesPosition( c,dayofWeek(c),0 );
            availableDays.remove(index);
            tiles.add(new Tile("sweeptakes.png",test) );
        }
        for (int i=0; i<3; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new LoterryPosition(c,dayofWeek(c),1000);
            availableDays.remove(index);
            tiles.add(new Tile("lottery.png",test) );
        }
        for (int i=0; i<2; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new RadioContestPosition(c,dayofWeek(c),0 );
            availableDays.remove(index);
            tiles.add(new Tile("radio.png",test) );
        }
        for (int i=0; i<6; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new BuyerPosition(c,dayofWeek(c) );
            availableDays.remove(index);
            tiles.add(new Tile("buyer.png",test) );
        }
        for (int i=0; i<2; i++){
            int index = new Random().nextInt(availableDays.size() );
            int c = availableDays.get(index);
            Position test = new CasinoPosition(c,dayofWeek(c),500);
            availableDays.remove(index);
            tiles.add(new Tile("casino.png",test) );
        }
        for (int i=0; i<2; i++){
            int index = new Random().nextInt(availableDays.size());
            int c = availableDays.get(index);
            Position test = new YardSalePosition(c,dayofWeek(c),0 );
            availableDays.remove(index);
            tiles.add(new Tile("yardsale.png",test) );
        }
        Position test = new PayDayPosition(3500);
        tiles.add( new Tile("payday.png",test) );
    }

    /**
     * <b>transformer(mutative)</b>: sets the jackpot of board <br />
     * <p><b>Postcondition:</b> the jackpot has been set</p>
     *
     * @param jackpot
     */
    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    /**
     * <b>accessor(selector)</b>:Returns the day-th tile <br />
     *
     *  <b>precondition</b>: day should be a value between 1-31 <br />
     *
     * <p><b>Postcondition:</b> Returns the day-th tile </p>
     *
     * @param day
     *
     * @return the day-th tile
     */
    public Tile getTileByDay(int day){
        for (int i=0; i<tiles.size(); i++){
            if (tiles.elementAt(i).getBoardNumber()==day) return  tiles.elementAt(i);
        }
        return null;
    }

    /**
     * <b>accessor(selector)</b>:Returns the jackpot of board <br />
     *
     * <p><b>Postcondition:</b> Returns the jackpot of board </p>
     *
     * @return the jackpot of board
     */
    public int getJackpot() {
        return jackpot;
    }

    /**
     * <b>Observer/Transformer:</b> Selects a random card, removes it and
     * returns it.If choice is true this happens for a DealCard.Otherwise for
     * a MailCard.
     * <b>Postcondition:</b> A card has been removed from mailcards/dealcards and returned.
     *
     * @param choice
     *
     * @return a random MailCard/DealCard
     */
    public Card drawOneCard(boolean choice){
        if (choice){
            int index = new Random().nextInt( dealCards.size() );
            Card a = dealCards.elementAt(index);
            dealCards.remove(index);
            return a;
        }
        else{
            int index = new Random().nextInt( mailCards.size() );
            Card a = mailCards.elementAt(index);
            mailCards.remove(index);
            return a;
        }
    }

}
