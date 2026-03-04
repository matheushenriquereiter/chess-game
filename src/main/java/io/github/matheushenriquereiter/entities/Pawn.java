package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final List<List<Integer>> legalMovements = new ArrayList<>();
    private final int movementDirection;
    private boolean isFirstMove = true;

    public Pawn(PieceColor color) {
        super(color);
        movementDirection = getColor() == PieceColor.WHITE ? -1 : 1;
    }

    @Override
    public List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares) {
        legalMovements.clear();

        addVerticalMovements(row, column);
        addDiagonalCaptureMovement(row, column);

        return legalMovements;
    }

    private void addVerticalMovements(int row, int column) {
        Square[][] squares = Chessboard.getBoardSquares();

        for (int i = row + movementDirection; i != row + (isFirstMove ? 3 : 2) * movementDirection; i += movementDirection) {
            if (squares[i][column].hasPiece()) {
                break;
            }

            addLegalMovement(i, column);
        }
    }

    private void addDiagonalCaptureMovement(int row, int column) {
        Square[][] squares = Chessboard.getBoardSquares();

        if (column != 0) {
            Square leftDiagonalSquare = squares[row + movementDirection][column - 1];

            if (leftDiagonalSquare.hasPiece() && leftDiagonalSquare.getPiece().getColor() != getColor()) {
                addLegalMovement(row + movementDirection, column - 1);
            }
        }

        if (column != 7) {
            Square rightDiagonalSquare = squares[row + movementDirection][column + 1];

            if (rightDiagonalSquare.hasPiece() && rightDiagonalSquare.getPiece().getColor() != getColor()) {
                addLegalMovement(row + movementDirection, column + 1);
            }
        }
    }

    public void addLegalMovement(int row, int column) {
        List<Integer> movement = new ArrayList<>();

        movement.add(row);
        movement.add(column);

        legalMovements.add(movement);
    }

    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }
}
