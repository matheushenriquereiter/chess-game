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
    private Piece[][] whitePieces = createDefaultPieces(PieceColor.BLACK);
    private Piece[][] blackPieces = createDefaultPieces(PieceColor.WHITE);

    public Chessboard() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 8));
        createSquares();


        positionChessPieces(whitePieces, blackPieces);

        setVisible(true);
        pack();
    }

    public static boolean isWithinBounds(int row, int column) {
        return row >= 0 && row <= 7 && column >= 0 && column <= 7;
    }

    public void positionChessPieces(Piece[][] whitePieces, Piece[][] blackPieces) {
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

    public void createSquares() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = new Square(i, j);

                square.addActionListener(_ -> onSquareClicked(square));

                squares[i][j] = square;
                add(square);
            }
        }
    }

    public void onSquareClicked(Square clickedSquare) {
        clearSelectedSquares();

        if (clickedSquare.hasPiece() && playerTurn == clickedSquare.getPiece().getColor()) {
            selectedSquare = clickedSquare;
            selectedPiece = clickedSquare.getPiece();

            paintLegalMovementsSquares(selectedSquare, selectedPiece);
            selectedSquare.setBackground(SquareColor.LIGHT_SELECTED.color);

            return;
        }

        if (selectedPiece != null && isMovementLegal(clickedSquare, selectedSquare, selectedPiece)) {
            if (selectedPiece instanceof Pawn pawn) {
                pawn.setIsFirstMove(false);
            }

//            if (selectedPiece instanceof Pawn && clickedSquare.getRow() == 0 && playerTurn == PieceColor.WHITE) {
//                System.out.println("dsads");
//                clickedSquare.setPiece(new Queen(PieceColor.WHITE));
//            }
//
//            if (selectedPiece instanceof Pawn && clickedSquare.getRow() == 7 && playerTurn == PieceColor.BLACK) {
//                clickedSquare.setPiece(new Queen(PieceColor.BLACK));
//            }

            if (clickedSquare.hasPiece()) {
                clickedSquare.removePiece();
            }

            clickedSquare.setPiece(selectedPiece);
            selectedSquare.removePiece();
            changePlayerTurn();
            selectedSquare = null;
            selectedPiece = null;

            if (isCheckmate()) {
                JOptionPane.showMessageDialog(this, "Winning team: %s Pieces".formatted(playerTurn == PieceColor.WHITE ? "Black" : "White"), "Game Over", JOptionPane.PLAIN_MESSAGE);

                for (int i = 0; i <= 7; i++) {
                    for (int j = 0; j <= 7; j++) {
                        squares[i][j].removePiece();
                    }
                }

                positionChessPieces(whitePieces, blackPieces);
            }
        }
    }

    public void changePlayerTurn() {
        playerTurn = playerTurn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public void clearSelectedSquares() {
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

    public void paintLegalMovementsSquares(Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = getLegalMovements(selectedSquare, selectedPiece);
        List<Square> squaresToPaint = new ArrayList<>();

        for (Position legalMovement : legalMovements) {
            int row = legalMovement.row();
            int col = legalMovement.column();

            squaresToPaint.add(squares[row][col]);
        }

        for (Square squareToPaint : squaresToPaint) {
            squareToPaint.paintLegalSquare();
        }
    }

    public boolean isMovementLegal(Square square, Square selectedSquare, Piece selectedPiece) {
        List<Position> legalMovements = getLegalMovements(selectedSquare, selectedPiece);

        for (Position legalMovement : legalMovements) {
            if (square.getRow() == legalMovement.row() && square.getColumn() == legalMovement.column()) {
                return true;
            }
        }

        return false;
    }

    public List<Position> getLegalMovements(Square selectedSquare, Piece selectedPiece) {
        List<Position> possibleMovements = selectedPiece.getPossibleMovements(selectedSquare.getRow(), selectedSquare.getColumn(), squares);
        List<Position> legalMovements = new ArrayList<>();

        selectedSquare.removePiece();

        for (Position possibleMovement : possibleMovements) {
            Square possibleMovementSquare = squares[possibleMovement.row()][possibleMovement.column()];
            Piece squarePreviousPiece = possibleMovementSquare.getPiece();

            possibleMovementSquare.setPiece(selectedPiece);
            Square kingSquare = getCurrentTurnKingSquare();
            King currentTurnKing = (King) kingSquare.getPiece();

            if (currentTurnKing.isInCheck(kingSquare.getRow(), kingSquare.getColumn(), squares)) {
                possibleMovementSquare.setPiece(squarePreviousPiece);
                continue;
            }

            legalMovements.add(new Position(possibleMovement.row(), possibleMovement.column()));
            possibleMovementSquare.setPiece(squarePreviousPiece);
        }

        selectedSquare.setPiece(selectedPiece);

        return legalMovements;
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

    public boolean isCheckmate() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Square square = squares[i][j];

                if (square.hasPiece() && square.getPiece().getColor() == playerTurn) {
                    List<Position> legalMovements = getLegalMovements(square, square.getPiece());

                    if (!legalMovements.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
