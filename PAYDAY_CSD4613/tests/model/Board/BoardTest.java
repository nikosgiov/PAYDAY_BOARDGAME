package model.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Vector;

import model.Card.Bill;
import model.Card.Card;
import model.Card.MoveToDealBuyer;
import model.Tile.Tile;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void testConstructor() {
        Board actualBoard = new Board(1);
        actualBoard.setJackpot(1);
        assertEquals(1, actualBoard.getJackpot());
        assertEquals(1, actualBoard.getMonths());
        assertEquals(20, actualBoard.dealCards.size());
        assertEquals(40, actualBoard.mailCards.size());
        assertEquals(31, actualBoard.tiles.size());
    }

    @Test
    void testConstructor2() {
        Board actualBoard = new Board(1);
        assertEquals(0, actualBoard.getJackpot());
        Vector<Tile> tileList = actualBoard.tiles;
        assertEquals(31, tileList.size());
        Vector<Card> cardList = actualBoard.mailCards;
        assertEquals(40, cardList.size());
        assertEquals(1, actualBoard.getMonths());
        Vector<Card> cardList1 = actualBoard.dealCards;
        assertEquals(20, cardList1.size());
        Card getResult = cardList.get(38);
        assertEquals(0, ((MoveToDealBuyer) getResult).getAmount());
        Tile getResult1 = tileList.get(1);
        assertTrue(getResult1.getPosition() instanceof model.Position.Mail1Position);
        assertEquals("mail1.png", getResult1.getImage());
        Card getResult2 = cardList.get(1);
        assertEquals("Ξ Ξ»Ξ·Ο�Ο‰ΞΌΞ® 50 Ξ•Ο…Ο�Ο� Ξ³ΞΉΞ± Ο„ΞΏΞ½ Ξ›ΞΏΞ³Ξ±Ο�ΞΉΞ±ΟƒΞΌΞΏ Ξ¤Ξ·Ξ»ΞµΟ†Ο�Ξ½ΞΏΟ…",
                getResult2.getMessage());
        assertEquals("cosmote.jpg", getResult2.getImage());
        assertEquals(50, ((Bill) getResult2).getAmount());
        Card getResult3 = cardList.get(0);
        assertEquals("Ξ Ξ»Ξ·Ο�Ο‰ΞΌΞ® 500 Ξ•Ο…Ο�Ο� Ξ³ΞΉΞ± Ο„Ξ·Ξ½ Ο€Ο�Ο�Ο„Ξ· Ξ΄Ο�ΟƒΞ· Ο„ΞΏΟ… Ξ•Ξ�Ξ¦Ξ™Ξ‘",
                getResult3.getMessage());
        assertEquals("enfia.jpg", getResult3.getImage());
        assertEquals(500, ((Bill) getResult3).getAmount());
        Tile getResult4 = tileList.get(0);
        assertTrue(getResult4.getPosition() instanceof model.Position.Mail1Position);
        assertEquals("mail1.png", getResult4.getImage());
        Card getResult5 = cardList1.get(17);
        assertEquals("Agora Eishthriou gia ton teliko tou NBA", getResult5.getMessage());
        assertEquals("nba.jpg", getResult5.getImage());
        Card getResult6 = cardList1.get(2);
        assertEquals("Agora Ethereum", getResult6.getMessage());
        assertEquals("ethereum.png", getResult6.getImage());
        Tile getResult7 = tileList.get(29);
        assertTrue(getResult7.getPosition() instanceof model.Position.YardSalePosition);
        assertEquals("yardsale.png", getResult7.getImage());
        Tile getResult8 = tileList.get(28);
        assertTrue(getResult8.getPosition() instanceof model.Position.YardSalePosition);
        assertEquals("yardsale.png", getResult8.getImage());
        assertEquals("db.jpg", getResult.getImage());
        Tile getResult9 = tileList.get(30);
        assertEquals("payday.png", getResult9.getImage());
        assertEquals("Bill", getResult3.getType());
        Card getResult10 = cardList.get(39);
        assertEquals("Ξ Ξ·Ξ³Ξ±Ξ―Ξ½ΞµΟ„Ξµ ΟƒΟ„Ξ·Ξ½ ΞµΟ€Ο�ΞΌΞµΞ½Ξ· ΞΈΞ­ΟƒΞ· Ξ£Ο…ΞΌΟ†Ο‰Ξ½Ξ―Ξ±Ο‚/Ξ‘Ξ³ΞΏΟ�Ξ±ΟƒΟ„Ξ®",
                getResult10.getMessage());
        assertEquals("db.jpg", getResult10.getImage());
        assertEquals("Deal", getResult5.getType());
        Card getResult11 = cardList1.get(18);
        assertEquals("Agora Spanias Gatas", getResult11.getMessage());
        assertEquals("spania.jpeg", getResult11.getImage());
        Card getResult12 = cardList1.get(19);
        assertEquals("Agora Zaxaroplasteiou", getResult12.getMessage());
        assertEquals("zaxar.jpg", getResult12.getImage());
        assertEquals("Deal", getResult11.getType());
        assertEquals("Ξ Ξ·Ξ³Ξ±Ξ―Ξ½ΞµΟ„Ξµ ΟƒΟ„Ξ·Ξ½ ΞµΟ€Ο�ΞΌΞµΞ½Ξ· ΞΈΞ­ΟƒΞ· Ξ£Ο…ΞΌΟ†Ο‰Ξ½Ξ―Ξ±Ο‚/Ξ‘Ξ³ΞΏΟ�Ξ±ΟƒΟ„Ξ®",
                getResult.getMessage());
        assertEquals(31, getResult9.getBoardNumber());
        assertEquals("Deal", getResult12.getType());
        assertEquals("Bill", getResult2.getType());
        assertEquals(0, ((MoveToDealBuyer) getResult10).getAmount());
        assertEquals("MoveToDealBuyer", getResult10.getType());
        assertEquals("MoveToDealBuyer", getResult.getType());
        assertEquals("Deal", getResult6.getType());
    }

    @Test
    void testInit_board() {
        Board board = new Board(1);
        board.init_board();
        assertEquals(0, board.getJackpot());
        Vector<Tile> tileList = board.tiles;
        assertEquals(62, tileList.size());
        Tile getResult = tileList.get(60);
        assertTrue(getResult.getPosition() instanceof model.Position.YardSalePosition);
        assertEquals("yardsale.png", getResult.getImage());
        Tile getResult1 = tileList.get(59);
        assertTrue(getResult1.getPosition() instanceof model.Position.YardSalePosition);
        assertEquals("yardsale.png", getResult1.getImage());
        Tile getResult2 = tileList.get(61);
        assertEquals("payday.png", getResult2.getImage());
        assertEquals(31, getResult2.getBoardNumber());
    }

    @Test
    void testDayofWeek() {
        assertEquals("Monday", Board.dayofWeek(1));
        assertNull(Board.dayofWeek(0));
        assertEquals("Tuesday", Board.dayofWeek(2));
        assertEquals("Wednesday", Board.dayofWeek(3));
        assertEquals("Thursday", Board.dayofWeek(4));
        assertEquals("Friday", Board.dayofWeek(5));
        assertEquals("Saturday", Board.dayofWeek(6));
        assertEquals("Sunday", Board.dayofWeek(7));
        assertEquals("Monday", Board.dayofWeek(8));
        assertEquals("Tuesday", Board.dayofWeek(9));
        assertEquals("Wednesday", Board.dayofWeek(10));
        assertEquals("Thursday", Board.dayofWeek(11));
        assertEquals("Friday", Board.dayofWeek(12));
        assertEquals("Saturday", Board.dayofWeek(13));
        assertEquals("Sunday", Board.dayofWeek(14));
        assertEquals("Monday", Board.dayofWeek(15));
        assertEquals("Tuesday", Board.dayofWeek(Short.SIZE));
        assertEquals("Wednesday", Board.dayofWeek(17));
    }

    @Test
    void testGetTileByDay() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by getTileByDay(int)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        (new Board(1)).getTileByDay(1);
    }

    @Test
    void testGetTileByDay2() {
        assertNull((new Board(1)).getTileByDay(0));
    }

    @Test
    void testDrawOneCard() {
        Board board = new Board(1);
        board.drawOneCard(true);
        assertEquals(19, board.dealCards.size());
    }

    @Test
    void testDrawOneCard2() {
        Board board = new Board(1);
        board.drawOneCard(false);
        assertEquals(39, board.mailCards.size());
    }
}

