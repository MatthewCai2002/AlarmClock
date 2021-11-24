package model;

import exceptions.InvalidDifficultyException;
import puzzles.EasyMathPuzzle;
import puzzles.MathPuzzle;
import puzzles.MediumMathPuzzle;

/*

represents a puzzle manager with a puzzle and difficulty.

 */

public class PuzzleManager {
    private static final int EASY = 0;
    private static final int MEDIUM = 1;

    private MathPuzzle puzzle; // the puzzle required to solve to turn off alarm
    private int difficulty;

    // EFFECTS: constructs a puzzle manager with a puzzle and difficulty
    public PuzzleManager() {
        difficulty = EASY;
        puzzle = new EasyMathPuzzle();
    }

    // MODIFIES: this
    // EFFECTS: depending on the difficulty, generate a random puzzle of appropriate difficulty
    //          if difficulty = medium then generate a medium puzzle
    //          otherwise generates an easy puzzle
    public void setPuzzle(String difficulty) throws InvalidDifficultyException {
        setDifficulty(difficulty);
        if (this.difficulty == MEDIUM) {
            puzzle = new MediumMathPuzzle();
        } else if (this.difficulty == EASY) {
            puzzle = new EasyMathPuzzle();
        }
    }

    // REQUIRES: difficulty is either "easy" or "medium"
    // MODIFIES: this
    // EFFECTS: if difficulty is = either "easy" or "medium"
    //             sets puzzle difficulty to what's given
    //          otherwise throws InvalidDifficultyException
    public void setDifficulty(String difficulty) throws InvalidDifficultyException {
        if (difficulty.equals("easy") || difficulty.equals("medium")) {
            int intDifficulty = toInt(difficulty);
            this.difficulty = intDifficulty;
        } else {
            throw new InvalidDifficultyException();
        }
    }

    // MODIFIES: stringDifficulty
    // EFFECTS: stringDifficulty convert from a string to its appropriate integer
    //          if stringDifficulty = "medium" then converts to MEDIUM
    //          otherwise returns EASY
    public int toInt(String stringDifficulty) {
        if (stringDifficulty.equals("medium")) {
            return MEDIUM;
        }

        return EASY;
    }

    // EFFECTS: returns current difficulty setting
    public int getDifficulty() {
        return difficulty;
    }

    // EFFECTS: returns the puzzle needed to turn off the alarm
    public MathPuzzle getPuzzle() {
        return puzzle;
    }

}