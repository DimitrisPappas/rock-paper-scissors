package org.dspappas;

public class GameFixture {

    public static final String PLAYER_A_NAME = "Player A";
    public static final String PLAYER_B_NAME = "Player B";
    public static final Choice PLAYER_A_DEFAULT_HAND = Choice.PAPER;
    public static final int INITIAL_SCORE = 0;
    public static final int SCORE_100 = 100;
    public static final int SCORE_0 = 0;
    public static final int MIXED_SCORE_A = 45;
    public static final int MIXED_SCORE_B = 35;
    public static final int TIE_SCORE = 20;
    public static final int TIE_SCORE_A = 40;
    public static final int TIE_SCORE_B = 40;
    public static final int TOTAL_ROUNDS = 100;


    public static Game createGameWithScores(int playerAScore, int playerBScore) {
        Game game = new Game();
        game.playerOne.score = playerAScore;
        game.playerTwo.score = playerBScore;
        return game;
    }

    public static Game createWinningGameForPlayerA() {
        return createGameWithScores(SCORE_100, SCORE_0);
    }

    public static Game createWinningGameForPlayerB() {
        return createGameWithScores(SCORE_0, SCORE_100);
    }

    public static Game createTiedGame() {
        return createGameWithScores(TIE_SCORE_A, TIE_SCORE_B);
    }

    public static Game createMixedResultGame() {
        return createGameWithScores(MIXED_SCORE_A, MIXED_SCORE_B);
    }
}