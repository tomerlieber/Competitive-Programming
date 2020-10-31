import java.util.Arrays;
import java.util.Random;

public class VerifyingMatrixMultiplication {

    public static void main(String[] args) {

        int n = 2;
        int[][] A = {{6, 8}, {1, 3}};
        int[][] B = {{4, 2}, {9, 0}};

        int[][] C = {{96, 12}, {31, 2}};

        Random r = new Random();

        int[] X = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = r.nextInt();
        }

        // Calculate ABX = A(BX)
        int[] BX = Mult(B, X);
        int[] ABX = Mult(A, BX);

        // Check if ABC = CX
        int[] CX = Mult(C, X);

        boolean result = Arrays.equals(ABX, CX);
        System.out.println(result);

        int[][] D = {{4, 2, 4}, {9, 0, 4}};

        int[][] res = Mult(A, D);


    }

    // Time Complexity O(n^2)
    public static int[] Mult(int[][] mat, int[] vec) {
        int[] res = new int[vec.length];

        for (int i = 0; i < mat.length; i++) {

            int sum = 0;

            for (int j = 0; j < mat.length; j++) {
                sum += (mat[i][j] * vec[j]);
            }

            res[i] = sum;
        }

        return res;
    }

    public static int[][] Mult(int[][] mat1, int[][] mat2) {

        int rowsNumber = mat1.length;
        int colsNumber = mat2[0].length;
        int[][] res = new int[rowsNumber][colsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < colsNumber; j++) {

                int sum = 0;

                for (int k = 0; k < rowsNumber; k++) {
                    sum += (mat1[i][k] * mat2[k][j]);
                }

                res[i][j] = sum;
            }
        }

        return res;
    }
}
