package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Chessboard extends JFrame {
    private Square[][] boardSquares;
    private Square selectedSquare;
    private Piece selectedPiece;
    private Colors playerTurn = Colors.WHITE;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));

        this.boardSquares = createSquares();

        Pawn pawn = new Pawn(Colors.WHITE);
        Rook rook = new Rook(Colors.BLACK);

        boardSquares[6][1].setPiece(pawn);
        boardSquares[0][0].setPiece(rook);

        setVisible(true);
        pack();
    }

    public Square[][] createSquares() {
        Square[][] squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j, null);

                square.addActionListener(_ -> {
                    if (square.hasPiece() && playerTurn == square.getPiece().getColor()) {
                        selectedSquare = square;
                        selectedPiece = square.getPiece();
                        return;
                    }


                    if (selectedPiece != null) {
                        if (isMovementPossible(square, selectedSquare, selectedPiece)) {
                            if (square.hasPiece()) {
                                square.removePiece();
                            }

                            square.setPiece(selectedPiece);
                            selectedSquare.removePiece();
                            playerTurn = playerTurn == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
                        }

                        selectedSquare = null;
                        selectedPiece = null;
                    }
                });

                squares[i][j] = square;
                add(square);
            }
        }

        return squares;
    }

    public boolean isMovementPossible(Square square, Square selectedSquare, Piece selectedPiece) {
        List<List<Integer>> movements = selectedPiece.getMovements(selectedSquare.getXPosition(), selectedSquare.getYPosition(), boardSquares);

        for (List<Integer> movement : movements) {
            if (square.getXPosition() == movement.get(0) && square.getYPosition() == movement.get(1)) {
                return true;
            }
        }

        return false;
    }
}
