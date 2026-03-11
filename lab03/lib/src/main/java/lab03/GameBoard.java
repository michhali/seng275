package lab03;

public class GameBoard {

    private static final int SIZE = 6;
    private static int[][] board;

    // Called once before tests
    public static void initializeBoard() {
        board = new int[SIZE][SIZE];
    }

    // Checks if a point is inside the board
    public static boolean isInsideBoard(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            throw new IllegalArgumentException("Player is outside the board");
        }
        return true;
    }
}
