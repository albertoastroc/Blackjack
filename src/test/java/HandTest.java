import org.example.model.Card;
import org.example.model.Hand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {

    private Hand playerHand;
    private Hand dealerHand;

    @Before
    public void setup() {

        playerHand = new Hand();
        dealerHand = new Hand();

    }

    @Test
    public void dealer_beats_player_with_blackjack_player_twenty_one() {

        int expected = -1;

        dealerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        dealerHand.addCardToHand(new Card("Queen", 10, "Clubs"));

        playerHand.addCardToHand(new Card("7", 7, "Clubs"));
        playerHand.addCardToHand(new Card("9", 9, "Hearts"));
        playerHand.addCardToHand(new Card("5", 5, "Diamonds"));

        int actual = playerHand.compareTo(dealerHand);

        assertEquals(expected, actual);

    }

    @Test
    public void get_correct_hand_score_with_no_aces() {

        int expected = 19;

        playerHand.addCardToHand(new Card("5", 5, "Diamonds"));
        playerHand.addCardToHand(new Card("Queen", 10, "Clubs"));
        playerHand.addCardToHand(new Card("4", 4, "Clubs"));

        int actual = playerHand.getHandScore();

        assertEquals(expected, actual);

    }

    @Test
    public void get_correct_hand_score_with_one_ace() {

        int expected = 14;

        playerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        playerHand.addCardToHand(new Card("3", 3, "Clubs"));

        int actual = playerHand.getHandScore();

        assertEquals(expected, actual);

    }

    @Test
    public void get_correct_hand_score_with_two_aces() {

        int expected = 16;

        playerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        playerHand.addCardToHand(new Card("Ace", 1, "Clubs"));
        playerHand.addCardToHand(new Card("4", 4, "Clubs"));

        int actual = playerHand.getHandScore();

        assertEquals(expected, actual);

    }

    @Test
    public void get_correct_number_of_aces() {

        int expected = 1;

        playerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        playerHand.addCardToHand(new Card("3", 3, "Clubs"));

        int actual = playerHand.getNumberOfAces();

        assertEquals(expected, actual);

    }

    @Test
    public void player_beats_dealer_with_blackjack_dealer_twenty_one() {

        int expected = 1;

        playerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        playerHand.addCardToHand(new Card("Queen", 10, "Clubs"));

        dealerHand.addCardToHand(new Card("7", 7, "Clubs"));
        dealerHand.addCardToHand(new Card("9", 9, "Hearts"));
        dealerHand.addCardToHand(new Card("5", 5, "Diamonds"));

        int actual = playerHand.compareTo(dealerHand);

        assertEquals(expected, actual);

    }

    @Test
    public void player_and_dealer_tie_with_non_natural_twenty_one() {

        int expected = 0;

        playerHand.addCardToHand(new Card("5", 5, "Diamonds"));
        playerHand.addCardToHand(new Card("10", 10, "Clubs"));
        playerHand.addCardToHand(new Card("6", 6, "Clubs"));

        dealerHand.addCardToHand(new Card("7", 7, "Clubs"));
        dealerHand.addCardToHand(new Card("9", 9, "Hearts"));
        dealerHand.addCardToHand(new Card("5", 5, "Diamonds"));

        int actual = playerHand.compareTo(dealerHand);

        assertEquals(expected, actual);

    }

    @Test
    public void player_and_dealer_tie_with_both_having_natural_twenty_one() {

        int expected = 0;

        playerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        playerHand.addCardToHand(new Card("Queen", 10, "Clubs"));

        dealerHand.addCardToHand(new Card("Ace", 1, "Diamonds"));
        dealerHand.addCardToHand(new Card("Queen", 10, "Clubs"));

        int actual = playerHand.compareTo(dealerHand);

        assertEquals(expected, actual);

    }
}