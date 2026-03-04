package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();
    private boolean firstMove = true;

    public King(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

        if (y != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x);
            coordinates.add(y - 1);

            movements.add(coordinates);
        }

        if (y != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x);
            coordinates.add(y + 1);

            movements.add(coordinates);
        }

        if (x != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (x != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 1, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x + 1][y + 1].hasPiece()) {
                coordinates.add(x + 1);
                coordinates.add(y + 1);
                movements.add(coordinates);
            } else {
                coordinates.add(x + 1);
                coordinates.add(y + 1);

                movements.add(coordinates);
            }
        }

        if (Chessboard.positionExists(x - 1, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x - 1][y - 1].hasPiece()) {
                coordinates.add(x - 1);
                coordinates.add(y - 1);
                movements.add(coordinates);
            } else {
                coordinates.add(x - 1);
                coordinates.add(y - 1);

                movements.add(coordinates);
            }
        }

        if (Chessboard.positionExists(x + 1, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x + 1][y - 1].hasPiece()) {
                coordinates.add(x + 1);
                coordinates.add(y - 1);
                movements.add(coordinates);
            } else {
                coordinates.add(x + 1);
                coordinates.add(y - 1);

                movements.add(coordinates);
            }
        }

        if (Chessboard.positionExists(x - 1, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x - 1][y + 1].hasPiece()) {
                coordinates.add(x - 1);
                coordinates.add(y + 1);
                movements.add(coordinates);
            } else {
                coordinates.add(x - 1);
                coordinates.add(y + 1);

                movements.add(coordinates);
            }
        }

        return movements;
    }
}
