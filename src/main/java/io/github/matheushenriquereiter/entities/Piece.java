package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import java.util.List;

public abstract class Piece {
    private final PieceColor color;

    protected Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return "src/main/resources/piece-icons/%s-%s.png".formatted(pieceColor, pieceName);
    }

    public abstract List<List<Integer>> getLegalMovements(int row, int column, Square[][] squares);
}
