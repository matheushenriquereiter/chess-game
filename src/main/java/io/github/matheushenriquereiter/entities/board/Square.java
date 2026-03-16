package io.github.matheushenriquereiter.entities.board;

import io.github.matheushenriquereiter.entities.pieces.Piece;
import io.github.matheushenriquereiter.enums.SquareColor;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    private final int row;
    private final int column;
    private Piece piece = null;

    public Square(int row, int column) {
        this.row = row;
        this.column = column;

        setPreferredSize(new Dimension(100, 100));
        setBorderPainted(false);
        setBackground(getSquareColor());
        setFocusPainted(false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;

        if (piece == null) {
            setIcon(null);
            return;
        }

        Image scaledImage = new ImageIcon(piece.getIconPath()).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
    }

    public void removePiece() {
        this.piece = null;
        setIcon(null);
    }

    public Color getSquareColor() {
        if ((row + column) % 2 == 0) {
            return SquareColor.LIGHT.color;
        }

        return SquareColor.BLACK.color;
    }

    public void paintLegalSquare() {
        if (!this.hasPiece()) {
            Image circleImage = new ImageIcon("src/main/resources/piece-icons/circle.png").getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(circleImage));
        }
    }

    public boolean hasPiece() {
        return piece != null;
    }
}
