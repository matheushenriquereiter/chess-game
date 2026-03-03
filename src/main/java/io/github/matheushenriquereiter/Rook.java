package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Rook(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

        for (int i = x - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece()) {
                coordinates.add(x);
                coordinates.add(i);
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
                coordinates.add(x);
                coordinates.add(i);
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

        return movements;
    }
}
