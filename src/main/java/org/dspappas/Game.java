package org.dspappas;

import lombok.NoArgsConstructor;

import static java.lang.Math.*;

@NoArgsConstructor
public class Game {

    public Player playerOne = new Player(
            "Player A",
            Choice.PAPER
    );

    public Player playerTwo = new Player(
            "Player B"
    );

    public void pressStart() {
        for (int i = 0; i < 100; i++) {
            playerTwo.hand = randomChoice();
            rockPaperScissorsRound(playerTwo.hand);
        }
        displayOutput();
    }

    public Choice randomChoice() {
        Choice result = null;
        double randomNumber = random() * 3;
        int roundRandomNumber = (int) floor(randomNumber);
        result = switch (roundRandomNumber) {
            case 0 -> Choice.ROCK;
            case 1 -> Choice.PAPER;
            case 2 -> Choice.SCISSORS;
            default -> result;
        };
        return result;
    }

    private void rockPaperScissorsRound(Choice handTwo) {
        if (handTwo == Choice.ROCK) {
            playerOne.score += 1;
        }
        if (handTwo == Choice.SCISSORS) {
            playerTwo.score += 1;
        }
    }

    private void displayOutput() {
        int tiedRounds = 100 - playerOne.score - playerTwo.score;
        System.out.printf("%s wins %d of 100 games\n", playerOne.name, playerOne.score);
        System.out.printf("%s wins %d of 100 games\n", playerTwo.name, playerTwo.score);
        System.out.printf("Tie: %d of 100 games\n", tiedRounds);
        System.out.println(displayWinner());
    }

    private String displayWinner() {
        String winner = "Winner: ";
        if (playerOne.score == playerTwo.score) {
            winner = "TIE";
        } else if (playerOne.score > playerTwo.score) {
            winner += playerOne.name;
        } else {
            winner += playerTwo.name;
        }
        return winner;
    }
}