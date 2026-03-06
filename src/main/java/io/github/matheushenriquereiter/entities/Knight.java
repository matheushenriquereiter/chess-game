package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addLShapeMovement(row, column, -2, -1, squares, legalMovements);
        addLShapeMovement(row, column, -2, 1, squares, legalMovements);

        addLShapeMovement(row, column, -1, 2, squares, legalMovements);
        addLShapeMovement(row, column, 1, 2, squares, legalMovements);

        addLShapeMovement(row, column, 2, 1, squares, legalMovements);
        addLShapeMovement(row, column, 2, -1, squares, legalMovements);

        addLShapeMovement(row, column, 1, -2, squares, legalMovements);
        addLShapeMovement(row, column, -1, -2, squares, legalMovements);

        return legalMovements;
    }

    private void addLShapeMovement(int row, int column, int rowDelta, int columnDelta, Square[][] squares, List<List<Integer>> legalMovements) {
        if (Chessboard.positionExists(row + rowDelta, column + columnDelta)) {
            Square square = squares[row + rowDelta][column + columnDelta];

            if (!square.hasPiece() || square.hasPiece() && square.getPiece().getColor() != getColor()) {
                insertLegalMovement(row + rowDelta, column + columnDelta, legalMovements);
            }
        }
    }
}
