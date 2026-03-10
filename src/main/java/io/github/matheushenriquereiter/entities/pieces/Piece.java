package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Chessboard;
import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.List;

public abstract class Piece {
    private static final String ICON_PATH_TEMPLATE = "src/main/resources/piece-icons/%s-%s.png";
    private final PieceColor color;

    protected Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract List<Position> getPossibleMovements(int row, int column, Square[][] squares);

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return ICON_PATH_TEMPLATE.formatted(pieceColor, pieceName);
    }

    protected boolean addPossibleMovement(int row, int column, List<Position> possibleMovements, Square[][] squares) {
        Square square = squares[row][column];

        if (square.hasPiece() && square.getPiece().getColor() == getColor()) {
            return true;
        }

        if (square.hasPiece()) {
            possibleMovements.add(new Position(row, column));
            return true;
        }

        possibleMovements.add(new Position(row, column));
        return false;
    }

    protected void addDirectionalMovements(int row, int column, int rowDelta, int columnDelta, List<Position> possibleMovements, Square[][] squares, Integer movementLimit) {
        int r = row + rowDelta;
        int c = column + columnDelta;
        int counter = 0;

        while (Chessboard.isWithinBounds(r, c)) {
            boolean reachedMovementLimit = (movementLimit != null) && (counter == movementLimit);
            if (reachedMovementLimit) {
                break;
            }

            boolean squareHasPiece = addPossibleMovement(r, c, possibleMovements, squares);
            if (squareHasPiece) {
                break;
            }

            r += rowDelta;
            c += columnDelta;
            counter++;
        }
    }

    protected void addOrthogonalMovements(int row, int column, List<Position> possibleMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, 0, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 0, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, -1, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, 1, possibleMovements, squares, limiter);
    }

    protected void addDiagonalMovements(int row, int column, List<Position> possibleMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, -1, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, -1, 1, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, -1, possibleMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 1, possibleMovements, squares, limiter);
    }

    protected void addPieceJumpMovement(int row, int column, int rowDelta, int columnDelta, Square[][] squares, List<Position> possibleMovements) {
        if (Chessboard.isWithinBounds(row + rowDelta, column + columnDelta)) {
            Square square = squares[row + rowDelta][column + columnDelta];

            if (!square.hasPiece() || square.hasPiece() && square.getPiece().getColor() != getColor()) {
                possibleMovements.add(new Position(row + rowDelta, column + columnDelta));
            }
        }
    }
}
