package org.example;

import org.example.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.Constants.*;

public class Game {

    private final Scanner scanner;
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();
    private Set<Participant> setOfPeople = new HashSet<>();
    private Map<String, Integer> betsMap = new HashMap<>();


    public Game(Scanner scanner) {
        this.scanner = scanner;

    }

    public void addBots() {

        OpenAiService openAiService = new OpenAiService();

        OpenAIBot bot1 = new OpenAIBot(openAiService);
        OpenAIBot bot2 = new OpenAIBot(openAiService);

        setOfPeople.add(bot1);
        setOfPeople.add(bot2);


    }

    public void addPlayerFunds(String playerName) {

        //Filters for Participant that has the matching name
        if (setOfPeople.contains(new Person(playerName))) {

            while (true) {
                try {
                    System.out.println("How much should be added?");
                    String amount = scanner.nextLine();

                    Participant person = setOfPeople.stream().filter(p -> p.getParticipantName().equals(playerName))
                            .collect(Collectors.toList()).get(0);
                    person.addToParticipantBalance(Integer.parseInt(amount));

                    break;

                } catch (NumberFormatException numberFormatException) {

                    System.out.println("Invalid amount");
                }
            }

        } else {

            System.out.println(playerName + " doesn't exist");

        }
    }

    public void addPlayers(String inputPlayers) {

        addBots();

        String[] playerNames = inputPlayers.split(",");

        for (String playerName : playerNames) {
            //Participant names must be unique
            Participant newPerson = new Person(playerName.trim());

            if (!newPerson.getParticipantName().isBlank()) {

                boolean added = setOfPeople.add(newPerson);
                if (!added) {
                    System.out.println("Participant with the name " + playerName.trim() + " already exists");
                }

            }


        }

    }

    public void loadNewDeck() {

        deck.loadDeck();

    }

    public void payBets(Set<Participant> setOfPersonNotBusted, Set<Participant> setOfPlayersThatBusted) {

        System.out.println("********* Results *********");
        System.out.println();

        removeFromBustedPlayersBalance(setOfPlayersThatBusted);
        System.out.println();

        if (setOfPersonNotBusted.isEmpty()) {
            System.out.println("All players busted");

        } else {
            System.out.println("Dealer score " + dealer.getHandScore());
            System.out.println();

            for (Participant person : setOfPersonNotBusted) {
                //If dealer busted everyone still in it gets paid
                if (dealer.getHandScore() > HIGHEST_POSSIBLE_SCORE) {
                    payoutWhenDealerBusts(person);

                } else {
                    payoutWhenDealerNotBustAtLeastOnePlayerNotBust(person);

                }
            }
        }

        System.out.println();
        //Reset the map for new round of betting
        betsMap = new HashMap<>();

    }

