package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMovements(int row, int column, Square[][] squares) {
        List<Position> possibleMovements = new ArrayList<>();

        possibleMovements.addAll(getOrthogonalMovements(row, column, squares, 1));
        possibleMovements.addAll(getDiagonalMovements(row, column, squares, 1));

        return possibleMovements;
    }

    public boolean isInCheck(int kingRow, int kingColumn, Square[][] squares) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (isKingUnderAttack(kingRow, kingColumn, square, squares)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isKingUnderAttack(int kingRow, int kingColumn, Square square, Square[][] squares) {
        if (square.hasPiece() && square.getPiece().getColor() == getOpponentColor()) {
            List<Position> opponentPossibleMovements = square.getPiece().getPossibleMovements(square.getRow(), square.getColumn(), squares);

            for (Position opponentLegalMovement : opponentPossibleMovements) {
                if (opponentLegalMovement.row() == kingRow && opponentLegalMovement.column() == kingColumn) {
                    return true;
                }
            }
        }

        return false;
    }
}
