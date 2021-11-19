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
        num1 = (int)(Math.random() * range);
        num2 = (int)(Math.random() * range);
        operatorSelector = (int)(Math.random() * operatorRange);
        genRandomPuzzle();
    }

    @Override
    public void genRandomPuzzle() {
        if (operatorSelector == ADD) {
            problem = num1 + " + " + num2;
            correctSolution = num1 + num2;
        } else if (operatorSelector == SUBTRACT) {
            problem = num1 + " - " + num2;
            correctSolution = num1 - num2;
        } else  {
            // last case is multiplication
            problem = num1 + " * " + num2;
            correctSolution = num1 * num2;
        }
    }
}
