package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int x, int y, Square[][] squares) {
        movements.clear();

        if (y != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x);
            coordinates.add(y - 1);

            movements.add(coordinates);
        }

        if (y != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x);
            coordinates.add(y + 1);

            movements.add(coordinates);
        }

        if (x != 0) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (x != 7) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y);

            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 1, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y + 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 1, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y - 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x + 1, y - 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x + 1);
            coordinates.add(y - 1);
            movements.add(coordinates);
        }

        if (Chessboard.positionExists(x - 1, y + 1)) {
            List<Integer> coordinates = new ArrayList<>();

            coordinates.add(x - 1);
            coordinates.add(y + 1);

            movements.add(coordinates);
        }

        return movements;
    }

    public boolean inCheck() {
        Square[][] boardSquares = Chessboard.getBoardSquares();

        for (Square[] rows : boardSquares) {
            for (Square square : rows) {
                if (square.hasPiece() && square.getPiece().getColor() == PieceColor.WHITE) {
                    List<List<Integer>> rivalPossibleMovements = square.getPiece().getLegalMovements(square.getXPosition(), square.getYPosition(), boardSquares);
                }
            }
        }

        return false;
    }
}
