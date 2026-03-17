package io.github.matheushenriquereiter.entities.pieces;

import io.github.matheushenriquereiter.entities.board.Chessboard;
import io.github.matheushenriquereiter.entities.board.Position;
import io.github.matheushenriquereiter.entities.board.Square;
import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private static final String ICON_PATH_TEMPLATE = "src/main/resources/piece-icons/%s-%s.png";
    private final PieceColor color;

    protected Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract List<Position> getPossibleMovements(int row, int column, Square[][] squares);

    public PieceColor getOpponentColor() {
        return getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return ICON_PATH_TEMPLATE.formatted(pieceColor, pieceName);
    }

    protected List<Position> getDirectionalMovements(int row, int column, int rowDelta, int columnDelta, Square[][] squares, Integer movementLimit) {
        List<Position> directionalMovements = new ArrayList<>();

        int r = row + rowDelta;
        int c = column + columnDelta;
        int counter = 0;

        while (Chessboard.isWithinBounds(r, c) && (movementLimit == null || counter < movementLimit)) {
            Square square = squares[r][c];

            if (square.hasPiece()) {
                if (square.getPiece().getColor() == getOpponentColor()) {
                    directionalMovements.add(new Position(r, c));
                }

                break;
            }

            directionalMovements.add(new Position(r, c));

            r += rowDelta;
            c += columnDelta;
            counter++;
        }

        return directionalMovements;
    }

    protected List<Position> getOrthogonalMovements(int row, int column, Square[][] squares, Integer limiter) {
        List<Position> orthogonalMovements = new ArrayList<>();

        orthogonalMovements.addAll(getDirectionalMovements(row, column, -1, 0, squares, limiter));
        orthogonalMovements.addAll(getDirectionalMovements(row, column, 1, 0, squares, limiter));
        orthogonalMovements.addAll(getDirectionalMovements(row, column, 0, -1, squares, limiter));
        orthogonalMovements.addAll(getDirectionalMovements(row, column, 0, 1, squares, limiter));

        return orthogonalMovements;
    }

    protected List<Position> getDiagonalMovements(int row, int column, Square[][] squares, Integer limiter) {
        List<Position> diagonalMovements = new ArrayList<>();

        diagonalMovements.addAll(getDirectionalMovements(row, column, -1, -1, squares, limiter));
        diagonalMovements.addAll(getDirectionalMovements(row, column, -1, 1, squares, limiter));
        diagonalMovements.addAll(getDirectionalMovements(row, column, 1, -1, squares, limiter));
        diagonalMovements.addAll(getDirectionalMovements(row, column, 1, 1, squares, limiter));

        return diagonalMovements;
    }

    protected List<Position> getJumpMovement(int row, int column, int rowDelta, int columnDelta, Square[][] squares) {
        List<Position> jumpMovement = new ArrayList<>();

        if (Chessboard.isWithinBounds(row + rowDelta, column + columnDelta)) {
            Square square = squares[row + rowDelta][column + columnDelta];

            if (!square.hasPiece() || square.hasPiece() && square.getPiece().getColor() != getColor()) {
                jumpMovement.add(new Position(row + rowDelta, column + columnDelta));
            }
        }

        return jumpMovement;
    }
}
