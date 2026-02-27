package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JFrame {
    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));

        Square[][] squares = createSquares();

        squares[6][1].setPiece(new Pawn(Colors.WHITE));
        squares[1][1].setPiece(new Pawn(Colors.BLACK));

        setVisible(true);
        pack();
    }

    public Square[][] createSquares() {
        Square[][] squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color = (i + j) % 2 == 0 ? new Color(0xE8EDF9) : new Color(0xB7C0D8);

                Square square = new Square(color);
                squares[i][j] = square;
                add(square);
            }
        }

        return squares;
    }
}
