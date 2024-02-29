# Blackjack by Alberto Castro

## Project needs OpeaAI api key to make use of bots, uses GPT-3.5 Turbo, bots can be toggled off in main menu.

## Brainstorming

- Identify what could be a class
    - Dealer
    - Game
    - Card
    - Player
    - Hand

## Must have features

- [x] Initial cards get dealt
- [x] Player is shown hand
- [x] Player is asked if they want to hit or stay
- [x] Dealer hits if their hand score is less than 17
- [x] Outcome is calculated

## Additional features

- [ ] Use DB to store players, winnings info
- [x] Use of money for bets
- [ ] ASCII art for cards
- [x] Multiplayer
- [x] Add players in the middle of the game
- [x] Add money to person balance

## Known rules

- If a person gets a natural they get 1.5 of their bet
- Naturals tie both get money back
- Naturals beat 21 made up 3 or more cards
- Dealer stays on all 17s
- Dealer must take card if 16 or under
- Dealer must count ace as 11 if it would give them 17

## Cleanup

- Create constants for magic numbers
- Add comments/java docs

## Bugs

- [x] Entering number when asked to enter name of person to add balance to crashes
- [x] Not currently checking to make sure scanner input can be parsed to int

## Small additions

- [x] Show person score when asking hit or stay
- [x] Show that no players won if all bust
- [x] Show lowercase letters for input prompts

## Bigger additions

- [ ] Record wins and losses and store it in a db if they have a password
- [ ] Create an endpoint to show wins and losses
- [ ] Allow players the option to create a password
- [ ] Split hands
- [ ] Double down
- [ ] Online multiplayer (web sockets)
