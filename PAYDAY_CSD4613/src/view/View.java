package view;

import model.Board.Board;
import model.Player.Player;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * View class describes the characteristics of the GUI
 * of our board game and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class View extends JFrame implements ActionListener, Serializable {
    JLayeredPane layeredPane;
    JTextArea infobox;
    JLabel background;
    JLabel player1box;
    JLabel player2box;
    JButton rollDice1, rollDice2;
    JButton mydealCards1, mydealCards2;
    JButton getLoan1, getLoan2;
    JButton endTurn1, endTurn2;
    JButton move1, move2;
    JButton savedata;
    JLabel dice1, dice2;
    JButton mailCard,dealCard;
    JLabel banner;
    JLabel jackpot;
    JLabel jackpotText;
    JLabel pawn1,pawn2;
    public int action;
    private Board b;

    /**Constructor.
     *
     * <b>precondition</b>: board, p1 and p2 should not be null. <br />
     *
     * <b>Postcondition</b>Creates a new frame with all the
     * components that our board game needs in order to play
     *
     * @param board
     * @param p1
     * @param p2
     */
    public View(Board board,Player p1, Player p2) {
        b = board;
        layeredPane = new JLayeredPane();
        dice1 = new JLabel();
        dice1.setBounds(1160,200,80,80);
        layeredPane.add(dice1,JLayeredPane.DRAG_LAYER);
        dice2 = new JLabel();
        dice2.setBounds(1160,760,80,80);
        layeredPane.add(dice2,JLayeredPane.DRAG_LAYER);
        action=0;
        printphotos();
        printinfobox();
        printCardButtons();
        printPlayerBoxes(board,p1,p2);
        printTiles(board);
        //BASIC
        this.add(layeredPane);
        layeredPane.setBounds(0,0,1400,1000);
        this.setResizable(false);
        this.setSize(new Dimension(1400, 1000) );
        this.setTitle("PAYDAY BOARD GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    /**
     * <b>transformer</b>: updates the status of javkpot and boxes of the players<br />
     *
     * <b>precondition</b>: board, p1 and p2 should not be null. <br />
     *
     * <b>postcondition</b>: jackpot and player boxes should be updated.
     *
     * @param b
     * @param p1
     * @param p2
     */
    public void updateinfo(Board b, Player p1, Player p2, String info) {
        //JACKPOT TEXT
        jackpotText.setText("Jackpot: " + b.getJackpot() + " Euros");
        //PLAYER 1 TEXT
        player1box.setText("<html><B>Player 1</B>" + "<br>Money: " + p1.getWallet() + " Euros<br>Loan: " +
                p1.getDebts() + " Euros<br>Bills: " + p1.getBills() + " Euros<br>" + "</html>");
        //PLAYER 2 TEXT
        player2box.setText("<html><B>Player 2</B>" + "<br>Money: " + p2.getWallet() + " Euros<br>Loan: " +
                p2.getDebts() + " Euros<br>Bills: " + p2.getBills() + " Euros<br>" + "</html>");
        //INFOBOX
        infobox.setText(info);
        repaint();
    }

    /**
     * <b>transformer</b>: Prints the info box of our window's game<br />
     *
     * <b>postcondition</b>: info box is shown on the screen.
     */
    public void printinfobox(){
        infobox = new JTextArea("Info Box",5,10);
        infobox.setFont(infobox.getFont().deriveFont(16f));
        infobox.setBounds(1000,380,350,100);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        infobox.setBorder(border);
        infobox.setEditable(false);
        add(infobox);
    }

    /**
     * <b>transformer</b>: Prints the images of window's game
     * (background, banner, jackpot logo)<br />
     *
     * <b>postcondition</b>: images of window's game are shown on the screen.
     */
    public void printphotos(){
        //BACKGROUND
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/background.png"));
        background = new JLabel(img);
        background.setBounds(0,0,1400,1000);
        setContentPane(background);
        //BANNER
        img = new ImageIcon(this.getClass().getResource("/resources/banner.png"));
        banner = new JLabel(img);
        banner.setBounds(0,0,1000,200);
        layeredPane.add(banner, JLayeredPane.DEFAULT_LAYER);
        //JACKOT
        img = new ImageIcon(this.getClass().getResource("/resources/jackpot.png"));
        jackpot = new JLabel(img);
        jackpot.setBounds(650,810,250,100);
        layeredPane.add(jackpot);
    }

    /**
     * <b>transformer</b>: Prints the MailCard and DealCard buttons on screen.<br />
     *
     * <b>postcondition</b>: MailCard/DealCard buttons are shown on the screen.
     */
    public void printCardButtons(){
        //MAILCARD BUTTON
        ImageIcon cardbutton = new ImageIcon(this.getClass().getResource("/resources/mailcard.png"));
        mailCard = new JButton();
        mailCard.setIcon(cardbutton);
        mailCard.setBounds(1000,490,150,80);
        mailCard.addActionListener(this);
        add(mailCard);
        //DEALCARDBUTTON
        cardbutton = new ImageIcon(this.getClass().getResource("/resources/dealcard.png"));
        dealCard = new JButton();
        dealCard.setIcon(cardbutton);
        dealCard.setBounds(1200,490,150,80);
        dealCard.addActionListener(this);
        add(dealCard);
    }

    /**
     * <b>transformer</b>: Prints a dialog box where user
     * decides if he wants to start a new game or load an
     * existing one.Then, returns his selection.<br />
     *
     * <b>postcondition</b>: Selection dialog appears on user's screen.
     *
     * @return the selection of user.
     */
    static public int selectDialog(){
        Object[] options = {"NEW GAME","LOAD SAVEDATA"};
        Image image = new ImageIcon(View.class.getResource("/resources/banner.png")).getImage();
        image = image.getScaledInstance(550, 300, java.awt.Image.SCALE_SMOOTH);
        JOptionPane jp = new JOptionPane();
        int n = jp.showOptionDialog(null,
                "",
                "PAYDAY: Message",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
        while (n ==-1){
            n = jp.showOptionDialog(null,
                    "",
                    "PAYDAY: Message",
                    JOptionPane.OK_OPTION,
                    0,
                    new ImageIcon(image),
                    options,
                    options[0]);
        }
        return n;
    }

    /**
     * <b>transformer</b>: Prints a dialog box where user decides
     * the duration of game(in months).<br />
     *
     * <b>postcondition</b>: Dialog appears on user's screen.
     */
    static public int durationDialog(){
        int months;
        while (true) {
            try {
                months = Integer.parseInt(JOptionPane.showInputDialog(
                        "Select the duration of game (months between 1-3)", 1));
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                        "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (months>=1 && months<=3) break;
            else JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
                    "PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
        }
        return months;
    }

    /**
     * <b>transformer</b>: Prints the box of Player 1/2, with
     * all their buttons and informations.<br />
     *
     * <b>postcondition</b>: Player boxes are shown on the screen.
     */
    public void printPlayerBoxes(Board board, Player p1, Player p2){
        //PLAYER1 BOX
        player1box = new JLabel("<html><B>Player 1</B>"+"<br>Money: "+p1.getWallet()+" Euros<br>Loan: "+
                p1.getDebts()+" Euros<br>Bills: "+p1.getBills()+" Euros<br>"+"</html>");
        player1box.setFont(new Font("testin", Font.PLAIN, 18));
        player1box.setVerticalAlignment(JLabel.TOP);
        player1box.setOpaque(true);
        player1box.setBounds(1000,20,350,350);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        player1box.setBorder(border);
        layeredPane.add(player1box, JLayeredPane.DEFAULT_LAYER);
        //ADD ITS BUTTONS
        //ROLLDICE
        rollDice1 = new JButton();
        rollDice1.setText("Roll Dice");
        rollDice1.setBounds(1020,200,100,30);
        rollDice1.addActionListener(this);
        add(rollDice1);
        //MYDEALCARDS
        mydealCards1 = new JButton();
        mydealCards1.setText("My Deal Cards");
        mydealCards1.setBounds(1020,250,100,30);
        mydealCards1.addActionListener(this);
        add(mydealCards1);
        //GETLOAN
        getLoan1 = new JButton();
        getLoan1.setText("Get Loan");
        getLoan1.setBounds(1020,300,100,30);
        getLoan1.addActionListener(this);
        add(getLoan1);
        //ENDTURN
        endTurn1 = new JButton();
        endTurn1.setText("End Turn");
        endTurn1.setBounds(1150,300,100,30);
        endTurn1.addActionListener(this);
        add(endTurn1);
        //MOVE
        move1 = new JButton();
        move1.setText("MOVE!");
        move1.setBounds(1250,100,80,80);
        move1.addActionListener(this);
        add(move1);
        //--------------
        //PLAYER2 BOX
        player2box = new JLabel("<html><B>Player 2</B>"+"<br>Money: "+p2.getWallet()+" Euros<br>Loan: "+
                p2.getDebts()+" Euros<br>Bills: "+p2.getBills()+" Euros<br>"+"</html>");
        player2box.setFont(new Font("testin", Font.PLAIN, 18));
        player2box.setVerticalAlignment(JLabel.TOP);
        player2box.setOpaque(true);
        player2box.setBounds(1000,580,350,350);
        border = BorderFactory.createLineBorder(Color.RED, 5);
        player2box.setBorder(border);
        layeredPane.add(player2box, JLayeredPane.DEFAULT_LAYER);
        //ADD ITS BUTTONS
        //ROLLDICE
        rollDice2 = new JButton();
        rollDice2.setText("Roll Dice");
        rollDice2.setBounds(1020,760,100,30);
        rollDice2.addActionListener(this);
        add(rollDice2);
        //MYDEALCARDS
        mydealCards2 = new JButton();
        mydealCards2.setText("My Deal Cards");
        mydealCards2.setBounds(1020,810,100,30);
        mydealCards2.addActionListener(this);
        add(mydealCards2);
        //GETLOAN
        getLoan2 = new JButton();
        getLoan2.setText("Get Loan");
        getLoan2.setBounds(1020,860,100,30);
        getLoan2.addActionListener(this);
        add(getLoan2);
        //ENDTURN
        endTurn2 = new JButton();
        endTurn2.setText("End Turn");
        endTurn2.setBounds(1150,860,100,30);
        endTurn2.addActionListener(this);
        add(endTurn2);
        //MOVE
        move2 = new JButton();
        move2.setText("MOVE!");
        move2.setBounds(1250,660,80,80);
        move2.addActionListener(this);
        add(move2);
        //text of jackpot
        jackpotText = new JLabel("Jackpot: " + board.getJackpot() + " Euros");
        jackpotText.setFont(new Font("testin", Font.BOLD, 30));
        jackpotText.setBounds(660, 880, 500, 100);
        jackpotText.setForeground(Color.WHITE);
        layeredPane.add(jackpotText);
        //savedata button
        savedata = new JButton();
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/savedata.png"));
        savedata.setIcon(img);
        savedata.setOpaque(false);
        savedata.setContentAreaFilled(false);
        savedata.setBorderPainted(false);
        savedata.setBounds(0,0,50,50);
        savedata.addActionListener(this);
        add(savedata);
    }

    /**
     * <b>transformer</b>: Opens a dialog box when
     * player p is on the day Thursday/Sunday for playing a minigame.<br />
     *
     * <b>precondition</b>: p should not be null <br />
     *
     * <b>postcondition</b>: Player should be able to select from
     * dialog box what he wants to do with the minigame.
     *
     * @param p
     */
    public void ThursdaySunday(Player p){
        int win = 0;
        if ( p.getposition().equals("Sunday") ){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/sunday.png"));
            String[] responses = {"Νίκη Μπαρτσελόνα","Ισοπαλία","Νίκη Ρεάλ","Δεν κάνω πρόβλεψη"};
            int answer = JOptionPane.showOptionDialog(null,
                    "Στοιχημάτησε 500 ευρώ για το ντέρμπι Barcelona-Real",
                    "Ποδοσφαιρικός αγώνας Κυριακής",
                    JOptionPane.DEFAULT_OPTION,
                    0, icon, responses, responses[0]);
            if (answer==3) return;
            int d = p.dice.roll();
            if ( (d==1 || d==2) && answer==0) win = 1;
            if ( (d==3 || d==4) && answer==1) win = 1;
            if ((d==5 || d==6) && answer==2) win = 1;

            if (win==1){
                String[] responses1 = {"Κέρδισες 1000 Ευρώ"};
                answer = JOptionPane.showOptionDialog(null,
                        "Σωστη πρόβλεψη!!!",
                        "Ποδοσφαιρικός αγώνας Κυριακής",
                        JOptionPane.DEFAULT_OPTION,
                        0, icon, responses1, responses1[0]);
                p.setWallet(p.getWallet()+1000);
            }else{
                String[] responses2 = {"Έχασες 500 Ευρώ"};
                answer = JOptionPane.showOptionDialog(null,
                        "Λάθος πρόβλεψη!!!",
                        "Ποδοσφαιρικός αγώνας Κυριακής",
                        JOptionPane.DEFAULT_OPTION,
                        0, icon, responses2, responses2[0]);
                p.setWallet(p.getWallet()-500);
            }
        }
        if ( p.getposition().equals("Thursday") ){
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/crypto.png"));
            String[] responses = {"Πόνταρε: Πτώση αξίας","Πόνταρε: Σταθεροποίηση αξίας","Άνοδος αξίας","Παράβλεψε το ποντάρισμα"};
            int answer = JOptionPane.showOptionDialog(null,
                    "Ποντάρισμα 300 ευρώ σε κρυπτονομίσματα",
                    "Crypto Thursday",
                    JOptionPane.DEFAULT_OPTION,
                    0, icon, responses, responses[0]);
            if (answer==3) return;
            int d = p.dice.roll();
            if( d==1 || d==2 ) win = 0;
            if( d==3 || d==4 ) win = 1;
            if( d==5 || d==6 ) win = 2;

            if (win==0){
                String[] responses1 = {"Έχασες 300 Ευρώ"};
                answer = JOptionPane.showOptionDialog(null,
                        "Η πρόβλεψη σου ήταν λάθος",
                        "Crypto Thursday",
                        JOptionPane.DEFAULT_OPTION,
                        0, icon, responses1, responses1[0]);
                p.setWallet(p.getWallet()-300);
            }
            else if(win==1){
                String[] responses1 = {"OK"};
                answer = JOptionPane.showOptionDialog(null,
                        "Η πρόβλεψη σου ήταν λάθος.Δεν κέρδισες/έχασες τίποτα.",
                        "Crypto Thursday",
                        JOptionPane.DEFAULT_OPTION,
                        0, icon, responses1, responses1[0]);
            }
            else{
                String[] responses1 = {"Κέρδισες 600 Ευρώ"};
                answer = JOptionPane.showOptionDialog(null,
                        "Η πρόβλεψη σου ήταν σωστή.Κέρδισες τα διπλάσια χρήματα",
                        "Crypto Thursday",
                        JOptionPane.DEFAULT_OPTION,
                        0, icon, responses1, responses1[0]);
                p.setWallet(p.getWallet()+600);
            }
        }
    }

    /**
     * <b>transformer</b>: Prints the dice of each player
     * and updates their value according to the new rolls
     * players do with them.<br />
     *
     * <b>postcondition</b>: Dice are shown on the screen
     * and they get updated as the game continues.
     */
    public void printDice(int playerNumber,int diceNumber){
        String name;
        if(diceNumber==1) name = "d1";
        else if(diceNumber==2) name = "d2";
        else if (diceNumber==3) name = "d3";
        else if (diceNumber==4) name = "d4";
        else if (diceNumber==5) name = "d5";
        else name = "d6";
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/"+name+".png"));
        if (playerNumber==1){
            dice1.setIcon(img);
        }else{
            dice2.setIcon(img);
        }
    }

    /**
     * <b>transformer</b>: Prints the pawn of each player
     * and updates their position on the board according to
     * the new position of ach player.<br />
     *
     * <b>postcondition</b>: pawns are shown on the screen
     * and they get updated as the game continues.
     */
    public void printPlayer(Player p,Board board,int day){
        if (day==0){
            String name;
            if (p.getPlayerNumber()==1) name = "pawn1.png";
            else name = "pawn2.png";
            ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/"+name));

            if (p.getPlayerNumber()==1){
                pawn1 = new JLabel(img);
                pawn1.setBounds(30,230,70,100);
                layeredPane.add(pawn1,JLayeredPane.DRAG_LAYER);
            }else{
                pawn2 = new JLabel(img);
                pawn2.setBounds(30,230,70,100);
                layeredPane.add(pawn2,JLayeredPane.DRAG_LAYER);
            }
        }
        else{
            if (p.getPlayerNumber()==1){
                pawn1.setBounds(board.getTileByDay(day).x+30,board.getTileByDay(day).y+25,70,100);
            }else{
                pawn2.setBounds(board.getTileByDay(day).x+30,board.getTileByDay(day).y+25,70,100);
            }
        }
    }

    /**
     * <b>transformer</b>: Opens a dialog box
     * presents the 'num' player as a winner.<br />
     *
     * <b>precondition</b>: num must be a value between 1-2. <br />
     *
     * <b>postcondition</b>: Dialog box is shown on the screen
     * presenting Player 'num' as a winner.
     *
     * @param num
     */
    public void printwinner(int num){
        Object[] options = {"OK"};
        Image image = new ImageIcon(this.getClass().getResource("/resources/win.png")).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane jp = new JOptionPane();
        int n = jp.showOptionDialog(null,
                "CONGRATULATIONS! PLAYER"+num+" IS THE WINNER!",
                "PAYDAY: Message",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
    }

    /**
     * <b>transformer</b>: Prints all the tiles that the
     * board has  in the window of our game.<br />
     *
     * <b>precondition</b>: board must not be null. <br />
     *
     * <b>postcondition</b>: 32 tiles are shown on the screen.
     *
     * @param board
     */
    public void printTiles(Board board){
        ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/start.png"));
        JLabel tile = new JLabel(img);
        int width = 140;
        int height = 150;
        tile.setBounds(0,205,width,height);
        layeredPane.add(tile);
        int x=width,y=205;
        for (int i=1; i<=31; i++) {
            String name = board.getTileByDay(i).getImage();
            img = new ImageIcon(this.getClass().getResource("/resources/"+name));
            tile = new JLabel(img);
            if (i==7||i==14||i==21||i==28){
                y+=height;
                x=0;
            }
            tile.setBounds(x,y,width,height);
            board.getTileByDay(i).x=x;
            board.getTileByDay(i).y=y;
            x+=width;
            layeredPane.add(tile);
        }
        img = new ImageIcon(this.getClass().getResource("/resources/daybanner.png"));
        JLabel days = new JLabel(img);
        days.setBounds(0,280,980,750);
        layeredPane.add(days,JLayeredPane.DRAG_LAYER);
    }

    /**
     * <b>transformer</b>:Puts the corresoponding value
     * to action field of our class based on the button
     * that players pressed.<br />
     *
     * <b>postcondition</b>: action field must change after
     * a button is pressed..
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        Object obj = ae.getSource();
        if (obj==rollDice1) action = 15;
        if (obj==rollDice2) action = 16;
        if (obj==move1) action = 17;
        if (obj==move2) action = 18;
        if (obj==getLoan1) action = 19;
        if (obj==getLoan2) action = 20;
        if (obj==endTurn1){ action = 21;}
        if (obj==endTurn2) action = 22;
        if (obj==mydealCards1) action = 23;
        if (obj==mydealCards2) action =24;
        if (obj==dealCard) action = 54;
        if (obj==mailCard) action = 55;
        if (obj==mydealCards1) action = 56;
        if (obj==mydealCards2) action = 57;
        if (obj==savedata) action = 66;
    }
}