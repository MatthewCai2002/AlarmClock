package puzzles;

/*

represents a single math problem between 2 numbers from 0-100 with a solution,
and whether or not it has been solved

 */

public abstract class MathPuzzle {

    protected int range;
    protected  int operatorRange;
    protected Boolean solved;
    protected int correctSolution;
    protected int num1;
    protected int num2;
    protected int operatorSelector;
    protected String problem;

    // EFFECTS: constructs a null puzzle that is not solved
    public MathPuzzle() {
        solved = false;
        problem = null;
    }

    // MODIFIES: this
    // EFFECTS: generates a random math problem with 2 numbers and an operator
    //          and calculates the correct solution
    public abstract void genRandomPuzzle();


    // MODIFIES: this
    // EFFECTS: if given solution = problem solution then problem is solved
    //          if not then problem is not solved
    public void solvePuzzle(int solution) {
        if (solution == correctSolution) {
            solved = true;
        } else {
            solved = false;
        }
    }

    // EFFECTS: returns whether or not the problem is solved
    public Boolean isSolved() {
        return solved;
    }

    // EFFECTS: returns the current math problem
    public String getProblem() {
        return problem;
    }

    // for testing
    // EFFECTS: returns the current correct solution
    public int getCorrectSolution() {
        return correctSolution;
    }

    // for testing
    // EFFECTS: returns the current correct solution
    public int getNum1() {
        return num1;
    }

    // for testing
    // EFFECTS: returns the current correct solution
    public int getNum2() {
        return num2;
    }

    // for testing
    // MODIFIES: this
    // EFFECTS: sets current problem to given problem
    public void setProblem(String problem) {
        this.problem = problem;
    }

    // for testing
    // MODIFIES: this
    // EFFECTS:
    public void setSolution(int solution) {
        correctSolution = solution;
    }
}
