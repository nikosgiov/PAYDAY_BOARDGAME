package model.Tile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Board.Board;
import model.Player.Player;
import model.Position.BuyerPosition;
import model.Position.PayDayPosition;
import org.junit.jupiter.api.Test;

class TileTest {
    @Test
    void testConstructor() {
        PayDayPosition payDayPosition = new PayDayPosition(1);
        Tile actualTile = new Tile("Image", payDayPosition);

        assertEquals("Image", actualTile.getImage());
        assertSame(payDayPosition, actualTile.getPosition());
    }

    @Test
    void testAction() {
        Tile tile = new Tile("Image", new BuyerPosition(10, "Day"));
        Board board = new Board(1);
        Player player = new Player(10);
        Player player1 = new Player(10);
        tile.action(board, player, player1);
        assertEquals(0, board.getJackpot());
        assertEquals(1, board.getMonths());
        assertEquals(0, player.getBills());
        assertEquals("Start", player.getposition());
        assertTrue(player.getCardsOwned().isEmpty());
        assertEquals(0, player.getDebts());
        assertEquals(10, player.getPlayerNumber());
        assertEquals(0, player.getNumOfPaydays());
        assertEquals(3500, player.getWallet());
        assertTrue(player.getCardsToPay().isEmpty());
        assertEquals(0, player1.getBills());
        assertEquals("Start", player1.getposition());
        assertTrue(player1.getCardsOwned().isEmpty());
        assertEquals(0, player1.getDebts());
        assertEquals(10, player1.getPlayerNumber());
        assertEquals(0, player1.getNumOfPaydays());
        assertEquals(3500, player1.getWallet());
        assertTrue(player1.getCardsToPay().isEmpty());
        assertEquals(10, tile.getBoardNumber());
        assertEquals(0, tile.y);
        assertEquals(0, tile.x);
        assertEquals("Image", tile.getImage());
    }

    @Test
    void testGetBoardNumber() {
        assertEquals(31, (new Tile("Image", new PayDayPosition(1))).getBoardNumber());
    }
}

