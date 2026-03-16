package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMovements(int row, int column, Square[][] squares) {
        List<Position> possibleMovements = new ArrayList<>();

        possibleMovements.addAll(getOrthogonalMovements(row, column, squares, null));
        possibleMovements.addAll(getDiagonalMovements(row, column, squares, null));

        return possibleMovements;
    }
}
