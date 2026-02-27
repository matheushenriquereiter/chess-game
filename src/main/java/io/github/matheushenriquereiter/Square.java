package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    public Square(Color color) {
        setPreferredSize(new Dimension(100, 100));
        setBackground(color);
        setBorderPainted(false);
    }

    public void setPiece(Piece piece) {
        Image scaledImage = new ImageIcon(piece.getIconPath()).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        setIcon(icon);
    }
}
