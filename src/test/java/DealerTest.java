import org.example.model.Dealer;
import org.example.model.Deck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DealerTest {

    private Dealer dealer;

    @Before
    public void setup() {

        dealer = new Dealer();

    }

    @Test
    public void starting_hand_contains_one_card() {

        int expected = 1;

        Deck deck = new Deck();
        deck.loadDeck();

        dealer.setUpStartingHand(deck);
        int actual = dealer.getHand().getHandSize();

        assertEquals(expected, actual);

    }

}