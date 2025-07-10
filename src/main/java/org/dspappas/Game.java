package org.dspappas;

import lombok.NoArgsConstructor;

import static java.lang.Math.*;

@NoArgsConstructor
public class Game {

    public Player playerOne = new Player(
            "Player 1",
            Choice.PAPER
    );

    public Player playerTwo = new Player(
            "Player 2"
    );

    public void pressStart() {
        System.out.println("Press Start");
        playerTwo.hand = randomChoice();

    }

    private Choice randomChoice() {
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
}
