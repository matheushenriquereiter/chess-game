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

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return ICON_PATH_TEMPLATE.formatted(pieceColor, pieceName);
    }

    public abstract List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares);

    public void insertLegalMovement(int row, int column, List<List<Integer>> legalMovements) {
        List<Integer> legalMovement = new ArrayList<>();

        legalMovement.add(row);
        legalMovement.add(column);

        legalMovements.add(legalMovement);
    }

    public boolean addLegalMovement(int row, int column, List<List<Integer>> legalMovements, Square[][] squares) {
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

    public void addDirectionalMovements(int row, int column, int rowDelta, int columnDelta, List<List<Integer>> legalMovements, Square[][] squares, Integer movementLimit) {
        int r = row + rowDelta;
        int c = column + columnDelta;
        int counter = 0;

        while (r >= 0 && r <= 7 && c >= 0 && c <= 7) {
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

    public void addOrthogonalMovements(int row, int column, List<List<Integer>> legalMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, 0, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 0, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 0, 1, legalMovements, squares, limiter);
    }

    public void addDiagonalMovements(int row, int column, List<List<Integer>> legalMovements, Square[][] squares, Integer limiter) {
        addDirectionalMovements(row, column, -1, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, -1, 1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, -1, legalMovements, squares, limiter);
        addDirectionalMovements(row, column, 1, 1, legalMovements, squares, limiter);
    }
}
