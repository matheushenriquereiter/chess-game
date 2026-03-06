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
        List<List<Integer>> movements = new ArrayList<>();

        if (Chessboard.positionExists(row - 2, column - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (!(squares[row - 2][column - 1].hasPiece() && squares[row - 2][column - 1].getPiece().getColor() == getColor())) {
                coordinates.add(row - 2);
                coordinates.add(column - 1);

                movements.add(coordinates);
            }
        }

        if (Chessboard.positionExists(row - 2, column + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (!(squares[row - 2][column + 1].hasPiece() && squares[row - 2][column + 1].getPiece().getColor() == getColor())) {
                coordinates.add(row - 2);
                coordinates.add(column + 1);

                movements.add(coordinates);
            }
        }

        if (Chessboard.positionExists(row + 2, column - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 2);
            coordinates.add(column - 1);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row + 2, column + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 2);
            coordinates.add(column + 1);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row + 1, column + 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 1);
            coordinates.add(column + 2);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row - 1, column + 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row - 1);
            coordinates.add(column + 2);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row + 1, column - 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row + 1);
            coordinates.add(column - 2);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(row - 1, column - 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(row - 1);
            coordinates.add(column - 2);

            movements.add(coordinates);
        }

        return movements;
    }
}
