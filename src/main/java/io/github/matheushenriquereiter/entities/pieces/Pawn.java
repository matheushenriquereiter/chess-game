package io.github.matheushenriquereiter.entities.pieces;

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

        addForwardMovements(row, column, possibleMovements, squares);
        addDiagonalCaptureMovement(row, column, possibleMovements, squares);

        return possibleMovements;
    }

    private void addForwardMovements(int row, int column, List<Position> possibleMovements, Square[][] squares) {
        for (int i = row + movementDirection; i != row + (isFirstMove ? 3 : 2) * movementDirection; i += movementDirection) {
            if (squares[i][column].hasPiece()) {
                break;
            }

            possibleMovements.add(new Position(i, column));
        }
    }

    private void addDiagonalCaptureMovement(int row, int column, List<Position> possibleMovements, Square[][] squares) {
        if (column != 0) {
            Square leftDiagonalSquare = squares[row + movementDirection][column - 1];

            if (leftDiagonalSquare.hasPiece() && leftDiagonalSquare.getPiece().getColor() != getColor()) {
                possibleMovements.add(new Position(row + movementDirection, column - 1));
            }
        }

        if (column != 7) {
            Square rightDiagonalSquare = squares[row + movementDirection][column + 1];

            if (rightDiagonalSquare.hasPiece() && rightDiagonalSquare.getPiece().getColor() != getColor()) {
                possibleMovements.add(new Position(row + movementDirection, column + 1));
            }
        }
    }

    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }
}
