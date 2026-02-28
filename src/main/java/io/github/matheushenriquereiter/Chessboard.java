package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JFrame {
    private Square[][] boardSquares;
    private Square selectedSquare;
    private Piece selectedPiece;
    private int[][] possibleMovements;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));

        this.boardSquares = createSquares();

        Pawn pawn = new Pawn(Colors.WHITE);

        boardSquares[6][1].setPiece(pawn);

        setVisible(true);
        pack();
    }

    public Square[][] createSquares() {
        Square[][] squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j, null);

                square.addActionListener(_ -> {
                    if (square.hasPiece()) {
                        selectedSquare = square;
                        selectedPiece = square.getPiece();
                    }

                    if (!square.hasPiece()) {
                        if (selectedPiece != null) {
                            if (isMovementPossible(square, selectedSquare, selectedPiece)) {
                                square.setPiece(selectedPiece);
                                selectedSquare.removePiece();
                            }

                            selectedSquare = null;
                            selectedPiece = null;
                        }
                    }
                });

                squares[i][j] = square;
                add(square);
            }
        }

        return squares;
    }

    public boolean isMovementPossible(Square square, Square selectedSquare, Piece selectedPiece) {
        int[][] movements = selectedPiece.getMovements(selectedSquare.getXPosition(), selectedSquare.getYPosition());

        for (int[] movement : movements) {
            if (square.getXPosition() == movement[0] && square.getYPosition() == movement[1]) {
                return true;
            }
        }

        return false;
    }
}
