package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addDirectionalMovements(row, column, -1, 0, legalMovements, squares);
        addDirectionalMovements(row, column, 1, 0, legalMovements, squares);
        addDirectionalMovements(row, column, 0, -1, legalMovements, squares);
        addDirectionalMovements(row, column, 0, 1, legalMovements, squares);

        return legalMovements;
    }

    public void addDirectionalMovements(int row, int column, int rowDelta, int columnDelta, List<List<Integer>> legalMovements, Square[][] squares) {
        int r = row + rowDelta;
        int c = column + columnDelta;

        while (r >= 0 && r <= 7 && c >= 0 && c <= 7) {
            boolean hasPiece = addMovement(r, c, legalMovements, squares);

            if (hasPiece) {
                break;
            }

            r += rowDelta;
            c += columnDelta;
        }
    }

    public boolean addMovement(int row, int column, List<List<Integer>> legalMovements, Square[][] squares) {
        Square square = squares[row][column];

        if (square.hasPiece() && square.getPiece().getColor() == getColor()) {
            return true;
        }

        if (square.hasPiece()) {
            addLegalMovement(row, column, legalMovements);
            return true;
        }

        addLegalMovement(row, column, legalMovements);
        return false;
    }
}
