package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMovements(int row, int column, Square[][] squares) {
        List<Position> possibleMovements = new ArrayList<>();

        possibleMovements.addAll(getJumpMovement(row, column, -2, -1, squares));
        possibleMovements.addAll(getJumpMovement(row, column, -2, 1, squares));

        possibleMovements.addAll(getJumpMovement(row, column, -1, 2, squares));
        possibleMovements.addAll(getJumpMovement(row, column, 1, 2, squares));

        possibleMovements.addAll(getJumpMovement(row, column, 2, 1, squares));
        possibleMovements.addAll(getJumpMovement(row, column, 2, -1, squares));

        possibleMovements.addAll(getJumpMovement(row, column, 1, -2, squares));
        possibleMovements.addAll(getJumpMovement(row, column, -1, -2, squares));

        return possibleMovements;
    }
}
