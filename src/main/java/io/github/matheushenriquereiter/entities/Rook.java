package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addDirectionalMovements(row, column, -1, 0, legalMovements, squares);
        addDirectionalMovements(row, column, 1, 0, legalMovements, squares);
        addDirectionalMovements(row, column, 0, -1, legalMovements, squares);
        addDirectionalMovements(row, column, 0, 1, legalMovements, squares);

        return legalMovements;
    }
}
