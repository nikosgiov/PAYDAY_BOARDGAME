package Main;

import controller.Controller;
import view.View;
import java.io.*;
/**
 * Main class of our application.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Main implements Serializable{
    /**
     * <b>Main Method of our application.if user selects load savedata,
     * reads the "object.txt" file, makes a new instance of controller, calls loadstate in order to pass
     * the objects of game when user clicked "SAVE GAME" and calls gameflow
     * to start the game.Otherwise creates a new controller instance and calls game in such way
     * that initializes a new game.Then, again calls gameflow to start the game.</b>
     *
     * <b>precondition</b>"objects.txt" should exist.<br />
     *
     */
    public static void main(String[] args) {
        if (View.selectDialog()==1) {
            try {
                FileInputStream fileIn = new FileInputStream("objects.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Controller c = new Controller();
                c.loadstate(in.readObject(),in.readObject(),in.readObject(),in.readObject() );
                in.close();
                fileIn.close();
                c.start(true);
            } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        }
        else {
            Controller c = new Controller();
            c.start(false);
        }
    }
}
