package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Chessboard extends JFrame {
    private static Square[][] boardSquares;
    private PieceColor playerTurn = PieceColor.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        boardSquares = createSquares();

        Piece[][] whitePieces = generatePieces(PieceColor.BLACK);
        Piece[][] blackPieces = generatePieces(PieceColor.WHITE);
        Rook rook = new Rook(PieceColor.WHITE);

        boardSquares[4][4].setPiece(rook);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j].setPiece(whitePieces[i][j]);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 7; j >= 0; j--) {
                boardSquares[boardSquares[0].length - i - 1][j].setPiece(blackPieces[i][j]);
            }
        }

        setVisible(true);
        pack();
    }

    public static boolean positionExists(int x, int y) {
        return x >= 0 && x < boardSquares[0].length && y >= 0 && y < boardSquares[0].length;
    }

    public static Square[][] getBoardSquares() {
        return boardSquares;
    }

    public Square[][] createSquares() {
        Square[][] squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j);

                square.addActionListener(_ -> {
                    if (square.hasPiece() && playerTurn == square.getPiece().getColor()) {
                        selectedSquare = square;
                        selectedPiece = square.getPiece();
                        return;
                    }

                    if (selectedPiece != null) {
                        if (isMovementPossible(square, selectedSquare, selectedPiece)) {
                            if (selectedPiece instanceof Pawn pawn) {
                                pawn.setIsFirstMove(false);
                            }

                            if (square.hasPiece()) {
                                square.removePiece();
                            }

                            square.setPiece(selectedPiece);
                            selectedSquare.removePiece();
                            playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
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
        List<List<Integer>> movements = selectedPiece.getLegalMovements(selectedSquare.getRow(), selectedSquare.getColumn(), boardSquares);

        for (List<Integer> movement : movements) {
            if (square.getRow() == movement.get(0) && square.getColumn() == movement.get(1)) {
                return true;
            }
        }

        return false;
    }

    public Piece[][] generatePieces(PieceColor color) {
        return new Piece[][]{{new Rook(color), new Knight(color), new Bishop(color), new Queen(color), new King(color), new Bishop(color), new Knight(color), new Rook(color)}, {new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color), new Pawn(color)}};
    }
}
