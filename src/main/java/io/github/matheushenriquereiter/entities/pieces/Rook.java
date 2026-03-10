package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getLegalMovements(int row, int column, Square[][] squares) {
        List<Position> legalMovements = new ArrayList<>();

        addOrthogonalMovements(row, column, legalMovements, squares, null);

        return legalMovements;
    }
}
