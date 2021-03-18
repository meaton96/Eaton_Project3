package utility;

import java.util.Stack;

public class NQueens {
    
    public static int solve(int n) {
        /*
         * solve the n-queens problem by using backtracking
         */
        Stack<Integer> s;
        
        int numSol = 0;
        int col = 0;
        
        s = new Stack<Integer>();
        s.push(0); // start with the 1st queen at position 0
        
        while (true) {
            
            if (col < n && isValid(col, s)) {
                s.push(col);
                col = 0;
            } else if (col >= n) { /* back tracking */
                if (s.isEmpty()) /* end */
                    break;
                col = s.pop() + 1;
                
            } else
                col++;
            if (s.size() == n) { /* we have a solution */
                printSolution(s);
                numSol++;
                col = s.pop() + 1;
            }
        }
        
        return numSol;
        
    }
    
    private static boolean isValid(int col, Stack<Integer> s) {
        /*
         * Check if we can place a queen in the column col
         */
        
        int j = s.size();
        for (int i = 0; i < j; i++) {
            int prevCol = s.get(i);
            if (col == prevCol) // queen on same column
                return false;
            else if (j - i == col - prevCol) // queen on first diagonal
                return false;
            else if (j - i == prevCol - col)// queen on second diagonal
                return false;
        }
        
        return true;
    }
    
    private static void printSolution(Stack<Integer> s) {
        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < s.size(); j++) {
                if (j == s.get(i))
                    System.out.print("Q ");
                else
                    System.out.print("* ");
            }// for
            System.out.println();
        }// for
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        int n = 5;
        
        // pass in parameter n from command line
        if (args.length == 1) {
            n = Integer.parseInt(args[0].trim());
            if (n < 1) {
                System.out.println("Incorrect parameter");
                System.exit(-1);
            }// if
        }// if
        System.out.println("Solving  " + n + "-queens problem.");
        int number = solve(n);
        System.out.println("There are " + number + " solutions to the " + n
                + "-queens problem.");
    }// main()
    
}