    public void playRound() {

        //Load a new deck if deck is running out of cards
        if (deck.getDeckSize() < MIN_PLAYABLE_DECK_SIZE) {
            System.out.println("Loading new deck");
            loadNewDeck();

        }


        //Used for payout and compare hands
        Set<Participant> setOfPlayersNotBust = new HashSet<>();

        //Used to remove bets from their balances
        Set<Participant> setOfPlayersBust = new HashSet<>();

        getBets();

        dealStartingHands();

        //Ask the player if they want to keep hitting unless they have 21
        runPlayerPhase(setOfPlayersNotBust, setOfPlayersBust);

        //Participant choice is over, reveal dealer hidden card
        runDealerPhase(setOfPlayersNotBust);

        printHands();

        //Pays bets and prints results
        payBets(setOfPlayersNotBust, setOfPlayersBust);

        System.out.println("Play another round? y/n");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("y")) {
            playRound();

        }
    }

    public void printPlayersAndBalances() {

        for (Participant person : setOfPeople) {

            System.out.print(person.getParticipantName() + "'s balance is : ");
            System.out.println(person.getParticipantBalance());

        }
    }

    public void removePlayers(String inputPlayers) {

        String[] playerNames = inputPlayers.split(",");

        for (String playerName : playerNames) {

            setOfPeople = setOfPeople.stream()
                    .filter(person -> !person.getParticipantName().equals(playerName.trim())).collect(Collectors.toSet());

        }
    }

    /**
     * @return -1 for loss, 0 for tie, 1 for win
     */
    private int compareHandsGetResult(Participant person, Dealer dealer) {

        return person.getHand().compareTo(dealer.getHand());

    }

    private void dealStartingHands() {

        dealer.setUpStartingHand(deck);

        for (Participant person : setOfPeople) {

            if (person.getParticipantBalance() == 0) {
                setOfPeople.remove(person);

            } else {
                person.setUpStartingHand(deck);

            }
        }
    }

    private void getBets() {

        for (Participant currentPerson : setOfPeople) {

            int playerBalance = currentPerson.getParticipantBalance();

            while (true) {

                try {

                    int betAmount = 0;

                    while (betAmount < 200) {

                        System.out.println(currentPerson.getParticipantName() + " you have " + currentPerson.getParticipantBalance()
                                + " chips how many chips will you bet? (200 minimum)");
                        betAmount = Integer.parseInt(scanner.nextLine());
                    }

                    if (betAmount <= playerBalance) {
                        betsMap.put(currentPerson.getParticipantName(), betAmount);
                        System.out.println("Bet is placed");
                    }

                    break;

                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Invalid amount");

                }
            }
        }
    }

    private void payoutWhenDealerBusts(Participant person) {
        //Checks if a person got blackjack, so they can get paid 1.5
        if (person.getHand().getHandSize() == 2
                && person.getHand().getHandScore() == HIGHEST_POSSIBLE_SCORE) {

            System.out.println(
                    person.getParticipantName() + " wins with Blackjack! +" + (int) (betsMap.get(person.getParticipantName()) * 1.5));
            person.addToParticipantBalance(
                    (int) (betsMap.get(person.getParticipantName()) * 1.5));

            //Pay 1 to 1 to players that didn't bust
        } else {

            System.out.println("Dealer busts " + person.getParticipantName() + " wins!" + " +" + betsMap.get(
                    person.getParticipantName()));

            person.addToParticipantBalance(betsMap.get(person.getParticipantName()));

        }
    }

    private void payoutWhenDealerNotBustAtLeastOnePlayerNotBust(Participant person) {

        int result = compareHandsGetResult(person, dealer);

        //Calculate remaining players hand vs dealer

        if (result == -1 && dealer.getHand().getHandScore() == HIGHEST_POSSIBLE_SCORE && dealer.getHand().getHandSize() == 2) {
            System.out.println(
                    person.getParticipantName() + " loses with " + person.getHandScore() + " against dealer Blackjack -" + betsMap.get(
                            person.getParticipantName()));
            person.subtractFromParticipantBalance(betsMap.get(person.getParticipantName()));

        } else if (result == -1) {
            System.out.println(
                    person.getParticipantName() + " loses with " + person.getHandScore() + " -" + betsMap.get(
                            person.getParticipantName()));
            person.subtractFromParticipantBalance(betsMap.get(person.getParticipantName()));

        } else if (result == 0) {
            System.out.println(
                    person.getParticipantName() + " ties with " + person.getHandScore());

            //Check if person wins with Blackjack
        } else if (result == 1 && person.getHand().getHandSize() == 2
                && person.getHand().getHandScore() == HIGHEST_POSSIBLE_SCORE) {

            System.out.println(
                    person.getParticipantName() + " wins with Blackjack! +" + (int) (betsMap.get(person.getParticipantName()) * 1.5));
            person.addToParticipantBalance(
                    (int) (betsMap.get(person.getParticipantName()) * 1.5));

        } else if (result == 1) {

            System.out.println(
                    person.getParticipantName() + " wins with " + person.getHandScore() + " +" + betsMap.get(
                            person.getParticipantName()));
            person.addToParticipantBalance(betsMap.get(person.getParticipantName()));

        }
    }

    private void printHands() {
        System.out.println();
        System.out.println("********* Hands *********");
        System.out.println();

        for (Participant currentPerson : setOfPeople) {
            System.out.println(currentPerson.getParticipantName() + "'s hand");
            System.out.println(currentPerson.getHand());
            System.out.println("Hand score " + currentPerson.getHandScore());

            if (currentPerson.getHandScore() > HIGHEST_POSSIBLE_SCORE) {
                System.out.println(currentPerson.getParticipantName() + " busts!");

            }

            System.out.println("------------------------------");

        }

        System.out.println("Dealer's hand");

        // Prints dealers hand and score depending on if they're hiding a card
        if (dealer.isHidingCard()) {
            System.out.println("Hidden card, " + dealer.getHand());

            if (dealer.getHand().getNumberOfAces() > 0) {
                System.out.println("Visible score 1/11");

            } else {
                System.out.println("Visible score " + dealer.getHandScore());

            }

        } else {
            System.out.println(dealer.getHand());
            System.out.println("Dealer score " + dealer.getHandScore());

        }

        System.out.println();

    }

    private void removeFromBustedPlayersBalance(Set<Participant> setOfPlayersThatBusted) {
        //Take bet from players that busted
        for (Participant person : setOfPlayersThatBusted) {
            System.out.println(
                    person.getParticipantName() + " busted with " + person.getHandScore() + " -" + betsMap.get(person.getParticipantName()));
            person.subtractFromParticipantBalance(betsMap.get(person.getParticipantName()));

        }
    }

    private void runDealerPhase(Set<Participant> setOfPlayersNotBust) {
        dealer.setHidingCard(false);

        if (!setOfPlayersNotBust.isEmpty()) {
            while (dealer.getHandScore() < DEALER_STOP_SCORE) {
                dealer.getHand().addCardToHand(deck.getRandomCard());

            }
        }
    }

    private void runPlayerPhase(Set<Participant> setOfPlayersNotBust, Set<Participant> setOfPlayersBust) {

        for (Participant currentPerson : setOfPeople) {

            if (currentPerson.isBot()) {

                String botDecision = currentPerson.hitOrStay(dealer.getHand());

                while (botDecision.equals("h")) {

                    currentPerson.getHand().addCardToHand(deck.getRandomCard());

                    if (currentPerson.getHand().getHandScore() > 21) {

                        setOfPlayersBust.add(currentPerson);
                        break;

                    } else {

                        botDecision = currentPerson.hitOrStay(dealer.getHand());

                    }


                }

            } else {

                while (currentPerson.getHandScore() < HIGHEST_POSSIBLE_SCORE) {

                    printHands();

                    System.out.println(
                            currentPerson.getParticipantName() + " you have " + currentPerson.getHandScore()
                                    + " hit or stay? (h/s)");
                    String hitAnswer = scanner.nextLine().toLowerCase();

                    if (hitAnswer.equals("h")) {

                        currentPerson.getHand().addCardToHand(deck.getRandomCard());

                    } else {
                        break;
                    }
                }
            }

            if (currentPerson.getHandScore() <= HIGHEST_POSSIBLE_SCORE) {

                setOfPlayersNotBust.add(currentPerson);

            } else {

                setOfPlayersBust.add(currentPerson);

            }
        }
    }
}