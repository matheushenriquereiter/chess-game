package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();
    private boolean firstMove = true;

    public Queen(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

        for (int i = x - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece()) {
                coordinates.add(i);
                coordinates.add(y);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        for (int i = x + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece()) {
                coordinates.add(i);
                coordinates.add(y);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        for (int i = y - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x][i].hasPiece()) {
                coordinates.add(x);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        for (int i = y + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x][i].hasPiece()) {
                coordinates.add(x);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        int counterX = 1;
        int counterY = 1;
        while (Chessboard.positionExists(x + counterX, y + counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x + counterX][y + counterY].hasPiece()) {
                coordinates.add(x + counterX);
                coordinates.add(y + counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x + counterX);
            coordinates.add(y + counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(x - counterX, y - counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x - counterX][y - counterY].hasPiece()) {
                coordinates.add(x - counterX);
                coordinates.add(y - counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x - counterX);
            coordinates.add(y - counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(x + counterX, y - counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x + counterX][y - counterY].hasPiece()) {
                coordinates.add(x + counterX);
                coordinates.add(y - counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x + counterX);
            coordinates.add(y - counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        counterX = 1;
        counterY = 1;
        while (Chessboard.positionExists(x - counterX, y + counterY)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x - counterX][y + counterY].hasPiece()) {
                coordinates.add(x - counterX);
                coordinates.add(y + counterY);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x - counterX);
            coordinates.add(y + counterY);

            movements.add(coordinates);

            counterX++;
            counterY++;
        }

        return movements;
    }
}
