package io.github.matheushenriquereiter;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();
    private boolean firstMove = true;

    public Pawn(Colors color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getMovements(int x, int y, Square[][] squares) {
        movements.clear();

        int squaresMove = firstMove ? 3 : 2;

        for (int i = x - 1; i > x - squaresMove; i--) {
            firstMove = false;

            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece()) {
                break;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        return movements;
    }
}
