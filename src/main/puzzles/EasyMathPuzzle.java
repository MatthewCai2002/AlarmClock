package puzzles;
/*
 represents an easy difficulty math problem involving only addition and subtraction
 between 2 integers from 0-50
*/

public class EasyMathPuzzle extends MathPuzzle {
    private static final int ADD = 0;
    private static final int SUBTRACT = 1;


    // EFFECTS: constructs an easy math puzzle with 2 random integers from 0-50
    //          and a random operator of addition or subtraction
    public EasyMathPuzzle() {
        super();
        range = 50;
        operatorRange = 2;
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
        } else  {
            problem = num1 + " - " + num2;
            correctSolution = num1 - num2;
        }
    }
}
