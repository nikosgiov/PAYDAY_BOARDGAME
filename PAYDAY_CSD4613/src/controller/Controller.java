package controller;

import model.Card.Card;
import model.Player.Player;
import view.View;
import model.Board.Board;
import view.ViewDealCards;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 * Controller class connects the model with the view
 * components of our program.Its job is to synchronize
 * everything when we run the application.
 *
 * @version 1.0
 *
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Controller{

	private Player p1,p2;
	private Board board;
	private View view;

	/**
	 * <b>transformer</b>: Sets p1,p2,board and view field
	 * with pre-existing objects(from saved game) and makes
	 * the window visible.<br />
	 *
	 * <b>precondition</b>p1,p2,board and view should not be null.<br />
	 *
	 * <b>postcondition</b>: p1,p2,board and view should have values
	 * 'p1','p2','board','view' respectively.Window should also be visible.
	 *
	 * @param p1
	 * @param p2
	 * @param board
	 * @param view
	 */
	public void loadstate(Object p1, Object p2, Object board, Object view){
		this.p1 = (Player)p1;
		this.p2 = (Player)p2;
		this.board = (Board)board;
		this.view = (View)view;
		this.view.setVisible(true);
	}

	/**
	 * <b>transformer</b>: Reads "order.txt" file from the directory
	 * where program is located, in order to determine the order
	 * of each player's turn.Then returns the number of player who played last
	 * when the state of game got saved<br />
	 *
	 * <b>precondition</b>"order.txt" should exist.<br />
	 *
	 * <b>postcondition</b>: The game should start.
	 * field
	 */
	public int loadturn(){
		try {
			File myObj = new File("order.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				return Integer.parseInt(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {}
		return 0;
	}

	/**
	 * <b>transformer</b>: Initializes the Controller Class with whatever it may need
	 * like instantiating board, view and players,deciding who plays first and giving
	 * him the turn and goes on.This method is called when user decides to start a new game.<br />
	 *
	 * <b>postcondition</b>: The game should start.
	 * field
	 *
	 * @return the number of player who played last.
	 */
	private int init(){
		p1 = new Player(1);
		p2 = new Player(2);

		board = new Board(View.durationDialog());
		view = new View(board,p1,p2);
		view.setVisible(true);
		int first = whoPlaysFirst();
		view.printPlayer(p1,board,0);
		view.printPlayer(p2,board,0);
		int last;
		JOptionPane.showMessageDialog(null,"Player "+
				first+" plays first!","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
		if (first==1) last = turn(p1);
		else last = turn(p2);
		return last;
	}

	/**
	 * <b>transformer</b>: Calls the appropriate methods based on if the game is new or
	 * loading a previous one.It keeps the order between each player turn and exits when
	 * the game ends.<br />
	 *
	 * <b>postcondition</b>: The game should finish at some point.
	 *
	 * @param save Parameter that it's true if user want to load a savedata for game and
	 *             false if wants to start a new game.
	 */
	public void start(boolean save){
		//--------------------------------
		int last;
		if (save) last = loadturn();
		else last = init();
		if (last==1){
			while( !GameFinished() ){
				if (p1.getPosition()==31 && p1.getNumOfPaydays()==board.getMonths()){
					turn(p2);
				}else if (p2.getPosition()==31 && p2.getNumOfPaydays()==board.getMonths()){
					turn(p1);
				}else{
					turn(p2);
					turn(p1);
				}
			}
		}
		else{
			while( !GameFinished() ){
				if (p1.getPosition()==31 && p1.getNumOfPaydays()==board.getMonths()){
					turn(p2);
				}else if (p2.getPosition()==31 && p2.getNumOfPaydays()==board.getMonths()){
					turn(p1);
				}else{
					turn(p1);
					turn(p2);
				}
			}
		}
		if (getScore(p1)>getScore(p2)){
			view.printwinner(p1.getPlayerNumber());
		}else if (getScore(p1)==getScore(p2)){
			JOptionPane.showMessageDialog(null, "END OF" +
					"GAME! DRAW - NO WINNER.","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
		}else{
			view.printwinner(p2.getPlayerNumber());
		}
		System.exit(0);
	}

	/**
	 * <b>Observer:</b> Returns true if the game has finished and false otherwise.
	 *
	 * <b>Postcondition:</b> Returns true if the game has finished and false otherwise.
	 *
	 * @return true if the game has finished and false otherwise.
	 */
	public boolean GameFinished(){
		if ( (board.getMonths()==p1.getNumOfPaydays()) && (board.getMonths()==p2.getNumOfPaydays()) ) {
			return true;
		}
		return false;
	}

	/**
	 * <b>Observer:</b> Returns the other player of the given one of them.
	 *
	 * <b>precondition</b>:p should not be null.<br />
	 *
	 * <b>Postcondition:</b> Returns the other player of the given one of them.
	 *
	 * @param p
	 *
	 * @return the other player of the given one of them.
	 */
	private Player getOtherPlayer(Player p){
		if (p.getPlayerNumber()==1) return p2;
		else return p1;
	}

	//**********************************************************************************
	/**
	 * <b>transformer</b>: Implements the turn of the given player, giving him the
	 * ability to do actions like rolling his dice etc.Once his turn finishes returns
	 * his boardNumber
	 *
	 * <b>precondition</b>:p should not be null.<br />
	 *
	 * <b>postcondition</b>: Returns the boardNumber of the player whose turn ended.
	 * field
	 *
	 * @param p
	 *
	 * @return the boardNumber of the player whose turn ended.
	 */
	public int turn(Player p){
		JOptionPane.showMessageDialog(null,"Player "+
				p.getPlayerNumber()+" it's your turn","PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
		int rolldice,getloan,move,mydealcards,endturn,savedata=66;
		if (p.getPlayerNumber()==1) {
			rolldice = 15;
			getloan = 19;
			move =17;
			endturn = 21;
			mydealcards = 56;
		}else{
			rolldice = 16;
			getloan = 20;
			move =18;
			endturn = 22;
			mydealcards = 57;
		}
		class turn implements Runnable {
			@Override
			public void run() {
				try {
					view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
							getPlayerNumber()+"\n-->Roll the dice");
					while (true) {
						Thread.sleep(1000);
						if (view.action == mydealcards){
							ViewDealCards tmp = new ViewDealCards(p);
							view.action = 0;
						}
						if (view.action == getloan) {
							giveLoan(p);
							view.action = 0;
						}
						if (view.action == rolldice) {
							view.action = 0;
							break;
						}
						if (view.action == savedata){
							view.action = 0;
							File f = new File("order.txt");
							try (FileWriter fw = new FileWriter(f)) {
								fw.write(getOtherPlayer(p).getPlayerNumber()+"");
							} catch (IOException e) {System.err.println("lathos sto txt");}
							try {
								FileOutputStream fileOut = new FileOutputStream("objects.txt");
								ObjectOutputStream out = new ObjectOutputStream(fileOut);
								out.writeObject(p1);
								out.writeObject(p2);
								out.writeObject(board);
								out.writeObject(view);
								out.close();
								fileOut.close();
								JOptionPane.showMessageDialog(null,"Game state successfully saved."
										,"PAYDAY: Message",JOptionPane.INFORMATION_MESSAGE);
							}catch (IOException e){e.printStackTrace(); }
							view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
									getPlayerNumber()+"\n-->Roll the dice");
						}
					}
					int value = p.dice.roll();
					if (value == 6 && board.getJackpot() != 0) {
						JOptionPane.showMessageDialog(null, "Player " +
										p.getPlayerNumber() + ": You won the jackpot money!!",
								"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
						p.setWallet(p.getWallet() + board.getJackpot());
						board.setJackpot(0);
					}
					view.printDice(p.getPlayerNumber(), value);
					JOptionPane.showMessageDialog(null, "Hit the MOVE! button",
							"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
					view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
							getPlayerNumber()+"\n-->Hit the MOVE! button");
					while (true) {
						Thread.sleep(1000);
						if (view.action == getloan) {
							giveLoan(p);
							view.action = 0;
						}
						if (view.action == move) {
							movePlayer(p, value);
							view.action = 0;
							break;
						}
						if (view.action == mydealcards){
							ViewDealCards tmp = new ViewDealCards(p);
							view.action = 0;
						}
					}
					if (board.getTileByDay(p.getPosition()).getImage().equals("deal.png")) {
						JOptionPane.showMessageDialog(null, "Draw a deal card",
								"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
						view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
								getPlayerNumber()+"\n-->Draw a deal card");
						while (true) {
							Thread.sleep(500);
							if (view.action == 54){
								view.action=0;
								board.drawOneCard(true).action(p, getOtherPlayer(p), board);
								break;
							}
						}
					} else if (board.getTileByDay(p.getPosition()).getImage().equals("mail1.png")) {
						JOptionPane.showMessageDialog(null, "Draw a mail card",
								"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
						view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
								getPlayerNumber()+"\n-->Draw a mail card");
						while (true) {
							Thread.sleep(500);
							if (view.action == 55){
								view.action = 0;
								Card a = board.drawOneCard(false);
								if (a.getType().equals("MoveToDealBuyer") ) {
									a.action(p, getOtherPlayer(p), board);
									view.printPlayer(p, board, p.getPosition());
									view.revalidate();
									board.getTileByDay(p.getPosition()).action(board, p, getOtherPlayer(p));
								}else a.action(p, getOtherPlayer(p), board);
								break;
							}
						}
					} else if (board.getTileByDay(p.getPosition()).getImage().equals("mail2.png")) {
						JOptionPane.showMessageDialog(null, "Draw 2 mail card",
								"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);

						for (int i = 0; i < 2; i++) {
							view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
									getPlayerNumber()+"\n-->Draw a mail card");
							while (true) {
								Thread.sleep(500);
								if (view.action == 55) {
									view.action = 0;
									Card a = board.drawOneCard(false);
									if ( a.getType().equals("MoveToDealBuyer") ) {
										a.action(p, getOtherPlayer(p), board);
										view.printPlayer(p, board, p.getPosition());
										view.revalidate();
										board.getTileByDay(p.getPosition()).action(board, p, getOtherPlayer(p));
									}else a.action(p, getOtherPlayer(p), board);
									break;
								}
							}
						}
					} else board.getTileByDay(p.getPosition()).action(board, p, getOtherPlayer(p));
					view.ThursdaySunday(p);
					view.updateinfo(board, p1, p2,"Info Box\nMonths left: "+monthsLeft()+"\nTurn: Player "+p.
							getPlayerNumber()+"\n-->Hit the End Turn");
					while (true) {
						Thread.sleep(1000);
						if (view.action == getloan) {
							giveLoan(p);
							view.action = 0;
						}
						if (view.action == mydealcards){
							ViewDealCards tmp = new ViewDealCards(p);
							view.action = 0;
						}
						if (view.action == endturn) {
							view.action = 0;
							break;
						}
					}
				} catch (InterruptedException e) {
				}
			}
		}
		Thread t1 = new Thread( new turn() );
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return p.getPlayerNumber();
	}

	/**
	 * <b>Observer:</b> Returns the months left to end the game.
	 *
	 * <b>Postcondition:</b> Returns the months left to end the game.
	 *
	 * @return the months left to end the game
	 */
	public int monthsLeft(){
		int left = board.getMonths();
		left -= Math.max(p1.getNumOfPaydays(), p2.getNumOfPaydays());
		return left;
	}

	/**
	 * <b>transformer</b>: This method gives loan to player p.<br />
	 *
	 *  <b>precondition</b>:p should not be null.<br />
	 *
	 * <b>postcondition</b>:Player p now has incremented his loan and his wallet
	 * field.
	 *
	 * @param p
	 */
	public void giveLoan(Player p){
		boolean exit = false;
		int inputk = 0;
		while (!exit) {
			inputk = Integer.parseInt(JOptionPane.showInputDialog(
					"Take the amount of loan that you need: ", 10000));
			if (inputk % 1000 == 0) exit = true;
			else JOptionPane.showMessageDialog(null, "Bad Input! Try again:",
					"PAYDAY: Message", JOptionPane.ERROR_MESSAGE);
		}
		p.setDebts(p.getDebts()+inputk);
		p.setWallet(p.getWallet()+inputk);
		JOptionPane.showMessageDialog(null, "You have got "+inputk+" Loan.",
				"PAYDAY: Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * <b>Observer:</b> Returns the score of player p.
	 *
	 *  <b>precondition</b>:p should not be null.<br />
	 *
	 * <b>postcondition:</b> Returns the score of player p.
	 *
	 * @param p
	 *
	 * @return the score of player p.
	 */
	public int getScore(Player p) {
		return (p.getWallet()-(p.getDebts()+p.getDebts()*10/100) );
	}

	/**
	 * <b>transformer</b>: This method moves player p on the board,
	 * according to his current position and the number of dice (diceNum).<br />
	 *
	 *  <b>precondition</b>:p should not be null and diceNum should be a value
	 *  in the range of 1-6.<br />
	 *
	 * <b>postcondition</b>:Player p's position should've be updated.
	 *
	 * @param p
	 * @param diceNum
	 */
	public void movePlayer(Player p,int diceNum){
		int index = p.getPosition()+diceNum;
		if (index<=31)
			p.setPosition(index,Board.dayofWeek(index) );
		else
			p.setPosition(31,Board.dayofWeek(31) );
		view.printPlayer( p,board,p.getPosition() );
	}

	/**
	 * <b>transformer</b>: This method decides randomly the number of the
	 * player who will play first and returns it.<br />
	 *
	 * <b>postcondition</b>:Returns the number of player who plays first.
	 * field.
	 *
	 * @return the number of player who plays first.
	 */
	public int whoPlaysFirst(){
		return (new Random().nextInt(2) + 1);
	}
}
