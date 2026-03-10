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
    public List<Position> getLegalMovements(int row, int column, Square[][] squares) {
        List<Position> legalMovements = new ArrayList<>();

        addOrthogonalMovements(row, column, legalMovements, squares, 1);
        addDiagonalMovements(row, column, legalMovements, squares, 1);

        return legalMovements;
    }

    public boolean isInCheck(int kingRow, int kingColumn, Square[][] squares) {
        PieceColor rivalColor = getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece().getColor() == rivalColor) {
                    List<Position> rivalLegalMovements = square.getPiece().getLegalMovements(square.getRow(), square.getColumn(), squares);

                    for (Position rivalLegalMovement : rivalLegalMovements) {

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
