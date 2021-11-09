package model;

import exceptions.InvalidDifficultyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzles.MathPuzzle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PuzzleManagerTest {
    private PuzzleManager testPuzzleManager;

    @BeforeEach
    public void setup() {
        testPuzzleManager = new PuzzleManager();
    }

    @Test
    public void testSetDifficultyEasy() {
        try {
            testPuzzleManager.setDifficulty("easy");
            int difficulty = testPuzzleManager.getDifficulty();
            assertEquals(0, difficulty);
        } catch (InvalidDifficultyException e) {
            fail();
        }

    }

    @Test
    public void testSetDifficultyMedium() {
        try {
            testPuzzleManager.setDifficulty("medium");
            int difficulty = testPuzzleManager.getDifficulty();
            assertEquals(1, difficulty);
        } catch (InvalidDifficultyException e) {
            fail();
        }
    }

    @Test
    public void testSetDifficultyInvalidDifficulty() {
        try {
            testPuzzleManager.setDifficulty("hard");
            fail();
        } catch (InvalidDifficultyException e) {
            // expected
        }
    }

    @Test
    public void testSetPuzzleEasy() {
        try {
            testPuzzleManager.setPuzzle("easy");
            int difficulty = testPuzzleManager.getDifficulty();
            MathPuzzle easyPuzzle = testPuzzleManager.getPuzzle();
            int num1 = easyPuzzle.getNum1();
            int num2 = easyPuzzle.getNum2();
            String problem = easyPuzzle.getProblem();
            System.out.println(problem);


            assertEquals(0,difficulty);
            if (0 <= num1 && num1 < 50 && 0 <= num2 && num2 < 50){
                // checking if easy problem
            }
        } catch (InvalidDifficultyException e) {
            fail();
        }
    }

    @Test
    public void testSetPuzzleMedium() {
        try {
            testPuzzleManager.setPuzzle("medium");
            int difficulty = testPuzzleManager.getDifficulty();
            MathPuzzle medPuzzle = testPuzzleManager.getPuzzle();
            int num1 = medPuzzle.getNum1();
            int num2 = medPuzzle.getNum2();
            String problem = medPuzzle.getProblem();
            System.out.println(problem);


            assertEquals(1,difficulty);
            if (0 <= num1 && num1 < 100 && 0 <= num2 && num2 < 100){
                // checking if medium problem
            }
        } catch (InvalidDifficultyException e) {
            fail();
        }
    }

    @Test
    public void testSetPuzzleInvalidDifficulty() {
        try {
            testPuzzleManager.setPuzzle("hard");
            fail();
        } catch (InvalidDifficultyException e) {
            // expected
        }

    }

}
