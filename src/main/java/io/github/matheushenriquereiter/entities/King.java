package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addOrthogonalMovements(row, column, legalMovements, squares, 1);
        addDiagonalMovements(row, column, legalMovements, squares, 1);

        return legalMovements;
    }
}
