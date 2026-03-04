package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addDirectionalMovements(row, column, -1, -1, legalMovements, squares);
        addDirectionalMovements(row, column, -1, 1, legalMovements, squares);
        addDirectionalMovements(row, column, 1, -1, legalMovements, squares);
        addDirectionalMovements(row, column, 1, 1, legalMovements, squares);

        return legalMovements;
    }
}
