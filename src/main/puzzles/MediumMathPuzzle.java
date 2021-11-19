package puzzles;

/*

represents an easy difficulty math problem involving addition, subtraction, and multiplication
between 2 integers from 0-100

 */

public class MediumMathPuzzle extends MathPuzzle {
    private static final int ADD = 0;
    private static final int SUBTRACT = 1;

    // EFFECTS: constructs a medium difficulty math puzzle with 2
    //          random integers from 0-50 and a random operator of either
    //          addition, subtraction, or multiplication
    public MediumMathPuzzle() {
        super();
        range = 100;
        operatorRange = 3;
        genRandomPuzzle();
    }

    @Override
    public void genRandomPuzzle() {
        super.genRandomNumber();
        if (operatorSelector == ADD) {
            problem = num1 + " + " + num2;
            correctSolution = num1 + num2;
        } else if (operatorSelector == SUBTRACT) {
            problem = num1 + " - " + num2;
            correctSolution = num1 - num2;
        } else  {
            // last case is multiplication because only 3 options
            problem = num1 + " * " + num2;
            correctSolution = num1 * num2;
        }
    }
}
