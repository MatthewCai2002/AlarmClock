package puzzles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzles.MathPuzzle;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetProblem() {
        String problem = testPuzzle.getProblem();
        assertEquals("1 + 1", problem);
    }
}
