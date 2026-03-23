package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public boolean isFirstMove = true;

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMovements(int row, int column, Square[][] squares) {
        return new ArrayList<>(getOrthogonalMovements(row, column, squares, null));
    }
}
