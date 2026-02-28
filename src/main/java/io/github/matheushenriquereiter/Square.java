package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    private int xPosition;
    private int yPosition;
    private Piece piece;

    public Square(int xPosition, int yPosition, Piece piece) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.piece = piece;
        setBackground(getSquareColor());

        setPreferredSize(new Dimension(100, 100));
        setBorderPainted(false);
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        Image scaledImage = new ImageIcon(piece.getIconPath()).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setIcon(icon);
    }

    public void removePiece() {
        this.piece = null;
        setIcon(null);
    }

    public Piece getPiece() {
        return piece;
    }

    public Color getSquareColor() {
        if ((xPosition + yPosition) % 2 == 0) {
            return new Color(0xE8EDF9);
        }

        return new Color(0xB7C0D8);
    }

    public boolean hasPiece() {
        return piece != null;
    }
}
