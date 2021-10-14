package puzzles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzles.MathPuzzle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathPuzzleTest {
    MathPuzzle testPuzzle;

    @BeforeEach
    public void setup() {
        testPuzzle = new MathPuzzle();
    }

    @Test
    public void testSolvePuzzle() {
        testPuzzle.solvePuzzle(0);
        Boolean solved = testPuzzle.isSolved();
        assertFalse(solved);

        testPuzzle.solvePuzzle(2);
        solved = testPuzzle.isSolved();
        assertTrue(solved);
    }
}
