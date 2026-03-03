package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Bishop(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

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
