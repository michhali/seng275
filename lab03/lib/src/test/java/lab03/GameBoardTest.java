package lab03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameBoardTest {


    // Setup before entire test suite


    @BeforeAll
    static void setupBoard() {
        GameBoard.initializeBoard();
    }


    // Good weather tests (inside board)


    @Test
    void centerPointIsInside() {
        assertTrue(GameBoard.isInsideBoard(3, 3));
    }

    @Test
    void topLeftCornerIsInside() {
        assertTrue(GameBoard.isInsideBoard(0, 0));
    }

    @Test
    void bottomRightCornerIsInside() {
        assertTrue(GameBoard.isInsideBoard(5, 5));
    }

    @Test
    void edgePointIsInside() {
        assertTrue(GameBoard.isInsideBoard(0, 4));
    }

    @Test
    void anotherValidPointIsInside() {
        assertTrue(GameBoard.isInsideBoard(2, 1));
    }


    // Bad weather tests (outside board)


    @Test
    void xBelowZeroIsOutside() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(-1, 2));
    }

    @Test
    void xAboveUpperBoundaryIsOutside() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(6, 2));
    }

    @Test
    void yBelowZeroIsOutside() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(2, -1));
    }

    @Test
    void yAboveUpperBoundaryIsOutside() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(2, 6));
    }


    // Boundary testing (ON and OFF points)

    // Left boundary
    @Test
    void leftBoundaryOnPoint() {
        assertTrue(GameBoard.isInsideBoard(0, 3));
    }

    @Test
    void leftBoundaryOffPoint() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(-1, 3));
    }

    // Right boundary
    @Test
    void rightBoundaryOnPoint() {
        assertTrue(GameBoard.isInsideBoard(5, 3));
    }

    @Test
    void rightBoundaryOffPoint() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(6, 3));
    }

    // Top boundary
    @Test
    void topBoundaryOnPoint() {
        assertTrue(GameBoard.isInsideBoard(3, 0));
    }

    @Test
    void topBoundaryOffPoint() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(3, -1));
    }

    // Bottom boundary
    @Test
    void bottomBoundaryOnPoint() {
        assertTrue(GameBoard.isInsideBoard(3, 5));
    }

    @Test
    void bottomBoundaryOffPoint() {
        assertThrows(IllegalArgumentException.class,
                () -> GameBoard.isInsideBoard(3, 6));
    }
}
