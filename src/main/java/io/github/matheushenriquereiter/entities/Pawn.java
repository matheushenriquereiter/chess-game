package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final int movementDirection = getColor() == PieceColor.WHITE ? -1 : 1;
    private boolean isFirstMove = true;

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        List<List<Integer>> legalMovements = new ArrayList<>();

        addVerticalMovements(row, column, legalMovements, squares);
        addDiagonalCaptureMovement(row, column, legalMovements, squares);

        return legalMovements;
    }

    private void addVerticalMovements(int row, int column, List<List<Integer>> legalMovements, Square[][] squares) {
        for (int i = row + movementDirection; i != row + (isFirstMove ? 3 : 2) * movementDirection; i += movementDirection) {
            if (squares[i][column].hasPiece()) {
                break;
            }

            addLegalMovement(i, column, legalMovements);
        }
    }

    private void addDiagonalCaptureMovement(int row, int column, List<List<Integer>> legalMovements, Square[][] squares) {
        if (column != 0) {
            Square leftDiagonalSquare = squares[row + movementDirection][column - 1];

            if (leftDiagonalSquare.hasPiece() && leftDiagonalSquare.getPiece().getColor() != getColor()) {
                addLegalMovement(row + movementDirection, column - 1, legalMovements);
            }
        }

        if (column != 7) {
            Square rightDiagonalSquare = squares[row + movementDirection][column + 1];

            if (rightDiagonalSquare.hasPiece() && rightDiagonalSquare.getPiece().getColor() != getColor()) {
                addLegalMovement(row + movementDirection, column + 1, legalMovements);
            }
        }
    }

    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }
}
