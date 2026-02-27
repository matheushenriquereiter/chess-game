package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JFrame {
    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));

        createSquares();

        setVisible(true);
        pack();
    }

    public void createSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;

                Square square = new Square(color);
                add(square);
            }
        }
    }
}
