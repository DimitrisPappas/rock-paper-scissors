package org.dspappas;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.dspappas.GameFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private Game sut;

    @Spy
    private Game game;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        sut = new Game();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void resetOutput() {
        System.setOut(originalOut);
    }

    @Nested
    class Initialization {

        @Test
        public void gameInitialization() {
            assertNotNull(sut.playerOne);
            assertNotNull(sut.playerTwo);
            assertEquals(PLAYER_A_NAME, sut.playerOne.name);
            assertEquals(PLAYER_B_NAME, sut.playerTwo.name);
            assertEquals(PLAYER_A_DEFAULT_HAND, sut.playerOne.hand);
            assertNull(sut.playerTwo.hand);
            assertEquals(INITIAL_SCORE, sut.playerOne.score);
            assertEquals(INITIAL_SCORE, sut.playerTwo.score);
        }
    }

    @Nested
    class GameLoop {

        @RepeatedTest(10)
        public void completesGame() {
            assertDoesNotThrow(
                    () -> sut.pressStart()
            );

            assertTrue(sut.playerOne.score >= INITIAL_SCORE);
            assertTrue(sut.playerTwo.score >= INITIAL_SCORE);
            assertTrue(sut.playerOne.score + sut.playerTwo.score <= TOTAL_ROUNDS);

            assertSame(PLAYER_A_DEFAULT_HAND, sut.playerOne.hand);
            assertNotNull(sut.playerTwo.hand);

            String output = outputStreamCaptor.toString();
            assertFalse(output.isEmpty());
            assertTrue(output.contains(PLAYER_A_NAME));
            assertTrue(output.contains(PLAYER_B_NAME));
            assertTrue(output.contains("of " + TOTAL_ROUNDS + " games"));
        }

        @Test
        void gameStateAfterPressStart() {
            assertDoesNotThrow(
                    () -> sut.pressStart()
            );

            int totalScored = sut.playerOne.score + sut.playerTwo.score;
            int ties = TOTAL_ROUNDS - totalScored;

            assertTrue(ties >= 0, "Number of ties should be non-negative");
            assertTrue(ties <= TOTAL_ROUNDS, "Number of ties should not exceed total rounds");

            assertEquals(PLAYER_A_NAME, sut.playerOne.name);
            assertEquals(PLAYER_B_NAME, sut.playerTwo.name);
            assertEquals(PLAYER_A_DEFAULT_HAND, sut.playerOne.hand);
        }
    }

    @Nested
    class PreFixedGames {

        @Test
        void playerOneWinsAllRounds() {
            doReturn(Choice.ROCK).when(game).randomChoice();

            assertDoesNotThrow(
                    () -> game.pressStart()
            );

            String output = outputStreamCaptor.toString();
            assertEquals(SCORE_100, game.playerOne.score);
            assertEquals(SCORE_0, game.playerTwo.score);
            assertTrue(output.contains("Winner: " + game.playerOne.name));
        }

        @Test
        void playerTwoWinsAllRounds() {
            doReturn(Choice.SCISSORS).when(game).randomChoice();

            assertDoesNotThrow(
                    () -> game.pressStart()
            );

            String output = outputStreamCaptor.toString();
            assertEquals(SCORE_0, game.playerOne.score);
            assertEquals(SCORE_100, game.playerTwo.score);
            assertTrue(output.contains("Winner: " + game.playerTwo.name));
        }

        @Test
        void tiedAllRounds() {
            doReturn(Choice.PAPER).when(game).randomChoice();

            assertDoesNotThrow(
                    () -> game.pressStart()
            );

            String output = outputStreamCaptor.toString();
            assertEquals(SCORE_0, game.playerOne.score);
            assertEquals(SCORE_0, game.playerTwo.score);
            assertTrue(output.contains("TIE"));
        }
    }
}
