package io.github.matheushenriquereiter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Chessboard extends JFrame {
    private static Square[][] boardSquares;
    private final Colors playerTurn = Colors.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        boardSquares = createSquares();

        Pawn pawn = new Pawn(Colors.WHITE);
        Pawn pawn1 = new Pawn(Colors.WHITE);
        Bishop bishop = new Bishop(Colors.WHITE);
        Queen queen = new Queen(Colors.WHITE);
        King king = new King(Colors.WHITE);
        Knight knight = new Knight(Colors.WHITE);

        Rook rook = new Rook(Colors.BLACK);

        boardSquares[6][1].setPiece(pawn);
        boardSquares[6][0].setPiece(pawn);
        boardSquares[0][0].setPiece(rook);
        boardSquares[4][4].setPiece(bishop);
        boardSquares[3][3].setPiece(queen);
        boardSquares[2][2].setPiece(king);

        boardSquares[6][6].setPiece(knight);

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
//                            playerTurn = playerTurn == Colors.WHITE ? Colors.BLACK : Colors.WHITE;
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

        System.out.println(movements);

        for (List<Integer> movement : movements) {
            if (square.getXPosition() == movement.get(0) && square.getYPosition() == movement.get(1)) {
                return true;
            }
        }

        return false;
    }
}
