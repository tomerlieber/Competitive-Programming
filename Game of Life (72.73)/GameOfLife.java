import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {

        // Uncomment the following line in order to simulate a specific input
        // setStandardInput();

        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // The number of rows in the board.
        int m = in.nextInt(); // The number of columns in the board.
        int c = in.nextInt(); // The number of iterations that you must simulate.
        in.skip("\n");

        boolean[][] board = createInitialBoard(n, m, in);

        ArrayList<String> boardHashings = new ArrayList<>();
        ArrayList<boolean[][]> boards = new ArrayList<>();

        int iteration = 0;

        String md5 = getBoardHashing(board, n, m);

        while (iteration < c && !boardHashings.contains(md5)) {

            boardHashings.add(md5);
            boards.add(board);

            board = calcNextBoard(board, n, m);

            md5 = getBoardHashing(board, n, m);
            iteration++;
        }

        if (iteration >= c) {
            boardHashings.add(md5);
            boards.add(board);
        }

        int cycleStartIndex = boardHashings.indexOf(md5);
        int resultIndex = cycleStartIndex + (c - cycleStartIndex) % boards.size();

        boolean[][] result = boards.get(resultIndex);
        printBoard(result, n, m);

    }

    private static boolean[][] createInitialBoard(int n, int m, Scanner in) {

        boolean[][] board = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = in.nextLine();

            for (int j = 0; j < m; j++) {
                board[i][j] = row.charAt(j) == '*';
            }
        }

        return board;
    }

    private static boolean[][] calcNextBoard(boolean[][] board, int n, int m) {

        boolean[][] nextBoard = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int count = countLiveNeighbors(board, i, j);

                if (board[i][j] && (count < 2 || count > 3)) {
                    nextBoard[i][j] = false;
                } else if (!board[i][j] && count == 3) {
                    nextBoard[i][j] = true;
                } else {
                    nextBoard[i][j] = board[i][j];
                }
            }
        }

        return nextBoard;
    }

    private static int countLiveNeighbors(boolean[][] board, int i, int j) {

        int count = 0;

        int up = i - 1, down = i + 1, left = j - 1, right = j + 1;
        int lastLine = board.length - 1;
        int lastColumn = board[0].length - 1;

        // Check for cases that one of the neighbors is in the second side of the board.
        if (i == 0) {
            up = lastLine;
        }
        if (i == lastLine) {
            down = 0;
        }
        if (j == 0) {
            left = lastColumn;
        }
        if (j == lastColumn) {
            right = 0;
        }

        if (board[up][left]) {
            count++;
        }
        if (board[up][j]) {
            count++;
        }
        if (board[up][right]) {
            count++;
        }
        if (board[i][left]) {
            count++;
        }
        if (board[i][right]) {
            count++;
        }
        if (board[down][left]) {
            count++;
        }
        if (board[down][j]) {
            count++;
        }
        if (board[down][right]) {
            count++;
        }

        return count;
    }

    private static String getBoardHashing(boolean[][] board, int n, int m) {

        String boardString = getBoardString(board, n, m);

        return getMD5(boardString);
    }

    private static String getBoardString(boolean[][] board, int n, int m) {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                str.append(board[i][j] ? "*" : "-");
            }
        }

        return str.toString();
    }

    private static String getMD5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printBoard(boolean[][] board, int n, int m) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                System.out.print(board[i][j] ? "*" : "-");
            }

            System.out.println();
        }

        System.out.println();
    }

    private static void setStandardInput() {

        String input = "5 6 1234\n" +
                "------\n" +
                "--*---\n" +
                "---*--\n" +
                "-***--\n" +
                "------";

        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(fakeIn);
    }
}
