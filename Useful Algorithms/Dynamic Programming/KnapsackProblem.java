/*
    A variant of Knapsack problem:
    Given a list of wights [w1, w2, ..., wn], determine all sums that can be constructed using the weights.
 */
public class KnapsackProblem {

    public static void main (String[] args) {

        int[] weights = {1, 3, 3, 5};

        // Let W denote the total sum of the weights.
        int W = 0;
        for (int weight : weights) {
            W += weight;
        }

        // Let possible[x,k] = true if we can construct a sum x using the first k weights,
        // and otherwise possible[x,k] = false
        boolean[][] possible = new boolean[W + 1][weights.length + 1];

        // Base case - if no weights are used, we can only form the sum 0.
        possible[0][0] = true;

        // The recursive case - we can either use or not the weight wk in the sum. If we use wk, the remaining task is
        // to form the sum x-wk using the first k-1 weights, and if we do not use wk, the remaining task is to form the
        // sum x using the first k-1 weights.
        for (int x = 0; x <= W; x++) {
            for (int k = 1; k <= weights.length; k++) {
                if (x - weights[k-1] >= 0) {
                    possible[x][k] = possible[x-weights[k-1]][k-1];
                }
                possible[x][k] = possible[x][k] || possible[x][k-1];
            }
        }

        System.out.println("The following table shows all values of the function for the weights:");
        System.out.print("k/x" + "\t");
        for (int i = 0; i <= W; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for (int k = 0; k <= weights.length; k++) {
            System.out.print(k + "\t");
            for (int x = 0; x <= W; x++) {
                System.out.print((possible[x][k] ? 'X' : ' ') + "\t");
            }
            System.out.println();
        }

        System.out.println();

        // Let secondPossibilities[x] indicates whether we can construct a subset with sum x.
        boolean[] secondPossibilities = new boolean[W+1];
        secondPossibilities[0] = true;
        for (int k = 1; k <= weights.length; k++) {
            for (int x = W; x >= 0; x--) {
                if (secondPossibilities[x]) {
                    secondPossibilities[x+weights[k-1]] = true;
                }
            }
        }

        for (int i = 0; i <= W; i++) {
            System.out.print(i + "\t");
        }

        System.out.println();

        for (int i = 0; i < secondPossibilities.length; i++) {
            System.out.print((secondPossibilities[i]? "X" : " " ) + "\t");
        }


    }
}
