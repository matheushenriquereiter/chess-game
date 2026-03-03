package io.github.matheushenriquereiter;

import java.util.List;

public abstract class Piece {
    private final Colors color;

    protected Piece(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return "src/main/resources/%s-%s.png".formatted(pieceColor, pieceName);
    }

    public abstract List<List<Integer>> getMovements(int x, int y, Square[][] squares);
}
