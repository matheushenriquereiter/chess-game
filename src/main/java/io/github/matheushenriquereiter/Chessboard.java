package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Chessboard extends JFrame {
    private static Square[][] boardSquares;
    private Colors playerTurn = Colors.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        boardSquares = createSquares();

        Piece[] blackPieces = generatePieces(Colors.BLACK);
        Piece[] whitePieces = generatePieces(Colors.WHITE);

        for (int i = 0; i < blackPieces.length; i++) {
            boardSquares[0][i].setPiece(blackPieces[i]);

            Pawn pawn = new Pawn(Colors.BLACK);
            boardSquares[1][i].setPiece(pawn);
        }

        for (int i = 7; i >= 0; i--) {
            boardSquares[7][i].setPiece(whitePieces[i]);
            Pawn pawn = new Pawn(Colors.WHITE);
            boardSquares[6][i].setPiece(pawn);
        }

        setVisible(true);
        pack();
    }

    public static boolean positionExists(int x, int y) {
        return x >= 0 && x < boardSquares[0].length && y >= 0 && y < boardSquares[0].length;
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

    public Piece[] generatePieces(Colors color) {
        return new Piece[]{new Rook(color), new Knight(color), new Bishop(color), new Queen(color), new King(color), new Bishop(color), new Knight(color), new Rook(color)};
    }
}
