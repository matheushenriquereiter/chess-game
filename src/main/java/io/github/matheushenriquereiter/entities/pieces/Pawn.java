package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Chessboard;
import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
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
    public List<Position> getPossibleMovements(int row, int column, Square[][] squares) {
        List<Position> possibleMovements = new ArrayList<>();

        possibleMovements.addAll(getForwardMovements(row, column, squares));
        possibleMovements.addAll(getDiagonalCaptureMovement(row, column, squares));

        return possibleMovements;
    }

    private List<Position> getForwardMovements(int row, int column, Square[][] squares) {
        List<Position> forwardMovements = new ArrayList<>();

        for (int i = row + movementDirection; i != row + (isFirstMove ? 3 : 2) * movementDirection; i += movementDirection) {
            if (!Chessboard.isWithinBounds(i, column) || squares[i][column].hasPiece()) {
                break;
            }

            forwardMovements.add(new Position(i, column));
        }

        return forwardMovements;
    }

    private List<Position> getDiagonalCaptureMovement(int row, int column, Square[][] squares) {
        List<Position> diagonalMovements = new ArrayList<>();

        if (Chessboard.isWithinBounds(row + movementDirection, column - 1)) {
            Square leftDiagonalSquare = squares[row + movementDirection][column - 1];

            if (leftDiagonalSquare.hasPiece() && leftDiagonalSquare.getPiece().getColor() != getColor()) {
                diagonalMovements.add(new Position(row + movementDirection, column - 1));
            }
        }

        if (Chessboard.isWithinBounds(row + movementDirection, column + 1)) {
            Square leftDiagonalSquare = squares[row + movementDirection][column + 1];

            if (leftDiagonalSquare.hasPiece() && leftDiagonalSquare.getPiece().getColor() != getColor()) {
                diagonalMovements.add(new Position(row + movementDirection, column + 1));
            }
        }

        return diagonalMovements;
    }

    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }
}
