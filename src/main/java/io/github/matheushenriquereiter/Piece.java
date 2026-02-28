package io.github.matheushenriquereiter;

public abstract class Piece {
    private final Colors color;

    protected Piece(Colors color) {
        this.color = color;
    }

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return "src/main/resources/%s-%s.png".formatted(pieceColor, pieceName);
    }

    public abstract int[][] getMovements(int x, int y);
}
