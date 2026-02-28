package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Pawn(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        for (int i = x - 1; i > x - 3; i--) {
            List<Integer> coordinate = new ArrayList<>();

            if (squares[i][y].hasPiece()) {
                break;
            }

            coordinate.add(i);
            coordinate.add(y);

            movements.add(coordinate);
        }

        return movements;
    }
}
