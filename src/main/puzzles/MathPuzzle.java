package puzzles;

/*

represents a single math puzzle with a solution, and whether or not it has been solved

 */

public class MathPuzzle {

    private Boolean solved;
    private int solution;
    private String problem;

    public MathPuzzle() {
        solved = false;
        solution = 2;
        problem = "1 + 1";
    }

    // MODIFIES: this
    // EFFECTS: if given solution = problem solution then problem is solved
    //          if not then problem is not solved
    public void solvePuzzle(int solution) {
        if (solution == this.solution) {
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
}
