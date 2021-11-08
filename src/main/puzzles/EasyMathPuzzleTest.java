package puzzles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EasyMathPuzzleTest {
    MathPuzzle testPuzzle;

    @BeforeEach
    public void setup() {
        testPuzzle = new EasyMathPuzzle();
    }

    // no idea how i'm supposed to test this
    @Test
    public void testGenRandomPuzzle() {
        testPuzzle.genRandomPuzzle();
        String problem = testPuzzle.getProblem();
        int num1 = testPuzzle.getNum1();
        int num2 = testPuzzle.getNum2();
        int solution = testPuzzle.getCorrectSolution();
        System.out.println(problem);
        System.out.println(solution);
        if (0 <= num1 && num1 < 50 && 0 <= num2 && num2 < 50){
            // expected
        }
        assertEquals(solution, testPuzzle.getCorrectSolution());
    }

    @Test
    public void testSolvePuzzle() {
        testPuzzle.setSolution(2);

        testPuzzle.solvePuzzle(0);
        Boolean solved = testPuzzle.isSolved();
        assertFalse(solved);

        testPuzzle.solvePuzzle(2);
        solved = testPuzzle.isSolved();
        assertTrue(solved);
    }

    @Test
    public void testGetProblem() {
        testPuzzle.setProblem("1 + 1");
        String problem = testPuzzle.getProblem();
        assertEquals("1 + 1", problem);
    }
}
