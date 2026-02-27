package io.github.matheushenriquereiter;

import java.awt.*;

public abstract class Piece {
    private Colors color;

    public Piece(Colors color) {
        this.color = color;
    }

    public String getIconPath() {
        String pieceName = getClass().getSimpleName().toLowerCase();
        String pieceColor = color.toString().toLowerCase();

        return "src/main/resources/%s-%s.png".formatted(pieceColor, pieceName);
    }
}
