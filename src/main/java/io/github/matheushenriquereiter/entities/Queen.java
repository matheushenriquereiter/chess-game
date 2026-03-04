package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        movements.clear();

        for (int i = row - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

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
