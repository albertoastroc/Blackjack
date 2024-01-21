package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class GameCLI {

    Scanner scanner = new Scanner(System.in);

    Game game = new Game(scanner);

    /**
     * Runs the game <br> Choose 1 to add players <br> Choose 2 to start a new round <br> Choose 3 to remove players <br>
     * Choose 4 to show players and balances <br> Choose 5 to add to a player's balance <br> Choose 6 to quit
     */

    public void run() {

        game.loadNewDeck();

        String userInput = "2";

        while (! userInput.equals("7")) {

            if (userInput.length() != 1) {

                System.out.println("Enter a valid option");
                printMainMenu();
                userInput = scanner.nextLine();

            }

            if (userInput.equals("1")) {

                game.playRound();
                printMainMenu();
                userInput = scanner.nextLine();

            }
            if (userInput.equals("2")) {

                System.out.println("Add players");
                System.out.println("Enter player names separated by a comma");
                String inputPlayers = scanner.nextLine();

                game.addPlayers(inputPlayers);
                printMainMenu();
                userInput = scanner.nextLine();

            }

            if (userInput.equals("3")) {

                System.out.println("Remove players");
                System.out.println("Enter player names separated by a comma");
                String inputPlayers = scanner.nextLine();

                game.removePlayers(inputPlayers);
                printMainMenu();
                userInput = scanner.nextLine();

            }

            if (userInput.equals("4")) {

                game.printPlayersAndBalances();
                printMainMenu();
                userInput = scanner.nextLine();

            }

            if (userInput.equals("5")) {

                System.out.println("Enter name of player to add balance to");
                String playerName = scanner.nextLine();

                game.addPlayerFunds(playerName);
                printMainMenu();
                userInput = scanner.nextLine();

            }

            if (userInput.equals("6")) {

                System.out.println("Use bots? y/n");

                game.toggleBots();
                printMainMenu();
                userInput = scanner.nextLine();

            }

        }
    }

    private void printMainMenu() {

        System.out.println();
        System.out.println("1. Play round");
        System.out.println("2. Add players");
        System.out.println("3. Remove players");
        System.out.println("4. Show current players and balances");
        System.out.println("5. Add to player balance");
        System.out.println("6. Toggle bots");
        System.out.println("7. Quit");

    }

}