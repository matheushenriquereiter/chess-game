package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        movements.clear();

        if (column != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row);
            coordinates.add(column - 1);

            movements.add(coordinates);
        }

        if (column != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row);
            coordinates.add(column + 1);

            movements.add(coordinates);
        }

        if (row != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row - 1);
            coordinates.add(column);

            movements.add(coordinates);
        }

        if (row != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 1);
            coordinates.add(column);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row + 1, column + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 1);
            coordinates.add(column + 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row - 1, column - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row - 1);
            coordinates.add(column - 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row + 1, column - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 1);
            coordinates.add(column - 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row - 1, column + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row - 1);
            coordinates.add(column + 1);

            movements.add(coordinates);
        }

        return movements;
    }

    public boolean inCheck() {
        Square[][] boardSquares = Chessboard.getBoardSquares();

        for (Square[] rows : boardSquares) {
            for (Square square : rows) {
                if (square.hasPiece() && square.getPiece().getColor() == PieceColor.WHITE) {
                    List<List<Integer>> rivalPossibleMovements = square.getPiece().getLegalMovements(square.getRow(), square.getColumn(), boardSquares);
                }
            }
        }

        return false;
    }
}
