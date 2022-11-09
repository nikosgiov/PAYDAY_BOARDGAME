package model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    void testGetBoardNumber() {
        assertEquals(10, (new BothPlayerDicePosition(10, "Day", 1)).getBoardNumber());
    }

    @Test
    void testGetDay() {
        assertEquals("Day", (new BothPlayerDicePosition(10, "Day", 1)).getDay());
    }

    @Test
    void testSetBoardNumber() {
        BothPlayerDicePosition bothPlayerDicePosition = new BothPlayerDicePosition(10, "Day", 1);
        bothPlayerDicePosition.setBoardNumber(10);
        assertEquals(10, bothPlayerDicePosition.getBoardNumber());
    }

    @Test
    void testSetDay() {
        BothPlayerDicePosition bothPlayerDicePosition = new BothPlayerDicePosition(10, "Day", 1);
        bothPlayerDicePosition.setDay("Day");
        assertEquals("Day", bothPlayerDicePosition.getDay());
    }
}

