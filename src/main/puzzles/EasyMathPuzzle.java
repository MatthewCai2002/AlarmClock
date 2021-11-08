package puzzles;
/*
 represents an easy difficulty math problem involving only addition and subtraction
 between 2 integers from 0-50
*/

public class EasyMathPuzzle extends MathPuzzle {

    // EFFECTS: constructs an easy math puzzle with 2 random integers from 0-50
    //          and a random operator of addition or subtraction
    public EasyMathPuzzle() {
        super();
        range = 50;
        operatorRange = 2;
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
        }
    }
}
