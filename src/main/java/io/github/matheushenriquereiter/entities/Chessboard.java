package io.github.matheushenriquereiter.entities;

import io.github.matheushenriquereiter.enums.PieceColor;
import io.github.matheushenriquereiter.enums.SquareColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chessboard extends JFrame {
    private static final Square[][] squares = new Square[8][8];
    private PieceColor playerTurn = PieceColor.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        createSquares();

        Piece[][] whitePieces = generatePieces(PieceColor.BLACK);
        Piece[][] blackPieces = generatePieces(PieceColor.WHITE);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setPiece(whitePieces[i][j]);
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 7; j >= 0; j--) {
                squares[squares[0].length - i - 1][j].setPiece(blackPieces[i][j]);
            }
        }

        setVisible(true);
        pack();
    }

    public static boolean positionExists(int x, int y) {
        return x >= 0 && x < squares[0].length && y >= 0 && y < squares[0].length;
    }

    public static Square[][] getSquares() {
        return squares;
    }

    public void createSquares() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j);

                square.addActionListener(_ -> movePieceAction(square));

                squares[i][j] = square;
                add(square);
            }
        }
    }

    public boolean isMovementPossible(Square square, Square selectedSquare, Piece selectedPiece) {
        List<List<Integer>> movements = selectedPiece.getLegalMovements(selectedSquare.getRow(), selectedSquare.getColumn(), squares);

        System.out.println(movements);

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

    public void movePieceAction(Square square) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square s = squares[i][j];

                if (!s.hasPiece()) {
                    s.setIcon(null);
                }

                s.setBackground(s.getSquareColor());
            }
        }

        if (square.hasPiece() && playerTurn == square.getPiece().getColor()) {
            selectedSquare = square;
            selectedPiece = square.getPiece();

            List<List<Integer>> legalMovements = selectedPiece.getLegalMovements(selectedSquare.getRow(), selectedSquare.getColumn(), squares);
            List<Square> validSquares = new ArrayList<>();

            for (List<Integer> legalMovement : legalMovements) {
                int row = legalMovement.get(0);
                int col = legalMovement.get(1);

                validSquares.add(squares[row][col]);
            }

            for (Square s : validSquares) {
                if (!s.hasPiece()) {
                    Image scaledImage = new ImageIcon("src/main/resources/piece-icons/circle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                    s.setIcon(new ImageIcon(scaledImage));
                }
            }

            selectedSquare.setBackground(new Color(SquareColor.LIGHT_SELECTED.getHex()));

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
    }
}
