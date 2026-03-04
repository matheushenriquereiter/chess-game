package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        movements.clear();

        for (int i = row - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][column].hasPiece() && squares[i][column].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[i][column].hasPiece()) {
                coordinates.add(i);
                coordinates.add(column);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(column);

            movements.add(coordinates);
        }

        for (int i = row + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][column].hasPiece() && squares[i][column].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[i][column].hasPiece()) {
                coordinates.add(i);
                coordinates.add(column);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(column);

            movements.add(coordinates);
        }

        for (int i = column - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row][i].hasPiece() && squares[row][i].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[row][i].hasPiece()) {
                coordinates.add(row);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row);
            coordinates.add(i);

            movements.add(coordinates);
        }

        for (int i = column + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row][i].hasPiece() && squares[row][i].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[row][i].hasPiece()) {
                coordinates.add(row);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row);
            coordinates.add(i);

            movements.add(coordinates);
        }

        return movements;
    }
}
