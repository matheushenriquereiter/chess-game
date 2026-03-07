package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
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

    public abstract List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares);

    protected String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return ICON_PATH_TEMPLATE.formatted(pieceColor, pieceName);
    }

    protected void insertLegalMovement(int row, int column, List<List<Integer>> legalMovements) {
        List<Integer> legalMovement = new ArrayList<>();

        legalMovement.add(row);
        legalMovement.add(column);

        legalMovements.add(legalMovement);
    }

    protected boolean addLegalMovement(int row, int column, List<List<Integer>> legalMovements, Square[][] squares) {
        Square square = squares[row][column];

        if (square.hasPiece() && square.getPiece().getColor() == getColor()) {
            return true;
        }

        if (square.hasPiece()) {
            insertLegalMovement(row, column, legalMovements);
            return true;
        }

        insertLegalMovement(row, column, legalMovements);
        return false;
    }

    protected void addDirectionalMovements(int row, int column, int rowDelta, int columnDelta, List<List<Integer>> legalMovements, Square[][] squares, Integer movementLimit) {
        int r = row + rowDelta;
        int c = column + columnDelta;
        int counter = 0;

        while (Chessboard.isWithinBounds(r, c)) {
            boolean reachedMovementLimit = (movementLimit != null) && (counter == movementLimit);
            if (reachedMovementLimit) {
                break;
            }

            boolean squareHasPiece = addLegalMovement(r, c, legalMovements, squares);
            if (squareHasPiece) {
                break;
            }

            r += rowDelta;
            c += columnDelta;
            counter++;
        }
    }

    protected void addOrthogonalMovements(int row, int column, List<List<Integer>> legalMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, 0, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 0, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, 1, legalMovements, squares, limiter);
    }

    protected void addDiagonalMovements(int row, int column, List<List<Integer>> legalMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, -1, 1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 1, legalMovements, squares, limiter);
    }

    protected void addPieceJumpMovement(int row, int column, int rowDelta, int columnDelta, Square[][] squares, List<List<Integer>> legalMovements) {
        if (Chessboard.isWithinBounds(row + rowDelta, column + columnDelta)) {
            Square square = squares[row + rowDelta][column + columnDelta];

            if (!square.hasPiece() || square.hasPiece() && square.getPiece().getColor() != getColor()) {
                insertLegalMovement(row + rowDelta, column + columnDelta, legalMovements);
            }
        }
    }
}
