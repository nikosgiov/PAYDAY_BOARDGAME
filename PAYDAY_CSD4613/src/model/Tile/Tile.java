package model.Tile;

import model.Board.Board;
import model.Player.Player;
import model.Position.Position;

import java.io.Serializable;

/**
 * Tile class describes the characteristics of a tile
 * and provides modification methods.
 * @version 1.0
 * @author Nikolaos Giovanopoulos - CSD4613
 */
public class Tile implements Serializable {
    private String image;
    private Position position;
    public int x,y;

    /**Constructor.
     *
     *  <b>precondition</b>: image and position should not be null <br />
     * <b>Postcondition</b>Creates a new tile with
     * 'image' image and 'position' position.
     *
     * @param image
     * @param position
     */
    public Tile(String image, Position position){
            this.image = image;
            this.position = position;
    }

    /**
     * <b>transformer</b>: This method makes the action that a Tile has to do,having
     * access to players and board<br />
     *
     *  <b>precondition</b>:p1,p2,b should not be null <br />
     *
     * <b>postcondition</b>: either p1,p2 or b should have an updated value on their attrivute/es
     *
     * @param board The board of our game
     * @param p1 Player 1 of our game
     * @param p2 Player 2 of our game
     */
    public void action(Board board, Player p1, Player p2){
        position.performAction(board,p1,p2);
    }

    /**
     * <b>accessor(selector)</b>:Returns the position of the tile <br />
     *
     * <p><b>Postcondition:</b> Returns the position of the tile </p>
     *
     * @return the position of the tile
     */
    public Position getPosition(){
        return position;
    }

    /**
     * <b>accessor(selector)</b>:Returns the image of the tile <br />
     *
     * <p><b>Postcondition:</b> Returns the image of the tile </p>
     *
     * @return the image of the tile
     */
    public String getImage(){return image;}

    /**
     * <b>accessor(selector)</b>:Returns the boardNumber of the tile <br />
     *
     * <p><b>Postcondition:</b> Returns the boardNumber of the tile </p>
     *
     * @return the boardNumber of the tile
     */
    public int getBoardNumber(){return position.getBoardNumber();}

}
