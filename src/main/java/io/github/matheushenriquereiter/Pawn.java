package io.github.matheushenriquereiter;

public class Pawn extends Piece {
    public Pawn(Colors color) {
        super(color);
    }

    public int[][] getMovements(int x, int y) {
        return new int[][]{{x - 1, y}, {x - 2, y}};
    }
}
