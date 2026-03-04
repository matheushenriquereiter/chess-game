package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Knight(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

        for (int i = x - 1; i > x - 3; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (!Chessboard.positionExists(i, y)) {
                continue;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 2, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 2);
            coordinates.add(y - 1);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 2, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 2);
            coordinates.add(y + 1);

            movements.add(coordinates);
        }

        for (int i = x + 1; i < x + 3; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (!Chessboard.positionExists(i, y)) {
                continue;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 2, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 2);
            coordinates.add(y - 1);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 2, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 2);
            coordinates.add(y + 1);

            movements.add(coordinates);
        }

        for (int i = y + 1; i < y + 3; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (!Chessboard.positionExists(x, i)) {
                continue;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 1, y + 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y + 2);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 1, y + 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y + 2);

            movements.add(coordinates);
        }

        for (int i = y - 1; i > y - 3; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (!Chessboard.positionExists(x, i)) {
                continue;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 1, y - 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y - 2);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 1, y - 2)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y - 2);

            movements.add(coordinates);
        }

        return movements;
    }
}
