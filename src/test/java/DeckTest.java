import org.example.model.Deck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    private Deck deck;

    @Before
    public void setup() {
        deck = new Deck();
        deck.loadDeck();
    }

    @Test
    public void six_deck_count_has_three_hundred_and_twelve_cards() {

        int expected = 312;

        int actual = deck.getDeckSize();

        assertEquals(expected, actual);

    }

}