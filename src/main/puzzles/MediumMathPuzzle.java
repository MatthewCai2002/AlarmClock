package puzzles;

/*

represents an easy difficulty math problem involving addition, subtraction, and multiplication
between 2 integers from 0-100

 */

public class MediumMathPuzzle extends MathPuzzle {

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
    }

    @Override
    public void genRandomPuzzle() {
        if (operatorSelector == 0) {
            problem = num1 + " + " + num2;
            correctSolution = num1 + num2;
        } else if (operatorSelector == 1) {
            problem = num1 + " - " + num2;
            correctSolution = num1 - num2;
        } else if (operatorSelector == 2) {
            problem = num1 + " * " + num2;
            correctSolution = num1 * num2;
        }
    }
}
