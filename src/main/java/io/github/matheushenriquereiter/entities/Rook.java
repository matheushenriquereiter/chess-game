package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final List<List<Integer>> movements = new ArrayList<>();

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int x, int y, Square[][] squares) {
        movements.clear();

        for (int i = x - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece() && squares[i][y].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[i][y].hasPiece()) {
                coordinates.add(i);
                coordinates.add(y);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        for (int i = x + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[i][y].hasPiece() && squares[i][y].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[i][y].hasPiece()) {
                coordinates.add(i);
                coordinates.add(y);
                movements.add(coordinates);
                break;
            }

            coordinates.add(i);
            coordinates.add(y);

            movements.add(coordinates);
        }

        for (int i = y - 1; i > -1; i--) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x][i].hasPiece() && squares[x][i].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[x][i].hasPiece()) {
                coordinates.add(x);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        for (int i = y + 1; i < 8; i++) {
            List<Integer> coordinates = new ArrayList<>();

            if (squares[x][i].hasPiece() && squares[x][i].getPiece().getColor() == getColor()) {
                break;
            }

            if (squares[x][i].hasPiece()) {
                coordinates.add(x);
                coordinates.add(i);
                movements.add(coordinates);
                break;
            }

            coordinates.add(x);
            coordinates.add(i);

            movements.add(coordinates);
        }

        return movements;
    }
}
