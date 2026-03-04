package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        movements.clear();

        int counterX = 1;
        int counterY = 1;
        while (Chessboard.positionExists(row + counterX, column + counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row + counterX][column + counterY].hasPiece()) {
                coordinates.add(row + counterX);
                coordinates.add(column + counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row + counterX);
            coordinates.add(column + counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(row - counterX, column - counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row - counterX][column - counterY].hasPiece()) {
                coordinates.add(row - counterX);
                coordinates.add(column - counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row - counterX);
            coordinates.add(column - counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(row + counterX, column - counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row + counterX][column - counterY].hasPiece()) {
                coordinates.add(row + counterX);
                coordinates.add(column - counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row + counterX);
            coordinates.add(column - counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(row - counterX, column + counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[row - counterX][column + counterY].hasPiece()) {
                coordinates.add(row - counterX);
                coordinates.add(column + counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(row - counterX);
            coordinates.add(column + counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        return movements;
    }
}
