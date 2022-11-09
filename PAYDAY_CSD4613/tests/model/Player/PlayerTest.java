package model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Card.Advertisement;
import model.Card.DealCard;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void testConstructor() {
        Player actualPlayer = new Player(10);
        actualPlayer.setBills(1);
        actualPlayer.setDebts(1);
        actualPlayer.setWallet(1);
        actualPlayer.updateNumofPaydays();
        assertEquals(1, actualPlayer.getBills());
        assertEquals(1, actualPlayer.getDebts());
        assertEquals(1, actualPlayer.getNumOfPaydays());
        assertEquals(10, actualPlayer.getPlayerNumber());
        assertEquals(1, actualPlayer.getWallet());
        assertEquals(0, actualPlayer.dice.getLastdice());
    }

    @Test
    void testConstructor2() {
        Player actualPlayer = new Player(10);
        assertEquals(0, actualPlayer.getBills());
        assertEquals("Start", actualPlayer.getposition());
        assertTrue(actualPlayer.getCardsOwned().isEmpty());
        assertEquals(0, actualPlayer.getDebts());
        assertEquals(0, actualPlayer.getPosition());
        assertEquals(10, actualPlayer.getPlayerNumber());
        assertEquals(0, actualPlayer.getNumOfPaydays());
        assertEquals(3500, actualPlayer.getWallet());
        assertTrue(actualPlayer.getCardsToPay().isEmpty());
    }

    @Test
    void testSetPosition() {
        Player player = new Player(10);
        player.setPosition(10, "Day");
        assertEquals("Day", player.getposition());
        assertEquals(10, player.getPosition());
    }

    @Test
    void testGetPosition() {
        assertEquals(0, (new Player(10)).getPosition());
    }

    @Test
    void testGetposition() {
        assertEquals("Start", (new Player(10)).getposition());
    }

    @Test
    void testWalletCheck() {
        Player player = new Player(10);
        player.walletCheck(10);
        assertEquals(0, player.getBills());
        assertEquals("Start", player.getposition());
        assertTrue(player.getCardsOwned().isEmpty());
        assertEquals(0, player.getDebts());
        assertEquals(10, player.getPlayerNumber());
        assertEquals(0, player.getNumOfPaydays());
        assertEquals(3500, player.getWallet());
        assertTrue(player.getCardsToPay().isEmpty());
    }

    @Test
    void testAddPayCard() {
        Player player = new Player(10);
        player.addPayCard(new Advertisement("Image", "Not all who wander are lost", 10));
        assertEquals(1, player.getCardsToPay().size());
    }

    @Test
    void testPopPayCard() {
        assertNull((new Player(10)).popPayCard());
    }

    @Test
    void testPopPayCard2() {
        Player player = new Player(10);
        Advertisement advertisement = new Advertisement("Image", "Not all who wander are lost", 10);

        player.addPayCard(advertisement);
        assertSame(advertisement, player.popPayCard());
        assertTrue(player.getCardsToPay().isEmpty());
    }

    @Test
    void testAddDealCard() {
        Player player = new Player(10);
        player.addDealCard(new DealCard("Image", "Not all who wander are lost", 1, 1));
        assertEquals(1, player.getCardsOwned().size());
    }

    @Test
    void testPopDealCard() {
        assertNull((new Player(10)).popDealCard(1));
    }

    @Test
    void testPopDealCard2() {
        Player player = new Player(10);
        player.addDealCard(new DealCard("Image", "Not all who wander are lost", 0, 0));
        DealCard dealCard = new DealCard("Image", "Not all who wander are lost", 0, 0);

        player.addDealCard(dealCard);
        assertSame(dealCard, player.popDealCard(1));
        assertEquals(1, player.getCardsOwned().size());
    }
}

