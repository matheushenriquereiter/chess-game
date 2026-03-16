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
        PieceColor rivalPlayerColor = getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece().getColor() == rivalPlayerColor) {
                    List<Position> rivalPossibleMovements = square.getPiece().getPossibleMovements(square.getRow(), square.getColumn(), squares);

                    for (Position rivalLegalMovement : rivalPossibleMovements) {

                        if ((rivalLegalMovement.row() == kingRow) && (rivalLegalMovement.column() == kingColumn)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
