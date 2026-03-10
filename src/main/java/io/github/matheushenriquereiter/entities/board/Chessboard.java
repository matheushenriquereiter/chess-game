package io.github.matheushenriquereiter.entities.board;

import io.github.matheushenriquereiter.entities.pieces.*;
import io.github.matheushenriquereiter.enums.PieceColor;
import io.github.matheushenriquereiter.enums.SquareColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chessboard extends JFrame {
    private final Square[][] squares = new Square[8][8];
    private PieceColor playerTurn = PieceColor.WHITE;
    private Square selectedSquare;
    private Piece selectedPiece;

    public Chessboard() {
        setLocationRelativeTo(null); //nu nu nu
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        createSquares();

        Piece[][] whitePieces = createDefaultPieces(PieceColor.BLACK);
        Piece[][] blackPieces = createDefaultPieces(PieceColor.WHITE);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= 7; j++) {
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

    public Piece[][] createDefaultPieces(PieceColor color) {
        return new Piece[][]{
                {
                        new Rook(color),
                        new Knight(color),
                        new Bishop(color),
                        new Queen(color),
                        new King(color),
                        new Bishop(color),
                        new Knight(color),
                        new Rook(color)
                },
                {
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color),
                        new Pawn(color)
                }
        };
    }

    public static boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public void createSquares() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = new Square(i, j);

                square.addActionListener(_ -> movePieceAction(square));

                squares[i][j] = square;
                add(square);
            }
        }
    }


    public void movePieceAction(Square square) {
        cleanSelectedSquares();

        if (square.hasPiece() && playerTurn == square.getPiece().getColor()) {
            selectedSquare = square;
            selectedPiece = square.getPiece();

            List<Position> legalMovements = getReallyLegalMovements(selectedSquare, selectedPiece);
            List<Square> validSquares = new ArrayList<>();

            for (Position legalMovement : legalMovements) {
                int row = legalMovement.row();
                int col = legalMovement.column();

                validSquares.add(squares[row][col]);
            }

            for (Square s : validSquares) {
                if (!s.hasPiece()) {
                    Image scaledImage = new ImageIcon("src/main/resources/piece-icons/circle.png").getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
                    s.setIcon(new ImageIcon(scaledImage));
                }
            }

            selectedSquare.setBackground(SquareColor.LIGHT_SELECTED.color);

            return;
        }

        if (selectedPiece != null) {
            if (isMovementLegal(square, selectedSquare, selectedPiece)) {
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

    private void cleanSelectedSquares() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (!square.hasPiece()) {
                    square.setIcon(null);
                }

                square.setBackground(square.getSquareColor());
            }
        }
    }

    public boolean isMovementLegal(Square square, Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = getReallyLegalMovements(selectedSquare, selectedPiece);

        for (Position legalMovement : legalMovements) {
            if (square.getRow() == legalMovement.row() && square.getColumn() == legalMovement.column()) {
                return true;
            }
        }

        return false;
    }

    public List<Position> getReallyLegalMovements(Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = selectedPiece.getLegalMovements(selectedSquare.getRow(), selectedSquare.getColumn(), squares);

        List<Position> reallyLegalMovements = new ArrayList<>();

        squares[selectedSquare.getRow()][selectedSquare.getColumn()].removePiece();

        for (Position legalMovement : legalMovements) {
            int row = legalMovement.row();
            int column = legalMovement.column();
            Piece oldPiece = squares[row][column].getPiece();

            squares[row][column].setPiece(selectedPiece);
            Square kingSquare = getCurrentTurnKingSquare();

            if (((King) kingSquare.getPiece()).isInCheck(kingSquare.getRow(), kingSquare.getColumn(), squares)) {
                squares[row][column].setPiece(oldPiece);
                continue;
            }
            reallyLegalMovements.add(new Position(row, column));
            squares[row][column].setPiece(oldPiece);
        }

        selectedSquare.setPiece(selectedPiece);

        return reallyLegalMovements;
    }

    public Square getCurrentTurnKingSquare() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece() instanceof King && square.getPiece().getColor() == playerTurn) {
                    return square;
                }
            }
        }

        return null;
    }
}
